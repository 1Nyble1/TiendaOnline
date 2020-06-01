package com.tiendaOnline.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imagen")
public class Imagen {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idImagen;

	@Column(name = "NOMBRE")
	private String nombre;

	@Lob
	@Column(name = "imagen")
	private byte[] imagen;

	@ManyToOne
	@JoinColumn(name = "idProducto", nullable = true)
	private Producto producto;

	public Imagen() {
		super();
	}

	public Imagen(String nombre, byte[] imagen) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(long idImagen) {
		this.idImagen = idImagen;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
