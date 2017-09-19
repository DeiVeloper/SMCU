package mx.com.desoft.hidrogas.buttons;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;
import mx.com.desoft.hidrogas.view.AdministrarEmpleadoController;

public class EliminarButtonEmpleado extends TableCell<EmpleadoProperty, Boolean> {

	final Image imageEliminar = new Image("file:resources/images/delete.png");
	final Button buttonEliminar = new Button("", new ImageView(imageEliminar));
	private AdministrarEmpleadoController controller;

	public EliminarButtonEmpleado(ObservableList<EmpleadoProperty> dataEmpleados){
		controller = new AdministrarEmpleadoController();
		buttonEliminar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
            	EmpleadoProperty empleado = (EmpleadoProperty) EliminarButtonEmpleado.this.getTableView().getItems().get(EliminarButtonEmpleado.this.getIndex());
            	boolean bandera = controller.eliminarEmpleado(Integer.parseInt(empleado.getNominaEmpleado().getValue().toString()));
            	if	(bandera){
            		dataEmpleados.remove(empleado);
            	}
            }
        });
    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(buttonEliminar);
        }else{
        	setGraphic(null);
        }
    }

}
