package mx.com.desoft.hidrogas.buttons;

import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.property.TipoRefaccionProperty;
import mx.com.desoft.hidrogas.view.AdministrarTipoRefaccionController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EditarButtonTipoRefaccion extends TableCell<TipoRefaccionProperty, Boolean> {
	
	Image imageEditar = new Image("file:resources/images/edit.png");
	final Button buttonEditar = new Button("", new ImageView(imageEditar));
	private AdministrarTipoRefaccionController administrarTipoRefaccionController;
	
	public EditarButtonTipoRefaccion (ObservableList<TipoRefaccionProperty> dataRefaccion) {
		administrarTipoRefaccionController = new AdministrarTipoRefaccionController();
		buttonEditar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
	        public void handle(ActionEvent t) {
				TipoRefaccionProperty refaccion = (TipoRefaccionProperty) EditarButtonTipoRefaccion.this.getTableView().getItems().get(EditarButtonTipoRefaccion.this.getIndex());
				CatTipoRefaccionesDTO refaccionDto = administrarTipoRefaccionController.handleEditRefaccion(new CatTipoRefaccionesDTO(
						Integer.parseInt(refaccion.getTipoRefaccionId().getValue()), 
						refaccion.getDescripcion().getValue(),
						Integer.parseInt(refaccion.getCantidad().getValue())));
				TipoRefaccionProperty refaccionPro = new TipoRefaccionProperty(
						new SimpleStringProperty(Integer.toString(refaccionDto.getTipoRefaccionId())),
						new SimpleStringProperty(refaccionDto.getDescripcion()),
						new SimpleStringProperty(Integer.toString(refaccionDto.getCantidad())));
				dataRefaccion.set(EditarButtonTipoRefaccion.this.getIndex(), refaccionPro);
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
