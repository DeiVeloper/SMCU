package mx.com.desoft.hidrogas.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.ISeguimientoOrdenBusiness;
import mx.com.desoft.hidrogas.business.SeguimientoOrdenBusinessImpl;
import mx.com.desoft.hidrogas.buttons.ButtonCell;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenPartesDTO;
import mx.com.desoft.hidrogas.property.SeguimientoOrdenPartesProperty;
import mx.com.desoft.hidrogas.util.Constantes;
import mx.com.desoft.hidrogas.util.DateUtil;

public class SeguimientoOrdenTrabajoController {

	private ISeguimientoOrdenBusiness seguimientoOrdenBusiness = Login.appContext.getBean(SeguimientoOrdenBusinessImpl.class);

	public static Stage stageOrden;
	private MainApp mainApp;
	private OrdenTrabajoDTO ordenDTO;
	private ObservableList<SeguimientoOrdenPartesProperty> dataPartesUsadas = FXCollections.observableArrayList();
	private ObservableList<SeguimientoOrdenPartesProperty> dataPartesSolicitadas = FXCollections.observableArrayList();
	private List<SeguimientoOrdenPartesProperty> dtoTablaPartesUsadas;
	private List<SeguimientoOrdenPartesDTO> dtoPartesUsadas;
	private List<SeguimientoOrdenPartesProperty> dtoTablaPartesSolicitadas;
	private List<SeguimientoOrdenPartesDTO> dtoPartesSolicitadas;
	private SeguimientoOrdenDTO seguimientoDTO;

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


	public SeguimientoOrdenTrabajoController() {
		super();
	}

	public SeguimientoOrdenTrabajoController (List<SeguimientoOrdenPartesProperty> dtoPartesUsadas) {
		this.dtoTablaPartesUsadas = dtoPartesUsadas;
	}

	@FXML
	public void initialize () {
		seguimientoDTO = new SeguimientoOrdenDTO();
		dtoTablaPartesUsadas = new ArrayList<>();
		dtoTablaPartesSolicitadas = new ArrayList<>();
	}

	public void cargarInformacion() {
		folio.setText(String.valueOf(ordenDTO.getFolio()));
		economico.setText(String.valueOf(ordenDTO.getEconomicoId()));
		empleado.setText(String.valueOf(ordenDTO.getNominaRegistro()));
		seguimientoDTO = seguimientoOrdenBusiness.getSeguimientoOrdenByFolio(ordenDTO.getFolio());
		if(seguimientoDTO.getIdSeguimiento() != Constantes.CERO) {
			trabajosRealizados.setText(seguimientoDTO.getTrabajosRealizados());
			observaciones.setText(seguimientoDTO.getObservaciones());
			if(seguimientoDTO.getFechaReparacionMayor() != Constantes.NULL) {
				reparacionMayor.setValue(DateUtil.getLocalDateFromSQLDate(seguimientoDTO.getFechaReparacionMayor()));
			}
			dtoTablaPartesUsadas = seguimientoOrdenBusiness.getListaPartesByFolioTipo(ordenDTO.getFolio(), 1);
			if(!dtoTablaPartesUsadas.isEmpty()) {
				this.cargarInformacionRefacciones(1);
			}
			dtoTablaPartesSolicitadas = seguimientoOrdenBusiness.getListaPartesByFolioTipo(ordenDTO.getFolio(), 2);
			if(!dtoTablaPartesSolicitadas.isEmpty()) {
				this.cargarInformacionRefacciones(2);
			}
		}

	}

	public void agregarPartesUsadas() {
		if(this.validarAgregarPartes((byte)1)) {
			dtoTablaPartesUsadas.add(new SeguimientoOrdenPartesProperty(0, new SimpleStringProperty(cantidadPU.getText()), new SimpleStringProperty(noPU.getText()),
					new SimpleStringProperty(marcaPU.getText()), new SimpleStringProperty(descripcionPU.getText())));
			this.cargarInformacionRefacciones(1);
			this.limpiaDatosDeRefaccion(1);
		}

	}

	public void agregarPartesSolicitadas() {
		if(this.validarAgregarPartes((byte)2)) {
			dtoTablaPartesSolicitadas.add(new SeguimientoOrdenPartesProperty(0, new SimpleStringProperty(cantidadPS.getText()), new SimpleStringProperty(marcaPS.getText()),
					new SimpleStringProperty(descripcionPS.getText())));
			this.cargarInformacionRefacciones(2);
			this.limpiaDatosDeRefaccion(2);
		}
	}

	private boolean validarAgregarPartes(byte tipo) {
		String errorMessage = "";
		boolean esCorrecto = false;
		switch (tipo){
			case 1:
				if(cantidadPU.getText().matches("[0-9]*")) {
					if(cantidadPU.getText() == Constantes.NULL || cantidadPU.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Cantidad no puede ir vacío.";
					} else if(noPU.getText() == Constantes.NULL || noPU.getText().length() == Constantes.CERO) {
						errorMessage = "El campo No de parte no puede ir vacío.";
					} else if(marcaPU.getText() == Constantes.NULL || marcaPU.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Marca no puede ir vacío.";
					} else if(descripcionPU.getText() == Constantes.NULL || descripcionPU.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Descripción no puede ir vacío.";
					} else {
						esCorrecto = true;
					}
				} else {
					errorMessage = "El campo Cantidad debe ser numérico.";
				}
				break;
			case 2:
				if(cantidadPS.getText().matches("[0-9]*")) {
					if(cantidadPS.getText() == Constantes.NULL || cantidadPS.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Cantidad no puede ir vacío.";
					} else if(marcaPS.getText() == Constantes.NULL || marcaPS.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Marca no puede ir vacío.";
					} else if(descripcionPS.getText() == Constantes.NULL || descripcionPS.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Descripción no puede ir vacío.";
					} else {
						esCorrecto = true;
					}
				} else {
					errorMessage = "El campo Cantidad debe ser numérico.";
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

	private void cargarInformacionRefacciones(int tipo) {
		if(tipo == 1) {
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
		}else {
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

	public void guardarSeguimiento() {
		if(this.validarFormulario()) {
			this.convertirCamposDTO();
			try {
				seguimientoOrdenBusiness.guardarSeguimiento(seguimientoDTO);
				Alert alert = new Alert(AlertType.WARNING);
	        	alert.setTitle("Guardando Seguimiento de Orden de Trabajo");
	        	alert.setHeaderText(null);
	        	alert.setContentText("¡El registro se guardó exitosamente!");
	        	alert.showAndWait();
	        	mainApp.showAdministrarOrdenTrabajo();
			} catch (Exception e) {
    			e.printStackTrace();
    			Alert alert = new Alert(AlertType.WARNING);
            	alert.setTitle("Error");
            	alert.setHeaderText(null);
            	alert.setContentText("Ocurrio un error, " + e.getMessage());
            	alert.showAndWait();
    		}
		} else {
			return;
		}
	}

	private boolean validarFormulario() {
		String errorMessage = "";
		boolean esCorrecto = false;
		if(trabajosRealizados.getText() == Constantes.NULL || trabajosRealizados.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Trabajos Realizados no puede ir vacío.";
		} else if(observaciones.getText() == Constantes.NULL || observaciones.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Observaciones no puede ir vacío.";
		}
		if(errorMessage.length() == Constantes.CERO) {
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
		seguimientoDTO.setFolio(ordenDTO.getFolio());
		seguimientoDTO.setTrabajosRealizados(trabajosRealizados.getText());
		seguimientoDTO.setObservaciones(observaciones.getText());
		if(reparacionMayor.getValue() == Constantes.NULL) {
			seguimientoDTO.setReparacionMayor(0);
			seguimientoDTO.setFechaReparacionMayor(null);
		} else {
			seguimientoDTO.setReparacionMayor(1);
			seguimientoDTO.setFechaReparacionMayor(DateUtil.getDateFromLocalDate(reparacionMayor.getValue()));
		}
		if(seguimientoDTO.getIdSeguimiento() == Constantes.CERO) {
			seguimientoDTO.setFechaRegistro(new Date());
		}
		seguimientoDTO.setNominaRegistro(12);
		dtoPartesUsadas = new ArrayList<>();
		dtoPartesSolicitadas = new ArrayList<>();
		for(SeguimientoOrdenPartesProperty parteUsada : dtoTablaPartesUsadas) {
			if(parteUsada.getIdRefaccion() == Constantes.CERO) {
				dtoPartesUsadas.add(new SeguimientoOrdenPartesDTO(ordenDTO.getFolio(), Integer.parseInt(parteUsada.getCantidad().getValue()), parteUsada.getParte().getValue(),
						parteUsada.getMarca().getValue(), parteUsada.getDescripcion().getValue(), 1, new Date(), 12));
			}
		}
		for(SeguimientoOrdenPartesProperty parteSolicitada : dtoTablaPartesSolicitadas) {
			if(parteSolicitada.getIdRefaccion() == Constantes.CERO) {
				dtoPartesSolicitadas.add(new SeguimientoOrdenPartesDTO(ordenDTO.getFolio(), Integer.parseInt(parteSolicitada.getCantidad().getValue()),
						parteSolicitada.getMarca().getValue(), parteSolicitada.getDescripcion().getValue(), 2, new Date(), 12));
			}
		}
		seguimientoDTO.setListaPartesUsadas(dtoPartesUsadas);
		seguimientoDTO.setListaPartesSolicitadas(dtoPartesSolicitadas);
	}

	public boolean eliminaRefaccion(int idRefaccion) {
		boolean isCorecto = true;
		String context = "¿Está seguro de eliminar el registro?";
		ButtonType aceptar = new ButtonType("Aceptar");
		ButtonType cancelar = new ButtonType("Cancelar");
		Alert alert = new Alert(AlertType.CONFIRMATION, context, aceptar, cancelar);
		alert.setTitle("Confirmación");
		alert.setHeaderText(null);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get().getText() == "Aceptar") {
			if(idRefaccion != Constantes.CERO) {
				isCorecto = seguimientoOrdenBusiness.eliminaRefaccion(idRefaccion);
			}
		} else {
			isCorecto = false;
		}
		return isCorecto;
	}

	private void limpiaDatosDeRefaccion(int tipo) {
		if(tipo == 1) {
			cantidadPU.setText(null);
			noPU.setText(null);
			marcaPU.setText(null);
			descripcionPU.setText(null);
		} else {
			cantidadPS.setText(null);
			marcaPS.setText(null);
			descripcionPS.setText(null);
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
