package com.tiendaOnline.service;

import java.util.Date;
import com.tiendaOnline.entity.Cliente;

public interface ClienteService {
	
	public Cliente iniciarSesion(String userName, String password);

	public boolean cerrarSesion(Cliente cliente);

	public Cliente buscarPorUsuario(String nombre);

	public void eliminarCompra(long idCliente, long idCompra);

	public Cliente registrarCliente(String nombreUsuario, String direccionEnvio, String password, String nombre,
			String apellidos, String email, Date fechaNa, String banco, long numTarjeta, String titular, int codigoS,
			String direccionFa);

	public Cliente obtenerCliente(long idCliente);

	public Cliente editarCliente(Cliente cliente);

	public void eliminarCliente(long idCliente);



}
