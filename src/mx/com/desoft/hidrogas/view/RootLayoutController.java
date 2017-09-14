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
import javafx.stage.FileChooser;
import mx.com.desoft.hidrogas.MainApp;

import java.io.File;
import java.io.IOException;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 *
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }

    @FXML
    private void showEmpleadosOverView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminEmpleados.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK, 
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
//            AministrarEmpleadosController controller = loader.getController();
//            controller.setMainApp(mainApp);
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
//            controller.setMainApp(mainApp);
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
//            controller.setMainApp(mainApp);
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
}