package mx.com.desoft.hidrogas.view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.bussines.BussinesService;
import mx.com.desoft.hidrogas.bussines.BussinesServiceImpl;
import mx.com.desoft.hidrogas.bussines.EmpleadoBussinesService;
import mx.com.desoft.hidrogas.bussines.EmpleadoBussinesServiceImpl;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;

public class AministrarEmpleados {

    @FXML
    private TableView<EmpleadoProperty> tablaEmpleados;
    
    @FXML
    private TableColumn<EmpleadoProperty, String> noNominaColumn;
    
    @FXML
    private TableColumn<EmpleadoProperty, String> nombreColumn;
    
    @FXML
    private TableColumn<EmpleadoProperty, String> tipoEmpleadoColumn;
    
    @FXML
    private TableColumn<EmpleadoProperty, String> economicoColumn;

    @FXML
    private TextField noNominaField;
    
    @FXML
    private TextField nombreField;
    
    @FXML
    private ComboBox<CatTipoEmpleadoDTO> tipoEmpleadoField;
    
    @FXML
    private ComboBox<EconomicoDTO> economicoField;
    
    // Reference to the main application.
    private MainApp mainApp;
    private EmpleadoDTO empleadoDTO;
    private EmpleadoBussinesService empleadoBussinesServiceImpl = Login.appContext.getBean(EmpleadoBussinesServiceImpl.class);
    private ObservableList<EmpleadoProperty> data = FXCollections.observableArrayList();
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AministrarEmpleados() {
    	
    }
   
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	 
    	empleadoDTO = new EmpleadoDTO();
    	empleadoDTO.setNominaEmpleado(null);
    	empleadoDTO.setNombreEmpleado(null);
    	empleadoDTO.setEconomicoId(null);
    	empleadoDTO.setDescripcionTipoEmpleado(null);
    	List<EmpleadoProperty> dto = empleadoBussinesServiceImpl.buscarEmpleadoByVista(empleadoDTO);
    	data.clear();
    	data.addAll(dto);
    	
//    	noNominaColumn.setCellValueFactory(cellData -> cellData.getValue().getNominaEmpleado());
//    	nombreColumn.setCellValueFactory(cellData -> cellData.getValue().getNombreEmpleado());
//    	tipoEmpleadoColumn.setCellValueFactory(cellData -> cellData.getValue().getTipoEmpleado());
//    	economicoColumn.setCellValueFactory(cellData -> cellData.getValue().getEconomicoId());
        // Initialize the person table with the two columns.
//        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
//        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

     // Clear person details.
//        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
      
    }
    
    
    @FXML
    private void buscarEmpleados() {
//    	empleadoDTO = new EmpleadoDTO();
//    	empleadoDTO.setNominaEmpleado(null);
//    	empleadoDTO.setNombreEmpleado(null);
//    	empleadoDTO.setEconomicoId(null);
//    	empleadoDTO.setDescripcionTipoEmpleado(null);
//    	List<EmpleadoDTO> dto = empleadoBussinesServiceImpl.buscarEmpleadoByVista(empleadoDTO);
//    	data.clear();
//    	data.addAll(dto);
//    	tablaEmpleados = new TableView<>();
//    	tablaEmpleados.setItems(data);
    	tablaEmpleados.setItems(getData());
    	noNominaColumn.setCellValueFactory(cellData -> cellData.getValue().getNominaEmpleado());
    	nombreColumn.setCellValueFactory(cellData -> cellData.getValue().getNombreEmpleado());
    	tipoEmpleadoColumn.setCellValueFactory(cellData -> cellData.getValue().getTipoEmpleado());
    	economicoColumn.setCellValueFactory(cellData -> cellData.getValue().getEconomicoId());
    	//tablaEmpleados.refresh();
//    	  tablaEmpleados.getSelectionModel().selectedItemProperty().addListener(
//                  (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(EmpleadoProperty person) {
        if (person != null) {
            // Fill the labels with info from the person object.
//            firstNameLabel.setText(person.getFirstName());
//            lastNameLabel.setText(person.getLastName());
//            streetLabel.setText(person.getStreet());
//            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
//            cityLabel.setText(person.getCity());
//            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
//            firstNameLabel.setText("");
//            lastNameLabel.setText("");
//            streetLabel.setText("");
//            postalCodeLabel.setText("");
//            cityLabel.setText("");
//            birthdayLabel.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = tablaEmpleados.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	tablaEmpleados.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("No Selection");
        	alert.setHeaderText(null);
        	alert.setContentText("Please select a person in the table.");
        	alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
    	try {
    		EmpleadoProperty tempPerson = new EmpleadoProperty();
           mainApp.showPersonEditDialog(tempPerson);
        
		} catch (Exception e) {
			new Throwable(e.getMessage());
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("DeSoft.ink");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Error.." + e);
	    	alert.showAndWait();
		}
    	

//        Person tempPerson = new Person();
//        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
//        if (okClicked) {
//            mainApp.getPersonData().add(tempPerson);
//        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        EmpleadoProperty selectedPerson = tablaEmpleados.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Editando Personal");
        	alert.setHeaderText(null);
        	alert.setContentText("Please select a person in the table.");
        	alert.showAndWait();
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        
    }

	public ObservableList<EmpleadoProperty> getData() {
		return data;
	}

	public void setData(ObservableList<EmpleadoProperty> data) {
		this.data = data;
	}

}