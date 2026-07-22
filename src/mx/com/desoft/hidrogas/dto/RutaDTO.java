package mx.com.desoft.hidrogas.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class RutaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer rutaId;
	private Date fechaRegistro;
	private Integer nominaRegistro;
	private Integer reparaciones;
	private String nombreRuta;
	
	public RutaDTO() {
	}
	
	
	public RutaDTO(Integer rutaId) {
		this.rutaId = rutaId;
	}
	
	

	public RutaDTO(Integer rutaId, Date fechaRegistro, Integer nominaRegistro, Integer reparaciones,
			String nombreRuta) {
		this.rutaId = rutaId;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
		this.reparaciones = reparaciones;
		this.nombreRuta = nombreRuta;
	}


	public Integer getRutaId() {
		return rutaId;
	}
	public void setRutaId(Integer rutaId) {
		this.rutaId = rutaId;
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
	public String getNombreRuta() {
		return nombreRuta;
	}
	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}

}
