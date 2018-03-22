package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lista_refacciones database table.
 *
 */
@Entity
@Table(name="lista_refacciones")
@NamedQuery(name="ListaRefacciones.findAll", query="SELECT l FROM ListaRefacciones l")
public class ListaRefacciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_refaccion;

	//bi-directional one-to-one association to OrdenTrabajo
	@OneToOne
	@JoinColumn(name="folio")
	private OrdenTrabajo ordenTrabajo;

	@Column(name="cantidad")
	private int cantidad;

//	@Column(name="no_parte")
//	private String noParte;
//
//	@Column(name="marca")
//	private String marca;

	//bi-directional many-to-one association to CatTipoListaRefaccion
	@ManyToOne
	@JoinColumn(name="tipo_lista_refaccion_id")
	private CatTipoListaRefaccion catTipoListaRefaccion;

	//bi-directional many-to-one association to CatTipoListaRefaccion
	@ManyToOne
	@JoinColumn(name="tipo_refaccion_id")
	private CatTipoRefaccion catTipoRefaccion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="nomina_registro")
	private int nominaRegistro;

	public ListaRefacciones() {
	}

	public int getId_refaccion() {
		return id_refaccion;
	}

	public void setId_refaccion(int id_refaccion) {
		this.id_refaccion = id_refaccion;
	}

	public ListaRefacciones(OrdenTrabajo ordenTrabajo, int cantidad, CatTipoRefaccion catTipoRefaccion,
			CatTipoListaRefaccion catTipoListaRefaccion, Date fechaRegistro, int nominaRegistro) {
		super();
		this.ordenTrabajo = ordenTrabajo;
		this.cantidad = cantidad;
		this.catTipoRefaccion = catTipoRefaccion;
		this.catTipoListaRefaccion = catTipoListaRefaccion;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}

//	public ListaRefacciones(OrdenTrabajo ordenTrabajo, int cantidad, String marca, String descripcion,
//			CatTipoListaRefaccion catTipoListaRefaccion, Date fechaRegistro, int nominaRegistro) {
//		super();
//		this.ordenTrabajo = ordenTrabajo;
//		this.cantidad = cantidad;
//		this.marca = marca;
//		this.descripcion = descripcion;
//		this.catTipoListaRefaccion = catTipoListaRefaccion;
//		this.fechaRegistro = fechaRegistro;
//		this.nominaRegistro = nominaRegistro;
//	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public CatTipoRefaccion getCatTipoRefaccion() {
		return catTipoRefaccion;
	}

	public void setCatTipoRefaccion(CatTipoRefaccion catTipoRefaccion) {
		this.catTipoRefaccion = catTipoRefaccion;
	}

	public int getNominaRegistro() {
		return this.nominaRegistro;
	}

	public void setNominaRegistro(int nominaRegistro) {
		this.nominaRegistro = nominaRegistro;
	}

	public CatTipoListaRefaccion getCatTipoListaRefaccion() {
		return this.catTipoListaRefaccion;
	}

	public void setCatTipoListaRefaccion(CatTipoListaRefaccion catTipoListaRefaccion) {
		this.catTipoListaRefaccion = catTipoListaRefaccion;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return this.ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

}