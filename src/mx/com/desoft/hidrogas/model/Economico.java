package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the economicos database table.
 * 
 */
@Entity
@Table(name="economicos")
public class Economico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="economico_id")
	private int economicoId;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="nomina_registro")
	private int nominaRegistro;

	private int reparaciones;

	//bi-directional many-to-one association to OrdenTrabajo
//	@OneToMany(mappedBy="economico")
//	private List<OrdenTrabajo> ordenTrabajos;

	public Economico() {
	}

	public int getEconomicoId() {
		return this.economicoId;
	}

	public void setEconomicoId(int economicoId) {
		this.economicoId = economicoId;
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

	public int getReparaciones() {
		return this.reparaciones;
	}

	public void setReparaciones(int reparaciones) {
		this.reparaciones = reparaciones;
	}
//
//	public List<OrdenTrabajo> getOrdenTrabajos() {
//		return this.ordenTrabajos;
//	}
//
//	public void setOrdenTrabajos(List<OrdenTrabajo> ordenTrabajos) {
//		this.ordenTrabajos = ordenTrabajos;
//	}
//
//	public OrdenTrabajo addOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
//		getOrdenTrabajos().add(ordenTrabajo);
//		ordenTrabajo.setEconomico(this);
//
//		return ordenTrabajo;
//	}
//
//	public OrdenTrabajo removeOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
//		getOrdenTrabajos().remove(ordenTrabajo);
//		ordenTrabajo.setEconomico(null);
//
//		return ordenTrabajo;
//	}

}