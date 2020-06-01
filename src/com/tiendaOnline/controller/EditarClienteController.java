package com.tiendaOnline.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.service.ClienteService;

@Controller
public class EditarClienteController {

	private String usuario;
	private String contrasenna;

	@Autowired
	ClienteService clienteService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/Borrar")
	public void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sesion = request.getSession();
		usuario = (String) sesion.getAttribute("accountSession");
		contrasenna = (String) sesion.getAttribute("passSession");
		Cliente cli = clienteService.iniciarSesion(usuario, contrasenna);
		Long id = cli.getIdCliente();
		clienteService.eliminarCliente(id);
		sesion.invalidate();

		response.sendRedirect("/tiendaOnline/");

	}

	@GetMapping("editarcliente")
	public ModelAndView editarView(HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView();
		HttpSession sesion = request.getSession();
		long id = (long) sesion.getAttribute("idSession");
		Cliente cliente = clienteService.obtenerCliente(id);
		modelView.addObject("cliente", cliente);
		modelView.setViewName("editarcliente");
		return modelView;
	}

	@PostMapping("editarcliente")
	public ModelAndView handleEdit(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("direccionEnvio") String direccionEnvio,
			@RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos,
			@RequestParam("email") String email, @RequestParam("fechaNa") Date fechaNa,
			@RequestParam("banco") String banco, @RequestParam("numTarjeta") long numTarjeta,
			@RequestParam("titular") String titular, @RequestParam("codigoS") int codigoS,
			@RequestParam("direccionFa") String direccionFa) {

		HttpSession session = request.getSession();

		usuario = (String) session.getAttribute("accountSession");
		contrasenna = (String) session.getAttribute("passSession");
		Cliente client = clienteService.iniciarSesion(usuario, contrasenna);
		Long id = client.getIdCliente();
		client.setIdCliente(id);
		client.setNombreUsuario(username);
		client.setPassword(bCryptPasswordEncoder.encode(password));
		client.setDireccionEnvio(direccionEnvio);
		client.setNombre(nombre);
		client.setApellidos(apellidos);
		client.setEmail(email);
		client.setFechaNa(fechaNa);
		client.setBanco(banco);
		client.setNumTarjeta(numTarjeta);
		client.setTitular(titular);
		client.setCodigoS(codigoS);
		client.setDireccionFa(direccionFa);
		Cliente cliente = clienteService.editarCliente(client);

		ModelAndView modelView = new ModelAndView();
		session.setAttribute("accountSession", client.getNombreUsuario());
		session.setAttribute("passSession", client.getPassword());
		modelView.addObject("account", cliente);
		modelView.setViewName("perfil");
		return modelView;
	}
}
