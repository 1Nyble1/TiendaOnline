package com.tiendaOnline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiendaOnline.dao.CategoriaDao;
import com.tiendaOnline.dao.ProductoDao;
import com.tiendaOnline.entity.Categoria;
import com.tiendaOnline.entity.Producto;

@Transactional
@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	ProductoDao productoDao;
	
	@Autowired
	CategoriaDao categoriaDao;
	
	@Override
	public Categoria obtenerCategoria(String nombre) {
		return categoriaDao.obtenerCategoria(nombre);
	}

	@Override
	public Categoria obtenerCategoriaPorId(long idCategoria) {
		return categoriaDao.find(idCategoria);
	}

	@Override
	public Categoria editarCategoria(Categoria categoria) {
		return categoriaDao.update(categoria);
	}

	@Override
	public List<Categoria> listarCategorias() {
		return categoriaDao.listarCategorias();
	}

	@Override
	public Categoria crearCategoria(String nombreCategoria, String descripcionCategoria) {
		Categoria categoria = new Categoria();
		categoria.setNombreCategoria(nombreCategoria);
		categoria.setDescripcionCategoria(descripcionCategoria);
		return categoriaDao.create(categoria);
	}

	@Override
	public Categoria listarCategoriaPorProducto(Producto producto) {
		return categoriaDao.listarCategoriaPorProducto(producto);
	}

	@Override
	public void borrarCategoria(long idCategoria) {
		categoriaDao.delete(idCategoria);
	}



}
