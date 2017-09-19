package mx.com.desoft.hidrogas.property;

import javafx.beans.property.StringProperty;

public class EconomicoProperty {

	private StringProperty economicoId;
	private StringProperty fechaRegistro;
	private StringProperty operador;

	public EconomicoProperty() {
	}

	public EconomicoProperty(StringProperty economicoId, StringProperty fechaRegistro) {
		this.economicoId = economicoId;
		this.fechaRegistro = fechaRegistro;
	}

	public EconomicoProperty(StringProperty economicoId, StringProperty fechaRegistro, StringProperty operador) {
		this.economicoId = economicoId;
		this.fechaRegistro = fechaRegistro;
		this.operador = operador;
	}

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

	public StringProperty getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(StringProperty fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
