package com.tiendaOnline.dao;

import java.util.List;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Pregunta;
import com.tiendaOnline.entity.Producto;

public interface PreguntaDao extends GenericDao<Pregunta> {
	
	public List<Pregunta> listarPreguntas(Producto producto, Cliente cliente);

	public Pregunta obtenerPregunta(long idPregunta);
	
	public Pregunta crearPregunta(String pregunta, Producto producto, Cliente cliente);

	

}
