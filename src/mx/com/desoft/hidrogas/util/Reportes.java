package mx.com.desoft.hidrogas.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;

public class Reportes {
	
	public void generarReporteIncidencias(List<T> lista){
		T[] miarray = new T[lista.size()];
        miarray = lista.toArray(miarray);
		// First, it's needed to create the workbook
				HSSFWorkbook workbook = new HSSFWorkbook();

				// Then, sheets are created in the workbook
				HSSFSheet    sheet    = workbook.createSheet("My Sheet");

				for (int i = 0; i < miarray.length; i++) {
					T registros =  miarray[i];
					HSSFRow      row      = sheet.createRow(i);
					HSSFCell     cell = row.createCell(i);
					cell.setCellValue(registros.toString());
				}
					
				// Rows are created in the sheets

				// Last, every row is composed by cells
				

				// Writing the excel to output file
				try {
					OutputStream out = new FileOutputStream("src/main/resources/SimpleExcel.xls");
					workbook.write(out);
					workbook.close();
					out.flush();
					out.close();
				}
				catch (IOException e) {
					System.err.println("Error at file writing");
					e.printStackTrace();
				}
	}
	
	public void generarReporteTipoReparacion(){
		// First, it's needed to create the workbook
				HSSFWorkbook workbook = new HSSFWorkbook();

				// Then, sheets are created in the workbook
				HSSFSheet    sheet    = workbook.createSheet("My Sheet");

				// Rows are created in the sheets
				HSSFRow      row      = sheet.createRow(0);

				// Last, every row is composed by cells
				HSSFCell     cell = row.createCell(0);
				cell.setCellValue("Hello, World!");

				// Writing the excel to output file
				try {
					OutputStream out = new FileOutputStream("src/main/resources/SimpleExcel.xls");
					workbook.write(out);
					workbook.close();
					out.flush();
					out.close();
				}
				catch (IOException e) {
					System.err.println("Error at file writing");
					e.printStackTrace();
				}
	}

	

}
