package mx.com.desoft.hidrogas.property;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SeguimientoOrdenPartesProperty {

	private StringProperty cantidad;
	private StringProperty parte;
	private StringProperty marca;
	private StringProperty descripcion;
	private Button eliminar;

	public SeguimientoOrdenPartesProperty(StringProperty cantidad, StringProperty parte, StringProperty marca,
			StringProperty descripcion, Button eliminar) {
		super();
		this.cantidad = cantidad;
		this.parte = parte;
		this.marca = marca;
		this.descripcion = descripcion;
		this.eliminar = eliminar;
		eliminar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
//				mainApp.showSeguimientoOrdenTrabajo(new OrdenTrabajoDTO(Integer.parseInt(getFolioOrden().getValue()), Integer.parseInt(getEconomicoOrden().getValue()), Integer.parseInt(getEmpleadoOrden().getValue())));

			}
		});
	}

	public SeguimientoOrdenPartesProperty(StringProperty cantidad, StringProperty marca, StringProperty descripcion,
			Button eliminar) {
		super();
		this.cantidad = cantidad;
		this.marca = marca;
		this.descripcion = descripcion;
		this.eliminar = eliminar;
		eliminar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
//				mainApp.showSeguimientoOrdenTrabajo(new OrdenTrabajoDTO(Integer.parseInt(getFolioOrden().getValue()), Integer.parseInt(getEconomicoOrden().getValue()), Integer.parseInt(getEmpleadoOrden().getValue())));

			}
		});
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

	public Button getEliminar() {
		return eliminar;
	}

	public void setEliminar(Button eliminar) {
		this.eliminar = eliminar;
	}

}
