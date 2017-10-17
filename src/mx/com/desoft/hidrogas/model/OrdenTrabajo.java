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

	@Column(name="apellido_mat_operador")
	private String apellidoMatOperador;

	@Column(name="apellido_pat_operador")
	private String apellidoPatOperador;

	@Column(name="falla_mecanica")
	private String fallaMecanica;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	private int kilometraje;

	@Column(name="nombre_operador")
	private String nombreOperador;

//	//bi-directional one-to-one association to ListaRefaccione
//	@OneToOne(mappedBy="ordenTrabajo")
//	private ListaRefacciones listaRefaccione;

	//bi-directional many-to-one association to Economico
	@ManyToOne
	@JoinColumn(name="economico_id")
	private Economico economico;

	//bi-directional many-to-one association to CatEstatusOrden
	@ManyToOne
	@JoinColumn(name="estatus_orden_id")
	private CatEstatusOrden catEstatusOrden;

	//bi-directional many-to-one association to Empleado
//	@ManyToOne
//	@JoinColumn(name="nomina_operador")
	@Column(name="nomina_operador")
	private int nominaOperador;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="nomina_registro")
	private Empleado empleado2;

	//bi-directional many-to-one association to CatTipoNecesidad
	@ManyToOne
	@JoinColumn(name="tipo_necesidad_id")
	private CatTipoNecesidad catTipoNecesidad;

	//bi-directional one-to-one association to SeguimientoOrden
	@OneToOne(mappedBy="ordenTrabajo")
	private SeguimientoOrden seguimientoOrden;

	public OrdenTrabajo() {
	}

	public OrdenTrabajo(String apellidoMatOperador, String apellidoPatOperador, String fallaMecanica,
			Date fechaRegistro, int kilometraje, String nombreOperador, Economico economico,
			CatEstatusOrden catEstatusOrden, int nominaOperador, Empleado empleado2,
			CatTipoNecesidad catTipoNecesidad) {
		super();
		this.apellidoMatOperador = apellidoMatOperador;
		this.apellidoPatOperador = apellidoPatOperador;
		this.fallaMecanica = fallaMecanica;
		this.fechaRegistro = fechaRegistro;
		this.kilometraje = kilometraje;
		this.nombreOperador = nombreOperador;
		this.economico = economico;
		this.catEstatusOrden = catEstatusOrden;
		this.nominaOperador = nominaOperador;
		this.empleado2 = empleado2;
		this.catTipoNecesidad = catTipoNecesidad;
	}

	public int getFolio() {
		return this.folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getApellidoMatOperador() {
		return this.apellidoMatOperador;
	}

	public void setApellidoMatOperador(String apellidoMatOperador) {
		this.apellidoMatOperador = apellidoMatOperador;
	}

	public String getApellidoPatOperador() {
		return this.apellidoPatOperador;
	}

	public void setApellidoPatOperador(String apellidoPatOperador) {
		this.apellidoPatOperador = apellidoPatOperador;
	}

	public String getFallaMecanica() {
		return this.fallaMecanica;
	}

	public void setFallaMecanica(String fallaMecanica) {
		this.fallaMecanica = fallaMecanica;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getKilometraje() {
		return this.kilometraje;
	}

	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}

	public String getNombreOperador() {
		return this.nombreOperador;
	}

	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}

//	public ListaRefacciones getListaRefaccione() {
//		return this.listaRefaccione;
//	}
//
//	public void setListaRefaccione(ListaRefacciones listaRefaccione) {
//		this.listaRefaccione = listaRefaccione;
//	}

	public Economico getEconomico() {
		return this.economico;
	}

	public void setEconomico(Economico economico) {
		this.economico = economico;
	}

	public CatEstatusOrden getCatEstatusOrden() {
		return this.catEstatusOrden;
	}

	public void setCatEstatusOrden(CatEstatusOrden catEstatusOrden) {
		this.catEstatusOrden = catEstatusOrden;
	}

	public int getNominaOperador() {
		return nominaOperador;
	}

	public void setNominaOperador(int nominaOperador) {
		this.nominaOperador = nominaOperador;
	}

	public Empleado getEmpleado2() {
		return this.empleado2;
	}

	public void setEmpleado2(Empleado empleado2) {
		this.empleado2 = empleado2;
	}

	public CatTipoNecesidad getCatTipoNecesidad() {
		return this.catTipoNecesidad;
	}

	public void setCatTipoNecesidad(CatTipoNecesidad catTipoNecesidad) {
		this.catTipoNecesidad = catTipoNecesidad;
	}

	public SeguimientoOrden getSeguimientoOrden() {
		return this.seguimientoOrden;
	}

	public void setSeguimientoOrden(SeguimientoOrden seguimientoOrden) {
		this.seguimientoOrden = seguimientoOrden;
	}

}