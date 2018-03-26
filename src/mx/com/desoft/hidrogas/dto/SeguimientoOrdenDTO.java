package mx.com.desoft.hidrogas.dto;

import java.util.Date;
import java.util.List;

public class SeguimientoOrdenDTO {

	private int idSeguimiento;
	private int folio;
	private String trabajosRealizados;
	private String observaciones;
	private int reparacionMayor;
	private Date fechaReparacionMayor;
	private Date fechaRegistro;
	private int nominaRegistro;
	private String descripcionPU;
	private String descripcionPS;
	private List<SeguimientoOrdenPartesDTO> listaPartesUsadas;
	private List<SeguimientoOrdenPartesDTO> listaPartesSolicitadas;

	public SeguimientoOrdenDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeguimientoOrdenDTO(int idSeguimiento, int folio, String trabajosRealizados, String observaciones, int reparacionMayor,
			Date fechaReparacionMayor, Date fechaRegistro, int nominaRegistro, List<SeguimientoOrdenPartesDTO> listaPartesUsadas,
			List<SeguimientoOrdenPartesDTO> listaPartesSolicitadas, String descripcionPU, String descripcionPS) {
		super();
		this.idSeguimiento = idSeguimiento;
		this.folio = folio;
		this.trabajosRealizados = trabajosRealizados;
		this.observaciones = observaciones;
		this.reparacionMayor = reparacionMayor;
		this.fechaReparacionMayor = fechaReparacionMayor;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
		this.listaPartesUsadas = listaPartesUsadas;
		this.listaPartesSolicitadas = listaPartesSolicitadas;
		this.descripcionPU = descripcionPU;
		this.descripcionPS= descripcionPS;
	}

	public SeguimientoOrdenDTO(int idSeguimiento, int folio, Date fechaReparacionMayor) {
		super();
		this.idSeguimiento = idSeguimiento;
		this.folio = folio;
		this.fechaReparacionMayor = fechaReparacionMayor;
	}

	public SeguimientoOrdenDTO(int idSeguimiento, int folio, String trabajosRealizados, String observaciones, int reparacionMayor,
			Date fechaReparacionMayor, Date fechaRegistro, int nominaRegistro) {
		super();
		this.idSeguimiento = idSeguimiento;
		this.folio = folio;
		this.trabajosRealizados = trabajosRealizados;
		this.observaciones = observaciones;
		this.reparacionMayor = reparacionMayor;
		this.fechaReparacionMayor = fechaReparacionMayor;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}

	public int getIdSeguimiento() {
		return idSeguimiento;
	}

	public void setIdSeguimiento(int idSeguimiento) {
		this.idSeguimiento = idSeguimiento;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getTrabajosRealizados() {
		return trabajosRealizados;
	}

	public void setTrabajosRealizados(String trabajosRealizados) {
		this.trabajosRealizados = trabajosRealizados;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getReparacionMayor() {
		return reparacionMayor;
	}

	public void setReparacionMayor(int reparacionMayor) {
		this.reparacionMayor = reparacionMayor;
	}

	public Date getFechaReparacionMayor() {
		return fechaReparacionMayor;
	}

	public void setFechaReparacionMayor(Date fechaReparacionMayor) {
		this.fechaReparacionMayor = fechaReparacionMayor;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getNominaRegistro() {
		return nominaRegistro;
	}

	public void setNominaRegistro(int nominaRegistro) {
		this.nominaRegistro = nominaRegistro;
	}

	public List<SeguimientoOrdenPartesDTO> getListaPartesUsadas() {
		return listaPartesUsadas;
	}

	public void setListaPartesUsadas(List<SeguimientoOrdenPartesDTO> listaPartesUsadas) {
		this.listaPartesUsadas = listaPartesUsadas;
	}

	public String getDescripcionPU() {
		return descripcionPU;
	}

	public void setDescripcionPU(String descripcionPU) {
		this.descripcionPU = descripcionPU;
	}

	public String getDescripcionPS() {
		return descripcionPS;
	}

	public void setDescripcionPS(String descripcionPS) {
		this.descripcionPS = descripcionPS;
	}

	public List<SeguimientoOrdenPartesDTO> getListaPartesSolicitadas() {
		return listaPartesSolicitadas;
	}

	public void setListaPartesSolicitadas(List<SeguimientoOrdenPartesDTO> listaPartesSolicitadas) {
		this.listaPartesSolicitadas = listaPartesSolicitadas;
	}

}
