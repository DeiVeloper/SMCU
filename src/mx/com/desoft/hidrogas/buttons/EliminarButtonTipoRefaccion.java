package mx.com.desoft.hidrogas.buttons;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.com.desoft.hidrogas.property.TipoRefaccionProperty;
import mx.com.desoft.hidrogas.view.AdministrarTipoRefaccionController;

public class EliminarButtonTipoRefaccion extends TableCell<TipoRefaccionProperty, Boolean> {

	final Image imageEliminar = new Image("file:resources/images/delete.png");
	final Button buttonEliminar = new Button("", new ImageView(imageEliminar));
	private AdministrarTipoRefaccionController controller;

	public EliminarButtonTipoRefaccion(ObservableList<TipoRefaccionProperty> data){
		controller = new AdministrarTipoRefaccionController();
		buttonEliminar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
            	TipoRefaccionProperty tipoRefaccion = (TipoRefaccionProperty) EliminarButtonTipoRefaccion.this.getTableView().getItems().get(EliminarButtonTipoRefaccion.this.getIndex());
            	boolean bandera = controller.eliminarTipoNecesidad(Integer.parseInt(tipoRefaccion.getTipoRefaccionId().getValue().toString()));
            	if(bandera){
            		data.remove(tipoRefaccion);
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
