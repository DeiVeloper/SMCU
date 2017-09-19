package mx.com.desoft.hidrogas.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.buttons.ButtonCell;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenPartesDTO;
import mx.com.desoft.hidrogas.property.SeguimientoOrdenPartesProperty;
import mx.com.desoft.hidrogas.util.Constantes;

public class SeguimientoOrdenTrabajoController {

	//variables para tabla partes usadas
	@FXML
    private TableView<SeguimientoOrdenPartesProperty> tablaPartesUsadas;
	@FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> cantidadUsadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> parteUsadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> marcaUsadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> descripcionUsadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, Boolean> eliminarUsadaColumn;

    @FXML
	private TextField cantidadPU;
	@FXML
	private TextField noPU;
	@FXML
	private TextField marcaPU;
	@FXML
	private TextArea descripcionPU;

    //variables para tabla partes solicitadas
    @FXML
    private TableView<SeguimientoOrdenPartesProperty> tablaPartesSolicitadas;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> cantidadSolicitadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> marcaSolicitadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> descripcionSolicitadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, Boolean> eliminarSolicitadaColumn;

    @FXML
	private TextField cantidadPS;
	@FXML
	private TextField marcaPS;
	@FXML
	private TextArea descripcionPS;

	@FXML
	private Label folio;
	@FXML
	private Label economico;
	@FXML
	private Label empleado;
	@FXML
	private TextArea trabajosRealizados;
	@FXML
	private DatePicker reparacionMayor;
	@FXML
	private TextArea observaciones;

	public static Stage stageOrden;
	private MainApp mainApp;
	private OrdenTrabajoDTO ordenDTO;
	private ObservableList<SeguimientoOrdenPartesProperty> dataPartesUsadas = FXCollections.observableArrayList();
	private ObservableList<SeguimientoOrdenPartesProperty> dataPartesSolicitadas = FXCollections.observableArrayList();
	private List<SeguimientoOrdenPartesProperty> dtoTablaPartesUsadas;
	private List<SeguimientoOrdenPartesDTO> dtoPartesUsadas;
	private List<SeguimientoOrdenPartesProperty> dtoTablaPartesSolicitadas;
	private List<SeguimientoOrdenPartesDTO> dtoPartesSolicitadas;


	public SeguimientoOrdenTrabajoController() {
		super();
	}

	public SeguimientoOrdenTrabajoController (List<SeguimientoOrdenPartesProperty> dtoPartesUsadas) {
		this.dtoTablaPartesUsadas = dtoPartesUsadas;
	}

	@FXML
	public void initialize () {
		System.out.println("entra a seguimiento");
		dtoTablaPartesUsadas = new ArrayList<>();
		dtoTablaPartesSolicitadas = new ArrayList<>();
	}

	public void cargarInformacion() {
		folio.setText(String.valueOf(ordenDTO.getFolio()));
		economico.setText(String.valueOf(ordenDTO.getEconomicoId()));
		empleado.setText(String.valueOf(ordenDTO.getNominaRegistro()));
	}

	public void agregarPartesUsadas() {
		if(this.validarAgregarPartes((byte)1)) {
			dtoTablaPartesUsadas.add(new SeguimientoOrdenPartesProperty(new SimpleStringProperty(cantidadPU.getText()), new SimpleStringProperty(noPU.getText()),
					new SimpleStringProperty(marcaPU.getText()), new SimpleStringProperty(descripcionPU.getText())));
			dataPartesUsadas.clear();
	    	dataPartesUsadas.addAll(dtoTablaPartesUsadas);
			tablaPartesUsadas.setItems(getDataPartesUsadas());
	    	cantidadUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
	    	parteUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getParte());
	    	marcaUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getMarca());
	    	descripcionUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcion());
	    	eliminarUsadaColumn.setSortable(false);
	    	eliminarUsadaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SeguimientoOrdenPartesProperty, Boolean>, ObservableValue<Boolean>>() {
	    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SeguimientoOrdenPartesProperty, Boolean> features) {
	    			return new SimpleBooleanProperty(features.getValue() != null);
    	      }
    	    });
	    	eliminarUsadaColumn.setCellFactory(new Callback<TableColumn<SeguimientoOrdenPartesProperty, Boolean>, TableCell<SeguimientoOrdenPartesProperty, Boolean>>() {
	    		@Override public TableCell<SeguimientoOrdenPartesProperty, Boolean> call(TableColumn<SeguimientoOrdenPartesProperty, Boolean> personBooleanTableColumn) {
	    			return new ButtonCell(dataPartesUsadas, dtoTablaPartesUsadas);
	    		}
    	    });
		}

	}

	public void agregarPartesSolicitadas() {
		if(this.validarAgregarPartes((byte)2)) {
			dtoTablaPartesSolicitadas.add(new SeguimientoOrdenPartesProperty(new SimpleStringProperty(cantidadPS.getText()), new SimpleStringProperty(marcaPS.getText()),
					new SimpleStringProperty(descripcionPS.getText())));
			dataPartesSolicitadas.clear();
	    	dataPartesSolicitadas.addAll(dtoTablaPartesSolicitadas);
			tablaPartesSolicitadas.setItems(getDataPartesSolicitadas());
	    	cantidadSolicitadaColumn.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
	    	marcaSolicitadaColumn.setCellValueFactory(cellData -> cellData.getValue().getMarca());
	    	descripcionSolicitadaColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcion());
	    	eliminarSolicitadaColumn.setSortable(false);

	    	eliminarSolicitadaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SeguimientoOrdenPartesProperty, Boolean>, ObservableValue<Boolean>>() {
	    		@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SeguimientoOrdenPartesProperty, Boolean> features) {
	    			return new SimpleBooleanProperty(features.getValue() != null);
	    		}
    	    });
	    	eliminarSolicitadaColumn.setCellFactory(new Callback<TableColumn<SeguimientoOrdenPartesProperty, Boolean>, TableCell<SeguimientoOrdenPartesProperty, Boolean>>() {
	    		@Override public TableCell<SeguimientoOrdenPartesProperty, Boolean> call(TableColumn<SeguimientoOrdenPartesProperty, Boolean> personBooleanTableColumn) {
	    			return new ButtonCell(dataPartesSolicitadas, dtoTablaPartesSolicitadas);
	    		}
    	    });
		}
	}

	private boolean validarAgregarPartes(byte tipo) {
		String errorMessage = "";
		boolean esCorrecto = false;
		switch (tipo){
			case 1:
				if(cantidadPU.getText().matches("[0-9]*")) {
					if(cantidadPU.getText() == Constantes.NULL || cantidadPU.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Cantidad no puede ir vac�o.";
					} else if(noPU.getText() == Constantes.NULL || noPU.getText().length() == Constantes.CERO) {
						errorMessage = "El campo No de parte no puede ir vac�o.";
					} else if(marcaPU.getText() == Constantes.NULL || marcaPU.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Marca no puede ir vac�o.";
					} else if(descripcionPU.getText() == Constantes.NULL || descripcionPU.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Descripci�n no puede ir vac�o.";
					} else {
						esCorrecto = true;
					}
				} else {
					errorMessage = "El campo Cantidad debe ser num�rico.";
				}
				break;
			case 2:
				if(cantidadPS.getText().matches("[0-9]*")) {
					if(cantidadPS.getText() == Constantes.NULL || cantidadPS.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Cantidad no puede ir vac�o.";
					} else if(marcaPS.getText() == Constantes.NULL || marcaPS.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Marca no puede ir vac�o.";
					} else if(descripcionPS.getText() == Constantes.NULL || descripcionPS.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Descripci�n no puede ir vac�o.";
					} else {
						esCorrecto = true;
					}
				} else {
					errorMessage = "El campo Cantidad debe ser num�rico.";
				}
				break;
			default:
				break;
		}
		if(esCorrecto == false) {
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Validando Formulario");
	    	alert.setHeaderText(null);
	    	alert.setContentText(errorMessage);
	    	alert.showAndWait();
		}
		return esCorrecto;
	}

	public void guardarSeguimiento() {
		if(this.validarFormulario()) {
			this.convertirCamposDTO();
		}
	}

	private boolean validarFormulario() {
		String errorMessage = "";
		boolean esCorrecto = false;
		if(trabajosRealizados.getText() == Constantes.NULL || trabajosRealizados.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Trabajos Realizados no puede ir vac�o.";
		} else if(observaciones.getText() == Constantes.NULL || observaciones.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Observaciones no puede ir vac�o.";
		} else if(errorMessage.length() == Constantes.CERO) {
			esCorrecto = true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Validando Formulario");
	    	alert.setHeaderText(null);
	    	alert.setContentText(errorMessage);
	    	alert.showAndWait();
		}
		return esCorrecto;
	}

	private void convertirCamposDTO() {
		dtoPartesUsadas = new ArrayList<>();
		dtoPartesSolicitadas = new ArrayList<>();
		for(SeguimientoOrdenPartesProperty parteUsada : dtoTablaPartesUsadas) {
			dtoPartesUsadas.add(new SeguimientoOrdenPartesDTO(ordenDTO.getFolio(), Integer.parseInt(parteUsada.getCantidad().toString()), parteUsada.getParte().toString(),
					parteUsada.getMarca().toString(), parteUsada.getDescripcion().toString(), 1, new Date(), 2));
		}
		for(SeguimientoOrdenPartesProperty parteSolicitada : dtoTablaPartesSolicitadas) {
			dtoPartesSolicitadas.add(new SeguimientoOrdenPartesDTO(ordenDTO.getFolio(), Integer.parseInt(parteSolicitada.getCantidad().toString()),
					parteSolicitada.getMarca().toString(), parteSolicitada.getDescripcion().toString(), 1, new Date(), 2));
		}
	}

	/**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void agregarOrden() {
        mainApp.showOrdenTrabajo();
    }

    @FXML
    public void cancelarSeguimiento() {
    	mainApp.cancelarOrdenTrabajo();
    }

    public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public OrdenTrabajoDTO getOrdenDTO() {
		return ordenDTO;
	}

	public void setOrdenDTO(OrdenTrabajoDTO ordenDTO) {
		this.ordenDTO = ordenDTO;
	}

	public ObservableList<SeguimientoOrdenPartesProperty> getDataPartesUsadas() {
		return dataPartesUsadas;
	}

	public void setDataPartesUsadas(ObservableList<SeguimientoOrdenPartesProperty> dataPartesUsadas) {
		this.dataPartesUsadas = dataPartesUsadas;
	}

	public ObservableList<SeguimientoOrdenPartesProperty> getDataPartesSolicitadas() {
		return dataPartesSolicitadas;
	}

	public void setDataPartesSolicitadas(ObservableList<SeguimientoOrdenPartesProperty> dataPartesSolicitadas) {
		this.dataPartesSolicitadas = dataPartesSolicitadas;
	}

	public List<SeguimientoOrdenPartesDTO> getDtoPartesUsadas() {
		return dtoPartesUsadas;
	}

	public void setDtoPartesUsadas(List<SeguimientoOrdenPartesDTO> dtoPartesUsadas) {
		this.dtoPartesUsadas = dtoPartesUsadas;
	}

	public List<SeguimientoOrdenPartesDTO> getDtoPartesSolicitadas() {
		return dtoPartesSolicitadas;
	}

	public void setDtoPartesSolicitadas(List<SeguimientoOrdenPartesDTO> dtoPartesSolicitadas) {
		this.dtoPartesSolicitadas = dtoPartesSolicitadas;
	}

	public List<SeguimientoOrdenPartesProperty> getDtoTablaPartesUsadas() {
		return dtoTablaPartesUsadas;
	}

	public void setDtoTablaPartesUsadas(List<SeguimientoOrdenPartesProperty> dtoTablaPartesUsadas) {
		this.dtoTablaPartesUsadas = dtoTablaPartesUsadas;
	}

	public List<SeguimientoOrdenPartesProperty> getDtoTablaPartesSolicitadas() {
		return dtoTablaPartesSolicitadas;
	}

	public void setDtoTablaPartesSolicitadas(List<SeguimientoOrdenPartesProperty> dtoTablaPartesSolicitadas) {
		this.dtoTablaPartesSolicitadas = dtoTablaPartesSolicitadas;
	}

}
