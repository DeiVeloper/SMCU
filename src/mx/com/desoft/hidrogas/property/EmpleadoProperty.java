package mx.com.desoft.hidrogas.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class EmpleadoProperty {

	private StringProperty nominaEmpleado;
	private StringProperty nombreEmpleado;
	private StringProperty economicoId;
	private StringProperty tipoEmpleado;
	
	public StringProperty getNominaEmpleado() {
		return nominaEmpleado;
	}
	
	public void setNominaEmpleado(StringProperty nominaEmpleado) {
		this.nominaEmpleado = nominaEmpleado;
	}
	
	public StringProperty getNombreEmpleado() {
		return nombreEmpleado;
	}
	
	public void setNombreEmpleado(StringProperty nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	
	public StringProperty getEconomicoId() {
		return economicoId;
	}
	
	public void setEconomicoId(StringProperty economicoId) {
		this.economicoId = economicoId;
	}
	
	public StringProperty getTipoEmpleado() {
		return tipoEmpleado;
	}
	
	public void setTipoEmpleado(StringProperty tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
}
