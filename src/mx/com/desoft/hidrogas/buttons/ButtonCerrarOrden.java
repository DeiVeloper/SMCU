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
import mx.com.desoft.hidrogas.util.Constantes;
import mx.com.desoft.hidrogas.view.AdministrarOrdenTrabajoController;

public class ButtonCerrarOrden extends TableCell<OrdenProperty, Boolean> {

//	private AdministrarOrdenTrabajoController administrarOrdenController;
	final Image imageEliminar = new Image("file:resources/images/cerrar.png");
	final Button cellButton = new Button("", new ImageView(imageEliminar));
	private AdministrarOrdenTrabajoController administrarOrdenController;
	private OrdenProperty orden;

	public ButtonCerrarOrden(ObservableList<OrdenProperty> dataOrdenes, List<OrdenProperty> dtoPartesOrdenes) {
		cellButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				administrarOrdenController = new AdministrarOrdenTrabajoController();
				// get Selected Item
				orden = (OrdenProperty) ButtonCerrarOrden.this.getTableView().getItems().get(ButtonCerrarOrden.this.getIndex());
				boolean cerrado = administrarOrdenController.cerrarOrden(Integer.parseInt(orden.getFolioOrden().getValue()));
				if(cerrado){
					cellButton.setVisible(false);
				}
				dtoPartesOrdenes.set(ButtonCerrarOrden.this.getIndex(), orden);
			}
		});
	}

	// Display button if the row is not empty
	protected void updateItem(Boolean t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			orden = (OrdenProperty) ButtonCerrarOrden.this.getTableView().getItems().get(getIndex());
			if	(orden.getEstatus() == Constantes.ESTATUS_ORDEN_CERRADA){
				setGraphic(null);
			} else	{
				setGraphic(cellButton);
			}
		} else {
			setGraphic(null);
		}
	}

}
