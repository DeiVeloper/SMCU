package mx.com.desoft.hidrogas.property;

import javafx.beans.property.StringProperty;

public class TipoNecesidadProperty {

	private StringProperty tipoNecesidadId;
	private StringProperty descripcion;

	public TipoNecesidadProperty() {
	}

	public TipoNecesidadProperty(StringProperty tipoNecesidadId, StringProperty descripcion) {
		this.tipoNecesidadId = tipoNecesidadId;
		this.descripcion = descripcion;
	}

	public StringProperty getTipoNecesidadId() {
		return tipoNecesidadId;
	}

	public void setTipoNecesidadId(StringProperty tipoNecesidadId) {
		this.tipoNecesidadId = tipoNecesidadId;
	}

	public StringProperty getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

}
