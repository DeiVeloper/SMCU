package mx.com.desoft.hidrogas.buttons;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.com.desoft.hidrogas.property.TipoNecesidadProperty;
import mx.com.desoft.hidrogas.view.AdministrarTipoNecesidadController;

public class EliminarButtonTipoNecesidad extends TableCell<TipoNecesidadProperty, Boolean> {

	final Image imageEliminar = new Image("file:resources/images/delete.png");
	final Button buttonEliminar = new Button("", new ImageView(imageEliminar));
	private AdministrarTipoNecesidadController controller;

	public EliminarButtonTipoNecesidad(ObservableList<TipoNecesidadProperty> data){
		controller = new AdministrarTipoNecesidadController();
		buttonEliminar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
            	TipoNecesidadProperty tipoNecesidad = (TipoNecesidadProperty) EliminarButtonTipoNecesidad.this.getTableView().getItems().get(EliminarButtonTipoNecesidad.this.getIndex());
            	boolean bandera = controller.eliminarTipoNecesidad(Integer.parseInt(tipoNecesidad.getTipoNecesidadId().getValue().toString()));
            	if(bandera){
            		data.remove(tipoNecesidad);
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
