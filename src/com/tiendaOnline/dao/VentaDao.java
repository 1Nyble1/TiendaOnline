package com.tiendaOnline.dao;

import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.entity.Venta;

public interface VentaDao extends GenericDao<Venta> {


	Venta obtenerVenta(Cliente cliente, Producto producto, Compra compra);
	
	public Venta crearVenta(Cliente cliente, Producto producto, int unidades);

}
