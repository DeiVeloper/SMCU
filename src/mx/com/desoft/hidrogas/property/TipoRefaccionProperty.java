package mx.com.desoft.hidrogas.property;

import javafx.beans.property.StringProperty;

public class TipoRefaccionProperty {

	private StringProperty tipoRefaccionId;
	private StringProperty descripcion;
	private StringProperty cantidad;

	public TipoRefaccionProperty() {
	}

	public TipoRefaccionProperty(StringProperty tipoRefaccionId, StringProperty descripcion, StringProperty cantidad) {
		this.tipoRefaccionId = tipoRefaccionId;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public StringProperty getTipoRefaccionId() {
		return tipoRefaccionId;
	}

	public void setTipoRefaccionId(StringProperty tipoRefaccionId) {
		this.tipoRefaccionId = tipoRefaccionId;
	}

	public StringProperty getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

	public StringProperty getCantidad() {
		return cantidad;
	}

	public void setCantidad(StringProperty cantidad) {
		this.cantidad = cantidad;
	}

}
