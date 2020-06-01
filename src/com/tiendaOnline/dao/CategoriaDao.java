package com.tiendaOnline.dao;

import java.util.List;
import com.tiendaOnline.entity.Categoria;
import com.tiendaOnline.entity.Producto;

public interface CategoriaDao extends GenericDao<Categoria> {
	
	public Categoria listarCategoriaPorProducto(Producto producto);

	public Categoria obtenerCategoria(String nombre);

	public Categoria editarCategoria(Categoria categoria);
	
	public Categoria crearCategoria(String nombreCategoria, String descripcionCategoria);

	public List<Categoria> listarCategorias();

	public void borrarCategoria(long idCategoria);

	public Categoria obtenerCategoriaPorId(long idCategoria);


}
