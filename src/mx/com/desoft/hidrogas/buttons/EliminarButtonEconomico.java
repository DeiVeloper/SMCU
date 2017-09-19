package mx.com.desoft.hidrogas.buttons;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.com.desoft.hidrogas.property.EconomicoProperty;
import mx.com.desoft.hidrogas.view.AdministrarEconomicoController;

public class EliminarButtonEconomico extends TableCell<EconomicoProperty, Boolean> {

	final Image imageEliminar = new Image("file:resources/images/delete.png");
	final Button buttonEliminar = new Button("", new ImageView(imageEliminar));
	private AdministrarEconomicoController controller;

	public EliminarButtonEconomico(ObservableList<EconomicoProperty> data){
		controller = new AdministrarEconomicoController();
		buttonEliminar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
            	EconomicoProperty economico = (EconomicoProperty) EliminarButtonEconomico.this.getTableView().getItems().get(EliminarButtonEconomico.this.getIndex());
            	boolean bandera = controller.eliminarEconomico(Integer.parseInt(economico.getEconomicoId().getValue().toString()));
            	if(bandera){
            		data.remove(economico);
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
