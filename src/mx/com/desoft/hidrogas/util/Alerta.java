package mx.com.desoft.hidrogas.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {
	
	public Alerta(String titulo, String mensaje, AlertType type){
		crearAlertaUsuario(titulo, mensaje, type);
	}
	
	private void crearAlertaUsuario(String titulo, String mensaje, AlertType type ) {
		Alert alert = new Alert(type);
    	alert.setTitle(titulo);
    	alert.setHeaderText(null);
    	alert.setContentText(mensaje);
    	alert.showAndWait();
	}

}
