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

import certus.edu.pe.modelo.Pedidos;
import certus.edu.pe.servicios.PedidosServicio;


@Controller
@RequestMapping("/pedidos")
public class PedidosWebController {

	@Autowired
	private PedidosServicio servicio;
	
	@RequestMapping("/listarTodo")
	public String listarPedidos (Model model) {
		
		List <Pedidos> listaPedidos = servicio.buscarTodo();
		System.out.println("LISTA DE PEDIDOS : " + listaPedidos);
		model.addAttribute("listaPedidos", listaPedidos);
		return "/moduloPedidos/listarTodo";
	}
	
	@RequestMapping("/nuevo")
	public String nuevaPedido(Model model) {
		Pedidos pedido = new Pedidos ();
		model.addAttribute("pedido", pedido);
		return "/moduloPedidos/nuevaPedido";	
	}
	
	@RequestMapping(value ="/guardar", method= RequestMethod.POST)
	public String crearPedido(@ModelAttribute("pedido") @Valid Pedidos pedido, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) {
		    	return "/moduloPedidos/nuevaPedido";
		    }
			servicio.crear(pedido);
		    return "redirect:/pedidos/listarTodo";
		
	}
	
	 @RequestMapping(value ="/actualizar/{id}")
	public ModelAndView editarPedido(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView("/moduloPedidos/editarPedido");
		Pedidos pedido = servicio.buscarPorId(id);
		mav.addObject("pedido",pedido);
		return mav;
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String EliminarPedido(@PathVariable(name="id") int id) {
		servicio.eliminarPedidos(id);
		return "redirect:/pedidos/listarTodo";
	}
	
}
