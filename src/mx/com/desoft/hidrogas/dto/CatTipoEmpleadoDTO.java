package mx.com.desoft.hidrogas.dto;

import java.io.Serializable;
import java.util.Date;

public class CatTipoEmpleadoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer tipoEmpleadoId;
	private String descripcion;
	private Date fechaRegistro;
	private Integer nominaRegistro;
	
	public Integer getTipoEmpleadoId() {
		return tipoEmpleadoId;
	}
	public void setTipoEmpleadoId(Integer tipoEmpleadoId) {
		this.tipoEmpleadoId = tipoEmpleadoId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
}
