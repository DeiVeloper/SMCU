package mx.com.desoft.hidrogas.property;

import javafx.beans.property.StringProperty;

public class TipoRefaccionProperty {

	private StringProperty tipoRefaccionId;
	private StringProperty descripcion;

	public TipoRefaccionProperty() {
	}

	public TipoRefaccionProperty(StringProperty tipoRefaccionId, StringProperty descripcion) {
		this.tipoRefaccionId = tipoRefaccionId;
		this.descripcion = descripcion;
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

}
