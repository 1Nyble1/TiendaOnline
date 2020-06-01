package com.tiendaOnline.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.service.ClienteService;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@RequestMapping("/perfil")
	public ModelAndView perfilCliente(HttpServletRequest request) {

		ModelAndView modelView = new ModelAndView();
		HttpSession httpSession = request.getSession();
		long idCliente = (long) httpSession.getAttribute("idSession");
		Cliente cliente = clienteService.obtenerCliente(idCliente);
		modelView.addObject("account", cliente);
		modelView.setViewName("perfil");
		return modelView;
	}

	@GetMapping("/cerrar")
	public void perfilCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		response.sendRedirect("/tiendaOnline/");
	}

}
