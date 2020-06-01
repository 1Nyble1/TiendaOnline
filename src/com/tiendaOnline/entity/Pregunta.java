package com.tiendaOnline.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pregunta")
public class Pregunta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPregunta")
	private long idPregunta;
	
	@Column(name = "textoPregunta")
	private String pregunta;
	
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto producto;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Respuesta> respuestas = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	public Pregunta(long idPregunta, String pregunta, Producto producto, Set<Respuesta> respuestas, Cliente cliente) {
		super();
		this.idPregunta = idPregunta;
		this.pregunta = pregunta;
		this.producto = producto;
		this.respuestas = respuestas;
		this.cliente = cliente;
	}

	public Pregunta() {

	}

	public Pregunta(String pregunta, Producto producto) {
		super();
		this.pregunta = pregunta;
		this.producto = producto;
	}

	public long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Set<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Set<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pregunta [idPregunta=" + idPregunta + ", pregunta=" + pregunta + ", producto=" + producto
				+ ", respuestas=" + respuestas + ", cliente=" + cliente + "]";
	}

}
