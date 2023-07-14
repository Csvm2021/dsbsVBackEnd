package certus.edu.pe.controladores.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import certus.edu.pe.modelo.Categorias;
import certus.edu.pe.servicios.CategoriasServicio;


@Controller
@RequestMapping("/categorias")
public class CategoriasWebController {

	@Autowired
	private CategoriasServicio servicio;

	@RequestMapping("/listarTodo")
	public String listarCategorias (Model model) {

		List <Categorias> listaCategorias = servicio.buscarTodo();
		System.out.println("LISTA DE CATEGORIAS : " + listaCategorias);
		model.addAttribute("listaCategorias", listaCategorias);
		return "/moduloCategorias/listarTodo";
	}

	@RequestMapping("/nuevo")
	public String nuevoCategoria(Model model) {
		Categorias categoria = new Categorias ();
		model.addAttribute("categorias", categoria);
		return "/moduloCategorias/nuevoCategoria";	
	}

	@RequestMapping(value ="/guardar", method= RequestMethod.POST)
	public String crearCategoria(@ModelAttribute("categorias")@Valid Categorias categorias, BindingResult bindingResult) {
		 	if(bindingResult.hasErrors()) {
		    	return "/moduloCategorias/nuevoCategoria";
		    }
			servicio.crear(categorias);
		    return "redirect:/categorias/listarTodo";

	}

	 @RequestMapping(value ="/actualizar/{id}")
	public ModelAndView editarCategoria(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView("/moduloCategorias/editarCategoria");
		Categorias categorias = servicio.buscarPorId(id);
		mav.addObject("categorias",categorias);
		return mav;
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String EliminarCategoria(@PathVariable(name="id") int id) {
		servicio.eliminarCategorias(id);
		return "redirect:/categorias/listarTodo";
	}

}
