package com.tiendaOnline.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiendaOnline.dao.CompraDao;
import com.tiendaOnline.dao.ProductoDao;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Producto;

@Transactional
@Service
public class CompraServiceImpl implements CompraService {
	
	@Autowired
	ProductoDao productoDao;
	
	@Autowired
	CompraDao compraDao;

	@Override
	public void devolverCompra(long idCompra) {
		compraDao.delete(idCompra);

	}

	@Override
	public Compra obtenerCompra(Cliente cliente, Producto producto) {
		return compraDao.obtenerCompra(cliente, producto);
	}

	@Override
	public Compra obtenerCompraPorId(long idCompra) {
		return compraDao.find(idCompra);
	}

	@Override
	public Compra crearCompra(Cliente cliente, Set<Producto> producto, Date fecha, float precioT) {
		Compra compra = new Compra();
		compra.setCliente(cliente);
		compra.setProductos(producto);
		compra.setFecha(fecha);
		compra.setPrecioT(precioT);
		return compraDao.create(compra);
	}

	@Override
	public List<Compra> listarCompras(Cliente cliente) {
		return compraDao.listarCompras(cliente);
	}



}
