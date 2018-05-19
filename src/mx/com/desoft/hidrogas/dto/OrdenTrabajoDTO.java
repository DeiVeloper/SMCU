package mx.com.desoft.hidrogas.dto;

import java.util.Date;

public class OrdenTrabajoDTO {

	private int folio;
	private int economicoId;
	private int nominaOperador;
	private String nombreOperador;
	private String apellidoPatOperador;
	private String apellidoMatOperador;
	private Long tipoNecesidadId;
	private String descripcionTipoNecesidad;
	private int kilometraje;
	private String fallaMecanica;
	private int estatusOrden;
	private Date fechaRegistro;
	private int nominaRegistro;
	private Date fechaInicio;
	private Date fechaFin;
	private EmpleadoDTO mecanico;
	private SeguimientoOrdenDTO seguimiento;
	private Integer total;

	public OrdenTrabajoDTO() {
		super();
	}

	public OrdenTrabajoDTO(int folio, int economicoId, int nominaRegistro) {
		super();
		this.folio = folio;
		this.economicoId = economicoId;
		this.nominaRegistro = nominaRegistro;
	}

	public OrdenTrabajoDTO(int folio, int economicoId, int nominaOperador, String nombreOperador,
			String apellidoPatOperador, String apellidoMatOperador, Long tipoNecesidadId, int kilometraje,
			String fallaMecanica, int estatusOrden, Date fechaRegistro, int nominaRegistro) {
		super();
		this.folio = folio;
		this.economicoId = economicoId;
		this.nominaOperador = nominaOperador;
		this.nombreOperador = nombreOperador;
		this.apellidoPatOperador = apellidoPatOperador;
		this.apellidoMatOperador = apellidoMatOperador;
		this.tipoNecesidadId = tipoNecesidadId;
		this.kilometraje = kilometraje;
		this.fallaMecanica = fallaMecanica;
		this.estatusOrden = estatusOrden;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}

	public OrdenTrabajoDTO(int folio, int economicoId, int nominaOperador, String nombreOperador,
			String apellidoPatOperador, String apellidoMatOperador, String descripcionTipoNecesidad, int kilometraje,
			String fallaMecanica, int estatusOrden, Date fechaRegistro, EmpleadoDTO mecanico, SeguimientoOrdenDTO seguimiento) {
		super();
		this.folio = folio;
		this.economicoId = economicoId;
		this.nominaOperador = nominaOperador;
		this.nombreOperador = nombreOperador;
		this.apellidoPatOperador = apellidoPatOperador;
		this.apellidoMatOperador = apellidoMatOperador;
		this.descripcionTipoNecesidad = descripcionTipoNecesidad;
		this.kilometraje = kilometraje;
		this.fallaMecanica = fallaMecanica;
		this.estatusOrden = estatusOrden;
		this.fechaRegistro = fechaRegistro;
		this.mecanico =mecanico;
		this.seguimiento = seguimiento;
	}
	
	

	public OrdenTrabajoDTO(int folio, Integer economicoId, int nominaOperador, String nombreOperador,
			String apellidoPatOperador, String apellidoMatOperador, Long tipoNecesidadId, String descripcionTipoNecesidad,
			int kilometraje, String fallaMecanica, int estatusOrden, Date fechaRegistro, int seguimiento) {
		this.folio = folio;
		this.economicoId = economicoId;
		this.nominaOperador = nominaOperador;
		this.nombreOperador = nombreOperador;
		this.apellidoPatOperador = apellidoPatOperador;
		this.apellidoMatOperador = apellidoMatOperador;
		this.tipoNecesidadId = tipoNecesidadId;
		this.descripcionTipoNecesidad = descripcionTipoNecesidad;
		this.kilometraje = kilometraje;
		this.fallaMecanica = fallaMecanica;
		this.estatusOrden = estatusOrden;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = seguimiento;
	
	}

	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public int getEconomicoId() {
		return economicoId;
	}
	public void setEconomicoId(int economicoId) {
		this.economicoId = economicoId;
	}
	public int getNominaOperador() {
		return nominaOperador;
	}
	public void setNominaOperador(int nominaOperador) {
		this.nominaOperador = nominaOperador;
	}
	public String getNombreOperador() {
		return nombreOperador;
	}
	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}
	public String getApellidoPatOperador() {
		return apellidoPatOperador;
	}
	public void setApellidoPatOperador(String apellidoPatOperador) {
		this.apellidoPatOperador = apellidoPatOperador;
	}
	public String getApellidoMatOperador() {
		return apellidoMatOperador;
	}
	public void setApellidoMatOperador(String apellidoMatOperador) {
		this.apellidoMatOperador = apellidoMatOperador;
	}
	public Long getTipoNecesidadId() {
		return tipoNecesidadId;
	}
	public void setTipoNecesidadId(Long tipoNecesidadId) {
		this.tipoNecesidadId = tipoNecesidadId;
	}

	public String getDescripcionTipoNecesidad() {
		return descripcionTipoNecesidad;
	}

	public void setDescripcionTipoNecesidad(String descripcionTipoNecesidad) {
		this.descripcionTipoNecesidad = descripcionTipoNecesidad;
	}

	public int getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}
	public String getFallaMecanica() {
		return fallaMecanica;
	}
	public void setFallaMecanica(String fallaMecanica) {
		this.fallaMecanica = fallaMecanica;
	}
	public int getEstatusOrden() {
		return estatusOrden;
	}
	public void setEstatusOrden(int estatusOrden) {
		this.estatusOrden = estatusOrden;
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public EmpleadoDTO getMecanico() {
		return mecanico;
	}

	public void setMecanico(EmpleadoDTO mecanico) {
		this.mecanico = mecanico;
	}

	public SeguimientoOrdenDTO getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(SeguimientoOrdenDTO seguimiento) {
		this.seguimiento = seguimiento;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
