package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cat_tipo_empleados database table.
 * 
 */
@Entity
@Table(name="cat_tipo_empleados")
@NamedQuery(name="CatTipoEmpleado.findAll", query="SELECT c FROM CatTipoEmpleado c")
public class CatTipoEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipo_empleado_id")
	private int tipoEmpleadoId;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="nomina_registro")
	private int nominaRegistro;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="catTipoEmpleado")
	private List<Empleado> empleados;

	public CatTipoEmpleado() {
	}

	public int getTipoEmpleadoId() {
		return this.tipoEmpleadoId;
	}

	public void setTipoEmpleadoId(int tipoEmpleadoId) {
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

	public void setNominaRegistro(int nominaRegistro) {
		this.nominaRegistro = nominaRegistro;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

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