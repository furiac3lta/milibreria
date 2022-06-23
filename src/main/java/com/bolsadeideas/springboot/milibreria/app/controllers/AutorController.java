package com.bolsadeideas.springboot.milibreria.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.milibreria.app.dao.IAutorDao;
import com.bolsadeideas.springboot.milibreria.app.entity.Autor;

@Controller
public class AutorController {
	
	@Autowired
	private IAutorDao autorDao;
	
	//@RequestMapping(value={"/clientes","/inmuebles"}) --> varios paths
	@RequestMapping(value="/listar_autor", method=RequestMethod.GET)	
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de autores");
		model.addAttribute("autores", autorDao.listaAutores());
		return "listar_autor";
	}
	
	@RequestMapping(value="/form")
	public String crearAutor(Map<String, Object> model) {
		Autor autor = new Autor();
		model.put("autor", autor);
		model.put("titulo", "Formulario de Autor");
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar (@Valid  Autor autor, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Autor");
			return "form";
		}
		autorDao.guardarAutor(autor);
		return "redirect:listar_autor";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") int idAutor, Map<String, Object> model){
		Autor autor = null;
		if(idAutor>0) {
			autor = autorDao.buscarAutor(idAutor);
		}else {
			return "redirect:/listar_autor";
		}
		model.put("autor", autor);
		model.put("titulo", "Editar Autor");
		return "form";
	}
	@RequestMapping(value="/eliminar/{id}")
	@Transactional
	public String eliminar(@PathVariable(value="id") int idAutor) {
		if(idAutor>0) {
			
			autorDao.bajaAutor(idAutor);
			
		}
		return "redirect:/listar_autor";
	}
	
	@GetMapping(value= {"/","/index"})
	public String mostrar() {
		return "index";
	}
	
	
}
