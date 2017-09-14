package mx.com.desoft.hidrogas.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mx.com.desoft.hidrogas.property.EconomicosProperty;

public class AdministrarTipoRefaccionesController {
	
	@FXML
	private TextField tipoRefaccionField;
	
	@FXML
	private Label contadorLista;
	
	@FXML
    private TableView<EconomicosProperty> tablaTipoRefaccion;
    
    @FXML
    private TableColumn<EconomicosProperty, String> tipoRefaccionIdColumn;
    
    @FXML
    private TableColumn<EconomicosProperty, String> descripcionColumn;
    
    @FXML
    private TableColumn<EconomicosProperty, String> borrarColumn;
	
	 /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AdministrarTipoRefaccionesController() {
    	
    }
   
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

}
