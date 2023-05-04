package idat.edu.pe.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.servicio.PaqueteService;
import idat.edu.pe.servicio.PasajeroService;


@Controller
@RequestMapping("/pasajero")
public class PasajeroController {
	
	@Autowired(required = false)
	private PasajeroService service;
	
	@Autowired(required = false)
	private PaqueteService servicepaq;
	

	@GetMapping("/listar")
	public String listaPasajeros(Model modelo) {
		modelo.addAttribute("pasajeros", service.findAll());
		return "pasajero";
	}
	
	@GetMapping("/{idPaquete}/agregar")
	public String crearPasajeros(Model modelo,@PathVariable("idPaquete") int idPaquete) {
		Paquete paquete = servicepaq.findById(idPaquete);
		Pasajero pasajero = new Pasajero();
		//modelo.addAttribute("titulo", "Formulario: Nuevo Paquete");
		
		modelo.addAttribute("paquete", paquete);
		modelo.addAttribute("pasajeros", pasajero);
		return "AddPasajero";
	}
	
	@PostMapping("/{idPaquete}/save")
	public String guardar(@Valid @ModelAttribute Pasajero pasajero, BindingResult result, Model model, RedirectAttributes attribute,@PathVariable("idPaquete") int idPaquete) {
		Paquete paquete = servicepaq.findById(idPaquete);
		if(result.hasErrors()) {
			//model.addAttribute("titulo", "Formulario: Nuevo Paquete");
			model.addAttribute("pasajeros", pasajero);
			System.out.println("Error en el formulario Pasajero");
			attribute.addFlashAttribute("error","Error en el formulario!");
			return "AddPasajero";
		}
		// Se agrega la venta al modelo y se muestra el formulario correspondiente
	    
	    
		service.insert(pasajero);
		int idPasajero = pasajero.getIdPasajero(); // Obtener el id del nuevo pasajero
		model.addAttribute("paquete", paquete);
		
		attribute.addFlashAttribute("success","Pasajero guardado con exito!");
		 return "redirect:/pago/" + idPaquete + "/metodoPago?idPasajero=" + idPasajero;
	}
	
	
}
