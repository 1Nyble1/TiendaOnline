package com.tiendaOnline.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiendaOnline.dao.PreguntaDao;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Pregunta;
import com.tiendaOnline.entity.Producto;

@Transactional
@Service
public class PreguntaServiceImpl implements PreguntaService {
	@Autowired
	PreguntaDao preguntaDao;
	
	@Override
	public List<Pregunta> listarPreguntas(Producto producto, Cliente cliente) {
		return preguntaDao.listarPreguntas(producto, cliente);
	}

	@Override
	public Pregunta obtenerPregunta(long idPregunta) {
		return preguntaDao.obtenerPregunta(idPregunta);
	}

	@Override
	public Pregunta crearPregunta(String pregunta, Producto producto, Cliente cliente) {
		Pregunta pre = new Pregunta();
		pre.setPregunta(pregunta);
		pre.setProducto(producto);
		pre.setCliente(cliente);
		return preguntaDao.create(pre);
	}



}
