package mx.com.desoft.hidrogas.buttons;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;
import mx.com.desoft.hidrogas.view.AdministrarEmpleadoController;

public class EditarButtonEmpleado extends TableCell<EmpleadoProperty, Boolean> {


	Image imageEditar = new Image("file:resources/images/edit.png");
	final Button buttonEditar = new Button("", new ImageView(imageEditar));
	private AdministrarEmpleadoController controllerEmpleados;

	public EditarButtonEmpleado(ObservableList<EmpleadoProperty> dataEmpleados){
		controllerEmpleados = new AdministrarEmpleadoController();
		buttonEditar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
	        public void handle(ActionEvent t) {
	        	EmpleadoProperty empleado = (EmpleadoProperty) EditarButtonEmpleado.this.getTableView().getItems().get(EditarButtonEmpleado.this.getIndex());
	        	EmpleadoDTO dto = controllerEmpleados.handleEditPerson(new EmpleadoDTO(Integer.parseInt(empleado.getNominaEmpleado().getValue()),
	        			empleado.getNombreEmpleado().getValue(),
	        			empleado.getApellidoPaterno().getValue(),
	        			empleado.getApellidoMaterno().getValue(),
	        			empleado.getPassword().getValue(),
	        			!empleado.getEconomicoId().getValue().equals(" - ") ? Integer.parseInt(empleado.getEconomicoId().getValue()): 0,
						new Date(),
						Integer.parseInt(empleado.getNoNominaRegistro().getValue()),
						Integer.parseInt(empleado.getTipoEmpleadoId().getValue()),
						empleado.getTipoEmpleadoDescripcion().getValue()));

		        	EmpleadoProperty prty = new  EmpleadoProperty(new SimpleStringProperty(dto.getNominaEmpleado().toString()),
		        			new SimpleStringProperty(dto.getNombreEmpleado()),
		            		new SimpleStringProperty(dto.getApellidoPatEmpleado()),
		            		new SimpleStringProperty(dto.getApellidoMatEmpleado()),
		            		new SimpleStringProperty(dto.getPassword()),
		            		new SimpleStringProperty(dto.getEconomicoId().toString()),
		            		new SimpleStringProperty(dto.getFechaRegistro().toString()),
		            		new SimpleStringProperty(dto.getNominaRegistro().toString()),
		            		new SimpleStringProperty(dto.getTipoEmpleadoId().toString()),
		            		new SimpleStringProperty(dto.getDescripcionTipoEmpleado()));
		        	dataEmpleados.set(EditarButtonEmpleado.this.getIndex(), prty);
	    	}
		});
	}

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(buttonEditar);
        }else{
        	setGraphic(null);
        }
    }



}
