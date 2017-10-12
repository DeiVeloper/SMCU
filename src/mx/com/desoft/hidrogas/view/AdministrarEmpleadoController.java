package mx.com.desoft.hidrogas.view;

import java.util.List;
import java.util.Optional;


import org.apache.log4j.Logger;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.CatalogoBusinessImpl;
import mx.com.desoft.hidrogas.business.EmpleadoBusinessImpl;
import mx.com.desoft.hidrogas.business.ICatalogoBusiness;
import mx.com.desoft.hidrogas.business.IEmpleadoBusiness;
import mx.com.desoft.hidrogas.buttons.EditarButtonEmpleado;
import mx.com.desoft.hidrogas.buttons.EliminarButtonEmpleado;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;

public class AdministrarEmpleadoController {

	private static final Logger log = Logger.getLogger(AdministrarEmpleadoController.class);

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
    private TableColumn<EmpleadoProperty, Boolean> editarColumn;

    @FXML
    private TableColumn<EmpleadoProperty, Boolean> eliminarColumn;

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

    public AdministrarEmpleadoController() {
    	tablaEmpleados = new TableView<>(data);
    	noNominaColumn = new TableColumn<EmpleadoProperty, String>();
    	nombreColumn = new TableColumn<EmpleadoProperty, String>();
    	tipoEmpleadoColumn = new TableColumn<EmpleadoProperty, String>();
    	economicoColumn = new TableColumn<EmpleadoProperty, String>();
    	editarColumn = new TableColumn<EmpleadoProperty, Boolean>();
    	eliminarColumn = new TableColumn<EmpleadoProperty, Boolean>();
    	totalLista = new Label();
    	noNominaField = new TextField();
    	nombreField= new TextField();
    	tipoEmpleadoField = new ComboBox<CatTipoEmpleadoDTO>();
    	economicoField = new ComboBox<>();
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
    	tablaEmpleados.getItems().clear();
    	noNominaColumn.setCellValueFactory(cellData -> cellData.getValue().getNominaEmpleado());
    	nombreColumn.setCellValueFactory(cellData -> cellData.getValue().getNombreEmpleado().concat(" ")
    			.concat(cellData.getValue().getApellidoPaterno()).concat(" ")
    			.concat(cellData.getValue().getApellidoMaterno()));
    	tipoEmpleadoColumn.setCellValueFactory(cellData -> cellData.getValue().getTipoEmpleadoDescripcion());
    	economicoColumn.setCellValueFactory(cellData -> cellData.getValue().getEconomicoId());
    	editarColumn.setSortable(false);
    	editarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmpleadoProperty, Boolean>, ObservableValue<Boolean>>() {
    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<EmpleadoProperty, Boolean> features) {
    			return new SimpleBooleanProperty(features.getValue() != null);
	      }
	    });
    	editarColumn.setCellFactory(new Callback<TableColumn<EmpleadoProperty, Boolean>, TableCell<EmpleadoProperty, Boolean>>() {
    		@Override public TableCell<EmpleadoProperty, Boolean> call(TableColumn<EmpleadoProperty, Boolean> personBooleanTableColumn) {
    			return new EditarButtonEmpleado(data);
    		}
	    });
    	editarColumn.setStyle("-fx-alignment: CENTER;");
    	eliminarColumn.setStyle("-fx-alignment: CENTER;");
    	eliminarColumn.setSortable(false);
    	eliminarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmpleadoProperty, Boolean>, ObservableValue<Boolean>>() {
    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<EmpleadoProperty, Boolean> features) {
    			return new SimpleBooleanProperty(features.getValue() != null);
	      }
	    });
    	eliminarColumn.setCellFactory(new Callback<TableColumn<EmpleadoProperty, Boolean>, TableCell<EmpleadoProperty, Boolean>>() {
    		@Override public TableCell<EmpleadoProperty, Boolean> call(TableColumn<EmpleadoProperty, Boolean> personBooleanTableColumn) {
    			return new EliminarButtonEmpleado(data);
    		}
	    });
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

    public boolean eliminarEmpleado(Integer id) {
    	String context ="¿Esta seguro de eliminar el registro?";
		ButtonType aceptar = new ButtonType("Aceptar");
		ButtonType cancelar = new ButtonType("Cancelar");
		Alert alert = new Alert(AlertType.CONFIRMATION,context, aceptar, cancelar);
    	alert.setTitle("Confirmación");
    	alert.setHeaderText(null);
    	Optional<ButtonType> result = alert.showAndWait();
    	 if (result.isPresent() && result.get().getText() == "Aceptar") {
    		 empleadoBusinessImpl.eliminarEmpleado(id);
    	 }	else	{
    		 return false;
    	 }
    	 return true;
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
             controller.getDialogStage().focusedProperty();
             dialogStage.showAndWait();
		} catch (Exception e) {
			log.error(e);
			Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
		}
    }

    public EmpleadoDTO handleEditPerson(EmpleadoDTO empleado) {
    	AgregarEditarEmpleadoController controller = null;
    	try {

        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AgregarEditarEmpleado.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setearEdicionEmpleado(empleado);
            dialogStage.showAndWait();

    	} catch (Exception e) {
    		log.error(e);
    		Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
		}
    	return controller.convertirCamposViewToDTO();
    }

    @FXML
    private void limpiarCamposView() {
    	noNominaField.clear();
    	nombreField.clear();
	 	tipoEmpleadoField.getSelectionModel().clearSelection();
	 	economicoField.getSelectionModel().clearSelection();
	 	tablaEmpleados.getItems().clear();
	 	totalLista.setText(null);
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
    	empleadoDTO.setDescripcionTipoEmpleado(tipoEmpleadoField.getSelectionModel().getSelectedItem() != Constantes.NULL ?
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