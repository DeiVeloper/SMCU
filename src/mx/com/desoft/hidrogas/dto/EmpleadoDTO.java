package mx.com.desoft.hidrogas.dto;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer nominaEmpleado;
	private String nombreEmpleado;
	private String apellidoPatEmpleado;
	private String apellidoMatEmpleado;
	private String password;
	private Integer economicoId;
	private Date fechaRegistro;
	private Integer nominaRegistro;
	private Integer tipoEmpleadoId;
	private String descripcionTipoEmpleado;
	
	public EmpleadoDTO() {
	}
	
	public EmpleadoDTO(Integer nominaEmpleado, String nombreEmpleado, String apellidoPatEmpleado,
			String apellidoMatEmpleado, String password, Integer economicoId, Date fechaRegistro,
			Integer nominaRegistro, Integer tipoEmpleadoId, String descripcionTipoEmpleado) {
		this.nominaEmpleado = nominaEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.apellidoPatEmpleado = apellidoPatEmpleado;
		this.apellidoMatEmpleado = apellidoMatEmpleado;
		this.password = password;
		this.economicoId = economicoId;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
		this.tipoEmpleadoId = tipoEmpleadoId;
		this.descripcionTipoEmpleado = descripcionTipoEmpleado;
	}



	public EmpleadoDTO(Integer nominaEmpleado, String nombreEmpleado, Integer economicoId, Integer tipoEmpleadoId) {
		this.nominaEmpleado = nominaEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.economicoId = economicoId;
		this.tipoEmpleadoId = tipoEmpleadoId;
	}

	public Integer getNominaEmpleado() {
		return nominaEmpleado;
	}
	
	public void setNominaEmpleado(Integer nominaEmpleado) {
		this.nominaEmpleado = nominaEmpleado;
	}
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	
	public String getApellidoPatEmpleado() {
		return apellidoPatEmpleado;
	}
	
	public void setApellidoPatEmpleado(String apellidoPatEmpleado) {
		this.apellidoPatEmpleado = apellidoPatEmpleado;
	}
	
	public String getApellidoMatEmpleado() {
		return apellidoMatEmpleado;
	}
	
	public void setApellidoMatEmpleado(String apellidoMatEmpleado) {
		this.apellidoMatEmpleado = apellidoMatEmpleado;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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

	public Integer getEconomicoId() {
		return economicoId;
	}

	public void setEconomicoId(Integer economicoId) {
		this.economicoId = economicoId;
	}

	public Integer getTipoEmpleadoId() {
		return tipoEmpleadoId;
	}

	public void setTipoEmpleadoId(Integer tipoEmpleadoId) {
		this.tipoEmpleadoId = tipoEmpleadoId;
	}

	public String getDescripcionTipoEmpleado() {
		return descripcionTipoEmpleado;
	}

	public void setDescripcionTipoEmpleado(String descripcionTipoEmpleado) {
		this.descripcionTipoEmpleado = descripcionTipoEmpleado;
	}
	
}
