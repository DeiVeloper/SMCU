package mx.com.desoft.hidrogas.buttons;


import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.com.desoft.hidrogas.property.SeguimientoOrdenPartesProperty;
import mx.com.desoft.hidrogas.view.SeguimientoOrdenTrabajoController;

public class ButtonCell extends TableCell<SeguimientoOrdenPartesProperty, Boolean> {

	private SeguimientoOrdenTrabajoController seguimientoController;
	final Image imageEliminar = new Image("file:resources/images/delete.png");
	final Button cellButton = new Button("", new ImageView(imageEliminar));
	private boolean elimina;

	public ButtonCell(ObservableList<SeguimientoOrdenPartesProperty> dataPartesUsadas,
			List<SeguimientoOrdenPartesProperty> dtoPartesUsadas) {

		// Action when the button is pressed
		cellButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				seguimientoController = new SeguimientoOrdenTrabajoController();
				// get Selected Item
				SeguimientoOrdenPartesProperty refaccion = (SeguimientoOrdenPartesProperty) ButtonCell.this
						.getTableView().getItems().get(ButtonCell.this.getIndex());
				elimina = seguimientoController.eliminaRefaccion(refaccion);
				if(elimina) {
					// remove selected item from the table list
					dataPartesUsadas.remove(refaccion);
					dtoPartesUsadas.remove(refaccion);
				}
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
