package mx.com.desoft.hidrogas.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private void initialize() {

	}

	/**
	 * Creates an empty address book.
	 */
	@FXML
	private void iniciarSession() {
		if	("admin".equals(userNameField.getText().toString()) && "admin".equals(passordField.getText().toString())){
			MainApp app = new MainApp();
			app.initRootLayout();
			Login.stageLogin.close();
		}
	}

}