package mx.com.desoft.hidrogas.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;

public class Reportes {

	private static final Logger log = Logger.getLogger(Reportes.class);

	public void generarReporteIncidencias(List<T> lista){
		T[] miarray = new T[lista.size()];
        miarray = lista.toArray(miarray);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet    sheet    = workbook.createSheet("My Sheet");

		for (int i = 0; i < miarray.length; i++) {
			T registros =  miarray[i];
			HSSFRow      row      = sheet.createRow(i);
			HSSFCell     cell = row.createCell(i);
			cell.setCellValue(registros.toString());
		}

		try {
			OutputStream out = new FileOutputStream("src/main/resources/SimpleExcel.xls");
			workbook.write(out);
			workbook.close();
			out.flush();
			out.close();
		}
		catch (IOException e) {
			log.error("Error at file writing",e);
		}
	}

	public void generarReporteTipoReparacion(){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("My Sheet");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("Hello, World!");
		try {
			OutputStream out = new FileOutputStream("SimpleExcel.xls");
			workbook.write(out);
			workbook.close();
			out.flush();
			out.close();
		}
		catch (IOException e) {
			log.error("Error at file writing",e);
		}
	}

}
