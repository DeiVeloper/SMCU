package mx.com.desoft.hidrogas.util;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenPartesDTO;

@Component
public class Reportes implements IReportes, Printable {

	private static final Logger log = Logger.getLogger(Reportes.class);
	private final String IMPRESORA = "EPSON TM-U220 Receipt";

	@Override
	public void generarTicketOrdenServicio(OrdenTrabajoDTO orden) throws UnsupportedEncodingException, PrintException, NullPointerException{
		StringBuilder ticket = new StringBuilder();
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("Fecha de Impresi"+Constantes.o+"n: " + DateUtil.getStringFromDate(new Date())+ "\n");
		ticket.append("Folio Orden: " + orden.getFolio() + "\n");
		ticket.append("Fecha Orden: " + DateUtil.getStringFromDate(orden.getFechaRegistro()) + "\n");
		ticket.append("Econ"+Constantes.o+"mico: " + orden.getEconomicoId() + "\n");
		ticket.append("Empleado: " + orden.getNombreOperador().concat(" ")
		.concat(orden.getApellidoPatOperador().concat(" ").concat(orden.getApellidoMatOperador())) + "\n");
		ticket.append("Tipo Neccesidad: "+ orden.getDescripcionTipoNecesidad() + "\n");
		ticket.append("Falla: " + orden.getFallaMecanica() + "\n");
		ticket.append("Trabajo realizado: " + orden.getSeguimiento().getTrabajosRealizados() + "\n");
		ticket.append("Refacciones: \n");
		for(SeguimientoOrdenPartesDTO refaccion : orden.getListaRefaccionesDTO()) {
			ticket.append(refaccion.getCantidad() + " " + refaccion.getDescripcion() + " \n");
		}
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");
		printString(IMPRESORA, ticket.toString());
	}

	@Override
	public void generarReporteIncidencias(List<OrdenTrabajoDTO> lista) throws IOException{
		OrdenTrabajoDTO[] miarray = new OrdenTrabajoDTO[lista.size()];
        miarray = lista.toArray(miarray);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet    sheet    = workbook.createSheet("Incidencias");

		String[] headers = new String[]{"Folio",
				"Mecanico", "Economico", "Operador", "Falla Mecanica",
					"Fecha Registro", "Kilometraje", "Tipo Necesidad", "Observaciones", "Trabajos Realizados"};
		HSSFRow headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; ++i) {
			String header = headers[i];
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellValue(header);
        }

		for (int i = 0; i < miarray.length; i++) {
			OrdenTrabajoDTO registro =  miarray[i];
			HSSFRow row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(registro.getFolio());
			row.createCell(1).setCellValue(registro.getMecanico().getNombreEmpleado());
			row.createCell(2).setCellValue(registro.getEconomicoId());
			row.createCell(3).setCellValue(registro.getNombreOperador().concat(" ")
					.concat(registro.getApellidoPatOperador()).concat(" ").concat(registro.getApellidoMatOperador()));
			row.createCell(4).setCellValue(registro.getFallaMecanica());
			row.createCell(5).setCellValue(DateUtil.convertirFechaToString(registro.getFechaRegistro()));
			row.createCell(6).setCellValue(registro.getKilometraje());
			row.createCell(7).setCellValue(registro.getDescripcionTipoNecesidad());
			row.createCell(8).setCellValue(registro.getSeguimiento().getObservaciones());
			row.createCell(9).setCellValue(registro.getSeguimiento().getTrabajosRealizados());
		}

		OutputStream out = new FileOutputStream("C:/Reportes/IncidenciasDel"+DateUtil.convertirFechaToString(new Date())+".xls");
		workbook.write(out);
		workbook.close();
		out.flush();
		out.close();
	}

	@Override
	public void generarReporteTipoReparacion(List<OrdenTrabajoDTO> lista) throws IOException{
		OrdenTrabajoDTO[] miarray = new OrdenTrabajoDTO[lista.size()];
        miarray = lista.toArray(miarray);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Reparaciones");

		String[] headers = new String[]{"Economico","Tipo Necesidad", "Total"};
		HSSFRow headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; ++i) {
			String header = headers[i];
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellValue(header);
        }

		for (int i = 0; i < miarray.length; i++) {
			OrdenTrabajoDTO registro =  miarray[i];
			HSSFRow row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(registro.getEconomicoId());
			row.createCell(1).setCellValue(registro.getDescripcionTipoNecesidad());
			row.createCell(2).setCellValue(registro.getTotal());
		}

		OutputStream out = new FileOutputStream("C:/Reportes/Reparaciones.xls");
		workbook.write(out);
		workbook.close();
		out.flush();
		out.close();
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		return 0;
	}

	private void printString(String printerName, String text) throws UnsupportedEncodingException, PrintException, NullPointerException {
			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
			PrintService service = findPrintService(printerName, printService);
			DocPrintJob job = service.createPrintJob();
			byte[] bytes;
			bytes = text.getBytes("CP437");
			Doc doc = new SimpleDoc(bytes, flavor, null);
			job.print(doc, null);

	}

	private PrintService findPrintService(String printerName,PrintService[] services) {
		for (PrintService service : services) {
			log.info("Nombre: " + service.getName());
			if (service.getName().equalsIgnoreCase(printerName)) {
				return service;
			}
		}
		return null;
	}

//	public List<String> getPrinters(){
//
//		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
//		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
//
//		PrintService printServices[] = PrintServiceLookup.lookupPrintServices(
//				flavor, pras);
//
//		List<String> printerList = new ArrayList<String>();
//		for(PrintService printerService: printServices){
//			printerList.add( printerService.getName());
//		}
//
//		return printerList;
//	}

}