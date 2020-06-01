package com.tiendaOnline.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiendaOnline.dao.CategoriaDao;
import com.tiendaOnline.dao.CompraDao;
import com.tiendaOnline.dao.ProductoDao;
import com.tiendaOnline.dao.VentaDao;
import com.tiendaOnline.entity.Categoria;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.entity.Venta;

@Transactional
@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	CompraDao compraDao;

	@Autowired
	VentaDao ventaDao;
	
	@Autowired
	ProductoDao productoDao;

	@Autowired
	CategoriaDao categoriaDao;

	@Override
	public List<Producto> listarProductosPorPrecio(float minPrecio, float maxPrecio, int count, int index) {
		return null;
	}

	@Override
	public List<Producto> listarProductosPorNombreYPrecio(String nombre, float minPrecio, float maxPrecio, int count,
			int index) {
		return null;
	}

	@Override
	public void eliminarComprayVenta(long idProducto, long idVenta, long idCompra) {

		Compra compra = compraDao.find(idCompra);
		Venta venta = ventaDao.find(idVenta);
		Producto producto = productoDao.find(idProducto);

		for (Compra c : producto.getCompras()) {
			if (c.getIdCompra() == idCompra) {
				productoDao.eliminarCompra(idProducto, compra);
			}
		}
		for (Venta v : producto.getVentas()) {
			if (v.getIdVenta() == idVenta && v.getCompra().getIdCompra() == idCompra) {
				productoDao.eliminarVentas(idProducto, venta);
				;
			}
		}
	}

	@Override
	public List<Producto> listarProductosPorCategoria(Categoria categoria) {
		return productoDao.listarProductosPorCategoria(categoria);
	}

	@Override
	public List<Producto> listarProductos() {
		return productoDao.listarProductos();
	}

	@Override
	public List<Producto> listarProductosPorNombre(String nombreProducto) {
		return productoDao.listarProductosPorNombre(nombreProducto);
	}

	@Override
	public Producto obtenerProducto(long idProducto) {
		return productoDao.find(idProducto);
	}

	@Override
	public Producto obtenerProductoPorNombre(String nombreProducto) {
		return productoDao.obtenerProductoPorNombre(nombreProducto);
	}

	@Override
	public Producto crearProducto(String nombre, float precio, int stock, Categoria categoria, String descripcion) {
		Producto producto = new Producto();
		producto.setNombreProducto(nombre);
		producto.setPrecio(precio);
		producto.setStock(stock);
		producto.setCategoria(categoria);
		producto.setDescripcion(descripcion);
		return productoDao.create(producto);
	}

	@Override
	public Producto editarProducto(Producto producto) {
		return productoDao.update(producto);
	}

	@Override
	public void eliminarProducto(long idProducto) {
		Producto p = productoDao.find(idProducto);
		Set<Producto> producto = p.getCategoria().getProductos();
		producto.remove(p);
		productoDao.delete(idProducto);
	}



}
