package com.tiendaOnline.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;

public interface CompraDao extends GenericDao<Compra> {
	
	public Compra obtenerCompra(Cliente cliente, Producto producto);

	public Compra obtenerCompraPorId(long idCompra);

	void eliminarCompra(Compra compra);
	
	public Compra crearCompra(Cliente cliente, Set<Producto> producto, Date fecha, float precioT);

	public List<Compra> listarCompras(Cliente cliente);

	public void devolverCompra(long idCompra);
	
}
