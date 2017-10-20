package mx.com.desoft.hidrogas.view;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.AdministrarOrdenBusinessImpl;
import mx.com.desoft.hidrogas.business.AgregarEditarOrdenBusinessImpl;
import mx.com.desoft.hidrogas.business.IAdministrarOrdenBusiness;
import mx.com.desoft.hidrogas.business.IAgregarEditarOrdenBusinessApp;
import mx.com.desoft.hidrogas.buttons.ButtonCerrarOrden;
import mx.com.desoft.hidrogas.buttons.ButtonEliminarOrden;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.property.OrdenProperty;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;

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
    private TableColumn<OrdenProperty, String> seguimientoColumn;
    @FXML
    private TableColumn<OrdenProperty, Boolean> eliminarOrdenColumn;
    @FXML
    private TableColumn<OrdenProperty, Boolean> cerrarOrdenColumn;

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
	@FXML
	private TextField folioOrdenBusqueda;

	private IAgregarEditarOrdenBusinessApp agregarEditarOrdenBusinessApp = Login.appContext.getBean(AgregarEditarOrdenBusinessImpl.class);
	private IAdministrarOrdenBusiness administrarOrdenBusiness = Login.appContext.getBean(AdministrarOrdenBusinessImpl.class);
	private ObservableList<OrdenProperty> data = FXCollections.observableArrayList();
	private MainApp mainApp;


	public AdministrarOrdenTrabajoController () {

	}

	@FXML
	public void initialize () {
		this.llenarComboNecesidad();
		this.llenarComboEstatus();

	}

	@FXML
	public void buscarOrden () {
		if(this.validarCampos()) {
			List<OrdenProperty> dto = administrarOrdenBusiness.buscarOrdenByVista(this.convertirCamposADTO(), mainApp);
	    	data.clear();
	    	data.addAll(dto);
			tablaOrdenes.setItems(getData());
	    	folioColumn.setCellValueFactory(cellData -> cellData.getValue().getFolioOrden());
	    	fechaColumn.setCellValueFactory(cellData -> cellData.getValue().getFechaOrden());
	    	economicoColumn.setCellValueFactory(cellData -> cellData.getValue().getEconomicoOrden());
	    	empleadoColumn.setCellValueFactory(cellData -> cellData.getValue().getEmpleadoOrden());
	    	necesidadColumn.setCellValueFactory(cellData -> cellData.getValue().getNecesidadOrden());
	    	seguimientoColumn.setCellValueFactory(new PropertyValueFactory<OrdenProperty, String> ("seguimiento"));
	    	eliminarOrdenColumn.setSortable(false);
	    	eliminarOrdenColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrdenProperty, Boolean>, ObservableValue<Boolean>>() {
	    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<OrdenProperty, Boolean> features) {
	    			return new SimpleBooleanProperty(features.getValue() != null);
    	      }
    	    });
	    	eliminarOrdenColumn.setCellFactory(new Callback<TableColumn<OrdenProperty, Boolean>, TableCell<OrdenProperty, Boolean>>() {
	    		@Override public TableCell<OrdenProperty, Boolean> call(TableColumn<OrdenProperty, Boolean> personBooleanTableColumn) {
	    			return new ButtonEliminarOrden(data, dto);
	    		}
    	    });
	    	cerrarOrdenColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrdenProperty, Boolean>, ObservableValue<Boolean>>() {
	    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<OrdenProperty, Boolean> features) {
	    			return new SimpleBooleanProperty(features.getValue() != null);
    	      }
    	    });
	    	cerrarOrdenColumn.setCellFactory(new Callback<TableColumn<OrdenProperty, Boolean>, TableCell<OrdenProperty, Boolean>>() {
	    		@Override public TableCell<OrdenProperty, Boolean> call(TableColumn<OrdenProperty, Boolean> personBooleanTableColumn) {
	    			return new ButtonCerrarOrden(data, dto);
	    		}
    	    });
		}
	}

	@FXML
	private void limpiarCamposView() {
		folioOrdenBusqueda.clear();
		fechaOrdenBusqueda.setValue(null);
		economicoOrdenBusqueda.clear();
		empleadoOrdenBusqueda.clear();;
		tipoNecesidadOrdenBusqueda.getSelectionModel().clearSelection();
		estatusOrdenBusqueda.getSelectionModel().clearSelection();
	}

	private OrdenTrabajoDTO convertirCamposADTO() {
		OrdenTrabajoDTO ordenTO = new OrdenTrabajoDTO();
		if(folioOrdenBusqueda.getText() == Constantes.NULL || folioOrdenBusqueda.getText().length() == Constantes.CERO) {
			ordenTO.setFolio(0);
		} else {
			ordenTO.setFolio(Integer.parseInt(folioOrdenBusqueda.getText()));
		}
		if(fechaOrdenBusqueda.getValue() == Constantes.NULL) {
			ordenTO.setFechaRegistro(null);
		} else {
			LocalDate localDate = fechaOrdenBusqueda.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			ordenTO.setFechaRegistro(date);
		}
		if(tipoNecesidadOrdenBusqueda.getSelectionModel().getSelectedItem() == Constantes.NULL) {
			ordenTO.setTipoNecesidadId(null);
		} else {
			ordenTO.setTipoNecesidadId(tipoNecesidadOrdenBusqueda.getSelectionModel().getSelectedItem().getTipoNecesidadId());
		}
		if(empleadoOrdenBusqueda.getText() == Constantes.NULL || empleadoOrdenBusqueda.getText().length() == Constantes.CERO) {
			ordenTO.setNominaRegistro(0);
		} else {
			ordenTO.setNominaRegistro(Integer.parseInt(empleadoOrdenBusqueda.getText()));
		}
		if(estatusOrdenBusqueda.getSelectionModel().getSelectedItem() == Constantes.NULL) {
			ordenTO.setEstatusOrden(0);
		} else {
			ordenTO.setEstatusOrden(estatusOrdenBusqueda.getSelectionModel().getSelectedItem().getId());
		}
		if(economicoOrdenBusqueda.getText() == Constantes.NULL || economicoOrdenBusqueda.getText().length() == Constantes.CERO) {
			ordenTO.setEconomicoId(0);
		} else {
			ordenTO.setEconomicoId(Integer.parseInt(economicoOrdenBusqueda.getText()));
		}
		return ordenTO;
	}

	private boolean validarCampos() {
		String errorMessage = "";
		boolean esCorrecto = false;
		if(!folioOrdenBusqueda.getText().matches("[0-9]*")) {
			errorMessage = "El campo Folio debe ser num" + Constantes.e + "rico.";
		} else if(!economicoOrdenBusqueda.getText().matches("[0-9]*")) {
			errorMessage = "El campo Econ" + Constantes.o + "mico debe ser num" + Constantes.e + "rico.";
		} else if(!empleadoOrdenBusqueda.getText().matches("[0-9]*")) {
			errorMessage = "El campo Empleado debe ser num" + Constantes.e + "rico.";
		} else {
			esCorrecto = true;
		}
		if(!esCorrecto) {
			Alerta.crearAlertaUsuario("Validando Formulario", errorMessage, AlertType.WARNING);
		}
		return esCorrecto;
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

	public boolean eliminaOrden(int folioOrden) {
		boolean isCorrecto = true;
		if(Alerta.eliminarRegistro("Confirmación", Constantes.ELIMINAR_REGISTRO, AlertType.CONFIRMATION)) {
			isCorrecto = agregarEditarOrdenBusinessApp.eliminaOrden(folioOrden);
		} else{
			isCorrecto = false;
		}
		return isCorrecto;
	}

	public boolean cerrarOrden(int folioOrden) {
		boolean isCorrecto = true;
		if(Alerta.eliminarRegistro("Confirmación", Constantes.CERRAR_ORDEN + folioOrden + "?", AlertType.CONFIRMATION)) {
			isCorrecto = agregarEditarOrdenBusinessApp.cerrarOrden(folioOrden);
		} else{
			isCorrecto = false;
		}
		return isCorrecto;
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
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

	public ObservableList<OrdenProperty> getData() {
		return data;
	}

	public void setData(ObservableList<OrdenProperty> data) {
		this.data = data;
	}

}
