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
import mx.com.desoft.hidrogas.util.Constantes;

public class ButtonUsarPzaSolicitada extends TableCell<SeguimientoOrdenPartesProperty, Boolean> {
	
	final Image imageEliminar = new Image("file:resources/images/delete.png");
	final Button cellButton = new Button("", new ImageView(imageEliminar));
	private SeguimientoOrdenPartesProperty refaccion;

	public ButtonUsarPzaSolicitada(ObservableList<SeguimientoOrdenPartesProperty> dataPartesSolicitadas,
			List<SeguimientoOrdenPartesProperty> dtoPartesSolicitadas, ObservableList<SeguimientoOrdenPartesProperty> dataPartesUsadas,
			List<SeguimientoOrdenPartesProperty> dtoPartesUsadas) {

		// Action when the button is pressed
		cellButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				refaccion = (SeguimientoOrdenPartesProperty) ButtonUsarPzaSolicitada.this
						.getTableView().getItems().get(ButtonUsarPzaSolicitada.this.getIndex());
				dataPartesUsadas.add(refaccion);
				dtoPartesUsadas.add(refaccion);
				dataPartesSolicitadas.remove(refaccion);
				dtoPartesSolicitadas.remove(refaccion);
			}
		});
	}

	// Display button if the row is not empty
	@Override
	protected void updateItem(Boolean t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			refaccion = (SeguimientoOrdenPartesProperty) ButtonUsarPzaSolicitada.this
					.getTableView().getItems().get(ButtonUsarPzaSolicitada.this.getIndex());
			if(refaccion.getIdRefaccion() == Constantes.CERO)
				setGraphic(null);
			else 
				setGraphic(cellButton);
		} else {
			setGraphic(null);
		}
	}

}
