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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.ITipoNecesidadBusiness;
import mx.com.desoft.hidrogas.business.TipoNecesidadBusinessImpl;
import mx.com.desoft.hidrogas.buttons.EliminarButtonTipoNecesidad;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.property.TipoNecesidadProperty;
import mx.com.desoft.hidrogas.util.Alerta;

public class AdministrarTipoNecesidadController {

	private static final Logger log = Logger.getLogger(AdministrarTipoNecesidadController.class);

	@FXML
	private TextField tipoNecesidadField;

	@FXML
	private Label contadorLista;

	@FXML
    private TableView<TipoNecesidadProperty> tablaTipoNecesidad;

    @FXML
    private TableColumn<TipoNecesidadProperty, String> tipoNecesidadIdColumn;

    @FXML
    private TableColumn<TipoNecesidadProperty, String> descripcionColumn;

    @FXML
    private TableColumn<TipoNecesidadProperty, Boolean> eliminarColumn;

    private ITipoNecesidadBusiness tipoNecesidadBusinessImpl = Login.appContext.getBean(TipoNecesidadBusinessImpl.class);
    private CatTipoNecesidadDTO catTipoNecesidadDTO;
    private ObservableList<TipoNecesidadProperty> data = FXCollections.observableArrayList();

	 /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AdministrarTipoNecesidadController() {
    	tablaTipoNecesidad = new TableView<>(data);
    	tipoNecesidadIdColumn = new TableColumn<TipoNecesidadProperty, String>();
    	descripcionColumn = new TableColumn<TipoNecesidadProperty, String>();
    	eliminarColumn = new TableColumn<TipoNecesidadProperty, Boolean>();
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
    private void buscarTipoNecesidad() {
		convertirCamposToDTO();
		List<TipoNecesidadProperty> dto = tipoNecesidadBusinessImpl.getTipoNecesidadByView(catTipoNecesidadDTO);
		data.clear();
		data.removeAll(data);
    	tipoNecesidadIdColumn.setCellValueFactory(cellData -> cellData.getValue().getTipoNecesidadId());
    	descripcionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcion());
    	eliminarColumn.setStyle("-fx-alignment: CENTER;");
    	eliminarColumn.setSortable(false);
    	eliminarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TipoNecesidadProperty, Boolean>, ObservableValue<Boolean>>() {
    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<TipoNecesidadProperty, Boolean> features) {
    			return new SimpleBooleanProperty(features.getValue() != null);
	      }
	    });
    	eliminarColumn.setCellFactory(new Callback<TableColumn<TipoNecesidadProperty, Boolean>, TableCell<TipoNecesidadProperty, Boolean>>() {
    		@Override public TableCell<TipoNecesidadProperty, Boolean> call(TableColumn<TipoNecesidadProperty, Boolean> personBooleanTableColumn) {
    			return new EliminarButtonTipoNecesidad(data);
    		}
	    });
    	data.addAll(dto);
    	tablaTipoNecesidad.setItems(data);
    	contadorLista.setText(dto.size() + "");
    }
    @FXML
    private void limpiarCamposView() {
    	tipoNecesidadField.clear();
    	tablaTipoNecesidad.getItems().clear();
    	contadorLista.setText(null);
    }

    @FXML
    private void agregarTipoNecesidad() {
    	try {
    		 FXMLLoader loader = new FXMLLoader();
             loader.setLocation(MainApp.class.getResource("view/AgregarEditarTipoNecesidad.fxml"));
             AnchorPane page = (AnchorPane) loader.load();
             Stage dialogStage = new Stage();
             dialogStage.initModality(Modality.WINDOW_MODAL);
             Scene scene = new Scene(page);
             dialogStage.setScene(scene);
             dialogStage.setResizable(false);
             dialogStage.setTitle("HidroGas");
             dialogStage.getIcons().add(new Image("file:resources/images/ic_launcher.png"));
             AgregarEditarTipoNecesidadController controller = loader.getController();
             controller.setDialogStage(dialogStage);
             dialogStage.showAndWait();
		} catch (Exception e) {
			log.error("Ocurrio un error al agregar un nuevo tipo de necesidad",e);
			Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);

		}
    }

    private void convertirCamposToDTO() {
    	catTipoNecesidadDTO = new CatTipoNecesidadDTO(tipoNecesidadField.getText());
    }

    public boolean eliminarTipoNecesidad(Integer id) {
    	String context ="Esta seguro de eliminar el registro?";
    	boolean resultado = Alerta.eliminarRegistro("Confirmacion",context, AlertType.CONFIRMATION);
    	 if (resultado) {
    		 try {
    			 tipoNecesidadBusinessImpl.eliminarTipoNecesidadById(id);
			} catch (Exception e) {
				log.error("Error: Al eliminar un tipo de necesidad", e);
				Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
			}
    	 }
    	 return resultado;
    }

}
