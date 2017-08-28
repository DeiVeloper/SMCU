package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the seguimientos_empleados database table.
 * 
 */
@Entity
@Table(name="seguimientos_empleados")
@NamedQuery(name="SeguimientosEmpleado.findAll", query="SELECT s FROM SeguimientosEmpleado s")
public class SeguimientosEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	 @EmbeddedId
	    @AttributeOverrides({ @AttributeOverride(name = "folio", column = @Column(name = "folio", nullable = false, precision = 9, scale = 0)),
	            @AttributeOverride(name = "nominaEmpleado", column = @Column(name = "nomina_empledado", nullable = false, precision = 5, scale = 0)) })
	private SeguimientosEmpleadoPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	//bi-directional many-to-one association to SeguimientoOrden
//	@ManyToOne
//	@JoinColumn(name="folio")
//	private SeguimientoOrden seguimientoOrden;

	//bi-directional many-to-one association to Empleado
//	@ManyToOne
//	@JoinColumn(name="nomina_empleado")
//	private Empleado empleado;

	public SeguimientosEmpleado() {
	}

	public SeguimientosEmpleadoPK getId() {
		return this.id;
	}

	public void setId(SeguimientosEmpleadoPK id) {
		this.id = id;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

//	public SeguimientoOrden getSeguimientoOrden() {
//		return this.seguimientoOrden;
//	}
//
//	public void setSeguimientoOrden(SeguimientoOrden seguimientoOrden) {
//		this.seguimientoOrden = seguimientoOrden;
//	}
//
//	public Empleado getEmpleado() {
//		return this.empleado;
//	}
//
//	public void setEmpleado(Empleado empleado) {
//		this.empleado = empleado;
//	}

}