package mx.com.desoft.hidrogas.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerta {

	public static void crearAlertaUsuario(String titulo, String mensaje, AlertType type ) {
		ButtonType aceptar = new ButtonType("Aceptar");
		Alert alert = new Alert(type,mensaje, aceptar);
    	alert.setTitle(titulo);
    	alert.setHeaderText(null);
    	alert.showAndWait();
	}

	public static boolean eliminarRegistro(String titulo, String mensaje, AlertType type ) {
		boolean isAccepted = false;
		ButtonType aceptar = new ButtonType("Aceptar");
		ButtonType cancelar = new ButtonType("Cancelar");
		Alert alert = new Alert(type,mensaje, aceptar, cancelar);
    	alert.setTitle(titulo);
    	alert.setHeaderText(null);
    	Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get().getText() == "Aceptar") {
			isAccepted = true;
		}
		return isAccepted;
	}

}

