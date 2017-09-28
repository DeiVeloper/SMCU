package mx.com.desoft.hidrogas.buttons;


import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;
import mx.com.desoft.hidrogas.property.SeguimientoOrdenPartesProperty;

public class ButtonCell extends TableCell<SeguimientoOrdenPartesProperty, Boolean> {



	final Button cellButton = new Button("Eliminar");

	public ButtonCell(ObservableList<SeguimientoOrdenPartesProperty> dataPartesUsadas, List<SeguimientoOrdenPartesProperty> dtoPartesUsadas){

    	//Action when the button is pressed
        cellButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {
            	String context ="¿Está seguro de eliminar el registro?";
        		ButtonType aceptar = new ButtonType("Aceptar");
        		ButtonType cancelar = new ButtonType("Cancelar");
        		Alert alert = new Alert(AlertType.CONFIRMATION,context, aceptar, cancelar);
            	alert.setTitle("Confirmación");
            	alert.setHeaderText(null);
            	Optional<ButtonType> result = alert.showAndWait();
            	if (result.isPresent() && result.get().getText() == "Aceptar") {
                    // get Selected Item
                	SeguimientoOrdenPartesProperty refaccion = (SeguimientoOrdenPartesProperty) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	dataPartesUsadas.remove(refaccion);
                	dtoPartesUsadas.remove(refaccion);
            	}
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }else{
        	setGraphic(null);
        }
    }

}
