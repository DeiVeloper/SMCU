package mx.com.desoft.hidrogas.util;

import java.io.IOException;
import java.util.List;

import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;

public interface IReportes {

	public void generarTicketOrdenServicio(OrdenTrabajoDTO orden);

	public void generarReporteIncidencias(List<OrdenTrabajoDTO> lista) throws IOException;

	public void generarReporteTipoReparacion(List<OrdenTrabajoDTO> lista) throws IOException;

}
