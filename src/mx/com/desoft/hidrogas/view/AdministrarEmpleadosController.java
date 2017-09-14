package mx.com.desoft.hidrogas.view;

import java.util.List;
import java.util.Optional;


import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.bussines.ICatalogoBusiness;
import mx.com.desoft.hidrogas.bussines.CatalogoBusinessImpl;
import mx.com.desoft.hidrogas.bussines.IEmpleadoBusiness;
import mx.com.desoft.hidrogas.bussines.EmpleadoBusinessImpl;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;

import javafx.util.Callback;

public class AdministrarEmpleadosController {
	
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(AgregarEditarEmpleadoController.class);

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
    private TableColumn<EmpleadoProperty, String> editarColumn;

    @FXML
    private TableColumn<EmpleadoProperty, Button> eliminarColumn;
    
    @FXML
    private TextField noNominaField;
    
    @FXML
    private TextField nombreField;
    
    @FXML
    private ComboBox<CatTipoEmpleadoDTO> tipoEmpleadoField;
    
    @FXML
    private ComboBox<EconomicoDTO> economicoField;
    
    @FXML
    private Label totalLista;
    
    private EmpleadoDTO empleadoDTO;
    private IEmpleadoBusiness empleadoBusinessImpl = Login.appContext.getBean(EmpleadoBusinessImpl.class);
    private ICatalogoBusiness catBusinessImpl = Login.appContext.getBean(CatalogoBusinessImpl.class);
    private ObservableList<EmpleadoProperty> data = FXCollections.observableArrayList();
    
    public AdministrarEmpleadosController() {
    	tablaEmpleados = new TableView<>(data);
    	noNominaColumn = new TableColumn<EmpleadoProperty, String>();
    	nombreColumn = new TableColumn<EmpleadoProperty, String>();
    	tipoEmpleadoColumn = new TableColumn<EmpleadoProperty, String>();
    	economicoColumn = new TableColumn<EmpleadoProperty, String>();
    	editarColumn = new TableColumn<EmpleadoProperty, String>();
    	eliminarColumn = new TableColumn<EmpleadoProperty, Button>();
    	totalLista = new Label();
    }
   
    @FXML
    private void initialize() {
    	this.llenarComboEconomico();
    	this.llenarComboCatTipoEmpleado();
    }
    
    @FXML
    private void buscarEmpleados() {
    	if(validarFormulario()) {
    		this.busquedaEmpleadosView(empleadoDTO);
    	}
    	
    }

    public void busquedaEmpleadosView(EmpleadoDTO empleadoDTO) {
    	List<EmpleadoProperty> dto = empleadoBusinessImpl.buscarEmpleadoByVista(empleadoDTO);
    	data.clear();
    	data.removeAll(dto);
    	noNominaColumn.setCellValueFactory(cellData -> cellData.getValue().getNominaEmpleado());
    	nombreColumn.setCellValueFactory(cellData -> cellData.getValue().getNombreEmpleado().concat(" ")
    			.concat(cellData.getValue().getApellidoPaterno()).concat(" ")
    			.concat(cellData.getValue().getApellidoMaterno()));
    	tipoEmpleadoColumn.setCellValueFactory(cellData -> cellData.getValue().getTipoEmpleadoDescripcion());
    	economicoColumn.setCellValueFactory(cellData -> cellData.getValue().getEconomicoId());
    	editarColumn.setCellValueFactory(new PropertyValueFactory<EmpleadoProperty, String>("editar"));
    	editarColumn.setStyle("-fx-alignment: CENTER;");
    	eliminarColumn.setCellValueFactory(new PropertyValueFactory<EmpleadoProperty, Button>("eliminar"));
    	eliminarColumn.setStyle("-fx-alignment: CENTER;");
    	totalLista.setText(String.valueOf(dto.size()));
    	recheck_table();
    	data.addAll(dto);
    	
    	tablaEmpleados.setItems(data);
    }
    
    private TableCell<EmpleadoProperty, String> recheck_table() {
    	nombreColumn.setCellFactory(column -> {
    	    return new TableCell<EmpleadoProperty, String>() {
    	        @Override
    	        protected void updateItem(String item, boolean empty) {
    	            super.updateItem(item, empty);

    	            if (item == null || empty) {
    	                setText(null);
    	            } else {
    	            	setText(item);
    	            }
    	        }
    	    };
    	});
		return null;
    }
    
    public void handleDeletePerson(Integer id) {
    	String context ="�Esta seguro de eliminar el registro?";
		ButtonType aceptar = new ButtonType("Aceptar");
		ButtonType cancelar = new ButtonType("Cancelar");
		Alert alert = new Alert(AlertType.CONFIRMATION,context, aceptar, cancelar);
    	alert.setTitle("Confirmaci�n");
    	alert.setHeaderText(null);
    	Optional<ButtonType> result = alert.showAndWait();
    	 if (result.isPresent() && result.get().getText() == "Aceptar") {
    		 empleadoBusinessImpl.eliminarEmpleado(id);
    		 empleadoDTO = new EmpleadoDTO(null, null, null, null);
    		 this.busquedaEmpleadosView(empleadoDTO);
    	 }	
    }

    @FXML
    private void handleNewPerson() {
    	try {
    		 FXMLLoader loader = new FXMLLoader();
             loader.setLocation(MainApp.class.getResource("view/AgregarEditarEmpleado.fxml"));
             AnchorPane page = (AnchorPane) loader.load();
             Stage dialogStage = new Stage();
             dialogStage.initModality(Modality.WINDOW_MODAL);
             Scene scene = new Scene(page);
             dialogStage.setScene(scene);
             AgregarEditarEmpleadoController controller = loader.getController();
             controller.setDialogStage(dialogStage);
             dialogStage.showAndWait();
		} catch (Exception e) {
			Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
			log.error(e);
		}
    }
    
    public void handleEditPerson(EmpleadoDTO empleado) {
    	try {
    		
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AgregarEditarEmpleado.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AgregarEditarEmpleadoController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setearEdicionEmpleado(empleado);
            dialogStage.showAndWait();
            
    	} catch (Exception e) {
    		Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
    		log.error(e);
		}
    }

    @FXML
    private void limpiarCamposView() {
    	noNominaField.clear();
    	nombreField.clear();
	 	tipoEmpleadoField.getSelectionModel().clearSelection();
	 	economicoField.getSelectionModel().clearSelection();
	 	tablaEmpleados.getItems().clear();
    } 
    
    
    private Boolean validarFormulario() {
    	convertirViewToDTO();
    	return true;
    }
    
    private void convertirViewToDTO() {
    	empleadoDTO = new EmpleadoDTO();
    	empleadoDTO.setNominaEmpleado((!noNominaField.getText().isEmpty() && noNominaField.getText() != Constantes.NULL) ? Integer.parseInt(noNominaField.getText()) : null);
    	empleadoDTO.setNombreEmpleado(nombreField.getText());
    	empleadoDTO.setEconomicoId(economicoField.getSelectionModel().getSelectedItem() != Constantes.NULL ? 
    			economicoField.getSelectionModel().getSelectedItem().getEconomicoId() : null);
    	empleadoDTO.setDescripcionTipoEmpleado(tipoEmpleadoField.getSelectionModel().getSelectedItem() != null ? 
    			tipoEmpleadoField.getSelectionModel().getSelectedItem().getDescripcion() : null);
    	
    }
    
    private void llenarComboCatTipoEmpleado() {
    	tipoEmpleadoField.setItems(FXCollections.observableArrayList(catBusinessImpl.findAllTipoEmpleados()));
    	tipoEmpleadoField.setConverter(new StringConverter<CatTipoEmpleadoDTO>() {
    		@Override
            public String toString(CatTipoEmpleadoDTO tipo) {
            	if (tipo == null) {
            		return null;
            	} else {
            		return tipo.getDescripcion();
            	}
            }

            @Override
            public CatTipoEmpleadoDTO fromString(String string) {
                   return null;
            }
    	});
    }
    
    private void llenarComboEconomico() {
    	economicoField.setItems(FXCollections.observableArrayList(catBusinessImpl.findAllEconomicos()));
    	economicoField.setConverter(new StringConverter<EconomicoDTO>() {
    		@Override
            public String toString(EconomicoDTO tipo) {
            	if (tipo == null) {
            		return null;
            	} else {
            		return tipo.getEconomicoId().toString();
            	}
            }

            @Override
            public EconomicoDTO fromString(String string) {
                   return null;
            }
    	});
    }

}