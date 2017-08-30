package mx.com.desoft.hidrogas.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.bussines.AdministrarOrdenBusinessImpl;
import mx.com.desoft.hidrogas.bussines.AgregarEditarOrdenBusinessApp;
import mx.com.desoft.hidrogas.bussines.AgregarEditarOrdenBusinessImpl;
import mx.com.desoft.hidrogas.bussines.IAdministrarOrdenBusiness;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.property.OrdenProperty;

public class AdministrarOrdenTrabajoController {

	@FXML
    private TableView<OrdenProperty> tablaOrdenes;

    @FXML
    private TableColumn<OrdenProperty, String> folioColumn;

    @FXML
    private TableColumn<OrdenProperty, String> fechaColumn;

    @FXML
    private TableColumn<OrdenProperty, String> economicoColumn;

    @FXML
    private TableColumn<OrdenProperty, String> empleadoColumn;

    @FXML
    private TableColumn<OrdenProperty, String> necesidadColumn;

	@FXML
	private DatePicker fechaOrdenBusqueda;

	@FXML
	private ComboBox<CatTipoNecesidadDTO> tipoNecesidadOrdenBusqueda;

	@FXML
	private TextField empleadoOrdenBusqueda;

	@FXML
	private ComboBox<CatEstatusOrdenDTO> estatusOrdenBusqueda;

	@FXML
	private TextField economicoOrdenBusqueda;

	private AgregarEditarOrdenBusinessApp agregarEditarOrdenBusinessApp = Login.appContext.getBean(AgregarEditarOrdenBusinessImpl.class);
	private IAdministrarOrdenBusiness administrarOrdenBusiness = Login.appContext.getBean(AdministrarOrdenBusinessImpl.class);
	private MainApp mainApp;


	public AdministrarOrdenTrabajoController () {

	}

	public AdministrarOrdenTrabajoController(MainApp mainApp) {
		super();
		this.mainApp = mainApp;
	}

	@FXML
	public void initialize () {
		this.llenarComboNecesidad();
		this.llenarComboEstatus();
	}

	@FXML
	public void buscarOrden () {
		tipoNecesidadOrdenBusqueda.setOnAction((event) -> {
			CatTipoNecesidadDTO tipoSeleccionado = tipoNecesidadOrdenBusqueda.getSelectionModel().getSelectedItem();
		    System.out.println("ComboBox: " + tipoSeleccionado.getId() + " - " + tipoSeleccionado.getDescripcion());
		});
	}

	private void llenarComboNecesidad() {
		tipoNecesidadOrdenBusqueda.setItems(FXCollections.observableArrayList(agregarEditarOrdenBusinessApp.buscarTiposNecesidad()));

		tipoNecesidadOrdenBusqueda.setConverter(new StringConverter<CatTipoNecesidadDTO>() {
			@Override
			public String toString(CatTipoNecesidadDTO tipo) {
				// TODO Auto-generated method stub
				if (tipo == null) {
		            return null;
		        } else {
		            return tipo.getDescripcion();
		        }
			}
			@Override
			public CatTipoNecesidadDTO fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	private void llenarComboEstatus() {
		estatusOrdenBusqueda.setItems(FXCollections.observableArrayList(administrarOrdenBusiness.buscarTiposEstatus()));

		estatusOrdenBusqueda.setConverter(new StringConverter<CatEstatusOrdenDTO>() {
			@Override
			public String toString(CatEstatusOrdenDTO tipo) {
				// TODO Auto-generated method stub
				if (tipo == null) {
		            return null;
		        } else {
		            return tipo.getDescripcion();
		        }
			}
			@Override
			public CatEstatusOrdenDTO fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	public TableView<OrdenProperty> getTablaOrdenes() {
		return tablaOrdenes;
	}

	public void setTablaOrdenes(TableView<OrdenProperty> tablaOrdenes) {
		this.tablaOrdenes = tablaOrdenes;
	}

	public TableColumn<OrdenProperty, String> getFolioColumn() {
		return folioColumn;
	}

	public void setFolioColumn(TableColumn<OrdenProperty, String> folioColumn) {
		this.folioColumn = folioColumn;
	}

	public TableColumn<OrdenProperty, String> getFechaColumn() {
		return fechaColumn;
	}

	public void setFechaColumn(TableColumn<OrdenProperty, String> fechaColumn) {
		this.fechaColumn = fechaColumn;
	}

	public TableColumn<OrdenProperty, String> getEconomicoColumn() {
		return economicoColumn;
	}

	public void setEconomicoColumn(TableColumn<OrdenProperty, String> economicoColumn) {
		this.economicoColumn = economicoColumn;
	}

	public TableColumn<OrdenProperty, String> getEmpleadoColumn() {
		return empleadoColumn;
	}

	public void setEmpleadoColumn(TableColumn<OrdenProperty, String> empleadoColumn) {
		this.empleadoColumn = empleadoColumn;
	}

	public TableColumn<OrdenProperty, String> getNecesidadColumn() {
		return necesidadColumn;
	}

	public void setNecesidadColumn(TableColumn<OrdenProperty, String> necesidadColumn) {
		this.necesidadColumn = necesidadColumn;
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public DatePicker getFechaOrdenBusqueda() {
		return fechaOrdenBusqueda;
	}

	public void setFechaOrdenBusqueda(DatePicker fechaOrdenBusqueda) {
		this.fechaOrdenBusqueda = fechaOrdenBusqueda;
	}

	public ComboBox<CatTipoNecesidadDTO> getTipoNecesidadOrdenBusqueda() {
		return tipoNecesidadOrdenBusqueda;
	}

	public void setTipoNecesidadOrdenBusqueda(ComboBox<CatTipoNecesidadDTO> tipoNecesidadOrdenBusqueda) {
		this.tipoNecesidadOrdenBusqueda = tipoNecesidadOrdenBusqueda;
	}

	public TextField getEmpleadoOrdenBusqueda() {
		return empleadoOrdenBusqueda;
	}

	public void setEmpleadoOrdenBusqueda(TextField empleadoOrdenBusqueda) {
		this.empleadoOrdenBusqueda = empleadoOrdenBusqueda;
	}

	public ComboBox<CatEstatusOrdenDTO> getEstatusOrdenBusqueda() {
		return estatusOrdenBusqueda;
	}

	public void setEstatusOrdenBusqueda(ComboBox<CatEstatusOrdenDTO> estatusOrdenBusqueda) {
		this.estatusOrdenBusqueda = estatusOrdenBusqueda;
	}

	public TextField getEconomicoOrdenBusqueda() {
		return economicoOrdenBusqueda;
	}

	public void setEconomicoOrdenBusqueda(TextField economicoOrdenBusqueda) {
		this.economicoOrdenBusqueda = economicoOrdenBusqueda;
	}

	/**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void agregarOrden() {
        mainApp.showOrdenTrabajo();
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void seguimientoOrden() {
        mainApp.showSeguimientoOrdenTrabajo();
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void cancelarOrden() {
        mainApp.cancelarOrdenTrabajo();
    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

}
