package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat_estatus_orden database table.
 * 
 */
@Entity
@Table(name="cat_estatus_orden")
@NamedQuery(name="CatEstatusOrden.findAll", query="SELECT c FROM CatEstatusOrden c")
public class CatEstatusOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="estatus_orden_id")
	private int estatusOrdenId;

	private String descripcion;

	//bi-directional many-to-one association to OrdenTrabajo
	@OneToMany(mappedBy="catEstatusOrden")
	private List<OrdenTrabajo> ordenTrabajos;

	public CatEstatusOrden() {
	}

	public int getEstatusOrdenId() {
		return this.estatusOrdenId;
	}

	public void setEstatusOrdenId(int estatusOrdenId) {
		this.estatusOrdenId = estatusOrdenId;
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
		ordenTrabajo.setCatEstatusOrden(this);

		return ordenTrabajo;
	}

	public OrdenTrabajo removeOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		getOrdenTrabajos().remove(ordenTrabajo);
		ordenTrabajo.setCatEstatusOrden(null);

		return ordenTrabajo;
	}

}