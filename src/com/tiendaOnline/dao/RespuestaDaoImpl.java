package com.tiendaOnline.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Pregunta;
import com.tiendaOnline.entity.Respuesta;

@Repository
@Component("RespuestaDao")
public class RespuestaDaoImpl extends GenericDaoImpl<Respuesta> implements RespuestaDao {
	
	private long idPregunta;
	
	private long idCliente;
	
	@Override
	public List<Respuesta> listarRespuestas(Pregunta pregunta, Cliente cliente) {
		idCliente = cliente.getIdCliente();
		idPregunta = pregunta.getIdPregunta();
		List<Respuesta> respuestas = new ArrayList<Respuesta>();

		Query query = this.em.createQuery("FROM Respuesta Where idCliente = :idCliente  AND idPregunta = :idPregunta",
				Respuesta.class);
		query.setParameter("idCliente", idCliente);
		query.setParameter("idPregunta", idPregunta);
		respuestas = query.getResultList();
		if (respuestas != null) {
			return respuestas;
		}
		return null;
	}

	@Override
	public Respuesta crearRespuesta(String respuesta, Pregunta pregunta, Cliente cliente) {
		return null;
	}

	

}
