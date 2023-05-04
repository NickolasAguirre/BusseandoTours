package idat.edu.pe.util.reportes;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

import idat.edu.pe.modelo.Cabezera;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.servicio.PaqueteService;
import idat.edu.pe.servicio.PasajeroService;
import idat.edu.pe.servicio.VentasService;


public class BoletaExporterPDF {
	
	
	
	private Cabezera venta;
	
	
	public BoletaExporterPDF(Cabezera venta) {
		
		this.venta = venta;
	}


	// Crea la Cabecera de la tabla
			private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
				PdfPCell celda = new PdfPCell();

				celda.setBackgroundColor(Color.gray);
				celda.setPadding(4);

				Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
				fuente.setColor(Color.WHITE);

				celda.setPhrase(new Phrase("Código", fuente));
				tabla.addCell(celda);

				celda.setPhrase(new Phrase("Descripción", fuente));
				tabla.addCell(celda);

				celda.setPhrase(new Phrase("Precio Unitario", fuente));
				tabla.addCell(celda);

				celda.setPhrase(new Phrase("Total", fuente));
				tabla.addCell(celda);


			}

			// Trae los datos de la base de datos
			private void escribirDatosDeLaTabla(PdfPTable tabla, Cabezera venta) {
					
				Paquete paquete = venta.getPaquete(); // obtener el objeto Paquete
				tabla.addCell(paquete.getIdPaquete().toString());
				tabla.addCell(paquete.getDescripcion().toString());
				//Operaciones con el precio
				tabla.addCell(paquete.getPrecioUnidad().toString());
				tabla.addCell(paquete.getPrecioUnidad().toString());	
					
				
			}

			public void exportar(HttpServletResponse response) throws DocumentException, IOException {
				Document documento = new Document(PageSize.A4);
				
				PdfWriter writer = PdfWriter.getInstance(documento, response.getOutputStream());
				documento.open();

				Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				fuente.setColor(Color.gray);
				fuente.setSize(17);
				
				Paragraph titulo = new Paragraph("BOLETA DE VENTA", fuente);
				Paragraph rucEmpresa = new Paragraph ("RUC: 20556106909", fuente) ;
				Paragraph id = new Paragraph ("EB01-" + venta.getIdVentas(), fuente);
				titulo.setAlignment(Paragraph.ALIGN_RIGHT);
				rucEmpresa.setAlignment(Paragraph.ALIGN_RIGHT);
				id.setAlignment(Paragraph.ALIGN_RIGHT);
				documento.add(titulo);
				documento.add(rucEmpresa);
				documento.add(id);
				
				
				
				 // Obtener el objeto PdfContentByte para la escritura directa en la página
			    PdfContentByte cb = writer.getDirectContent();

			    // Agregar el contenido a mostrar en la esquina superior izquierda del PDF
			    
			    String empresa = " BUSSEANDO TOURS S.A.C" ;
			    String direccion = " DIRECCION" ;
			    String distrito = " LIMA-LIMA" ;
			    
			    cb.beginText();
			    
			    //Posicion del texto Superior Izquierdo
			    cb.setFontAndSize(FontFactory.getFont(FontFactory.HELVETICA_BOLD).getBaseFont(), 14);
			    cb.showTextAligned(Element.ALIGN_LEFT, empresa, 36, 788, 0);
			    cb.showTextAligned(Element.ALIGN_LEFT, direccion, 36, 770, 0);
			    cb.showTextAligned(Element.ALIGN_LEFT, distrito, 36, 752, 0);
			    
			    cb.endText();
				
			    //Centro de hoja(Datos Boleta)
			    String fechaEmision = "Fecha de Emisión: " + venta.getFechaEmision();
			    String DNI = 		  "DNI             : " + venta.getNumeroDoc();
			    String nombre = 	  "Nombre          : " + venta.getNombre();
			    String moneda = 	  "Tipo Moneda     : NUEVOS SOLES";
			    String obs = 		  "Observación     : VENTA Y COMPRA DE PAQUETE TURISTICO";
			    PdfContentByte cb2 = writer.getDirectContent();
			    BaseFont fuente2 = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			    cb2.beginText();
			    cb2.setFontAndSize(fuente2, 14);
			    
			    cb2.setFontAndSize(FontFactory.getFont(FontFactory.COURIER).getBaseFont(), 14);
			    cb2.showTextAligned(Element.ALIGN_LEFT, fechaEmision, 36, 675, 0);
			    cb2.showTextAligned(Element.ALIGN_LEFT, DNI, 36, 650, 0);
			    cb2.showTextAligned(Element.ALIGN_LEFT, nombre, 36, 625, 0);
			    cb2.showTextAligned(Element.ALIGN_LEFT, moneda, 36, 600, 0);
			    cb2.showTextAligned(Element.ALIGN_LEFT, obs, 36, 575, 0);
			    cb2.endText();

				
			    
			    //Detalle(Centro)
				PdfPTable tabla = new PdfPTable(4);
				tabla.setWidthPercentage(100);
				tabla.setSpacingBefore(220);
				tabla.setWidths(new float[] { 2f, 3f, 3f, 3f });
				tabla.setWidthPercentage(110);
				
				
				
				
				 //DETALLE PRECIO(Inferior derecha)
			    PdfContentByte cb3 = writer.getDirectContent();
			    
			    Paquete paquete = venta.getPaquete(); // obtener el objeto Paquete
		
			    
			    
			    Double igv = paquete.getPrecioUnidad()*0.18;
			    DecimalFormat df = new DecimalFormat("#.##");
			    Double subtotal = paquete.getPrecioUnidad()-igv;
			    Double total= 0+paquete.getPrecioUnidad();
			    
			    cb3.beginText();
			    
			    //Posicion del texto Superior Izquierdo
			    cb3.setFontAndSize(FontFactory.getFont(FontFactory.COURIER).getBaseFont(), 14);
			    cb3.showTextAligned(Element.ALIGN_RIGHT, "SUB TOTAL: " + subtotal.toString(), 570, 418, 0);
			    cb3.showTextAligned(Element.ALIGN_RIGHT, "IGV:  " + df.format(igv), 570, 400, 0);
			    cb3.showTextAligned(Element.ALIGN_RIGHT, "TOTAL: " + total.toString(), 570, 382, 0);
			    
			    cb3.endText();
			    
			    
			    
			    

				escribirCabeceraDeLaTabla(tabla);
				escribirDatosDeLaTabla(tabla, venta);

				documento.add(tabla);
				documento.close();
			}
	}
