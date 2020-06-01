package com.tiendaOnline.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCliente;
	@Column(name = "direccionEnvio")
	private String direccionEnvio;
	@Column(name = "nombreUsuario")
	private String nombreUsuario;
	@Column(name = "password")
	private String password;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "email")
	private String email;
	@Column(name = "fechaNacimniento")
	private Date fechaNa;
	@Column(name = "banco")
	private String banco;
	@Column(name = "numTarjeta")
	private long numTarjeta;
	@Column(name = "titular")
	private String titular;
	@Column(name = "codigoSeguridad")
	private int codigoS;
	@Column(name = "direccionFacturacion")
	private String direccionFa;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "cliente_rol", joinColumns = @JoinColumn(name = "idCliente"), inverseJoinColumns = @JoinColumn(name = "idRol"))
	private Set<Rol> roles = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Venta> ventas = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Pregunta> preguntas = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Respuesta> respuestas = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Compra> compras = new HashSet<>();


	public Cliente() {

	}

	public Cliente(long idCliente, String direccionEnvio, String nombreUsuario, String password, String nombre,
			String apellidos, String email, Date fechaNa, String banco, long numTarjeta, String titular, int codigoS,
			String direccionFa) {
		super();
		this.idCliente = idCliente;
		this.direccionEnvio = direccionEnvio;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fechaNa = fechaNa;
		this.banco = banco;
		this.numTarjeta = numTarjeta;
		this.apellidos = apellidos;
		this.email = email;
		this.fechaNa = fechaNa;
	}

	public Cliente(long idCliente, String direccionEnvio, String nombreUsuario, String password) {
		this.idCliente = idCliente;
		this.direccionEnvio = direccionEnvio;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
//		this.ventas = ventas;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNa() {
		return fechaNa;
	}

	public void setFechaNa(Date fechaNa) {
		this.fechaNa = fechaNa;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public long getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(long numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public int getCodigoS() {
		return codigoS;
	}

	public void setCodigoS(int codigoS) {
		this.codigoS = codigoS;
	}

	public String getDireccionFa() {
		return direccionFa;
	}

	public void setDireccionFa(String direccionFa) {
		this.direccionFa = direccionFa;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public Set<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Set<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public void deleteCompra(Compra compras) {
		this.compras.remove(compras);
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", direccionEnvio=" + direccionEnvio + ", nombreUsuario="
				+ nombreUsuario + ", password=" + password + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", email=" + email + ", fechaNa=" + fechaNa + ", banco=" + banco + ", numTarjeta=" + numTarjeta
				+ ", titular=" + titular + ", codigoS=" + codigoS + ", direccionFa=" + direccionFa + ", roles=" + roles
				+ ", compras=" + compras + ", ventas=" + ventas + "]";
	}

}
