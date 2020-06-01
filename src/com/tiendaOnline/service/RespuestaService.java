package com.tiendaOnline.service;

import java.util.List;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Pregunta;
import com.tiendaOnline.entity.Respuesta;

public interface RespuestaService {
	
	public List<Respuesta> listarRespuestas(Pregunta pregunta, Cliente cliente);
	
	public Respuesta crearRespuesta(String respuesta, Pregunta pregunta, Cliente cliente);



}
