package com.tiendaOnline.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiendaOnline.dao.RespuestaDao;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Pregunta;
import com.tiendaOnline.entity.Respuesta;

@Transactional
@Service
public class RespuestaServiceImpl implements RespuestaService {
	
	@Autowired
	RespuestaDao respuestaDao;
	
	@Override
	public List<Respuesta> listarRespuestas(Pregunta pregunta, Cliente cliente) {
		return respuestaDao.listarRespuestas(pregunta, cliente);
	}


	@Override
	public Respuesta crearRespuesta(String respuesta, Pregunta pregunta, Cliente cliente) {
		Respuesta res = new Respuesta();
		res.setTextoRespuesta(respuesta);
		res.setPregunta(pregunta);
		res.setCliente(cliente);
		return respuestaDao.create(res);
	}


}
