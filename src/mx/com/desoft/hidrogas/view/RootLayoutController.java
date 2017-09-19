package mx.com.desoft.hidrogas.view;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import mx.com.desoft.hidrogas.MainApp;

import java.io.IOException;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 *
 */
public class RootLayoutController {

    private MainApp mainApp;

    @FXML
    private void showEmpleadosOverView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminEmpleados.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showEconomicosOverView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminEconomicos.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showTipoNecesidadView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminTipoNecesidad.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showTipoRefaccionesView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminTipoRefacciones.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void showReportesView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminReportes.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre pantalla de orden de trabajo
     */
    @FXML
    public void nuevaOrden() {
    	mainApp.showAdministrarOrdenTrabajo();
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("DeSoft.ink");
    	alert.setHeaderText(null);
    	alert.setContentText("Desarrollo de Software a tu medida..");
    	alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}