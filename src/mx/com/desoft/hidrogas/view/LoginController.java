package mx.com.desoft.hidrogas.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.ILoginBusiness;
import mx.com.desoft.hidrogas.business.LoginBusinessImpl;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Authenticator;
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

	private ILoginBusiness loginBussinesServiceImpl = Login.appContext.getBean(LoginBusinessImpl.class);
	private String mensaje = "";

	@FXML
	private void initialize() {
		this.inicializarEventosBoton();

	}

	private void iniciarSession() {
		if	(validarFormulario()){
			if	(validarSesion(Integer.parseInt(userNameField.getText()), passordField.getText())) {
				Authenticator.noNominaRegistro = Integer.parseInt(userNameField.getText());
				MainApp app = new MainApp();
				app.initRootLayout();
				Login.stageLogin.close();
			}	else	{
				Alerta.crearAlertaUsuario(Constantes.INICIAR_SESION, Constantes.LOGIN, AlertType.ERROR);
			}
		}	else	{
				Alerta.crearAlertaUsuario(Constantes.VALIDANDO_FORMULARIO, mensaje, AlertType.WARNING);
		}
	}

	private boolean validarSesion(Integer usuario, String password) {
		return loginBussinesServiceImpl.validarSesion(usuario, password);
	}

	private boolean validarFormulario() {
		if(userNameField.getText() == Constantes.NULL || userNameField.getText().length() == Constantes.CERO)	{
			mensaje = "Favor de capturar un Usuario";
			return false;
		}

		if(passordField.getText() == Constantes.NULL || passordField.getText().length() == Constantes.CERO)	{
			mensaje = "Favor de capturar una Contrase�a";
			return false;
		}
        return true;
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
