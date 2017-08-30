package mx.com.desoft.hidrogas.property;

import javafx.beans.property.StringProperty;

public class OrdenProperty {

	private StringProperty folioOrden;
	private StringProperty fechaOrden;
	private StringProperty economicoOrden;
	private StringProperty empleadoOrden;
	private StringProperty necesidadOrden;
	public StringProperty getFolioOrden() {
		return folioOrden;
	}
	public void setFolioOrden(StringProperty folioOrden) {
		this.folioOrden = folioOrden;
	}
	public StringProperty getFechaOrden() {
		return fechaOrden;
	}
	public void setFechaOrden(StringProperty fechaOrden) {
		this.fechaOrden = fechaOrden;
	}
	public StringProperty getEconomicoOrden() {
		return economicoOrden;
	}
	public void setEconomicoOrden(StringProperty economicoOrden) {
		this.economicoOrden = economicoOrden;
	}
	public StringProperty getEmpleadoOrden() {
		return empleadoOrden;
	}
	public void setEmpleadoOrden(StringProperty empleadoOrden) {
		this.empleadoOrden = empleadoOrden;
	}
	public StringProperty getNecesidadOrden() {
		return necesidadOrden;
	}
	public void setNecesidadOrden(StringProperty necesidadOrden) {
		this.necesidadOrden = necesidadOrden;
	}

}
