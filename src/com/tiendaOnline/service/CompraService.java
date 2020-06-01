package com.tiendaOnline.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;

public interface CompraService {
	
	public void devolverCompra(long idCompra);

	public Compra obtenerCompra(Cliente cliente, Producto producto);

	public Compra obtenerCompraPorId(long idCompra);
	
	public Compra crearCompra(Cliente cliente, Set<Producto> producto, Date fecha, float precioT);

	public List<Compra> listarCompras(Cliente cliente);


}
