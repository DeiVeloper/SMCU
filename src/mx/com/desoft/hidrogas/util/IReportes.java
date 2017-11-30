package mx.com.desoft.hidrogas.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.print.PrintException;

import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;

public interface IReportes {

	public void generarTicketOrdenServicio(OrdenTrabajoDTO orden) throws UnsupportedEncodingException, PrintException, NullPointerException;

	public void generarReporteIncidencias(List<OrdenTrabajoDTO> lista) throws IOException;

	public void generarReporteTipoReparacion(List<OrdenTrabajoDTO> lista) throws IOException;

}
