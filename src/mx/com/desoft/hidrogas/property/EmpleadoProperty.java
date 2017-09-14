package mx.com.desoft.hidrogas.property;

import java.util.Date;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.view.AdministrarEmpleadosController;

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
	private Button editar;
	private Button eliminar;
	private AdministrarEmpleadosController controllerEmpleados;
	
	public EmpleadoProperty() {
		
	}
	
	
	public EmpleadoProperty(StringProperty nominaEmpleado,StringProperty nombreEmpleado, StringProperty apellidoPaterno, StringProperty apellidoMaterno, StringProperty password, 
			StringProperty economicoId, StringProperty fechaRegistro, StringProperty noNominaRegistro, StringProperty tipoEmpleadoId, StringProperty tipoEmpleadoDescripcion, Button editar, Button eliminar) {
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
		this.editar = editar;
		this.eliminar = eliminar;
		
		controllerEmpleados = new AdministrarEmpleadosController();
		
		editar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controllerEmpleados.handleEditPerson(new EmpleadoDTO(Integer.parseInt(nominaEmpleado.getValue()), 
						nombreEmpleado.getValue(), 
						apellidoPaterno.getValue(), 
						apellidoMaterno.getValue(), 
						password.getValue(), 
						!economicoId.getValue().equals(" - ") ? Integer.parseInt(economicoId.getValue()): 0, 
						new Date(),
						Integer.parseInt(noNominaRegistro.getValue()), 
						Integer.parseInt(tipoEmpleadoId.getValue()), 
						tipoEmpleadoDescripcion.getValue()));
			}
		});
		
		eliminar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controllerEmpleados.handleDeletePerson(Integer.parseInt(nominaEmpleado.getValue()));
			}
		});
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
	
	public Button getEditar() {
		return editar;
	}


	public void setEditar(Button editar) {
		this.editar = editar;
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


	public Button getEliminar() {
		return eliminar;
	}


	public void setEliminar(Button eliminar) {
		this.eliminar = eliminar;
	}


	public AdministrarEmpleadosController getControllerEmpleados() {
		return controllerEmpleados;
	}


	public void setControllerEmpleados(AdministrarEmpleadosController controllerEmpleados) {
		this.controllerEmpleados = controllerEmpleados;
	}

}
