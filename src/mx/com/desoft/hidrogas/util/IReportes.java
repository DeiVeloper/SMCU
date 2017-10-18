package mx.com.desoft.hidrogas.util;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;

public interface IReportes {

	public void generarTicketOrdenServicio(OrdenTrabajoDTO orden);

	public void generarReporteIncidencias(List<OrdenTrabajoDTO> lista) throws IOException;

	public void generarReporteTipoReparacion(List<T> lista) throws IOException;

}
