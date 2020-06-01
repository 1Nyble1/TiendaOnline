package com.tiendaOnline.dao;

import java.util.Date;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;

public interface ClienteDao extends GenericDao<Cliente> {
	
	public Cliente iniciarSesion(String nombreUsuario, String password);

	public boolean cerrarSesion(Cliente cliente);

	public Cliente buscarPorUsuario(String nombre);

	public void eliminarCompras(long idCliente, Compra compra);

	public Cliente registrarCliente(String nombreUsuario, String direccionEnvio, String password, String nombre,
			String apellidos, String email, Date fechaNa, String banco, long numTarjeta, String titular, int codigoS,
			String direccionFa);

	public Cliente obtenerCliente(long idCliente);

	public Cliente editarCliente(Cliente cliente);

	public void eliminarCliente(long idCliente);

	

}
