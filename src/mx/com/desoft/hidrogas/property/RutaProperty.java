package mx.com.desoft.hidrogas.property;

import javafx.beans.property.StringProperty;

public class RutaProperty {

	private StringProperty rutaId;
	private StringProperty fechaRegistro;
	private StringProperty nombreRuta;

	public RutaProperty() {
	}

	public RutaProperty(StringProperty rutaId, StringProperty fechaRegistro) {
		this.rutaId = rutaId;
		this.fechaRegistro = fechaRegistro;
	}

	public RutaProperty(StringProperty rutaId, StringProperty fechaRegistro, StringProperty nombreRuta) {
		this.rutaId = rutaId;
		this.fechaRegistro = fechaRegistro;
		this.nombreRuta = nombreRuta;
	}

	public StringProperty getRutaId() {
		return rutaId;
	}

	public void setRutaId(StringProperty rutaId) {
		this.rutaId = rutaId;
	}

	public StringProperty getNombreRuta() {
		return nombreRuta;
	}

	public void setNombreRuta(StringProperty nombreRuta) {
		this.nombreRuta = nombreRuta;
	}

	public StringProperty getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(StringProperty fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
