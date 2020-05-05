package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the cat_tipo_lista_refaccion database table.
 *
 */
@Entity
@Table(name="cat_tipo_refaccion")
@NamedQuery(name="CatTipoRefaccion.findAll", query="SELECT c FROM CatTipoRefaccion c")
public class CatTipoRefaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_refaccion")
	private int idTipoRefaccion;

	private String descripcion;

	@Column(name="cantidad")
	private int cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="nomina_registro")
	private int nominaRegistro;

	public CatTipoRefaccion() {
		super();
	}

	public CatTipoRefaccion(int idTipoRefaccion, String descripcion, int cantidad,Date fechaRegistro, int nominaRegistro) {
		super();
		this.idTipoRefaccion = idTipoRefaccion;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}

	public CatTipoRefaccion(String descripcion,int cantidad, Date fechaRegistro, int nominaRegistro) {
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}

	public int getIdTipoRefaccion() {
		return idTipoRefaccion;
	}

	public void setIdTipoRefaccion(int idTipoRefaccion) {
		this.idTipoRefaccion = idTipoRefaccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

}
