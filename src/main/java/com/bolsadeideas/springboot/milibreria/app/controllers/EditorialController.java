package com.bolsadeideas.springboot.milibreria.app.controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bolsadeideas.springboot.milibreria.app.dao.IEditorialDao;
import com.bolsadeideas.springboot.milibreria.app.entity.Editorial;

@Controller
public class EditorialController {
	
	@Autowired
	private IEditorialDao editorialDao;
	
	@RequestMapping(value="/editoriales", method=RequestMethod.GET)	
	public String listarEditorial(Model model) {
		model.addAttribute("titulo", "Listado de Editoriales");
		model.addAttribute("editoriales", editorialDao.listaEditoriales());
		return "editoriales";
	}
	
	@RequestMapping(value="/form_edit")
	public String crearEditorial(Map<String, Object> model) {
		Editorial editorial = new Editorial();
		model.put("editorial", editorial);
		model.put("titulo", "Formulario de Editorial");
		return "form_edit";
	}
	
	@RequestMapping(value="/form_edit", method=RequestMethod.POST)
	public String guardarEditorial(@Valid Editorial editorial, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Editorial");
			return "form_edit";
		}
		editorialDao.guardarEditorial(editorial);
		return "redirect:editoriales";
		
	}
	
	@RequestMapping(value="/form_edit/{id}")
	public String editarEditorial(@PathVariable(value="id") int idEditorial, Map<String, Object> model){
		Editorial editorial = null;
		if(idEditorial>0) {
			editorial = editorialDao.buscarEditorial(idEditorial);
		}else {
			return "redirect:/editoriales";
		}
		model.put("editorial", editorial);
		model.put("titulo", "Editar Editorial");
		return "form_edit";
	}
	@RequestMapping(value="/eliminar_ed/{id}")
	@Transactional
	public String eliminarEditorial(@PathVariable(value="id") int idEditorial) {
		if(idEditorial>0) {
			editorialDao.bajaEditorial(idEditorial);
			
		}
		return "redirect:/editoriales";
	}

}
