package com.tiendaOnline.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.tiendaOnline.entity.Categoria;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Imagen;
import com.tiendaOnline.entity.Pregunta;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.entity.Respuesta;
import com.tiendaOnline.service.CategoriaService;
import com.tiendaOnline.service.ImagenService;
import com.tiendaOnline.service.PreguntaService;
import com.tiendaOnline.service.ProductoService;
import com.tiendaOnline.service.RespuestaService;
import com.tiendaOnline.service.ClienteService;

@Controller
@RequestMapping(value = "/producto")
public class ProductoController {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	RespuestaService respuestaService;
	
	@Autowired
	PreguntaService preguntaService;
	
	@Autowired
	ImagenService imagenService;
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("/listar/{idCategoria}")
	public ModelAndView listarProductos(@PathVariable("idCategoria") long idCategoria) {

		Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
		ModelAndView modelView = new ModelAndView();

		List<Producto> listaProducto = productoService.listarProductosPorCategoria(categoria);
		List<Categoria> categorias = categoriaService.listarCategorias();
		modelView.addObject("categorias", categorias);
		modelView.addObject("productos", listaProducto);
		modelView.setViewName("listarproductos");
		return modelView;
	}

	@GetMapping("crearproducto")
	public ModelAndView signUpView() {
		ModelAndView modelView = new ModelAndView();
		List<Categoria> categorias = categoriaService.listarCategorias();
		modelView.addObject("categorias", categorias);
		modelView.setViewName("crearproducto");
		return modelView;
	}

	@PostMapping("/detallesProducto/{idProducto}")
	public ModelAndView agregarCesta(@PathVariable("idProducto") long idProducto,
			@RequestParam("cantidad") int cantidad, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		Boolean conStock = true;
		boolean existe = false;
		ModelAndView modelView = new ModelAndView();
		int cantidad2 = cantidad;
		
		Producto productoCesta = productoService.obtenerProducto(idProducto);
		Producto productoVista = productoService.obtenerProducto(idProducto);
		Categoria categoria = categoriaService.listarCategoriaPorProducto(productoVista);
		
		HttpSession httpSesion = request.getSession();
		Boolean danger = false;
		if (productoVista.getStock() < cantidad2) {
			conStock = false;
			danger = true;
			modelView.addObject("danger", danger);
		} else {
			productoCesta.setStock(cantidad2);
			List<Producto> cestaProductos = (List<Producto>) httpSesion.getAttribute("lProductoSession");
			for (int i = 0; i < cestaProductos.size(); i++) {
				if (cestaProductos.get(i).getIdProducto() == productoCesta.getIdProducto()) {
					if ((cestaProductos.get(i).getStock() + productoCesta.getStock()) <= productoService
							.obtenerProducto(productoCesta.getIdProducto()).getStock()) {
						cestaProductos.get(i).setStock(cestaProductos.get(i).getStock() + productoCesta.getStock());
						existe = true;
					} else {
						danger = true;
						modelView.addObject("danger", danger);
						existe = true;
					}
				}
			}
			if (!existe) {
				cestaProductos.add(productoCesta);
			}
			httpSesion.setAttribute("lProductoSession", cestaProductos);
		}

		if (httpSesion.getAttribute("idSession") != null) {
			long id = (long) httpSesion.getAttribute("idSession");
			Cliente cliente = clienteService.obtenerCliente(id);
			List<Pregunta> preguntas = preguntaService.listarPreguntas(productoVista, cliente);
			List<ArrayList> respuestas = new ArrayList<ArrayList>();
			for (Pregunta pregunta : preguntas) {
				ArrayList<Respuesta> listar = new ArrayList<Respuesta>();
				listar = (ArrayList<Respuesta>) respuestaService.listarRespuestas(pregunta, cliente);
				respuestas.add(listar);
			}
			if (preguntas != null) {
				modelView.addObject("preguntas", preguntas);
			}
			if (respuestas != null) {
				modelView.addObject("respuestas", respuestas);
			}
		}

		if (!danger) {
			Boolean success = true;
			modelView.addObject("success", success);
		}
		modelView.addObject("producto", productoVista);
		modelView.addObject("categoria", categoria);
		modelView.setViewName("detallesproducto");
		return modelView;
	}

	@PostMapping("/list")
	public ModelAndView listarProductos(@RequestParam("producto") String nombreProducto) {

		ModelAndView modelView = new ModelAndView();

		List<Producto> lProducto = productoService.listarProductosPorNombre(nombreProducto);
		List<Categoria> categorias = categoriaService.listarCategorias();
		modelView.addObject("categorias", categorias);
		modelView.addObject("productos", lProducto);
		modelView.setViewName("listarproductos");
		return modelView;
	}

	

	@PostMapping("crearproducto")
	public void handleSignUp(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("nombreProducto") String nombre, @RequestParam("precio") float precio,
			@RequestParam("stock") int stock, @RequestParam("categoria") String categoria,
			@RequestParam("descripcion") String descripcion, @RequestParam("imagen") MultipartFile imagen)
			throws IOException {
		Categoria c = categoriaService.obtenerCategoria(categoria);
		Producto producto = productoService.crearProducto(nombre, precio, stock, c, descripcion);
		imagenService.agregarFotoProducto(producto.getIdProducto(), imagen);
		ModelAndView modelView = new ModelAndView();
		if (producto == null) {
			modelView.addObject("exception", "Username or password are empty.");
			modelView.setViewName("index");
		}
		HttpSession session = request.getSession();

		session.setAttribute("productoSession", nombre);
		response.sendRedirect("/tiendaOnline/");
	}
	
	@RequestMapping(value = "/imagen/{idProducto}")
	public @ResponseBody ResponseEntity getImageAsResponseEntity(@PathVariable("idProducto") String idProducto) {

		try {
			Producto p = productoService.obtenerProducto(Long.parseLong(idProducto));
			Set<Imagen> imagenes = new HashSet<>();
			imagenes = p.getImagen();
			Iterator<Imagen> it = imagenes.iterator();
			byte[] media = null;
			while (it.hasNext()) {
				media = it.next().getImagen();
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/editarproducto/{idProducto}")
	public ModelAndView updateView(@PathVariable("idProducto") long idProducto) {
		ModelAndView modelView = new ModelAndView();
		Producto producto = productoService.obtenerProducto(idProducto);
		List<Categoria> categorias = categoriaService.listarCategorias();
		modelView.addObject("categorias", categorias);
		modelView.addObject("producto", producto);
		modelView.setViewName("editarproducto");
		return modelView;
	}

	@PostMapping("/agregar")
	public void cambioSesionProducto(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("nombreProducto") String cliente, @RequestParam("precio") float precio,
			@RequestParam("stock") int stock, @RequestParam("categoria") Categoria categoria,
			@RequestParam("descripcion") String descripcion) throws IOException {
		HttpSession sesion = request.getSession();
		List<Producto> cProductos = (List<Producto>) sesion.getAttribute("lProductoSession");
		Producto producto = new Producto(cliente, precio, stock, categoria, descripcion);
		cProductos.add(producto);
		sesion.setAttribute("lProductoSession", cProductos);
		response.sendRedirect("/tiendaOnline/");
	}

	@RequestMapping(value = "/borrarproducto/{idProducto}")
	public void handleDelete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idProducto") long idProducto) throws IOException {
		productoService.eliminarProducto(idProducto);
		response.sendRedirect("/tiendaOnline/");

	}

	@PostMapping("/preguntar/{idProducto}")
	public void preguntar(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idProducto") long idProducto, @RequestParam("pregunta") String pre) throws IOException {
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("idSession");
		Cliente cliente = clienteService.obtenerCliente(id);
		Producto producto = (Producto) session.getAttribute("ProductoSession");
		preguntaService.crearPregunta(pre, producto, cliente);

		response.sendRedirect("/tiendaOnline/producto/detallesProducto/" + idProducto);
	}

	@PostMapping("/responder/{idPregunta}")
	public void responder(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idPregunta") long idPregunta, @RequestParam("idProducto") long idProducto,
			@RequestParam("respuesta") String res) throws IOException {
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("idSession");
		Cliente cliente = clienteService.obtenerCliente(id);
		Pregunta pregunta = preguntaService.obtenerPregunta(idPregunta);
		respuestaService.crearRespuesta(res, pregunta, cliente);
		response.sendRedirect("/tiendaOnline/producto/detallesProducto/" + idProducto);

	}
	
	@RequestMapping("/detallesProducto/{idProducto}")
	public ModelAndView perfilProducto(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idProducto") long idProducto) {
		HttpSession httpSesion = request.getSession();
		ModelAndView modelView = new ModelAndView();
		if (httpSesion.getAttribute("idSession") != null) {
			long id = (long) httpSesion.getAttribute("idSession");
			Cliente cliente = clienteService.obtenerCliente(id);
		}
		
		Producto producto = productoService.obtenerProducto(idProducto);
		Categoria categoria = categoriaService.listarCategoriaPorProducto(producto);
		
		if (httpSesion.getAttribute("idSession") != null) {
			long id = (long) httpSesion.getAttribute("idSession");
			Cliente cliente = clienteService.obtenerCliente(id);
			List<Pregunta> listaPregunta = preguntaService.listarPreguntas(producto, cliente);
			List<ArrayList> listaRespuestas = new ArrayList<ArrayList>();
			for (Pregunta pregunta : listaPregunta) {
				ArrayList<Respuesta> listar = new ArrayList<Respuesta>();
				listar = (ArrayList<Respuesta>) respuestaService.listarRespuestas(pregunta, cliente);
				listaRespuestas.add(listar);
			}
			if (listaPregunta != null) {
				modelView.addObject("preguntas", listaPregunta);
			}
			if (listaRespuestas != null) {
				modelView.addObject("respuestas", listaRespuestas);
			}
		}

		httpSesion.setAttribute("ProductoSession", producto);
		modelView.addObject("producto", producto);
		modelView.addObject("categoria", categoria);
		modelView.setViewName("detallesproducto");
		return modelView;
	}

	@PostMapping("/editarproducto/{idProducto}")
	public void handleEdit(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idProducto") long idProducto, @RequestParam("nombreProducto") String nombre,
			@RequestParam("precio") float precio, @RequestParam("stock") int stock,
			@RequestParam("categoria") String categoria, @RequestParam("descripcion") String descripcion,
			@RequestParam("imagen") MultipartFile imagen) throws IOException {

		Categoria c = categoriaService.obtenerCategoria(categoria);
		Producto pro = productoService.obtenerProducto(idProducto);
		pro.setNombreProducto(nombre);
		pro.setPrecio(precio);
		pro.setStock(stock);
		pro.setCategoria(c);
		pro.setDescripcion(descripcion);
		imagenService.agregarFotoProducto(idProducto, imagen);
		productoService.editarProducto(pro);
		response.sendRedirect("/tiendaOnline/producto/detallesProducto/" + idProducto);

	}


}
