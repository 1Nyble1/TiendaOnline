package com.tiendaOnline.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.service.ClienteService;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	private ClienteService userService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		List<Producto> lProductos = new ArrayList<Producto>();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HttpSession session = request.getSession();
		Cliente authUser = userService.buscarPorUsuario(userDetails.getUsername());
		session.setAttribute("accountSession", authUser.getNombreUsuario());
		session.setAttribute("passSession", authUser.getPassword());
		session.setAttribute("lProductoSession", lProductos);
		session.setAttribute("idSession", authUser.getIdCliente());
		session.setAttribute("nombreSession", authUser.getNombre());

		boolean isUser = false;
		boolean isAdmin = false;
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROL_USER")) {
				isUser = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROL_ADMIN")) {
				isAdmin = true;
				break;
			}
		}
		String targetUrl;
		if (isUser) {
			session.setAttribute("rol", "user");
			targetUrl = "/cliente/perfil";
		} else if (isAdmin) {
			session.setAttribute("rol", "admin");
			targetUrl = "/cliente/perfil";
		} else {
			throw new IllegalStateException();
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);

	}

}
