package mx.com.desoft.hidrogas;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.view.SeguimientoOrdenTrabajoController;
import mx.com.desoft.hidrogas.view.AdministrarOrdenTrabajoController;
import mx.com.desoft.hidrogas.view.AgregarEditarOrdenController;
import mx.com.desoft.hidrogas.view.RootLayoutController;

public class MainApp  {

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
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("SMCU - Sistema de Mantenimiento y Control de Unidades");
            primaryStage.getIcons().add(new Image("file:resources/images/ic_launcher.png"));
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            primaryStage.centerOnScreen();
            primaryStage.setMaximized(true);
            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre la pantlla de orden de trabajo.
     */
    public void showAdministrarOrdenTrabajo() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdministrarOrdenTrabajo.fxml"));
            AnchorPane orden = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(orden);

            // Give the controller access to the main app.
            AdministrarOrdenTrabajoController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre la pantlla de seguimiento de orden de trabajo.
     */
    public void showSeguimientoOrdenTrabajo(OrdenTrabajoDTO ordenDTO) {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SeguimientoOrdenTrabajo.fxml"));
            AnchorPane orden = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(orden);

            // Give the controller access to the main app.
            SeguimientoOrdenTrabajoController controller = loader.getController();
            controller.setMainApp(this);
            controller.setOrdenDTO(ordenDTO);
            controller.cargarInformacion();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Regresa a la pantalla de administracion de orden de trabajo.
     */
    public void cancelarOrdenTrabajo() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdministrarOrdenTrabajo.fxml"));
            AnchorPane orden = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(orden);

            // Give the controller access to the main app.
            AdministrarOrdenTrabajoController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre la pantlla de orden de trabajo.
     */
    public void showOrdenTrabajo() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OrdenTrabajo.fxml"));
            AnchorPane orden = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(orden);

            // Give the controller access to the main app.
            AgregarEditarOrdenController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
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