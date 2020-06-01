package com.tiendaOnline.dao;

import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.entity.Venta;

@Repository
@Component("VentaDao")
public class VentaDaoImpl extends GenericDaoImpl<Venta> implements VentaDao {
	
	private long idProducto;
	private long idCompra;
	private long idCliente;
	
	

	@Override
	public Venta obtenerVenta(Cliente cliente, Producto producto, Compra compra) {
		idCliente = cliente.getIdCliente();
		idProducto = producto.getIdProducto();
		idCompra = compra.getIdCompra();
		Query query = this.em.createQuery(
				"From Venta Where idCliente = :idCliente AND idProducto = :idProducto AND idCompra = :idCompra");
		query.setParameter("idCliente", idCliente);
		query.setParameter("idProducto", idProducto);
		query.setParameter("idCompra", idCompra);
		Venta venta = (Venta) query.getSingleResult();
		if (venta != null) {
			return venta;
		}
		return null;
	}

	@Override
	public Venta crearVenta(Cliente cliente, Producto producto, int unidades) {
		return null;
	}


}
