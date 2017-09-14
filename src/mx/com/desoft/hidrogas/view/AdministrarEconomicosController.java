package mx.com.desoft.hidrogas.view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.bussines.ICatalogoBusiness;
import mx.com.desoft.hidrogas.bussines.CatalogoBusinessImpl;
import mx.com.desoft.hidrogas.bussines.IEconomicoBusiness;
import mx.com.desoft.hidrogas.bussines.EconomicoBusinessImpl;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.property.EconomicosProperty;
import mx.com.desoft.hidrogas.util.Alerta;


public class AdministrarEconomicosController {	

	@FXML
	private ComboBox<EconomicoDTO> economicoComboBox;
	
	@FXML
	private TextField choferField;
	
	@FXML
	private Label contadorLista;
	
	@FXML
    private TableView<EconomicosProperty> tablaEconomicos;
    
    @FXML
    private TableColumn<EconomicosProperty, String> economicoColumn;
    
    @FXML
    private TableColumn<EconomicosProperty, String> choferColumn;
    
    @FXML
    private TableColumn<EconomicosProperty, String> reparacionesColumn;
    
    @FXML
    private TableColumn<EconomicosProperty, Button> borrarColumn;
    
    private EconomicoDTO economicoDTO = Login.appContext.getBean(EconomicoDTO.class);
    private IEconomicoBusiness economicoBussinesServiceImpl = Login.appContext.getBean(EconomicoBusinessImpl.class);
    private ICatalogoBusiness bussinesServiceImpl = Login.appContext.getBean(CatalogoBusinessImpl.class);
    private ObservableList<EconomicosProperty> data = FXCollections.observableArrayList();
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AdministrarEconomicosController() {
    	
    }
   
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	llenarComboEconomico();
    }
    
	@FXML
    private void getEconomicos() {
		convertirCamposToDTO();
//    	empleadoDTO = new EmpleadoDTO();
//    	empleadoDTO.setNominaEmpleado(null);
//    	empleadoDTO.setNombreEmpleado(null);
//    	empleadoDTO.setEconomicoId(null);
//    	empleadoDTO.setDescripcionTipoEmpleado(null);
    	List<EconomicosProperty> dto = economicoBussinesServiceImpl.getEconomicoByView(economicoDTO);
    	
//    	tablaEconomicos.getColumns().add(editarColumn);
//    	tablaEconomicos.getColumns().add(borrarColumn);
    	economicoColumn.setCellValueFactory(cellData -> cellData.getValue().getEconomico());
    	choferColumn.setCellValueFactory(cellData -> cellData.getValue().getChofer());
//    	ayudanteColumn.setCellValueFactory(cellData -> cellData.getValue().getAyudante());
//    	editarColumn.setCellValueFactory(new PropertyValueFactory<EconomicosProperty, Button>("Editar"));
    	borrarColumn.setCellValueFactory(new PropertyValueFactory<EconomicosProperty, Button>("Borrar"));
    	
    	data.clear();
    	data.addAll(dto);
    	tablaEconomicos.setItems(data);
    	contadorLista.setText(dto.size() + "");
//    	editarColumn.addEventHandler(MouseEvent.MOUSE_EXITED,
//                new EventHandler<MouseEvent>() {
//            @Override public void handle(MouseEvent e) {
//                System.out.println("iugiuh");
//            }
//        });
//    	editarColumn.setGraphic(value);
//    	borrarColumn.setCellValueFactory(cellData -> cellData.getValue().getBotonBorrar());
//    	tablaEmpleados.refresh();
//    	  tablaEmpleados.getSelectionModel().selectedItemProperty().addListener(
//                  (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    
	private void llenarComboEconomico() {
		economicoComboBox.setItems(FXCollections.observableArrayList(bussinesServiceImpl.findAllEconomicos()));
		economicoComboBox.setConverter(new StringConverter<EconomicoDTO>() {
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
    
    private void convertirCamposToDTO() {
    	economicoDTO.setEconomicoId(economicoComboBox.getSelectionModel().getSelectedItem() != null ? 
    			economicoComboBox.getSelectionModel().getSelectedItem().getEconomicoId() : null );
    	economicoDTO.setNombreChofer(choferField.getText());
    	
    }
    
    @FXML
    private void limpiarCamposView() {
    	economicoComboBox.getSelectionModel().clearSelection();
    	choferField.clear();
    	tablaEconomicos.getItems().clear();
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
             AgregarEditarEconomicoController controller = loader.getController();
             controller.setDialogStage(dialogStage);
             dialogStage.showAndWait();
		} catch (Exception e) {
			Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
    }

}
