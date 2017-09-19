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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.ITipoRefaccionesBusiness;
import mx.com.desoft.hidrogas.business.TipoRefaccionesBusinessImpl;
import mx.com.desoft.hidrogas.buttons.EliminarButtonTipoRefaccion;
import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.property.TipoRefaccionProperty;
import mx.com.desoft.hidrogas.util.Alerta;

public class AdministrarTipoRefaccionController {

	private static final Logger log = Logger.getLogger(AdministrarTipoRefaccionController.class);

	@FXML
	private TextField tipoRefaccionField;

	@FXML
	private Label contadorLista;

	@FXML
    private TableView<TipoRefaccionProperty> tablaTipoRefaccion;

    @FXML
    private TableColumn<TipoRefaccionProperty, String> tipoRefaccionIdColumn;

    @FXML
    private TableColumn<TipoRefaccionProperty, String> descripcionColumn;

    @FXML
    private TableColumn<TipoRefaccionProperty, Boolean> eliminarColumn;


    private ITipoRefaccionesBusiness tipoRefaccionesBusinessImpl = Login.appContext.getBean(TipoRefaccionesBusinessImpl.class);
    private CatTipoRefaccionesDTO catTipoRefaccionesDTO;
    private ObservableList<TipoRefaccionProperty> data = FXCollections.observableArrayList();

	 /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AdministrarTipoRefaccionController() {
    	tablaTipoRefaccion = new TableView<>(data);
    	tipoRefaccionIdColumn = new TableColumn<TipoRefaccionProperty, String>();
    	descripcionColumn = new TableColumn<TipoRefaccionProperty, String>();
    	eliminarColumn = new TableColumn<TipoRefaccionProperty, Boolean>();
    	contadorLista = new Label();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    @FXML
    private void buscarTipoRefaccion() {
		convertirCamposToDTO();
		List<TipoRefaccionProperty> dto = tipoRefaccionesBusinessImpl.getTipoRefaccionByView(catTipoRefaccionesDTO);
		data.clear();
		data.removeAll(data);
		tipoRefaccionIdColumn.setCellValueFactory(cellData -> cellData.getValue().getTipoRefaccionId());
    	descripcionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcion());
    	eliminarColumn.setStyle("-fx-alignment: CENTER;");
    	eliminarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TipoRefaccionProperty, Boolean>, ObservableValue<Boolean>>() {
    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<TipoRefaccionProperty, Boolean> features) {
    			return new SimpleBooleanProperty(features.getValue() != null);
	      }
	    });
    	eliminarColumn.setCellFactory(new Callback<TableColumn<TipoRefaccionProperty, Boolean>, TableCell<TipoRefaccionProperty, Boolean>>() {
    		@Override public TableCell<TipoRefaccionProperty, Boolean> call(TableColumn<TipoRefaccionProperty, Boolean> personBooleanTableColumn) {
    			return new EliminarButtonTipoRefaccion(data);
    		}
	    });
    	data.addAll(dto);
    	tablaTipoRefaccion.setItems(data);
    	contadorLista.setText(dto.size() + "");
    }
    @FXML
    private void limpiarCamposView() {
    	tipoRefaccionField.clear();
    	tablaTipoRefaccion.getItems().clear();
    	contadorLista.setText(null);
    }

    @FXML
    private void agregarTipoRefaccion() {
    	try {
    		 FXMLLoader loader = new FXMLLoader();
             loader.setLocation(MainApp.class.getResource("view/AgregarEditarTipoRefacciones.fxml"));
             AnchorPane page = (AnchorPane) loader.load();
             Stage dialogStage = new Stage();
             dialogStage.initModality(Modality.WINDOW_MODAL);
             Scene scene = new Scene(page);
             dialogStage.setScene(scene);
             AgregarEditarTipoRefaccionesController controller = loader.getController();
             controller.setDialogStage(dialogStage);
             dialogStage.showAndWait();
		} catch (Exception e) {
			log.error("Ocurrio un error al agregar un nuevo tipo de refaccion",e);
			Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);

		}
    }

    private void convertirCamposToDTO() {
    	catTipoRefaccionesDTO = new CatTipoRefaccionesDTO(tipoRefaccionField.getText());
    }

    public boolean eliminarTipoNecesidad(Integer id) {
    	String context ="�Esta seguro de eliminar el registro?";
		ButtonType aceptar = new ButtonType("Aceptar");
		ButtonType cancelar = new ButtonType("Cancelar");
		Alert alert = new Alert(AlertType.CONFIRMATION,context, aceptar, cancelar);
    	alert.setTitle("Confirmaci�n");
    	alert.setHeaderText(null);
    	Optional<ButtonType> result = alert.showAndWait();
    	 if (result.isPresent() && result.get().getText() == "Aceptar") {
    		 tipoRefaccionesBusinessImpl.eliminarTipoRefaccionById(id);
    	 }	else	{
    		 return false;
    	 }
    	 return true;
    }

}