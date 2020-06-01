package com.tiendaOnline.service;

import java.util.List;
import com.tiendaOnline.entity.Categoria;
import com.tiendaOnline.entity.Producto;

public interface CategoriaService {
	
	public Categoria editarCategoria(Categoria categoria);

	public Categoria obtenerCategoriaPorId(long idCategoria);

	public Categoria listarCategoriaPorProducto(Producto producto);

	public Categoria obtenerCategoria(String nombre);
	
	public Categoria crearCategoria(String nombreCategoria, String descripcionCategoria);

	public List<Categoria> listarCategorias();

	public void borrarCategoria(long idCategoria);


}
