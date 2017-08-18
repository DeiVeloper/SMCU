package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the seguimiento_orden database table.
 * 
 */
@Entity
@Table(name="seguimiento_orden")
@NamedQuery(name="SeguimientoOrden.findAll", query="SELECT s FROM SeguimientoOrden s")
public class SeguimientoOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int folio;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_repara_mayor")
	private Date fechaReparaMayor;

	@Column(name="nomina_registro")
	private int nominaRegistro;

	private String observaciones;

	@Column(name="reparacion_mayor")
	private int reparacionMayor;

	@Column(name="trabajos_realizados")
	private String trabajosRealizados;

	//bi-directional one-to-one association to OrdenTrabajo
	@OneToOne
	@JoinColumn(name="folio")
	private OrdenTrabajo ordenTrabajo;

	//bi-directional many-to-one association to SeguimientosEmpleado
	@OneToMany(mappedBy="seguimientoOrden")
	private List<SeguimientosEmpleado> seguimientosEmpleados;

	public SeguimientoOrden() {
	}

	public int getFolio() {
		return this.folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaReparaMayor() {
		return this.fechaReparaMayor;
	}

	public void setFechaReparaMayor(Date fechaReparaMayor) {
		this.fechaReparaMayor = fechaReparaMayor;
	}

	public int getNominaRegistro() {
		return this.nominaRegistro;
	}

	public void setNominaRegistro(int nominaRegistro) {
		this.nominaRegistro = nominaRegistro;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getReparacionMayor() {
		return this.reparacionMayor;
	}

	public void setReparacionMayor(int reparacionMayor) {
		this.reparacionMayor = reparacionMayor;
	}

	public String getTrabajosRealizados() {
		return this.trabajosRealizados;
	}

	public void setTrabajosRealizados(String trabajosRealizados) {
		this.trabajosRealizados = trabajosRealizados;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return this.ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public List<SeguimientosEmpleado> getSeguimientosEmpleados() {
		return this.seguimientosEmpleados;
	}

	public void setSeguimientosEmpleados(List<SeguimientosEmpleado> seguimientosEmpleados) {
		this.seguimientosEmpleados = seguimientosEmpleados;
	}

	public SeguimientosEmpleado addSeguimientosEmpleado(SeguimientosEmpleado seguimientosEmpleado) {
		getSeguimientosEmpleados().add(seguimientosEmpleado);
		seguimientosEmpleado.setSeguimientoOrden(this);

		return seguimientosEmpleado;
	}

	public SeguimientosEmpleado removeSeguimientosEmpleado(SeguimientosEmpleado seguimientosEmpleado) {
		getSeguimientosEmpleados().remove(seguimientosEmpleado);
		seguimientosEmpleado.setSeguimientoOrden(null);

		return seguimientosEmpleado;
	}

}