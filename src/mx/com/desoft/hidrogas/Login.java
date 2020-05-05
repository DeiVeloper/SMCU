package mx.com.desoft.hidrogas;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login extends Application {

	private static final Logger log = Logger.getLogger(Login.class);

	public static Stage stageLogin;
	private Pane login;
	public static AnnotationConfigApplicationContext appContext;

	@Override
	public void start(Stage primaryStage) {
		stageLogin = primaryStage;
		this.inicializarLogin();

	}

	public static void main(String[] args) {
		try {
            initSpringContextWithAnnotations();
            launch(args);
        } catch(Exception e) {
            log.error("Error: Al iniciar aplicación", e);
        }
	}

	public void inicializarLogin(){
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/LoginLayout.fxml"));
            login = (Pane) loader.load();
            Scene scene = new Scene(login);
            scene.getStylesheets().add("file:resources/css/login.css");
            stageLogin.setScene(scene);
            stageLogin.setResizable(false);
            stageLogin.setTitle("HidroGas");
            stageLogin.getIcons().add(new Image("file:resources/images/ic_launcher.png"));
            stageLogin.centerOnScreen();
            stageLogin.show();
        } catch (Exception e) {
        	log.error("Error: No se pudo iniciar la pantalla de Login.", e);
        }
	}
	
	private static void initSpringContextWithAnnotations() {
		appContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		appContext.start();
    }
	
}
