package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cat_tipo_empleados database table.
 *
 */
@Entity
@Table(name="cat_tipo_empleado")
public class CatTipoEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tipo_empleado_id")
	private Integer tipoEmpleadoId;

	@Column(name="descripcion")
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="nomina_registro")
	private Integer nominaRegistro;

	//bi-directional many-to-one association to Empleado
//	@OneToMany(mappedBy="catTipoEmpleado")
//	private List<Empleado> empleados;

	public CatTipoEmpleado() {
	}

	public CatTipoEmpleado(Integer tipoEmpleadoId, String descripcion, Date fechaRegistro, Integer nominaRegistro) {
		this.tipoEmpleadoId = tipoEmpleadoId;
		this.descripcion = descripcion;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}



	public int getTipoEmpleadoId() {
		return this.tipoEmpleadoId;
	}

	public void setTipoEmpleadoId(Integer tipoEmpleadoId) {
		this.tipoEmpleadoId = tipoEmpleadoId;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public void setNominaRegistro(Integer nominaRegistro) {
		this.nominaRegistro = nominaRegistro;
	}

//	public List<Empleado> getEmpleados() {
//		return this.empleados;
//	}
//
//	public void setEmpleados(List<Empleado> empleados) {
//		this.empleados = empleados;
//	}

	/*public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setCatTipoEmpleado(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setCatTipoEmpleado(null);

		return empleado;
	}*/

}
