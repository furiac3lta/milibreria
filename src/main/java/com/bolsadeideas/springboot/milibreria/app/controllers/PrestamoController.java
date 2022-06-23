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

import com.bolsadeideas.springboot.milibreria.app.dao.ILibroDao;
import com.bolsadeideas.springboot.milibreria.app.dao.IPrestamoDao;
import com.bolsadeideas.springboot.milibreria.app.entity.Libro;
import com.bolsadeideas.springboot.milibreria.app.entity.Prestamo;

@Controller
@RequestMapping(value = "/prestamo")
public class PrestamoController {
	@Autowired
	private IPrestamoDao prestamoDao;
	
	@Autowired
	private ILibroDao iLibroDao;
	
	@GetMapping("/miprestamo")
	public String miPrestamo(Model model) {
		model.addAttribute("titulo", "pagina de prestamo");
		return "/prestamo/miprestamo";
	}
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de prestamos");
		model.addAttribute("prestamos", prestamoDao.listaPrestamo());
		return "/prestamo/listar";
	}
	
	/*
	 * Muestra el formulario al cliente y crea la instancia cliente que esta
	 * vinculada a la base de datos
	 */
	@RequestMapping(value="/formulario")
	public String crearPrestamo(Map<String, Object> model) {
		
			Prestamo prestamo = new Prestamo();
			model.put("prestamo", prestamo);
			model.put("titulo", "Formulario de Prestamo");
//			String p =prestamo.getLibro().getTitulo();
//			System.out.println(p);
			return "/prestamo/formulario";
				
	}
	
	/*
	 * Recibe los datos poblados de cliente que vienen del formulario
	 * "metodo crearPrestamo" y los guarda
	 */
	@RequestMapping(value="/formulario", method=RequestMethod.POST)
	public String guardar(@Valid Prestamo prestamo, BindingResult result, Model model, int id) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Prestamo");
			System.out.println("Error");
			return "/prestamo/listar";
		}
		id = prestamo.getLibro().getId();
		Libro libro = iLibroDao.buscarLibro(id);
		System.out.println(libro.getEjemplaresRestantes());
		if(libro.getEjemplaresRestantes()>0) {
			libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
			libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()+1);
			libro.setEjemplares(libro.getEjemplaresPrestados()+ libro.getEjemplaresRestantes());
			prestamoDao.guardarPrestamo(prestamo);
		
		}else {
			System.out.println("no se pueden prestar mas libros");
		}
		
		
		
		return "redirect:/prestamo/listar";		
	}
	
	@RequestMapping(value="/formulario/{id}")
	public String editar(@PathVariable(value="id") int id, Map<String, Object> model) {
		Prestamo prestamo = null;
		if(id>0) {
			prestamo = prestamoDao.buscarPrestamo(id);
		}else {
			return "redirect:/prestamo/listar";
		}
		model.put("prestamo", prestamo);
		model.put("titulo", "Editar Prestamo");
		return "/prestamo/listar";
		
	}
	@Transactional
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") int id) {
		if(id>0) {
				Prestamo prestamo = prestamoDao.buscarPrestamo(id);
				int id1 = prestamo.getLibro().getId();
			  Libro libro = iLibroDao.buscarLibro(id1);
			  System.out.println(libro.getEjemplaresRestantes());
			  if(libro.getEjemplaresPrestados()>=0) {
			  libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
			  libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()-1);
			  libro.setEjemplares(libro.getEjemplaresPrestados()+ libro.getEjemplaresRestantes());
			 
			  prestamoDao.bajaPrestamo(id);
			  
			  }else { System.out.println("no se pueden devolver mas libros"); }
			 
		
		}
		return "redirect:/prestamo/listar";
	}
	
}
