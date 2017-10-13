package mx.com.desoft.hidrogas.util;

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

}

