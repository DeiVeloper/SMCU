package mx.com.desoft.hidrogas.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the orden_trabajo database table.
 *
 */
@Entity
@Table(name="orden_trabajo")
@NamedQuery(name="OrdenTrabajo.findAll", query="SELECT o FROM OrdenTrabajo o")
public class OrdenTrabajo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int folio;
	
	@Column(name="nomina_operador")
	private int nominaOperador;

	@Column(name="nombre_operador")
	private String nombreOperador;

	@Column(name="apellido_pat_operador")
	private String apellidoPatOperador;

	@Column(name="apellido_mat_operador")
	private String apellidoMatOperador;

	@Column(name="falla_mecanica")
	private String fallaMecanica;

	@Column(name="kilometraje")
	private int kilometraje;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_orden")
	private Date fechaOrden;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_terminacion")
	private Date fechaTerminacion;

	@ManyToOne
	@JoinColumn(name="nomina_registro")
	private Empleado empleado2;

	@ManyToOne
	@JoinColumn(name="economico_id")
	private Economico economico;

	@ManyToOne
	@JoinColumn(name="estatus_orden_id")
	private CatEstatusOrden catEstatusOrden;

	@ManyToOne
	@JoinColumn(name="tipo_necesidad_id")
	private CatTipoNecesidad catTipoNecesidad;

	public OrdenTrabajo() {
	}

	public OrdenTrabajo(int nominaOperador, String nombreOperador,
			String apellidoPatOperador, String apellidoMatOperador,
			String fallaMecanica, int kilometraje, Date fechaRegistro,
			Date fechaOrden, Empleado empleado2,
			Economico economico, CatEstatusOrden catEstatusOrden,
			CatTipoNecesidad catTipoNecesidad) {
		super();
		this.nominaOperador = nominaOperador;
		this.nombreOperador = nombreOperador;
		this.apellidoPatOperador = apellidoPatOperador;
		this.apellidoMatOperador = apellidoMatOperador;
		this.fallaMecanica = fallaMecanica;
		this.kilometraje = kilometraje;
		this.fechaRegistro = fechaRegistro;
		this.fechaOrden = fechaOrden;
		this.empleado2 = empleado2;
		this.economico = economico;
		this.catEstatusOrden = catEstatusOrden;
		this.catTipoNecesidad = catTipoNecesidad;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getNominaOperador() {
		return nominaOperador;
	}

	public void setNominaOperador(int nominaOperador) {
		this.nominaOperador = nominaOperador;
	}

	public String getNombreOperador() {
		return nombreOperador;
	}

	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}

	public String getApellidoPatOperador() {
		return apellidoPatOperador;
	}

	public void setApellidoPatOperador(String apellidoPatOperador) {
		this.apellidoPatOperador = apellidoPatOperador;
	}

	public String getApellidoMatOperador() {
		return apellidoMatOperador;
	}

	public void setApellidoMatOperador(String apellidoMatOperador) {
		this.apellidoMatOperador = apellidoMatOperador;
	}

	public String getFallaMecanica() {
		return fallaMecanica;
	}

	public void setFallaMecanica(String fallaMecanica) {
		this.fallaMecanica = fallaMecanica;
	}

	public int getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	public Date getFechaTerminacion() {
		return fechaTerminacion;
	}

	public void setFechaTerminacion(Date fechaTerminacion) {
		this.fechaTerminacion = fechaTerminacion;
	}

	public Empleado getEmpleado2() {
		return empleado2;
	}

	public void setEmpleado2(Empleado empleado2) {
		this.empleado2 = empleado2;
	}

	public Economico getEconomico() {
		return economico;
	}

	public void setEconomico(Economico economico) {
		this.economico = economico;
	}

	public CatEstatusOrden getCatEstatusOrden() {
		return catEstatusOrden;
	}

	public void setCatEstatusOrden(CatEstatusOrden catEstatusOrden) {
		this.catEstatusOrden = catEstatusOrden;
	}

	public CatTipoNecesidad getCatTipoNecesidad() {
		return catTipoNecesidad;
	}

	public void setCatTipoNecesidad(CatTipoNecesidad catTipoNecesidad) {
		this.catTipoNecesidad = catTipoNecesidad;
	}

}