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

	@Column(insertable=false, updatable=false)
	private int folio;

	@Column(name="nomina_empleado", insertable=false, updatable=false)
	private int nominaEmpleado;

	public SeguimientosEmpleadoPK() {
	}
	public int getFolio() {
		return this.folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public int getNominaEmpleado() {
		return this.nominaEmpleado;
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
			(this.folio == castOther.folio)
			&& (this.nominaEmpleado == castOther.nominaEmpleado);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.folio;
		hash = hash * prime + this.nominaEmpleado;
		
		return hash;
	}
}