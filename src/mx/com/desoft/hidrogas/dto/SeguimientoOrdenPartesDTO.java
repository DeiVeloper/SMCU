package mx.com.desoft.hidrogas.dto;

import java.util.Date;


public class SeguimientoOrdenPartesDTO {

	private int idRefaccion;
	private int folio;
	private int cantidad;
	private int idCatTipoRefaccion;
	private String descripcion;
	private int tipoRefaccionId;
	private Date fechaRegistro;
	private int nominaRegistro;
	private Boolean deSolicitadaAUsada;
	public SeguimientoOrdenPartesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeguimientoOrdenPartesDTO(int folio, int cantidad, int idCatTipoRefaccion, //String descripcion,
			int tipoRefaccionId, Date fechaRegistro, int nominaRegistro) {
		super();
		this.folio = folio;
		this.cantidad = cantidad;
		this.idCatTipoRefaccion = idCatTipoRefaccion;
		this.tipoRefaccionId = tipoRefaccionId;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}
	
	public SeguimientoOrdenPartesDTO(int cantidad, String descripcion) {
		super();
		this.cantidad = cantidad;
		this.descripcion = descripcion;
	}

	public SeguimientoOrdenPartesDTO(int idRefaccion, int folio, int cantidad, int idCatTipoRefaccion, //String descripcion,
			int tipoRefaccionId, Date fechaRegistro, int nominaRegistro, Boolean deSolicitadaAUsada) {
		super();
		this.idRefaccion = idRefaccion;
		this.folio = folio;
		this.cantidad = cantidad;
		this.idCatTipoRefaccion = idCatTipoRefaccion;
		this.tipoRefaccionId = tipoRefaccionId;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
		this.deSolicitadaAUsada = deSolicitadaAUsada;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdCatTipoRefaccion() {
		return idCatTipoRefaccion;
	}

	public void setIdCatTipoRefaccion(int idCatTipoRefaccion) {
		this.idCatTipoRefaccion = idCatTipoRefaccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTipoRefaccionId() {
		return tipoRefaccionId;
	}

	public void setTipoRefaccionId(int tipoRefaccionId) {
		this.tipoRefaccionId = tipoRefaccionId;
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

	public int getIdRefaccion() {
		return idRefaccion;
	}

	public void setIdRefaccion(int idRefaccion) {
		this.idRefaccion = idRefaccion;
	}

	public Boolean isDeSolicitadaAUsada() {
		return deSolicitadaAUsada;
	}

	public void setDeSolicitadaAUsada(Boolean deSolicitadaAUsada) {
		this.deSolicitadaAUsada = deSolicitadaAUsada;
	}

}