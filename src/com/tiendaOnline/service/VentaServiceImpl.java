package com.tiendaOnline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiendaOnline.dao.ProductoDao;
import com.tiendaOnline.dao.VentaDao;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.entity.Venta;

@Transactional
@Service
public class VentaServiceImpl implements VentaService {
	
	@Autowired
	ProductoDao productoDao;
	
	@Autowired
	VentaDao ventaDao;

	@Override
	public Venta obtenerVenta(Cliente cliente, Producto producto, Compra compra) {
		return ventaDao.obtenerVenta(cliente, producto, compra);
	}

	@Override
	public Venta crearVenta(Cliente cliente, Producto producto, Compra compra, int unidades) {
		Venta venta = new Venta();
		venta.setCliente(cliente);
		venta.setProducto(producto);
		venta.setCompra(compra);
		venta.setUnidades(unidades);
		return ventaDao.create(venta);
	}



}
