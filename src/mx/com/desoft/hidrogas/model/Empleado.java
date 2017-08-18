package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the empleados database table.
 * 
 */
@Entity
@Table(name="empleados")
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nomina_empleado")
	private int nominaEmpleado;

	@Column(name="apellido_mat_empleado")
	private String apellidoMatEmpleado;

	@Column(name="apellido_pat_empleado")
	private String apellidoPatEmpleado;

	private int economico;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="nombre_empleado")
	private String nombreEmpleado;

	@Column(name="nomina_registro")
	private int nominaRegistro;

	private String password;

	//bi-directional many-to-one association to CatTipoEmpleado
	/*@ManyToOne
	@JoinColumn(name="tipo_empleado_id")
	private CatTipoEmpleado catTipoEmpleado;

	//bi-directional many-to-one association to OrdenTrabajo
	@OneToMany(mappedBy="empleado1")
	private List<OrdenTrabajo> ordenTrabajos1;

	//bi-directional many-to-one association to OrdenTrabajo
	@OneToMany(mappedBy="empleado2")
	private List<OrdenTrabajo> ordenTrabajos2;

	//bi-directional many-to-one association to SeguimientosEmpleado
	@OneToMany(mappedBy="empleado")
	private List<SeguimientosEmpleado> seguimientosEmpleados;*/

	public Empleado() {
	}
	
	

	public Empleado(int nominaEmpleado, String nombreEmpleado) {
		this.nominaEmpleado = nominaEmpleado;
		this.nombreEmpleado = nombreEmpleado;
	}



	public int getNominaEmpleado() {
		return this.nominaEmpleado;
	}

	public void setNominaEmpleado(int nominaEmpleado) {
		this.nominaEmpleado = nominaEmpleado;
	}

	public String getApellidoMatEmpleado() {
		return this.apellidoMatEmpleado;
	}

	public void setApellidoMatEmpleado(String apellidoMatEmpleado) {
		this.apellidoMatEmpleado = apellidoMatEmpleado;
	}

	public String getApellidoPatEmpleado() {
		return this.apellidoPatEmpleado;
	}

	public void setApellidoPatEmpleado(String apellidoPatEmpleado) {
		this.apellidoPatEmpleado = apellidoPatEmpleado;
	}

	public int getEconomico() {
		return this.economico;
	}

	public void setEconomico(int economico) {
		this.economico = economico;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombreEmpleado() {
		return this.nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public int getNominaRegistro() {
		return this.nominaRegistro;
	}

	public void setNominaRegistro(int nominaRegistro) {
		this.nominaRegistro = nominaRegistro;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public CatTipoEmpleado getCatTipoEmpleado() {
		return this.catTipoEmpleado;
	}

	public void setCatTipoEmpleado(CatTipoEmpleado catTipoEmpleado) {
		this.catTipoEmpleado = catTipoEmpleado;
	}

	public List<OrdenTrabajo> getOrdenTrabajos1() {
		return this.ordenTrabajos1;
	}

	public void setOrdenTrabajos1(List<OrdenTrabajo> ordenTrabajos1) {
		this.ordenTrabajos1 = ordenTrabajos1;
	}

	public OrdenTrabajo addOrdenTrabajos1(OrdenTrabajo ordenTrabajos1) {
		getOrdenTrabajos1().add(ordenTrabajos1);
		ordenTrabajos1.setEmpleado1(this);

		return ordenTrabajos1;
	}

	public OrdenTrabajo removeOrdenTrabajos1(OrdenTrabajo ordenTrabajos1) {
		getOrdenTrabajos1().remove(ordenTrabajos1);
		ordenTrabajos1.setEmpleado1(null);

		return ordenTrabajos1;
	}

	public List<OrdenTrabajo> getOrdenTrabajos2() {
		return this.ordenTrabajos2;
	}

	public void setOrdenTrabajos2(List<OrdenTrabajo> ordenTrabajos2) {
		this.ordenTrabajos2 = ordenTrabajos2;
	}

	public OrdenTrabajo addOrdenTrabajos2(OrdenTrabajo ordenTrabajos2) {
		getOrdenTrabajos2().add(ordenTrabajos2);
		ordenTrabajos2.setEmpleado2(this);

		return ordenTrabajos2;
	}

	public OrdenTrabajo removeOrdenTrabajos2(OrdenTrabajo ordenTrabajos2) {
		getOrdenTrabajos2().remove(ordenTrabajos2);
		ordenTrabajos2.setEmpleado2(null);

		return ordenTrabajos2;
	}

	public List<SeguimientosEmpleado> getSeguimientosEmpleados() {
		return this.seguimientosEmpleados;
	}

	public void setSeguimientosEmpleados(List<SeguimientosEmpleado> seguimientosEmpleados) {
		this.seguimientosEmpleados = seguimientosEmpleados;
	}

	public SeguimientosEmpleado addSeguimientosEmpleado(SeguimientosEmpleado seguimientosEmpleado) {
		getSeguimientosEmpleados().add(seguimientosEmpleado);
		seguimientosEmpleado.setEmpleado(this);

		return seguimientosEmpleado;
	}

	public SeguimientosEmpleado removeSeguimientosEmpleado(SeguimientosEmpleado seguimientosEmpleado) {
		getSeguimientosEmpleados().remove(seguimientosEmpleado);
		seguimientosEmpleado.setEmpleado(null);

		return seguimientosEmpleado;
	}*/

}