package com.tiendaOnline.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiendaOnline.dao.ClienteDao;
import com.tiendaOnline.entity.Cliente;
import com.tiendaOnline.entity.Rol;

@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Cliente cliente = clienteDao.buscarPorUsuario(userName);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Rol rol : cliente.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombreRol()));
			System.out.println(rol.getNombreRol());
			System.out.println(cliente.getNombreUsuario());
			System.out.println(cliente.getPassword());
		}

		return new org.springframework.security.core.userdetails.User(cliente.getNombreUsuario(), cliente.getPassword(),
				grantedAuthorities);
	}
}
