package mx.com.desoft.hidrogas.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class EconomicoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer economicoId;
	private Date fechaRegistro;
	private Integer nominaRegistro;
	private Integer reparaciones;
	private String nombreChofer;
	
	public EconomicoDTO() {
	}
	
	
	public EconomicoDTO(Integer economicoId) {
		this.economicoId = economicoId;
	}
	
	

	public EconomicoDTO(Integer economicoId, Date fechaRegistro, Integer nominaRegistro, Integer reparaciones,
			String nombreChofer) {
		this.economicoId = economicoId;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
		this.reparaciones = reparaciones;
		this.nombreChofer = nombreChofer;
	}


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
	public String getNombreChofer() {
		return nombreChofer;
	}
	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

}
