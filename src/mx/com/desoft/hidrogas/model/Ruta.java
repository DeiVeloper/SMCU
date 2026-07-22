package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the rutas database table.
 * 
 */
@Entity
@Table(name="rutas")
public class Ruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ruta_id")
	private Integer rutaId;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="nomina_registro")
	private int nominaRegistro;

	private int reparaciones;

	public Ruta() {
	}
	
	public Ruta(Integer rutaId, Date fechaRegistro, int nominaRegistro) {
		this.rutaId = rutaId;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}

	public Integer getRutaId() {
		return this.rutaId;
	}

	public void setRutaId(Integer rutaId) {
		this.rutaId = rutaId;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getNominaRegistro() {
		return this.nominaRegistro;
	}

	public void setNominaRegistro(int nominaRegistro) {
		this.nominaRegistro = nominaRegistro;
	}

	public int getReparaciones() {
		return this.reparaciones;
	}

	public void setReparaciones(int reparaciones) {
		this.reparaciones = reparaciones;
	}

}
