package mx.com.desoft.hidrogas;

import org.apache.log4j.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.view.SeguimientoOrdenTrabajoController;
import mx.com.desoft.hidrogas.view.AdministrarOrdenTrabajoController;
import mx.com.desoft.hidrogas.view.AgregarEditarOrdenController;
import mx.com.desoft.hidrogas.view.RootLayoutController;

public class MainApp  {

	private static final Logger log = Logger.getLogger(MainApp.class);

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("SMCU - Sistema de Mantenimiento y Control de Unidades");
            primaryStage.getIcons().add(new Image("file:resources/images/ic_launcher.png"));
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setMaximized(true);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (Exception e) {
        	log.error("Error: No se pudo iniciar el Layout.", e);
        }
    }

    /**
     * Abre la pantlla de orden de trabajo.
     */
    public void showAdministrarOrdenTrabajo() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdministrarOrdenTrabajo.fxml"));
            AnchorPane orden = (AnchorPane) loader.load();
            rootLayout.setCenter(orden);
            AdministrarOrdenTrabajoController controller = loader.getController();
            controller.setMainApp(this);

        } catch (Exception e) {
        	log.error("Error: No se pudo iniciar la pantalla de Aministracion de Orde de Trabajo.", e);
        }
    }

    /**
     * Abre la pantlla de seguimiento de orden de trabajo.
     */
    public void showSeguimientoOrdenTrabajo(OrdenTrabajoDTO ordenDTO) {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SeguimientoOrdenTrabajo.fxml"));
            AnchorPane orden = (AnchorPane) loader.load();
            rootLayout.setCenter(orden);
            SeguimientoOrdenTrabajoController controller = loader.getController();
            controller.setMainApp(this);
            controller.setOrdenDTO(ordenDTO);
            controller.cargarInformacion();

        } catch (Exception e) {
        	log.error("Error: No se pudo iniciar la pantalla de Seguimiento Orden de Trabajo.", e);
        }
    }

    /**
     * Regresa a la pantalla de administracion de orden de trabajo.
     */
    public void cancelarOrdenTrabajo() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdministrarOrdenTrabajo.fxml"));
            AnchorPane orden = (AnchorPane) loader.load();
            rootLayout.setCenter(orden);
            AdministrarOrdenTrabajoController controller = loader.getController();
            controller.setMainApp(this);

        } catch (Exception e) {
        	log.error("Error: No se pudo iniciar la pantalla de Aministracion de Orden de Trabajo.", e);
        }
    }

    /**
     * Abre la pantlla de orden de trabajo.
     */
    public void showOrdenTrabajo() {
    	try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OrdenTrabajo.fxml"));
            AnchorPane orden = (AnchorPane) loader.load();
            rootLayout.setCenter(orden);
            AgregarEditarOrdenController controller = loader.getController();
            controller.setMainApp(this);

        } catch (Exception e) {
        	log.error("Error: No se pudo iniciar la pantalla de Orden de Trabajo.", e);
        }
    }

    public MainApp() {
    	this.primaryStage = new Stage();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

}