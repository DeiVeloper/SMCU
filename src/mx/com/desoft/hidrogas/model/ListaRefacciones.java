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
@NamedQuery(name="ListaRefaccione.findAll", query="SELECT l FROM ListaRefaccione l")
public class ListaRefacciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int folio;

	private int cantidad;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	private String marca;

	@Column(name="no_parte")
	private String noParte;

	@Column(name="nomina_registro")
	private int nominaRegistro;

	//bi-directional many-to-one association to CatTipoListaRefaccion
	@ManyToOne
	@JoinColumn(name="tipo_refaccion_id")
	private CatTipoListaRefaccion catTipoListaRefaccion;

	//bi-directional one-to-one association to OrdenTrabajo
	@OneToOne
	@JoinColumn(name="folio")
	private OrdenTrabajo ordenTrabajo;

	public ListaRefacciones() {
	}

	public int getFolio() {
		return this.folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNoParte() {
		return this.noParte;
	}

	public void setNoParte(String noParte) {
		this.noParte = noParte;
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