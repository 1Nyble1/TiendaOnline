package com.tiendaOnline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.service.ClienteService;

@Controller
public class LoginController {
	@Autowired
	private ClienteService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView handleLogin(HttpServletRequest request) {

		ModelAndView modelView = new ModelAndView();
		
		String usuario = request.getParameter("username");
		String contrasenna = request.getParameter("password");
		
		Cliente cliente = userService.iniciarSesion(usuario, contrasenna);
		String nombre = cliente.getNombreUsuario();
		String contrasenna2 = cliente.getPassword();

		if (usuario.equals(nombre) && contrasenna.equals(contrasenna2)) {
			HttpSession session = request.getSession();
			
			modelView.addObject("account", cliente);
			modelView.setViewName("perfil");
			
			session.setAttribute("accountSession", usuario);
			session.setAttribute("passSession", contrasenna);
			return modelView;
		} else {
			modelView.addObject("exception", "El usuario no existe");
			modelView.setViewName("signup");
			return modelView;
		}
	}

}
