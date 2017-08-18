package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat_tipo_necesidad database table.
 * 
 */
@Entity
@Table(name="cat_tipo_necesidad")
@NamedQuery(name="CatTipoNecesidad.findAll", query="SELECT c FROM CatTipoNecesidad c")
public class CatTipoNecesidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipo_necesidad_id")
	private int tipoNecesidadId;

	private String descripcion;

	//bi-directional many-to-one association to OrdenTrabajo
	@OneToMany(mappedBy="catTipoNecesidad")
	private List<OrdenTrabajo> ordenTrabajos;

	public CatTipoNecesidad() {
	}

	public int getTipoNecesidadId() {
		return this.tipoNecesidadId;
	}

	public void setTipoNecesidadId(int tipoNecesidadId) {
		this.tipoNecesidadId = tipoNecesidadId;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<OrdenTrabajo> getOrdenTrabajos() {
		return this.ordenTrabajos;
	}

	public void setOrdenTrabajos(List<OrdenTrabajo> ordenTrabajos) {
		this.ordenTrabajos = ordenTrabajos;
	}

	public OrdenTrabajo addOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		getOrdenTrabajos().add(ordenTrabajo);
		ordenTrabajo.setCatTipoNecesidad(this);

		return ordenTrabajo;
	}

	public OrdenTrabajo removeOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		getOrdenTrabajos().remove(ordenTrabajo);
		ordenTrabajo.setCatTipoNecesidad(null);

		return ordenTrabajo;
	}

}