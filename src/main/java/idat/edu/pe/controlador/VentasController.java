package idat.edu.pe.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.message.callback.SecretKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.modelo.Usuario;
import idat.edu.pe.modelo.Cabezera;
import idat.edu.pe.repositorio.UsuarioRepositorio;
import idat.edu.pe.repositorio.VentasRepositorio;
import idat.edu.pe.servicio.PaqueteService;
import idat.edu.pe.servicio.PasajeroService;
import idat.edu.pe.servicio.UsuarioServicio;
import idat.edu.pe.servicio.VentasService;
import idat.edu.pe.util.reportes.BoletaExporterPDF;
import idat.edu.pe.util.reportes.FacturaExporterPDF;
import idat.edu.pe.util.reportes.VentaExporterExcel;
import idat.edu.pe.util.reportes.VentaExporterPDF;


@Controller
@RequestMapping("/ventas")
public class VentasController {
	
	@Autowired(required = false)
	private VentasService service;
	
	@Autowired(required = false)
	private PaqueteService servicepaq;
	
	@Autowired(required = false)
	private PasajeroService servicepas;
	
	@Autowired(required = false)
	private UsuarioServicio servicesus;

	@GetMapping("/listar")
	public String verPaginaDeVentas(Model modelo, @Param("palabraClave")String palabraClave) {
		modelo.addAttribute("ventas", service.listAll(palabraClave));
		
		return "ventas";
	}
	
	@GetMapping("/listarcompra/{id}")
	public String listaCompras(Model modelo,  @PathVariable("id") Long id) {
		Optional<Usuario> usuario = servicesus.findById(id);
	    if (usuario.isPresent()) {
	        List<Cabezera> ventas = usuario.get().getVentas();
	        modelo.addAttribute("ventas", ventas);
	        return "Compras";
	    } else {
	        return "";
	    }
	}
	
	
	@GetMapping("/nuevaBoleta")
	public String nuevaVentaB(@RequestParam("idPaquete") int idPaquete,@RequestParam("idPasajero") int idPasajero,@RequestParam("id") Long id, HttpServletRequest request, Model model) {
	    // Se busca objeto correspondiente utilizando el id recibido como parámetro
	    Paquete paquete = servicepaq.findById(idPaquete);
	    Pasajero pasajero = servicepas.findById(idPasajero);
	    Optional<Usuario> optionalUsuario = servicesus.findById(id);
   
	 // Se verifica si el usuario existe en la base de datos
	    if (optionalUsuario.isPresent()) {
	        Usuario usuario = optionalUsuario.get();

	        // Se crea una nueva venta y se establece la relación con el objeto
	        Cabezera cabezera = new Cabezera();
	        cabezera.getUsuario().add(usuario);
	        cabezera.setPaquete(paquete);
	        cabezera.setPasajero(pasajero);

	        // Se agrega la venta al modelo y se muestra el formulario correspondiente
	        model.addAttribute("cabezera", cabezera);
	        model.addAttribute("paquete", paquete);
	        model.addAttribute("pasajero", pasajero);
	        request.setAttribute("id", id.toString());
	        return "Boleta";
	    } else {
	        // Si el usuario no existe en la base de datos, mostrar un mensaje de error
	        model.addAttribute("mensajeError", "El usuario con id " + id + " no existe");
	        return "paginaDeError";
	    }
	}
	
	@GetMapping("/nuevaFactura")
	public String nuevaVentaF(@RequestParam("idPaquete") int idPaquete,@RequestParam("idPasajero") int idPasajero,@RequestParam("id") Long id, HttpServletRequest request, Model model) {
		// Se busca objeto correspondiente utilizando el id recibido como parámetro
	    Paquete paquete = servicepaq.findById(idPaquete);
	    Pasajero pasajero = servicepas.findById(idPasajero);
	    Optional<Usuario> optionalUsuario = servicesus.findById(id);
   
	 // Se verifica si el usuario existe en la base de datos
	    if (optionalUsuario.isPresent()) {
	        Usuario usuario = optionalUsuario.get();

	        // Se crea una nueva venta y se establece la relación con el objeto
	        Cabezera cabezera = new Cabezera();
	        cabezera.getUsuario().add(usuario);
	        cabezera.setPaquete(paquete);
	        cabezera.setPasajero(pasajero);

	        // Se agrega la venta al modelo y se muestra el formulario correspondiente
	        model.addAttribute("cabezera", cabezera);
	        model.addAttribute("paquete", paquete);
	        model.addAttribute("pasajero", pasajero);
	        request.setAttribute("id", id.toString());
	        return "Factura";
	    } else {
	        // Si el usuario no existe en la base de datos, mostrar un mensaje de error
	        model.addAttribute("mensajeError", "El usuario con id " + id + " no existe");
	        return "paginaDeError";
	    }
	}
	

		@PostMapping("/saveBoleta")
		public String guardarVentaBoleta(@ModelAttribute("cabezera") @Valid Cabezera cabezera, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		    if (result.hasErrors()) {
		        // Si hay errores de validación, se vuelve a mostrar el formulario con los errores correspondientes
		        return "AddVenta";
		    } else {
		        // Si no hay errores, se guarda la venta en la base de datos
		        service.insert(cabezera);
		        int IdVentas = cabezera.getIdVentas();
		        redirectAttributes.addFlashAttribute("mensaje", "La venta se ha guardado correctamente");
		        return "redirect:/ventas/CompraCerradaBoleta?IdVentas=" + IdVentas;
		        
		    }
		}
		
		@PostMapping("/saveFactura")
		public String guardarVentaFactura(@ModelAttribute("cabezera") @Valid Cabezera cabezera, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		    if (result.hasErrors()) {
		        // Si hay errores de validación, se vuelve a mostrar el formulario con los errores correspondientes
		        return "AddVenta";
		    } else {
		        // Si no hay errores, se guarda la venta en la base de datos
		        service.insert(cabezera);
		        int IdVentas = cabezera.getIdVentas();
		        redirectAttributes.addFlashAttribute("mensaje", "La venta se ha guardado correctamente");
		        return "redirect:/ventas/CompraCerradaFactura?IdVentas=" + IdVentas;
		        
		    }
		}
		
		@GetMapping("/CompraCerradaBoleta")
		public String Boleta(@RequestParam("IdVentas") int IdVentas, Model model) {
		    // Lógica para obtener el paquete y el pasajero
			Cabezera venta = service.findById(IdVentas);
		    model.addAttribute("venta", venta);
		    // Resto de la lógica
		    return "CompraCerradaBoleta";
		}
		
		@GetMapping("/CompraCerradaFactura")
		public String Factura(@RequestParam("IdVentas") int IdVentas, Model model) {
		    // Lógica para obtener el paquete y el pasajero
			Cabezera venta = service.findById(IdVentas);
		    model.addAttribute("venta", venta);
		    // Resto de la lógica
		    return "CompraCerradaFactura";
		}
		
		
		//la exportación de las ventas son con la fecha actual que se descarga y el Componente de Venta
		@GetMapping("/exportarPDF")
		public void exportarListadoDeVentasEnPDF(HttpServletResponse response) throws DocumentException, IOException {
			response.setContentType("application/pdf");
			
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String fechaActual = dateFormatter.format(new Date());
			
			String cabecera = "Content-Disposition";
			String palabraClave = "attachment; filename=Ventas_" + fechaActual + ".pdf";
			
			response.setHeader(cabecera, palabraClave);
			
			List<Cabezera> ventas = service.listAll();

			
			VentaExporterPDF exporter = new VentaExporterPDF(ventas);
			exporter.exportar(response);
			
		}
		
		
		//la exportación de las ventas en EXCEL
			@GetMapping("/exportarExcel")
			public void exportarListadoDeVentasEnExcel(HttpServletResponse response) throws DocumentException, IOException {
				response.setContentType("application/octet-steam");
				
				DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				String fechaActual = dateFormatter.format(new Date());
				
				String cabecera = "Content-Disposition";
				String palabraClave = "attachment; filename=Ventas_" + fechaActual + ".xlsx";
				
				response.setHeader(cabecera, palabraClave);
				
				List<Cabezera> ventas = service.listAll();

				
				VentaExporterExcel exporter = new VentaExporterExcel(ventas);
				exporter.exportar(response);
				
			}
			
		
			
			//la exportación de las ventas son con la fecha actual que se descarga y el Componente de Venta
			@GetMapping("/exportarPDFBoleta/{IdVentas}")
			
			public void exportarBoletaEnPDF(@PathVariable("IdVentas") int IdVentas, HttpServletResponse response) throws DocumentException, IOException {
				//busqueda por id
				Cabezera venta = service.findById(IdVentas);
				response.setContentType("application/pdf");
				
				DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				String fechaActual = dateFormatter.format(new Date());
				
				String cabecera = "Content-Disposition";
				String palabraClave = "attachment; filename=Boleta_" + fechaActual + ".pdf";
				
				response.setHeader(cabecera, palabraClave);
				
				//Mandar resultado de busqueda por id
				BoletaExporterPDF exporter = new BoletaExporterPDF(venta);
				exporter.exportar(response);
				
			}
			
			
			//la exportación de las ventas son con la fecha actual que se descarga y el Componente de Venta
			@GetMapping("/exportarPDFFactura/{IdVentas}")
			
			public void exportarFacturaEnPDF(@PathVariable("IdVentas") int IdVentas, HttpServletResponse response) throws DocumentException, IOException {
				//busqueda por id
				Cabezera venta = service.findById(IdVentas);
				response.setContentType("application/pdf");
				
				DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				String fechaActual = dateFormatter.format(new Date());
				
				String cabecera = "Content-Disposition";
				String palabraClave = "attachment; filename=Factura_" + fechaActual + ".pdf";
				
				response.setHeader(cabecera, palabraClave);
				
				//Mandar resultado de busqueda por id
				FacturaExporterPDF exporter = new FacturaExporterPDF(venta);
				exporter.exportar(response);
				
			}
			
			
			
			
}
