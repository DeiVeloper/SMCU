package mx.com.desoft.hidrogas.dto;

import java.io.Serializable;
import java.util.Date;

public class EconomicoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer economicoId;
	private Date fechaRegistro;
	private Integer nominaRegistro;
	private Integer reparaciones;
	
	public Integer getEconomicoId() {
		return economicoId;
	}
	public void setEconomicoId(Integer economicoId) {
		this.economicoId = economicoId;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getNominaRegistro() {
		return nominaRegistro;
	}
	public void setNominaRegistro(Integer nominaRegistro) {
		this.nominaRegistro = nominaRegistro;
	}
	public Integer getReparaciones() {
		return reparaciones;
	}
	public void setReparaciones(Integer reparaciones) {
		this.reparaciones = reparaciones;
	}

}
