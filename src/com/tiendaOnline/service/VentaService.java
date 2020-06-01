package com.tiendaOnline.service;

import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.entity.Venta;

public interface VentaService {
	
	Venta obtenerVenta(Cliente cliente, Producto producto, Compra compra);
	
	Venta crearVenta(Cliente cliente, Producto producto, Compra compra, int unidades);


}
