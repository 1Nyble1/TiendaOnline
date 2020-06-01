package com.tiendaOnline.dao;

import java.util.List;
import com.tiendaOnline.entity.Categoria;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.entity.Venta;

public interface ProductoDao extends GenericDao<Producto> {
	
	public Producto obtenerProductoPorNombre(String nombreProducto);

	public Producto editarProducto(Producto producto);
	
	public List<Producto> listarProductosPorCategoria(Categoria categoria);

	public Producto crearProducto(String nombre, float precio, int stock, Categoria categoria, String descripcion);

	public void eliminarProducto(long idProducto);

	public List<Producto> obtenerProductosNombre(String nombre, int count, int index);

	public List<Producto> obtenerProductosPorPrecio(float minPrecio, float maxPrecio, int count, int index);

	public void eliminarCompra(long idProducto, Compra compra);

	public void eliminarVentas(long idProducto, Venta venta);

	public List<Producto> listarProductos();

	public List<Producto> listarProductosPorNombre(String nombreModulo);

	

	public Producto obtenerProducto(long idProducto);

	

}
