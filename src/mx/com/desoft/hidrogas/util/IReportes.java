package mx.com.desoft.hidrogas.util;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public interface IReportes {

	public void generarTicketOrdenServicio(List<T> lista);

	public void generarReporteIncidencias(List<T> lista);

	public void generarReporteTipoReparacion(List<T> lista);

}
