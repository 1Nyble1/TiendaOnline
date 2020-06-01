package com.tiendaOnline.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;

@Repository
@Component("CompraDao")
public class CompraDaoImpl extends GenericDaoImpl<Compra> implements CompraDao {
	
	private long idCliente;

	@Override
	public Compra obtenerCompra(Cliente cliente, Producto producto) {
		return null;
	}

	@Override
	public Compra obtenerCompraPorId(long idCompra) {
		return null;
	}

	@Override
	public Compra crearCompra(Cliente cliente, Set<Producto> producto, Date fecha, float precioT) {
		return null;
	}

	@Override
	public void eliminarCompra(Compra compra) {
		Producto p = new Producto();
		p.deleteCompras(compra);

	}
	
	@Override
	public List<Compra> listarCompras(Cliente cliente) {
		idCliente = cliente.getIdCliente();
		List<Compra> compra = new ArrayList<Compra>();

		compra = this.em.createQuery("FROM Compra Where idCliente = " + idCliente, Compra.class).getResultList();
		if (compra != null) {
			return compra;
		}
		return null;
	}

	@Override
	public void devolverCompra(long idCompra) {
	}

	

}
