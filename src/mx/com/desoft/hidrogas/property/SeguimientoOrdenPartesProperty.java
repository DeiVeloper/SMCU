package mx.com.desoft.hidrogas.property;


import javafx.beans.property.StringProperty;

public class SeguimientoOrdenPartesProperty {

	private int idRefaccion;
	private StringProperty cantidad;
	private int idTipoRefaccion;
	private StringProperty descripcionTipoRefaccion;
	private StringProperty descripcion;

	public SeguimientoOrdenPartesProperty(int idRefaccion, StringProperty cantidad, int idTipoRefaccion, StringProperty descripcionTipoRefaccion, StringProperty descripcion) {
		super();
		this.idRefaccion = idRefaccion;
		this.cantidad = cantidad;
		this.idTipoRefaccion = idTipoRefaccion;
		this.descripcionTipoRefaccion = descripcionTipoRefaccion;
		this.descripcion = descripcion;
	}

//	public SeguimientoOrdenPartesProperty(StringProperty cantidad, StringProperty parte, StringProperty marca,
//			StringProperty descripcion) {
//		super();
//		this.cantidad = cantidad;
//		this.parte = parte;
//		this.marca = marca;
//		this.descripcion = descripcion;
//	}

//	public SeguimientoOrdenPartesProperty(int idRefaccion, StringProperty cantidad, StringProperty marca,
//			StringProperty descripcion) {
//		super();
//		this.idRefaccion = idRefaccion;
//		this.cantidad = cantidad;
//		this.marca = marca;
//		this.descripcion = descripcion;
//	}

//	public SeguimientoOrdenPartesProperty(StringProperty cantidad, StringProperty marca, StringProperty descripcion) {
//		super();
//		this.cantidad = cantidad;
//		this.marca = marca;
//		this.descripcion = descripcion;
//	}

	public int getIdRefaccion() {
		return idRefaccion;
	}

	public void setIdRefaccion(int idRefaccion) {
		this.idRefaccion = idRefaccion;
	}

	public StringProperty getCantidad() {
		return cantidad;
	}

	public void setCantidad(StringProperty cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdTipoRefaccion() {
		return idTipoRefaccion;
	}

	public void setIdTipoRefaccion(int idTipoRefaccion) {
		this.idTipoRefaccion = idTipoRefaccion;
	}

	public StringProperty getDescripcionTipoRefaccion() {
		return descripcionTipoRefaccion;
	}

	public void setDescripcionTipoRefaccion(StringProperty descripcionTipoRefaccion) {
		this.descripcionTipoRefaccion = descripcionTipoRefaccion;
	}

	public StringProperty getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

}
