package mx.com.desoft.hidrogas.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.bussines.LoginBussinesService;
import mx.com.desoft.hidrogas.bussines.LoginBussinesServiceImpl;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;

public class LoginController {

	@FXML
	private Label userName;
	@FXML
	private Label password;
	@FXML
	private TextField userNameField;
	@FXML
	private TextField passordField;
	@FXML
	private Button iniciarSession;
	
	private LoginBussinesService loginBussinesServiceImpl = Login.appContext.getBean(LoginBussinesServiceImpl.class);
	
	@FXML
	private void initialize() {
		this.inicializarEventosBoton();
		
	}

	private void iniciarSession() {
//		if	(validarFormulario()){
//			if	(validarSesion(Integer.parseInt(userNameField.getText()), passordField.getText())) {
				MainApp app = new MainApp();
				app.initRootLayout();
				Login.stageLogin.close();
//			}	else	{
//				new Alerta("Iniciar Sesión", "Usuario y/o Contraseña incorrectos, Favor de validar", AlertType.ERROR);
//			}
//		}
	}
	
	private Boolean validarSesion(Integer usuario, String password) {
		return loginBussinesServiceImpl.validarsesion(usuario, password);
	}
	
	private Boolean validarFormulario() {
		String errorMessage = "";
		if(userNameField.getText() == Constantes.NULL || userNameField.getText().length() == Constantes.CERO)	{
			errorMessage += "Favor de capturar un Ususario";
		}
		
		if(passordField.getText() == Constantes.NULL || passordField.getText().length() == Constantes.CERO)	{
			errorMessage += "Favor de capturar una Contraseña";
		}
		
		if (errorMessage.length() == Constantes.CERO) {
            return true;
        } else {
        	new Alerta("Validando Formulario", errorMessage, AlertType.WARNING);
            return false;
        }
	}

	private void inicializarEventosBoton(){
		iniciarSession.setOnKeyPressed(e ->{
			final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
	            if (ENTER.match(e)) {
	            	iniciarSession();
	            }
		});

		iniciarSession.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	iniciarSession();
		    }
		});
	}
}
