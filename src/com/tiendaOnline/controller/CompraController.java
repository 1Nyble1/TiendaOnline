package com.tiendaOnline.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.entity.Venta;
import com.tiendaOnline.service.CompraService;
import com.tiendaOnline.service.ProductoService;
import com.tiendaOnline.service.ClienteService;
import com.tiendaOnline.service.VentaService;

@Controller
@RequestMapping(value = "/compras")
public class CompraController {
	
	private Date fechaCompra;
	
	@Autowired
	VentaService ventaService;
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	CompraService compraService;
	
	@GetMapping("/detallesCompra/{idCompra}")
	public ModelAndView perfilCompra(HttpServletRequest request, @PathVariable("idCompra") long idCompra) {
		HttpSession httpSession = request.getSession();
		long id = (long) httpSession.getAttribute("idSession");
		Cliente cliente = clienteService.obtenerCliente(id);
		ModelAndView modelView = new ModelAndView();
		Compra compra = compraService.obtenerCompraPorId(idCompra);
		List<Venta> ventas = new ArrayList<Venta>();
		Set<Producto> productos = new HashSet<>();
		productos = compra.getProductos();
		for (Producto producto : productos) {
			ventas.add(ventaService.obtenerVenta(cliente, producto, compra));
		}
		modelView.addObject("ventas", ventas);
		modelView.addObject("compra", compra);
		modelView.setViewName("detallescompra");
		return modelView;
	}

	@RequestMapping("/crear_Compra")
	public void handlecrear(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession();
		long id = (long) httpSession.getAttribute("idSession");
		Cliente cliente = clienteService.obtenerCliente(id);
		Date fecha = new Date();
		Calendar calendar = Calendar.getInstance();
		boolean conStock = true;
		fecha = calendar.getTime();
		List<Producto> productos = (List<Producto>) httpSession.getAttribute("lProductoSession");
		if (!productos.isEmpty()) {
			int unidades = 0;
			float precioFinal = 0f;
			Set<Producto> productosDos = new HashSet<>();
			for (Producto producto : productos) {
				Producto procductoTres = productoService.obtenerProducto(producto.getIdProducto());
				if (procductoTres.getStock() < producto.getStock()) {
					conStock = false;
				}
				unidades = producto.getStock();
				float precio = producto.getPrecio();
				precioFinal += precio * unidades;
				productosDos.add(producto);
			}
			if (conStock) {
				Compra compra = compraService.crearCompra(cliente, productosDos, fecha, precioFinal);

				for (Producto product : productos) {
					unidades = product.getStock();
					long idP = product.getIdProducto();
					Producto producto = productoService.obtenerProducto(idP);
					int stock = producto.getStock();
					int stockResul = stock - unidades;
					producto.setStock(stockResul);
					productoService.editarProducto(producto);
					Venta venta = ventaService.crearVenta(cliente, producto, compra, unidades);
				}
				String condicion = "success";
				httpSession.setAttribute("lProductoSession", new ArrayList<Producto>());
				httpSession.setAttribute("condicion", condicion);
				response.sendRedirect("/tiendaOnline/compras/cesta");
			} else {
				String condicion = "stock";
				httpSession.setAttribute("condicion", condicion);
				response.sendRedirect("/tiendaOnline/compras/cesta");
			}
		} else {
			String condicion = "empty";
			httpSession.setAttribute("condicion", condicion);
			response.sendRedirect("/tiendaOnline/compras/cesta");
		}
	}
	

	@GetMapping("/devolucion/{idCompra}")
	public void handleDelete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idCompra") long idCompra) throws IOException {
		Compra compra = compraService.obtenerCompraPorId(idCompra);
		HttpSession session = request.getSession();
		Cliente cliente = clienteService.obtenerCliente((long) session.getAttribute("idSession"));
		Long id = compra.getIdCompra();
		fechaCompra = compra.getFecha();
		Calendar fecha = Calendar.getInstance();
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fechaCompra);
		calendario.add(Calendar.DAY_OF_YEAR, 15);
		if (fecha.DAY_OF_YEAR <= calendario.DAY_OF_YEAR) {
			List<Venta> ventas = new ArrayList<Venta>();
			Set<Producto> productos = new HashSet<>();
			productos = compra.getProductos();
			for (Producto producto : productos) {
				ventas.add(ventaService.obtenerVenta(cliente, producto, compra));
			}
			int i = 0;
			for (Producto producto : productos) {
				productoService.eliminarComprayVenta(producto.getIdProducto(), ventas.get(i).getIdVenta(), idCompra);
				i++;
			}

			clienteService.eliminarCompra(cliente.getIdCliente(), idCompra);

			response.sendRedirect("/tiendaOnline/");
		} else {
			System.out.println("Compra no valida");
		}
	}

	@RequestMapping("/miscompras")
	public ModelAndView listarCompras(HttpServletRequest request) {
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("idSession");
		Cliente cliente = clienteService.obtenerCliente(id);
		ModelAndView modelView = new ModelAndView();

		List<Compra> compras = compraService.listarCompras(cliente);
		modelView.addObject("compras", compras);
		modelView.setViewName("listarcompras");
		return modelView;
	}
	
	@GetMapping("/cesta")
	public ModelAndView cesta(HttpServletRequest request) {
		Boolean bool = false;
		ModelAndView modelView = new ModelAndView();
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("condicion") != null) {
			String condicion = (String) httpSession.getAttribute("condicion");
			if (condicion.equalsIgnoreCase("success")) {
				bool = true;
				String str = "";
				modelView.addObject("success", bool);
				httpSession.setAttribute("condicion", str);
			} else if (condicion.equalsIgnoreCase("stock")) {
				bool = true;
				String str = "";
				modelView.addObject("danger", bool);
				httpSession.setAttribute("condicion", str);
			} else if (condicion.equalsIgnoreCase("empty")) {
				bool = true;
				String str = "";
				modelView.addObject("info", bool);
				httpSession.setAttribute("condicion", str);
			}
		}
		
		List<Producto> cestaProductos = (List<Producto>) httpSession.getAttribute("lProductoSession");
		modelView.addObject("productos", cestaProductos);
		modelView.setViewName("compracarro");
		return modelView;
	}

}
