package mx.com.desoft.hidrogas.view;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

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
import mx.com.desoft.hidrogas.business.ITipoRefaccionesBusiness;
import mx.com.desoft.hidrogas.business.TipoRefaccionesBusinessImpl;
import mx.com.desoft.hidrogas.buttons.EditarButtonTipoRefaccion;
import mx.com.desoft.hidrogas.buttons.EliminarButtonTipoRefaccion;
import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.property.TipoRefaccionProperty;
import mx.com.desoft.hidrogas.util.Alerta;

public class AdministrarTipoRefaccionController {

	private static final Logger log = Logger.getLogger(AdministrarTipoRefaccionController.class);
	private static final String ERROR = "Error";

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
    private TableColumn<TipoRefaccionProperty, String> cantidadColumn;

    @FXML
    private TableColumn<TipoRefaccionProperty, Boolean> eliminarColumn;
    
    @FXML
    private TableColumn<TipoRefaccionProperty, Boolean> editarColumn;


    private ITipoRefaccionesBusiness tipoRefaccionesBusinessImpl = Login.appContext.getBean(TipoRefaccionesBusinessImpl.class);
    private CatTipoRefaccionesDTO catTipoRefaccionesDTO;
    private ObservableList<TipoRefaccionProperty> data = FXCollections.observableArrayList();

	 /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AdministrarTipoRefaccionController() {
    	tablaTipoRefaccion = new TableView<>(data);
    	tipoRefaccionIdColumn = new TableColumn<>();
    	descripcionColumn = new TableColumn<>();
    	cantidadColumn = new TableColumn<>();
    	eliminarColumn = new TableColumn<>();
    	editarColumn = new TableColumn<>();
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
		List<TipoRefaccionProperty> dto = tipoRefaccionesBusinessImpl.getRefaccionByView(catTipoRefaccionesDTO);
		data.clear();
		data.stream().close();
		tipoRefaccionIdColumn.setCellValueFactory(cellData -> cellData.getValue().getTipoRefaccionId());
    	descripcionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcion());
    	cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
    	editarColumn.setSortable(false);

    	editarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TipoRefaccionProperty, Boolean>, ObservableValue<Boolean>>() {
    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<TipoRefaccionProperty, Boolean> features) {
    			return new SimpleBooleanProperty(features.getValue() != null);
	      }
	    });
    	editarColumn.setCellFactory(new Callback<TableColumn<TipoRefaccionProperty, Boolean>, TableCell<TipoRefaccionProperty, Boolean>>() {
    		@Override public TableCell<TipoRefaccionProperty, Boolean> call(TableColumn<TipoRefaccionProperty, Boolean> personBooleanTableColumn) {
    			return new EditarButtonTipoRefaccion(data);
    		}
	    });
    	editarColumn.setStyle("-fx-alignment: CENTER;");
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
    
    public CatTipoRefaccionesDTO handleEditRefaccion(CatTipoRefaccionesDTO refaccion) {
    	AgregarEditarTipoRefaccionesController controller = null;
    	try {

        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AgregarEditarTipoRefacciones.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.setTitle("HidroGas");
            dialogStage.getIcons().add(new Image("file:resources/images/ic_launcher.png"));
            controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setearEdicionRefaccion(refaccion);
            dialogStage.showAndWait();

    	} catch (Exception e) {
    		log.error(e);
    		Alerta.crearAlertaUsuario(ERROR, e.getMessage(), AlertType.ERROR);
		}
    	return controller.convertirCamposViewToDTO();
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
             dialogStage.setResizable(false);
             dialogStage.setTitle("HidroGas");
             dialogStage.getIcons().add(new Image("file:resources/images/ic_launcher.png"));
             AgregarEditarTipoRefaccionesController controller = loader.getController();
             controller.setDialogStage(dialogStage);
             dialogStage.showAndWait();
		} catch (Exception e) {
			log.error("Ocurrio un error al agregar un nuevo tipo de refaccion",e);
			Alerta.crearAlertaUsuario(ERROR, e.getMessage(), AlertType.ERROR);

		}
    }

    private void convertirCamposToDTO() {
    	catTipoRefaccionesDTO = new CatTipoRefaccionesDTO(tipoRefaccionField.getText());
    }

    public boolean eliminarTipoRefaccion(Integer id) {
    	String context ="Esta seguro de eliminar el registro?";
    	boolean resultado = Alerta.eliminarRegistro("Confirmacion",context, AlertType.CONFIRMATION);
    	 if (resultado) {
    		 try {
    			 tipoRefaccionesBusinessImpl.eliminarRefaccionById(id);
    			 resultado = true;
			} catch (ConstraintViolationException e) {
				resultado = false;
				log.error("Error: Al eliminar un tipo de refaccion", e);
				Alerta.crearAlertaUsuario(ERROR, "Ocurrio un errro al borrar el registro, favor de intentar nuevamnete", AlertType.ERROR);
			}
    	 }
    	 return resultado;
    }

}
