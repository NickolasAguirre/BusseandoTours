package idat.edu.pe.util.reportes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import idat.edu.pe.modelo.Cabezera;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;

public class VentaExporterExcel {

	private XSSFWorkbook libro;
	private XSSFSheet hoja;

	private List<Cabezera> listaVentas;


	public VentaExporterExcel(List<Cabezera> listaVentas) {
		this.listaVentas = listaVentas;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Ventas");
	}

	
		private void escribirCabeceraDeTabla() {
			Row fila = hoja.createRow(0);
			
			CellStyle estilo = libro.createCellStyle();
			XSSFFont fuente = libro.createFont();
			fuente.setBold(true);
			fuente.setFontHeight(16);
			estilo.setFont(fuente);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue("ID");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue("Fecha de Emisión");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue("Tipo de Documento");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(3);
			celda.setCellValue("Numero de Documento");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(4);
			celda.setCellValue("Nombre");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(5);
			celda.setCellValue("PAQUETE");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(6);
			celda.setCellValue("DESTINO PAQUETE");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(7);
			celda.setCellValue("PASAJERO");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(8);
			celda.setCellValue("NOMBRE PASAJERO");
			celda.setCellStyle(estilo);
			
		}
		
		private void escribirDatosDeLaTabla() {
			int numeroFilas = 1;
			
			CellStyle estilo = libro.createCellStyle();
			XSSFFont fuente = libro.createFont();
			fuente.setFontHeight(14);
			estilo.setFont(fuente);
			
			for(Cabezera venta : listaVentas){
				Row fila = hoja.createRow(numeroFilas ++);
				
				Cell celda = fila.createCell(0);
				celda.setCellValue(venta.getIdVentas());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estilo);
				
				celda = fila.createCell(1);
				celda.setCellValue(venta.getFechaEmision().toString());
				hoja.autoSizeColumn(1);
				celda.setCellStyle(estilo);
				
				celda = fila.createCell(2);
				celda.setCellValue(venta.getTipoDoc());
				hoja.autoSizeColumn(2);
				celda.setCellStyle(estilo);
				
				celda = fila.createCell(3);
				celda.setCellValue(venta.getNumeroDoc());
				hoja.autoSizeColumn(3);
				celda.setCellStyle(estilo);
				
				celda = fila.createCell(4);
				celda.setCellValue(venta.getNombre());
				hoja.autoSizeColumn(4);
				celda.setCellStyle(estilo);
				
				Paquete paquete = venta.getPaquete(); // obtener el objeto Paquete
				
				celda = fila.createCell(5);
				celda.setCellValue(paquete.getIdPaquete());
				hoja.autoSizeColumn(5);
				celda.setCellStyle(estilo);
				
				celda = fila.createCell(6);
				celda.setCellValue(paquete.getLugarDestino());
				hoja.autoSizeColumn(6);
				celda.setCellStyle(estilo);
		        
		        Pasajero pasajero = venta.getPasajero(); // obtener el objeto Pasajero
		        
		        celda = fila.createCell(7);
				celda.setCellValue(pasajero.getIdPasajero());
				hoja.autoSizeColumn(7);
				celda.setCellStyle(estilo);
				
				celda = fila.createCell(8);
				celda.setCellValue(pasajero.getNombre());
				hoja.autoSizeColumn(8);
				celda.setCellStyle(estilo);
				
				
				
			}
		}
		public void exportar(HttpServletResponse response) throws IOException {
		    escribirCabeceraDeTabla();
		    escribirDatosDeLaTabla();

		    ServletOutputStream outPutStream = response.getOutputStream();
		    libro.write(outPutStream);

		    outPutStream.close();
		}
}
