package com.tiendaOnline.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.tiendaOnline.entity.Producto;
import com.tiendaOnline.service.ProductoService;

@Controller
public class IndexController {
	@Autowired
	private ProductoService productoService;

	@GetMapping({ "/", "index" })
	public String index(Model model) {
		
		List<Producto> productos = productoService.listarProductos();
		ArrayList<Producto> arrayProductos = new ArrayList<Producto>();
		for (int i = 0; i < 6 && i < productos.size(); i++) {
			arrayProductos.add(productos.get(i));
		}
		model.addAttribute("productos", arrayProductos);
		return "index";
		
	}
}
