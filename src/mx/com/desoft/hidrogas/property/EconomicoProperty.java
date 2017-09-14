package mx.com.desoft.hidrogas.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class EconomicoProperty {
	
	private StringProperty economicoId;
	private StringProperty operador;
	private IntegerProperty reparaciones;
	
	public StringProperty getEconomicoId() {
		return economicoId;
	}
	public void setEconomicoId(StringProperty economicoId) {
		this.economicoId = economicoId;
	}
	public StringProperty getOperador() {
		return operador;
	}
	public void setOperador(StringProperty operador) {
		this.operador = operador;
	}
	public IntegerProperty getReparaciones() {
		return reparaciones;
	}
	public void setReparaciones(IntegerProperty reparaciones) {
		this.reparaciones = reparaciones;
	}

}
