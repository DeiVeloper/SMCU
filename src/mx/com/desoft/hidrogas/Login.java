package mx.com.desoft.hidrogas;


import java.io.IOException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.dao.EmpleadosDAO;
import mx.com.desoft.hidrogas.dao.EmpleadosImplDAO;
import mx.com.desoft.hidrogas.model.Empleado;

public class Login extends Application {
	
	/**
     * Logger para la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(Login.class);
 
	
	public static Stage stageLogin;
	private BorderPane login;
	public static AnnotationConfigApplicationContext appContext;
	

	@Override
	public void start(Stage primaryStage) {
		stageLogin = primaryStage;
		stageLogin.setTitle("HidroGas");
		stageLogin.getIcons().add(new Image("file:resources/images/ic_launcher.png"));
		this.inicializarLogin();
		
	}

	public static void main(String[] args) {
		try {
            initSpringContextWithAnnotations();
            
            
        } catch(Exception ex) {
            // TODO: Aqui se colocan las acciones a tomar cuando existe un
            // error en el arranque.
             
            ex.printStackTrace();
        }
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
	
	private static void initSpringContextWithAnnotations() {
         appContext =
            new AnnotationConfigApplicationContext(SpringConfig.class);
 
        appContext.start();
        System.out.println("dflmnvlkdfngklf"+appContext.getAutowireCapableBeanFactory());
        
        
        String [] a = appContext.getBeanDefinitionNames();
        for (String string : a) {
			System.out.println(string.toString());
		}
        
        LOGGER.info("A continuacion registramos el shutdown hook.");
        /*
         * Permite que se ejecuten los metodos anotados con predestroy al
         * finalizar el contexto.
         */
       // appContext.registerShutdownHook();
    }

	
}
