package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the seguimientos_empleados database table.
 *
 */
@Embeddable
public class SeguimientosEmpleadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_seguimiento", insertable=false, updatable=false)
	private int idSeguimiento;

	@Column(name="nomina_empleado", insertable=false, updatable=false)
	private int nominaEmpleado;

	public SeguimientosEmpleadoPK() {

	}

	public SeguimientosEmpleadoPK(int idSeguimiento, int nominaEmpleado) {
		super();
		this.idSeguimiento = idSeguimiento;
		this.nominaEmpleado = nominaEmpleado;
	}

	public int getIdSeguimiento() {
		return idSeguimiento;
	}

	public void setIdSeguimiento(int idSeguimiento) {
		this.idSeguimiento = idSeguimiento;
	}

	public int getNominaEmpleado() {
		return nominaEmpleado;
	}

	public void setNominaEmpleado(int nominaEmpleado) {
		this.nominaEmpleado = nominaEmpleado;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SeguimientosEmpleadoPK)) {
			return false;
		}
		SeguimientosEmpleadoPK castOther = (SeguimientosEmpleadoPK)other;
		return
			(this.idSeguimiento == castOther.idSeguimiento)
			&& (this.nominaEmpleado == castOther.nominaEmpleado);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idSeguimiento;
		hash = hash * prime + this.nominaEmpleado;

		return hash;
	}
}