package mx.com.desoft.hidrogas.property;


import javafx.beans.property.StringProperty;

public class SeguimientoOrdenPartesProperty {

	private int idRefaccion;
	private StringProperty cantidad;
	private StringProperty parte;
	private StringProperty marca;
	private StringProperty descripcion;

	public SeguimientoOrdenPartesProperty(int idRefaccion, StringProperty cantidad, StringProperty parte,
			StringProperty marca, StringProperty descripcion) {
		super();
		this.idRefaccion = idRefaccion;
		this.cantidad = cantidad;
		this.parte = parte;
		this.marca = marca;
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

	public SeguimientoOrdenPartesProperty(int idRefaccion, StringProperty cantidad, StringProperty marca,
			StringProperty descripcion) {
		super();
		this.idRefaccion = idRefaccion;
		this.cantidad = cantidad;
		this.marca = marca;
		this.descripcion = descripcion;
	}

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

	public StringProperty getParte() {
		return parte;
	}

	public void setParte(StringProperty parte) {
		this.parte = parte;
	}

	public StringProperty getMarca() {
		return marca;
	}

	public void setMarca(StringProperty marca) {
		this.marca = marca;
	}

	public StringProperty getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

}
