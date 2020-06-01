package com.tiendaOnline.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiendaOnline.dao.ClienteDao;
import com.tiendaOnline.dao.CompraDao;
import com.tiendaOnline.dao.RolRepository;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Compra;
import com.tiendaOnline.entity.Rol;

@Transactional
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private CompraDao compraDao;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Cliente editarCliente(Cliente cliente) {
		return clienteDao.update(cliente);
	}

	@Override
	public void eliminarCliente(long idCliente) {
		clienteDao.delete(idCliente);
	}

	@Override
	public Cliente iniciarSesion(String userName, String password) {
		return clienteDao.iniciarSesion(userName, password);
	}

	@Override
	public boolean cerrarSesion(Cliente cliente) {
		return clienteDao.cerrarSesion(cliente);
	}

	@Override
	public Cliente buscarPorUsuario(String nombre) {
		return clienteDao.buscarPorUsuario(nombre);

	}

	@Override
	public void eliminarCompra(long idCliente, long idCompra) {

		Compra compra = compraDao.find(idCompra);
		Cliente cliente = clienteDao.find(idCliente);

		for (Compra c : cliente.getCompras()) {
			if (c.getIdCompra() == idCompra) {
				clienteDao.eliminarCompras(idCliente, compra);
			}
		}

	}

	@Override
	public Cliente registrarCliente(String nombreUsuario, String password, String direccionEnvio, String nombre,
			String apellidos, String email, Date fechaNa, String banco, long numTarjeta, String titular, int codigoS,
			String direccionFa) {
		Cliente cliente = new Cliente();
		cliente.setNombreUsuario(nombreUsuario);
		cliente.setDireccionEnvio(direccionEnvio);
		cliente.setPassword(bCryptPasswordEncoder.encode(password));
		cliente.setNombre(nombre);
		cliente.setApellidos(apellidos);
		cliente.setEmail(email);
		cliente.setFechaNa(fechaNa);
		cliente.setBanco(banco);
		cliente.setNumTarjeta(numTarjeta);
		cliente.setTitular(titular);
		cliente.setCodigoS(codigoS);
		cliente.setDireccionFa(direccionFa);
		Set<Rol> roles = new HashSet<Rol>();
		Rol rol = rolRepository.getOne(1);
		roles.add(rol);
		cliente.setRoles(roles);
		return clienteDao.create(cliente);
	}

	@Override
	public Cliente obtenerCliente(long idCliente) {
		return clienteDao.find(idCliente);
	}



}
