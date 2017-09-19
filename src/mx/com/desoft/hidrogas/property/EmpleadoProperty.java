package mx.com.desoft.hidrogas.property;


import javafx.beans.property.StringProperty;
import mx.com.desoft.hidrogas.view.AdministrarEmpleadoController;

public class EmpleadoProperty {

	private StringProperty nominaEmpleado;
	private StringProperty nombreEmpleado;
	private StringProperty apellidoPaterno;
	private StringProperty apellidoMaterno;
	private StringProperty password;
	private StringProperty economicoId;
	private StringProperty fechaRegistro;
	private StringProperty noNominaRegistro;
	private StringProperty tipoEmpleadoId;
	private StringProperty tipoEmpleadoDescripcion;
	private AdministrarEmpleadoController controllerEmpleados;

	public EmpleadoProperty() {

	}

	public EmpleadoProperty(StringProperty nominaEmpleado,StringProperty nombreEmpleado, StringProperty apellidoPaterno, StringProperty apellidoMaterno, StringProperty password,
			StringProperty economicoId, StringProperty fechaRegistro, StringProperty noNominaRegistro, StringProperty tipoEmpleadoId, StringProperty tipoEmpleadoDescripcion) {
		this.nominaEmpleado = nominaEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.password = password;
		this.economicoId = economicoId;
		this.fechaRegistro = fechaRegistro;
		this.noNominaRegistro = noNominaRegistro;
		this.tipoEmpleadoId = tipoEmpleadoId;
		this.tipoEmpleadoDescripcion = tipoEmpleadoDescripcion;

	}

	public StringProperty getNominaEmpleado() {
		return nominaEmpleado;
	}

	public void setNominaEmpleado(StringProperty nominaEmpleado) {
		this.nominaEmpleado = nominaEmpleado;
	}

	public StringProperty getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(StringProperty nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public StringProperty getEconomicoId() {
		return economicoId;
	}

	public void setEconomicoId(StringProperty economicoId) {
		this.economicoId = economicoId;
	}

	public StringProperty getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(StringProperty apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public StringProperty getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(StringProperty apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public StringProperty getTipoEmpleadoId() {
		return tipoEmpleadoId;
	}

	public void setTipoEmpleadoId(StringProperty tipoEmpleadoId) {
		this.tipoEmpleadoId = tipoEmpleadoId;
	}

	public StringProperty getTipoEmpleadoDescripcion() {
		return tipoEmpleadoDescripcion;
	}

	public void setTipoEmpleadoDescripcion(StringProperty tipoEmpleadoDescripcion) {
		this.tipoEmpleadoDescripcion = tipoEmpleadoDescripcion;
	}

	public StringProperty getPassword() {
		return password;
	}

	public void setPassword(StringProperty password) {
		this.password = password;
	}

	public StringProperty getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(StringProperty fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public StringProperty getNoNominaRegistro() {
		return noNominaRegistro;
	}

	public void setNoNominaRegistro(StringProperty noNominaRegistro) {
		this.noNominaRegistro = noNominaRegistro;
	}

	public AdministrarEmpleadoController getControllerEmpleados() {
		return controllerEmpleados;
	}

	public void setControllerEmpleados(AdministrarEmpleadoController controllerEmpleados) {
		this.controllerEmpleados = controllerEmpleados;
	}

}
