package mx.com.desoft.hidrogas.property;


import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;

public class OrdenProperty {

	private StringProperty folioOrden;
	private StringProperty fechaOrden;
	private StringProperty economicoOrden;
	private StringProperty empleadoOrden;
	private StringProperty necesidadOrden;
	private int estatus;
	private int empleadoOrdenId;
	private Button seguimiento;

	public OrdenProperty(StringProperty folioOrden, StringProperty fechaOrden, StringProperty economicoOrden,
			StringProperty empleadoOrden, StringProperty necesidadOrden, int empleadoOrdenId,int estatus, Button seguimiento, MainApp mainApp) {
		super();
		this.folioOrden = folioOrden;
		this.fechaOrden = fechaOrden;
		this.economicoOrden = economicoOrden;
		this.empleadoOrden = empleadoOrden;
		this.necesidadOrden = necesidadOrden;
		this.empleadoOrdenId = empleadoOrdenId;
		this.estatus = estatus;
		this.seguimiento = seguimiento;
		if(estatus == 2)
			seguimiento.setVisible(false);
		seguimiento.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				mainApp.showSeguimientoOrdenTrabajo(new OrdenTrabajoDTO(Integer.parseInt(getFolioOrden().getValue()), Integer.parseInt(getEconomicoOrden().getValue()), empleadoOrdenId));

			}
		});
	}
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
	public int getEmpleadoOrdenId() {
		return empleadoOrdenId;
	}
	public void setEmpleadoOrdenId(int empleadoOrdenId) {
		this.empleadoOrdenId = empleadoOrdenId;
	}
	public Button getSeguimiento() {
		return seguimiento;
	}
	public void setSeguimiento(Button seguimiento) {
		this.seguimiento = seguimiento;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

}
