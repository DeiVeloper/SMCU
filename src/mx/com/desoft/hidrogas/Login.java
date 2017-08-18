package mx.com.desoft.hidrogas;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Login extends Application {
	public static Stage stageLogin;
	private BorderPane login;
 
	@Override
	public void start(Stage primaryStage) {
		stageLogin = primaryStage;
		stageLogin.setTitle("HidroGas");
		stageLogin.getIcons().add(new Image("file:resources/images/ic_launcher.png"));
		this.inicializarLogin();
	}

	public static void main(String[] args) {
		launch(args);
	}


	private void inicializarLogin(){
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Login.class.getResource("view/LoginLayout.fxml"));
            login = (BorderPane) loader.load();
            Scene scene = new Scene(login);
            stageLogin.setScene(scene);
            stageLogin.centerOnScreen();
            stageLogin.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
