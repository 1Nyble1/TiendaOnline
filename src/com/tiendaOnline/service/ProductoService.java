package com.tiendaOnline.service;

import java.util.List;
import com.tiendaOnline.entity.Categoria;
import com.tiendaOnline.entity.Producto;

public interface ProductoService {
	
	public Producto editarProducto(Producto producto);

	public void eliminarProducto(long idProducto);

	public List<Producto> listarProductosPorPrecio(float minPrecio, float maxPrecio, int count, int index);

	public List<Producto> listarProductosPorNombreYPrecio(String nombre, float minPrecio, float maxPrecio, int count,
			int index);

	public void eliminarComprayVenta(long idProducto, long idVenta, long idCompra);

	public List<Producto> listarProductos();

	public List<Producto> listarProductosPorNombre(String nombreProducto);

	public List<Producto> listarProductosPorCategoria(Categoria categoria);

	public Producto obtenerProducto(long idProducto);

	public Producto obtenerProductoPorNombre(String nombreProducto);

	public Producto crearProducto(String nombre, float precio, int stock, Categoria categoria, String descripcion);


}
