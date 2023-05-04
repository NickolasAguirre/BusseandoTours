package idat.edu.pe.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.servicio.PaqueteService;
import idat.edu.pe.servicio.UsuarioServicio;


@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio servicio;
	@Autowired
	private PaqueteService service;
	
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo, @Param("palabraClave")String palabraClave) {
		
		
		
		modelo.addAttribute("paquetess", service.listAll(palabraClave));
		
	return "principal";
	}
	
	
	
	
	@GetMapping("/usuarios")
	public String verPaginaDeUsuarios(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "index";
	}
}
