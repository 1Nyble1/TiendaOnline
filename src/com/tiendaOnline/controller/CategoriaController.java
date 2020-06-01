package com.tiendaOnline.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.tiendaOnline.entity.Categoria;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.service.CategoriaService;
import com.tiendaOnline.service.ProductoService;

@Controller
@RequestMapping(value = "/categoria")
public class CategoriaController {
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	CategoriaService categoriaService;
	
	
	@RequestMapping("/editarcategoria/{idCategoria}")
	public ModelAndView updateView(@PathVariable("idCategoria") long idCategoria) {
		ModelAndView modelView = new ModelAndView();
		Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
		modelView.addObject("categoria", categoria);
		modelView.setViewName("editarcategoria");
		return modelView;
	}
	
	@RequestMapping("/list")
	public ModelAndView listarCategorias() {
		ModelAndView modelView = new ModelAndView();
		List<Categoria> listaCategoria = categoriaService.listarCategorias();
		modelView.addObject("categorias", listaCategoria);
		modelView.setViewName("listarcategorias");
		return modelView;
	}

	@GetMapping("/crearcategoria")
	public String signUpView() {
		return "crearcategoria";
	}

	@PostMapping("/editarcategoria/{idCategoria}")
	public void handleEdit(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idCategoria") long idCategoria, @RequestParam("nombreCategoria") String nombreCategoria,
			@RequestParam("descripcionCategoria") String descripcionCategoria) throws IOException {

		Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
		categoria.setNombreCategoria(nombreCategoria);
		categoria.setDescripcionCategoria(descripcionCategoria);

		categoriaService.editarCategoria(categoria);
		response.sendRedirect("/tiendaOnline/categoria/detallesCategoria/" + idCategoria);

	}

	@RequestMapping(value = "/borrarcategoria/{idCategoria}")
	public void handleDelete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idCategoria") long idCategoria) throws IOException {
		categoriaService.borrarCategoria(idCategoria);
		response.sendRedirect("/tiendaOnline/");

	}

	@RequestMapping("/detallesCategoria/{idCategoria}")
	public ModelAndView perfilCategoria(HttpServletRequest request, HttpServletResponse response, @PathVariable("idCategoria") long idCategoria) {
		
		HttpSession httpSession = request.getSession();
		ModelAndView modelView = new ModelAndView();
		Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
		httpSession.setAttribute("CategoriaSession", categoria);
		
		List<Producto> productos = new ArrayList<Producto>();
		
		productos = productoService.listarProductosPorCategoria(categoria);
		
		modelView.addObject("categoria", categoria);
		modelView.addObject("productos", productos);
		modelView.setViewName("detallescategoria");
		return modelView;
	}


	@PostMapping("/crearcategoria")
	public void handleSignUp(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("nombreCategoria") String nombreCategoria,
			@RequestParam("descripcionCategoria") String descripcionCategoria) throws IOException {
		Categoria categoria = categoriaService.crearCategoria(nombreCategoria, descripcionCategoria);

		ModelAndView modelView = new ModelAndView();
		if (categoria == null) {
			modelView.addObject("exception", "Usuario o contrasenna estan vacias");
			modelView.setViewName("index");
		}
		HttpSession httpSession = request.getSession();

		httpSession.setAttribute("categoriaSession", nombreCategoria);
		response.sendRedirect("/tiendaOnline/");
	}

	

}
