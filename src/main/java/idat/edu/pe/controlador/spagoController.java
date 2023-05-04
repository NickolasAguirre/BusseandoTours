package idat.edu.pe.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.servicio.PaqueteService;
import idat.edu.pe.servicio.PasajeroService;


@Controller
@RequestMapping("/pago")
public class spagoController {
	
	@Autowired(required = false)
	private PaqueteService service;
	
	
	@Autowired(required = false)
	private PasajeroService pasajeroservice;
	
	
	
	
	
	
	
	@GetMapping("/{idPaquete}/metodoPago")
	public String metodoPago(@PathVariable("idPaquete") int idPaquete, @RequestParam("idPasajero") int idPasajero, Model model) {
	    // Lógica para obtener el paquete y el pasajero
		Paquete paquete = service.findById(idPaquete);
		Pasajero pasajero = pasajeroservice.findById(idPasajero);
	    model.addAttribute("paquetedetail", paquete);
	    model.addAttribute("pasajero", pasajero);
	    // Resto de la lógica
	    return "spago";
	}
	
	
	
	
	@GetMapping("/{idPaquete}/{idPasajero}/efectivo")
	public String Efectivo(@PathVariable("idPaquete") int idPaquete, Model model, @PathVariable("idPasajero") int idPasajero) {
	    Paquete paquete = service.findById(idPaquete);
	    Pasajero pasajero = pasajeroservice.findById(idPasajero);
	    model.addAttribute("paqueteefectivo", paquete);
	    model.addAttribute("pasajero", pasajero);
	    return "mdppagoefectivo";
	}
	
	@GetMapping("/{idPaquete}/{idPasajero}/tarjeta")
	public String Tarjeta(@PathVariable("idPaquete") int idPaquete, Model model, @PathVariable("idPasajero") int idPasajero) {
	    Paquete paquete = service.findById(idPaquete);
	    Pasajero pasajero = pasajeroservice.findById(idPasajero);
	    model.addAttribute("paquetetarjeta", paquete);
	    model.addAttribute("pasajero", pasajero);
	    return "mdptarjeta";
	}
	
	@GetMapping("/{idPaquete}/{idPasajero}/yape")
	public String Yape(@PathVariable("idPaquete") int idPaquete, Model model, @PathVariable("idPasajero") int idPasajero) {
	    Paquete paquete = service.findById(idPaquete);
	    Pasajero pasajero = pasajeroservice.findById(idPasajero);
	    model.addAttribute("paqueteyape", paquete);
	    model.addAttribute("pasajero", pasajero);
	    return "mdpyape";
	}
}
