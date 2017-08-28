package mx.com.desoft.hidrogas;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.model.Person;
import mx.com.desoft.hidrogas.model.PersonListWrapper;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;
import mx.com.desoft.hidrogas.view.OrdenTrabajoController;
import mx.com.desoft.hidrogas.view.AgregarEditarEmpleadoController;
import mx.com.desoft.hidrogas.view.RootLayoutController;

public class MainApp  {

    private Stage primaryStage;
    private BorderPane rootLayout;
	private ObservableList<EmpleadoDTO> data = FXCollections.observableArrayList();

    public MainApp() {
    	this.primaryStage = new Stage();
    }

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

        // Try to load last opened person file.
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(EmpleadoProperty person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AgregarEditarEmpleado.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AgregarEditarEmpleadoController controller = loader.getController();
            controller.setDialogStage(dialogStage);
//            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
            OrdenTrabajoController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Abre la pantlla de seguimiento de orden de trabajo.
     */
    public void showSeguimientoOrdenTrabajo() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SeguimientoOrdenTrabajo.fxml"));
            AnchorPane orden = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(orden);

            // Give the controller access to the main app.
            OrdenTrabajoController controller = loader.getController();
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
            OrdenTrabajoController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
//            primaryStage.setTitle("HidroGas");
        } else {
            prefs.remove("filePath");

            // Update the stage title.
//            primaryStage.setTitle("HidroGas");
        }
    }


    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     *
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            data.clear();
            data.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
           e.printStackTrace();
        }
    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(data);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<EmpleadoDTO> getData() {
        return data;
    }

    public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

}