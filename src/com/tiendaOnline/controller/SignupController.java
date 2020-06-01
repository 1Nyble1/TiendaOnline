package com.tiendaOnline.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.service.ClienteService;

@Controller
public class SignupController {
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/signup")
	public String signUpView() {
		return "signup";
	}

	@PostMapping("/signup")
	public void handleSignUp(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("direccionEnvio") String direccionEnvio, @RequestParam("nombre") String nombre,
			@RequestParam("apellidos") String apellidos, @RequestParam("email") String email,
			@RequestParam("fechaNa") Date fechaNa, @RequestParam("banco") String banco,
			@RequestParam("numTarjeta") long numTarjeta, @RequestParam("titular") String titular,
			@RequestParam("codigoS") int codigoS, @RequestParam("direccionFa") String direccionFa) throws IOException {

		Cliente cliente = clienteService.registrarCliente(username, password, direccionEnvio, nombre, apellidos, email, fechaNa,
				banco, numTarjeta, titular, codigoS, direccionFa);

		ModelAndView modelView = new ModelAndView();

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("accountSession", cliente.getNombreUsuario());
		httpSession.setAttribute("passSession", cliente.getPassword());
		httpSession.setAttribute("idSession", cliente.getIdCliente());
		response.sendRedirect("/tiendaOnline/cliente/perfil");
	}
}