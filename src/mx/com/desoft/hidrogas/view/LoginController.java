package mx.com.desoft.hidrogas.view;

import org.apache.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import mx.com.desoft.hidrogas.Authenticator;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.ILoginBusiness;
import mx.com.desoft.hidrogas.business.LoginBusinessImpl;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;

public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class);

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

	@FXML
	private ImageView logo;

	@FXML
	private ImageView usuarioView;

	@FXML
	private ImageView passwordView;

	private ILoginBusiness loginBussinesServiceImpl = Login.appContext.getBean(LoginBusinessImpl.class);
	private String mensaje = "";

	public LoginController() {
		logo = new ImageView();
		usuarioView = new ImageView();
		passwordView = new ImageView();
	}

	@FXML
	private void initialize() {
		logo.setImage(new Image("file:resources/images/ic_launcher.png"));
		usuarioView.setImage(new Image("file:resources/images/user.png"));
		passwordView.setImage(new Image("file:resources/images/password.png"));
		this.inicializarEventosBoton();

	}

	private void iniciarSession() {
		try {
			if	(validarFormulario()){
				if	(validarSesion(Integer.parseInt(userNameField.getText()), passordField.getText())) {
					this.setearSesion(Integer.parseInt(userNameField.getText()));
					MainApp app = new MainApp();
					app.initRootLayout();
					Login.stageLogin.close();
				}	else	{
					Alerta.crearAlertaUsuario(Constantes.INICIAR_SESION, Constantes.LOGIN, AlertType.ERROR);
				}
			}	else	{
					Alerta.crearAlertaUsuario(Constantes.VALIDANDO_FORMULARIO, mensaje, AlertType.WARNING);
			}
		} catch (Exception e) {
			log.error("Error al iniciar sesion", e);
			Alerta.crearAlertaUsuario("Error", "Ocurrio un error al iniciar sesion", AlertType.ERROR);
		}
	}

	private boolean validarSesion(Integer usuario, String password) {
		return loginBussinesServiceImpl.validarSesion(usuario, password);
	}

	private boolean validarFormulario() {
		if(!userNameField.getText().matches("[0-9]*")){
			mensaje = "El No. Nomina debe de ser numerico, favor de validar.";
			return false;
		}
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
	
	private void setearSesion(int noNomina){
		Authenticator.usuarioSesion = loginBussinesServiceImpl.getEmpleadoSesion(noNomina);
	}
}
