package mx.com.desoft.hidrogas.view;

import java.util.List;

import org.apache.log4j.Logger;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.EconomicoBusinessImpl;
import mx.com.desoft.hidrogas.business.IEconomicoBusiness;
import mx.com.desoft.hidrogas.buttons.EliminarButtonEconomico;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.helper.CatalogosHelper;
import mx.com.desoft.hidrogas.helper.CatalogosHelperImpl;
import mx.com.desoft.hidrogas.property.EconomicoProperty;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.DateUtil;


public class AdministrarEconomicoController {

	private static final Logger log = Logger.getLogger(AdministrarEconomicoController.class);

	@FXML
	private ComboBox<EconomicoDTO> economicoComboBox;

	@FXML
	private DatePicker fechaRegistroField;

	@FXML
	private Label contadorLista;

	@FXML
    private TableView<EconomicoProperty> tablaEconomicos;

    @FXML
    private TableColumn<EconomicoProperty, String> economicoColumn;

    @FXML
    private TableColumn<EconomicoProperty, String> fechaRegistroColumn;

    @FXML
    private TableColumn<EconomicoProperty, String> reparacionesColumn;

    @FXML
    private TableColumn<EconomicoProperty, Boolean> eliminarColumn;

    private CatalogosHelper catalogosHelperImpl = Login.appContext.getBean(CatalogosHelperImpl.class);
    private EconomicoDTO economicoDTO = Login.appContext.getBean(EconomicoDTO.class);
    private IEconomicoBusiness economicoBusinessImpl = Login.appContext.getBean(EconomicoBusinessImpl.class);
    private ObservableList<EconomicoProperty> data = FXCollections.observableArrayList();

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AdministrarEconomicoController() {
    	tablaEconomicos = new TableView<>(data);
    	economicoColumn = new TableColumn<EconomicoProperty, String>();
    	fechaRegistroColumn = new TableColumn<EconomicoProperty, String>();
    	reparacionesColumn = new TableColumn<EconomicoProperty, String>();
    	eliminarColumn = new TableColumn<EconomicoProperty, Boolean>();
    	contadorLista = new Label();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	catalogosHelperImpl.llenarComboEconomico(economicoComboBox);
    }

	@FXML
    private void buscarEconomicos() {
		convertirCamposToDTO();
    	this.buscarEconomicosView();
    }

	public void buscarEconomicosView(){
		List<EconomicoProperty> dto = economicoBusinessImpl.getEconomicoByView(economicoDTO);
		data.clear();
		economicoColumn.setCellValueFactory(cellData -> cellData.getValue().getEconomicoId());
    	fechaRegistroColumn.setCellValueFactory(cellData -> cellData.getValue().getFechaRegistro());
    	fechaRegistroColumn.setStyle("-fx-alignment: CENTER;");
    	eliminarColumn.setStyle("-fx-alignment: CENTER;");
    	eliminarColumn.setSortable(false);
    	eliminarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EconomicoProperty, Boolean>, ObservableValue<Boolean>>() {
    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<EconomicoProperty, Boolean> features) {
    			return new SimpleBooleanProperty(features.getValue() != null);
	      }
	    });
    	eliminarColumn.setCellFactory(new Callback<TableColumn<EconomicoProperty, Boolean>, TableCell<EconomicoProperty, Boolean>>() {
    		@Override public TableCell<EconomicoProperty, Boolean> call(TableColumn<EconomicoProperty, Boolean> personBooleanTableColumn) {
    			return new EliminarButtonEconomico(data);
    		}
	    });
    	data.addAll(dto);
    	contadorLista.setText(String.valueOf(dto.size()));
    	tablaEconomicos.setItems(data);
	}

    private void convertirCamposToDTO() {
    	economicoDTO.setEconomicoId(economicoComboBox.getSelectionModel().getSelectedItem() != null ?
    			economicoComboBox.getSelectionModel().getSelectedItem().getEconomicoId() : null );
    	economicoDTO.setFechaRegistro(fechaRegistroField.getValue() != null ? DateUtil.getFechaDatePicker(fechaRegistroField) : null);

    }

    @FXML
    private void limpiarCamposView() {
    	economicoComboBox.getSelectionModel().clearSelection();
    	fechaRegistroField.setValue(null);
    	tablaEconomicos.getItems().clear();
    	contadorLista.setText(null);
    }

    @FXML
    private void agregarNuevoEconomico() {
    	try {
    		 FXMLLoader loader = new FXMLLoader();
             loader.setLocation(MainApp.class.getResource("view/AgregarEditarEconomico.fxml"));
             AnchorPane page = (AnchorPane) loader.load();
             Stage dialogStage = new Stage();
             dialogStage.initModality(Modality.WINDOW_MODAL);
             Scene scene = new Scene(page);
             dialogStage.setScene(scene);
             dialogStage.setResizable(false);
             dialogStage.setTitle("HidroGas");
             dialogStage.getIcons().add(new Image("file:resources/images/ic_launcher.png"));
             AgregarEditarEconomicoController controller = loader.getController();
             controller.setDialogStage(dialogStage);
             dialogStage.showAndWait();
		} catch (Exception e) {
			Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
    }

    public boolean eliminarEconomico(Integer id) {
    	String context ="�Esta seguro de eliminar el registro?";
    	boolean resultado = Alerta.eliminarRegistro("Confirmaci�n",context, AlertType.CONFIRMATION);
    	 if (resultado) {
    		 try {
    			 economicoBusinessImpl.eliminarEconomicoById(id);
			} catch (Exception e) {
				log.error("Error: Al eliminar un economico", e);
				Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
			}
    	 }
    	 return resultado;
    }

}
