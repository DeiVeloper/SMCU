package mx.com.desoft.hidrogas.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;

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

	@FXML
	private void initialize() {
		this.inicializarEventosBoton();
	}

	private void iniciarSession() {
		if	("admin".equals(userNameField.getText().toString()) && "admin".equals(passordField.getText().toString())){
			MainApp app = new MainApp();
			app.initRootLayout();
			Login.stageLogin.close();
		}
	}

	private void inicializarEventosBoton(){
		iniciarSession.setOnKeyPressed(k ->{
			final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
	            if (ENTER.match(k)) {
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
