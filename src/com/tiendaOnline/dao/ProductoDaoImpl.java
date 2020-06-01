package com.tiendaOnline.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.tiendaOnline.entity.Categoria;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.entity.Venta;

@Repository
@Component("ProductoDao")
public class ProductoDaoImpl extends GenericDaoImpl<Producto> implements ProductoDao {
	
	@Override
	public Producto editarProducto(Producto producto) {
		return null;
	}

	@Override
	public void eliminarProducto(long idProducto) {

	}

	@Override
	public List<Producto> obtenerProductosNombre(String nombre, int count, int index) {
		return null;
	}

	@Override
	public List<Producto> obtenerProductosPorPrecio(float minPrecio, float maxPrecio, int count, int index) {
		return null;
	}

	@Override
	public void eliminarCompra(long idProducto, Compra compra) {
		Producto p = this.find(idProducto);
		p.deleteCompras(compra);
	}

	@Override
	public void eliminarVentas(long idProducto, Venta venta) {
		Producto p = this.find(idProducto);
		p.deleteVentas(venta);
	}

	@Override
	public List<Producto> listarProductosPorCategoria(Categoria categoria) {
		long idCategoria = categoria.getIdCategoria();

		Query query = this.em.createQuery("From Producto Where idCategoria = :idCategoria");
		query.setParameter("idCategoria", idCategoria);
		List<Producto> lProducto = query.getResultList();

		if (lProducto != null) {
			return lProducto;
		}
		return null;
	}

	@Override
	public List<Producto> listarProductosPorNombre(String nombreProducto) {
		Query query = this.em.createQuery("FROM Producto u where u.nombreProducto LIKE :nombre", Producto.class);
		query.setParameter("nombre", '%' + nombreProducto + '%');
		List<Producto> lProducto = query.getResultList();

		if (lProducto != null) {
			return lProducto;
		}
		return null;
	}

	@Override
	public Producto obtenerProductoPorNombre(String nombreProducto) {
		Query query = this.em.createQuery("FROM Producto u where u.nombreProducto = :nombreProducto", Producto.class);
		query.setParameter("nombreProducto", nombreProducto);
		Producto producto = (Producto) query.getSingleResult();

		if (producto != null) {
			return producto;
		}
		return null;
	}

	@Override
	public List<Producto> listarProductos() {
		List<Producto> lProducto = new ArrayList<Producto>();

		lProducto = this.em.createQuery("FROM Producto", Producto.class).getResultList();

		if (lProducto != null) {
			return lProducto;
		}
		return null;
	}

	@Override
	public Producto crearProducto(String nombre, float precio, int stock, Categoria categoria, String descripcion) {
		return null;
	}

	@Override
	public Producto obtenerProducto(long idProducto) {
		return null;
	}

	

}
