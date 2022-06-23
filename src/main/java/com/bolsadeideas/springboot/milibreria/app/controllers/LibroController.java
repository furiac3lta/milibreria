package com.bolsadeideas.springboot.milibreria.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.milibreria.app.dao.IAutorDao;
import com.bolsadeideas.springboot.milibreria.app.dao.IEditorialDao;
import com.bolsadeideas.springboot.milibreria.app.dao.ILibroDao;
import com.bolsadeideas.springboot.milibreria.app.entity.Libro;

@Controller
public class LibroController {
	@Autowired
	private IEditorialDao editorialDao;
	
	@Autowired
	private ILibroDao libroDao;

	@Autowired
	private IAutorDao autorDao;
	

	// @RequestMapping(value={"/listar_libro","/"},
	@RequestMapping(value = "/listar_libro", method = RequestMethod.GET)
	public String editarLibro(Model model) {
		model.addAttribute("titulo", "Listado de libros");
		model.addAttribute("libros", libroDao.listaLibros());
		return "listar_libro";
	}

	@RequestMapping(value = "/libros", method = RequestMethod.GET)
	public String listarLibro(Model model) {
		model.addAttribute("titulo", "Listado de libros");
		model.addAttribute("libros", libroDao.listaLibros());
		return "libros";
	}

	@RequestMapping(value = "/form_lib")
	public String crearLibro(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		model.addAttribute("titulo", "Formulario crear Libro");
        model.addAttribute("autores", autorDao.listaAutores()) ;
		model.addAttribute("editoriales", editorialDao.listaEditoriales());
		
		return "form_lib";
	}

//	Implementar subida de imagen
	@RequestMapping(value = "/form_lib",method = RequestMethod.POST)
	public String guardarLibro(@Valid @ModelAttribute Libro libro, BindingResult result, Model model,
			@RequestParam("file") MultipartFile imagen, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Libro");
			model.addAttribute("libro", libro);
			
			System.out.println("errrorrrr");
			
			return "form_lib";
		}

		if (!imagen.isEmpty()) {
			// Path directorioImagenes =Paths.get("src//main//resources//static/images");
			// String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			String rutaAbsoluta = "//home//marce//Documentos//imagen";

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				libro.setImagen(imagen.getOriginalFilename());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		
		System.out.println(libro.toString());
		
		libroDao.guardarLibro(libro);
		return "redirect:/libros";

	}

	@RequestMapping(value = "/form_lib/{id}")
	public String editar(@PathVariable(value = "id") int id, Model model) {
		Libro libro = null;
		model.addAttribute("autores", autorDao.listaAutores()) ;
		model.addAttribute("editoriales", editorialDao.listaEditoriales());
		
		if (id > 0) {
			libro = libroDao.buscarLibro(id);
		} else {
			return "redirect:/listar_libro";
		}
		model.addAttribute("libro", libro);
		model.addAttribute("titulo", "Editar Libro");
		return "form_lib";
	}

	@RequestMapping(value = "/eliminar_lib/{id}")
	@Transactional
	public String eliminar(@PathVariable(value = "id") int id) {
		if (id > 0) {
			libroDao.bajaLibro(id);

		}
		return "redirect:/listar_libro";
	}

}
