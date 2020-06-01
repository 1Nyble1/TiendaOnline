package com.tiendaOnline.dao;

import java.util.Date;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;

@Repository
@Component("UserDao")
public class ClienteDaoImpl extends GenericDaoImpl<Cliente> implements ClienteDao {
	
	@Override
	public void eliminarCliente(long idCliente) {

	}

	@Override
	public Cliente iniciarSesion(String nombreUsuario, String password) {
		try {
			Query query = this.em
					.createQuery("From Cliente c Where c.nombreUsuario = :nombreUsuario AND c.password = :password");
			query.setParameter("nombreUsuario", nombreUsuario);
			query.setParameter("password", password);
			Cliente cliente = (Cliente) query.getSingleResult();
			if (cliente != null) {
				return cliente;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Cliente registrarCliente(String nombreUsuario, String direccionEnvio, String password, String nombre,
			String apellidos, String email, Date fechaNa, String banco, long numTarjeta, String titular, int codigoS,
			String direccionFa) {

		return null;
	}

	@Override
	public Cliente obtenerCliente(long idCliente) {
		return null;
	}

	@Override
	public Cliente editarCliente(Cliente cliente) {
		return null;
	}

	

	@Override
	public boolean cerrarSesion(Cliente cliente) {
		return false;
	}

	@Override
	public Cliente buscarPorUsuario(String nombre) {
		try {
			Query query = this.em.createQuery("From Cliente c Where c.nombreUsuario = :nombreUsuario");
			query.setParameter("nombreUsuario", nombre);
			Cliente cliente = (Cliente) query.getSingleResult();
			if (cliente != null) {
				return cliente;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void eliminarCompras(long idCliente, Compra compra) {
		Cliente c = this.find(idCliente);
		c.deleteCompra(compra);
	}

}
