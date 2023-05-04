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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.servicio.PaqueteService;


@Controller
@RequestMapping("/paquete")
public class PaqueteController {
	
	@Autowired(required = false)
	private PaqueteService service;
	
	

	@GetMapping("/listar2")
	public String listaPaquetes(Model modelo) {
		modelo.addAttribute("paquetes", service.findAll());
		return "paquete";
	}
	
	
	
	@GetMapping("/verpaquete/{idPaquete}")
	public String verPaquetePorId(Model modelo, @PathVariable("idPaquete") int idPaquete) {
	    Paquete paquete = service.findById(idPaquete);
	    if (paquete == null) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paquete no encontrado");
	    }
	    modelo.addAttribute("paquete", paquete);
	    return "verpaquete";
	}
	

	
	
	
	
	
	
	@GetMapping("/agregar2")
	public String crearPaquetes(Model modelo) {
		Paquete paquete = new Paquete();
		//modelo.addAttribute("titulo", "Formulario: Nuevo Paquete");
		modelo.addAttribute("paquete", paquete);
		
		return "frmCrear";
	}
	
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Paquete paquete, BindingResult result, Model model,@RequestParam(name="file", required = false) MultipartFile imagen, RedirectAttributes attribute) {
		
		if(result.hasErrors()) {
			//model.addAttribute("titulo", "Formulario: Nuevo Paquete");
			model.addAttribute("paquete", paquete);
			System.out.println("Error en el formulario Paquete");
			attribute.addFlashAttribute("error","Error en el formulario!");
			return "frmCrear";
		}
		
		//Inserccion de imagen a su respectivo folder, model y bd
		if(!imagen.isEmpty()) {
			//Path directorioImagenes = Paths.get("src//main//resources//static/img");
			//String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			//String rutaAbsoluta = "src//main//resources//static/img";
			String rutaAbsoluta = "C://Imagenes";
			
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				paquete.setImagen(imagen.getOriginalFilename());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		service.insert(paquete);
		attribute.addFlashAttribute("success","Paquete guardado con exito!");
		return "redirect:/paquete/listar2";
	}
	
	@GetMapping("/edit/{idPaquete}")
	public String editarPaquetes(@PathVariable("idPaquete")Integer idPaquete, Model modelo, RedirectAttributes attribute ) {
		Paquete paquete = null;
		if(idPaquete > 0) {
			paquete = service.findById(idPaquete);
			if(paquete == null) {
				attribute.addFlashAttribute("error","ATENCION: El ID del paquete no existe!");
				return "redirect:/paquete/listar2";
			}
			
		}else {
			attribute.addFlashAttribute("error","ATENCION: Error con el ID del paquete");
			return "redirect:/paquete/listar2";
		}
		
		modelo.addAttribute("paquete", paquete);
		
		return "frmCrear";
	}
	
	@GetMapping("/delete/{idPaquete}")
	public String eliminarPaquetes(@PathVariable("idPaquete")Integer idPaquete, RedirectAttributes attribute) {
		Paquete paquete = null;
		
		if(idPaquete > 0) {
			paquete = service.findById(idPaquete);
			if(paquete == null) {
				attribute.addFlashAttribute("error","ATENCION: El ID del paquete no existe!");
				return "redirect:/paquete/listar2";
			}
			
		}else {
			attribute.addFlashAttribute("error","ATENCION: Error con el ID del paquete");
			return "redirect:/paquete/listar2";
		}
		
		service.delete(idPaquete);
		
		attribute.addFlashAttribute("warning","Registro Eliminado con exito!");
		return "redirect:/paquete/listar2";
	}
	

	
}
