package com.tiendaOnline.dao;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Pregunta;
import com.tiendaOnline.entity.Producto;

@Repository
@Component("PreguntaDao")
public class PreguntaDaoImpl extends GenericDaoImpl<Pregunta> implements PreguntaDao {
	
	private long idProducto;
	
	private long idCliente;

	
	@Override
	public List<Pregunta> listarPreguntas(Producto producto, Cliente cliente) {
		idCliente = cliente.getIdCliente();
		idProducto = producto.getIdProducto();
		Query query = this.em.createQuery("FROM Pregunta Where idCliente = :idCliente  AND idProducto = :idProducto",
				Pregunta.class);
		query.setParameter("idCliente", idCliente);
		query.setParameter("idProducto", idProducto);
		List<Pregunta> preguntas = query.getResultList();

		if (preguntas != null) {
			return preguntas;
		}
		return null;
	}

	@Override
	public Pregunta obtenerPregunta(long idPregunta) {

		Query query = this.em.createQuery("FROM Pregunta Where idPregunta = :idPregunta", Pregunta.class);
		query.setParameter("idPregunta", idPregunta);
		Pregunta pregunta = (Pregunta) query.getSingleResult();

		if (pregunta != null) {
			return pregunta;
		}
		return null;

	}

	@Override
	public Pregunta crearPregunta(String pregunta, Producto producto, Cliente cliente) {
		return null;
	}

	

}
