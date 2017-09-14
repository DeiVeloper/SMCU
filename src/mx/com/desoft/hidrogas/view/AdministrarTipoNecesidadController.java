package mx.com.desoft.hidrogas.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mx.com.desoft.hidrogas.property.EconomicosProperty;

public class AdministrarTipoNecesidadController {

	@FXML
	private TextField tipoNecesidadField;
	
	@FXML
	private Label contadorLista;
	
	@FXML
    private TableView<EconomicosProperty> tablaTipoNecesidad;
    
    @FXML
    private TableColumn<EconomicosProperty, String> tipoNecesidadIdColumn;
    
    @FXML
    private TableColumn<EconomicosProperty, String> descripcionColumn;
    
    @FXML
    private TableColumn<EconomicosProperty, String> borrarColumn;
	
	 /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AdministrarTipoNecesidadController() {
    	
    }
   
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
}
