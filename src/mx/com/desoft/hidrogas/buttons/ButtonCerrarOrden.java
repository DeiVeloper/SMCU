package mx.com.desoft.hidrogas.buttons;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.com.desoft.hidrogas.property.OrdenProperty;

public class ButtonCerrarOrden extends TableCell<OrdenProperty, Boolean> {

//	private AdministrarOrdenTrabajoController administrarOrdenController;
	final Image imageEliminar = new Image("file:resources/images/cerrar.png");
	final Button cellButton = new Button("", new ImageView(imageEliminar));
//	private boolean elimina;

	public ButtonCerrarOrden(ObservableList<OrdenProperty> dataOrdenes,
			List<OrdenProperty> dtoPartesOrdenes) {

		// Action when the button is pressed
		cellButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
//				administrarOrdenController = new AdministrarOrdenTrabajoController();
//				// get Selected Item
//				OrdenProperty orden = (OrdenProperty) ButtonEliminarOrden.this
//						.getTableView().getItems().get(ButtonEliminarOrden.this.getIndex());
//				elimina = administrarOrdenController.eliminaOrden(Integer.parseInt(orden.getFolioOrden().getValue()));
//				if(elimina) {
//					// remove selected item from the table list
//					dataOrdenes.remove(orden);
//					dtoPartesOrdenes.remove(orden);
//				}
			}
		});
	}

	// Display button if the row is not empty
	@Override
	protected void updateItem(Boolean t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			setGraphic(cellButton);
		} else {
			setGraphic(null);
		}
	}

}
