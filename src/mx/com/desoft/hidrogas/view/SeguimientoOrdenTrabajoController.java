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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.CatalogoBusinessImpl;
import mx.com.desoft.hidrogas.business.ICatalogoBusiness;
import mx.com.desoft.hidrogas.business.ISeguimientoOrdenBusiness;
import mx.com.desoft.hidrogas.business.SeguimientoOrdenBusinessImpl;
import mx.com.desoft.hidrogas.buttons.ButtonCell;
import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenPartesDTO;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.property.SeguimientoOrdenPartesProperty;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.Authenticator;
import mx.com.desoft.hidrogas.util.Constantes;
import mx.com.desoft.hidrogas.util.DateUtil;

public class SeguimientoOrdenTrabajoController {

	private ISeguimientoOrdenBusiness seguimientoOrdenBusiness = Login.appContext.getBean(SeguimientoOrdenBusinessImpl.class);
	private ICatalogoBusiness catalogoBusiness = Login.appContext.getBean(CatalogoBusinessImpl.class);

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
    private TableColumn<SeguimientoOrdenPartesProperty, String> tipoRefaccionUsadaColumn;
	@FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> cantidadUsadaColumn;
//    @FXML
//    private TableColumn<SeguimientoOrdenPartesProperty, String> descripcionUsadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, Boolean> eliminarUsadaColumn;

    @FXML
	private TextField cantidadPU;
	@FXML
	private TextArea descripcionPU;

    //variables para tabla partes solicitadas
    @FXML
    private TableView<SeguimientoOrdenPartesProperty> tablaPartesSolicitadas;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> tipoRefaccionSolicitadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> cantidadSolicitadaColumn;
//    @FXML
//    private TableColumn<SeguimientoOrdenPartesProperty, String> descripcionSolicitadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, Boolean> eliminarSolicitadaColumn;

    @FXML
	private TextField cantidadPS;
	@FXML
	private TextArea descripcionPS;

	@FXML
	private Label folio;
	@FXML
	private Label economico;
	@FXML
	private Label empleado;
	@FXML
	private Label fecha;
	@FXML
	private TextArea trabajosRealizados;
	@FXML
	private DatePicker reparacionMayor;
	@FXML
	private TextArea observaciones;
	@FXML
	private ComboBox<CatTipoRefaccionesDTO> tipoRefaccionUsada;
	@FXML
	private ComboBox<CatTipoRefaccionesDTO> tipoRefaccionSolicitada;


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
		observaciones.setDisable(true);
		this.llenarComboTipoRefaccion();
		reparacionMayor.setOnAction(e -> {
			if(reparacionMayor.getValue() == Constantes.NULL) {
				observaciones.setDisable(true);
			} else {
				observaciones.setDisable(false);
			}
		});
	}

	public void cargarInformacion() {
		folio.setText(String.valueOf(ordenDTO.getFolio()));
		economico.setText(String.valueOf(ordenDTO.getEconomicoId()));
		empleado.setText(String.valueOf(ordenDTO.getNominaRegistro()));
		OrdenTrabajo orden = seguimientoOrdenBusiness.getOrdenByFolio(ordenDTO.getFolio());
		fecha.setText(DateUtil.getStringFromDate(orden.getFechaRegistro()));
		seguimientoDTO = seguimientoOrdenBusiness.getSeguimientoOrdenByFolio(ordenDTO.getFolio());
		if(seguimientoDTO.getIdSeguimiento() != Constantes.CERO) {
			trabajosRealizados.setText(seguimientoDTO.getTrabajosRealizados());
			observaciones.setText(seguimientoDTO.getObservaciones());
			descripcionPU.setText(seguimientoDTO.getDescripcionPU());
			descripcionPS.setText(seguimientoDTO.getDescripcionPS());
			if(seguimientoDTO.getFechaReparacionMayor() != Constantes.NULL) {
				reparacionMayor.setValue(DateUtil.getLocalDateFromSQLDate(seguimientoDTO.getFechaReparacionMayor()));
			}
			dtoTablaPartesUsadas = seguimientoOrdenBusiness.getListaPartesByFolioTipo(ordenDTO.getFolio(), Constantes.N1);
			if(!dtoTablaPartesUsadas.isEmpty()) {
				this.cargarInformacionRefacciones(Constantes.N1);
			}
			dtoTablaPartesSolicitadas = seguimientoOrdenBusiness.getListaPartesByFolioTipo(ordenDTO.getFolio(), Constantes.N2);
			if(!dtoTablaPartesSolicitadas.isEmpty()) {
				this.cargarInformacionRefacciones(Constantes.N2);
			}
		}

	}

	public void agregarPartesUsadas() {
		if(this.validarAgregarPartes((byte)Constantes.N1)) {
			dtoTablaPartesUsadas.add(new SeguimientoOrdenPartesProperty(Constantes.CERO, new SimpleStringProperty(cantidadPU.getText()), tipoRefaccionUsada.getSelectionModel().getSelectedItem().getTipoRefaccionId(),
					new SimpleStringProperty(tipoRefaccionUsada.getSelectionModel().getSelectedItem().getDescripcion())));
			this.cargarInformacionRefacciones(Constantes.N1);
			this.limpiaDatosDeRefaccion(Constantes.N1);
		}

	}

	public void agregarPartesSolicitadas() {
		if(this.validarAgregarPartes((byte)Constantes.N2)) {
			dtoTablaPartesSolicitadas.add(new SeguimientoOrdenPartesProperty(Constantes.CERO, new SimpleStringProperty(cantidadPS.getText()), tipoRefaccionSolicitada.getSelectionModel().getSelectedItem().getTipoRefaccionId(),
					new SimpleStringProperty(tipoRefaccionSolicitada.getSelectionModel().getSelectedItem().getDescripcion())));
			this.cargarInformacionRefacciones(Constantes.N2);
			this.limpiaDatosDeRefaccion(Constantes.N2);
		}
	}

	private boolean validarAgregarPartes(byte tipo) {
		String errorMessage = "";
		boolean esCorrecto = false;
		switch (tipo){
			case Constantes.N1:
				if(cantidadPU.getText().matches("[0-9]*")) {
					if(cantidadPU.getText() == Constantes.NULL || cantidadPU.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Cantidad no puede ir vac" + Constantes.i + "o.";
					} else if(tipoRefaccionUsada.getSelectionModel().getSelectedItem() == Constantes.NULL){
						errorMessage = "Debe seleccionar una Refacci" + Constantes.o + "n";
					} else if(Integer.parseInt(cantidadPU.getText()) > 99) {
						errorMessage = "El campo Cantidad no puede tener m" + Constantes.a + "s de 99.";
					} else if(descripcionPU.getText().length() > 300) {
						errorMessage = "El campo Descripci" + Constantes.o + "n no puede tener m" + Constantes.a + "s de 300 caracteres.";
					} else {
						esCorrecto = true;
					}
				} else {
					errorMessage = "El campo Cantidad debe ser num" + Constantes.e + "rico.";
				}
				break;
			case Constantes.N2:
				if(cantidadPS.getText().matches("[0-9]*")) {
					if(cantidadPS.getText() == Constantes.NULL || cantidadPS.getText().length() == Constantes.CERO) {
						errorMessage = "El campo Cantidad no puede ir vac" + Constantes.i + "o.";
					} else if(tipoRefaccionSolicitada.getSelectionModel().getSelectedItem() == Constantes.NULL){
						errorMessage = "Debe seleccionar una Refacci" + Constantes.o + "n";
					} else if(Integer.parseInt(cantidadPS.getText()) > 99) {
						errorMessage = "El campo Cantidad no puede tener m" + Constantes.a + "s de 99.";
					} else if(descripcionPS.getText().length() > 300) {
						errorMessage = "El campo Descripci" + Constantes.o + "n no puede tener m" + Constantes.a + "s de 300 caracteres.";
					} else {
						esCorrecto = true;
					}
				} else {
					errorMessage = "El campo Cantidad debe ser num" + Constantes.e + "rico.";
				}
				break;
			default:
				break;
		}
		if(esCorrecto == false) {
			Alerta.crearAlertaUsuario("Validando Formulario", errorMessage, AlertType.WARNING);
		}
		return esCorrecto;
	}

	private void cargarInformacionRefacciones(int tipo) {
		if(tipo == Constantes.N1) {
			dataPartesUsadas.clear();
	    	dataPartesUsadas.addAll(dtoTablaPartesUsadas);
			tablaPartesUsadas.setItems(getDataPartesUsadas());
			tipoRefaccionUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcionTipoRefaccion());
	    	cantidadUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
//	    	descripcionUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcion());
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
			tipoRefaccionSolicitadaColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcionTipoRefaccion());
	    	cantidadSolicitadaColumn.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
//	    	descripcionSolicitadaColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcion());
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
				Alerta.crearAlertaUsuario("Guardando Seguimiento de Orden de Trabajo", Constantes.MENSAJE_EXITOSO, AlertType.CONFIRMATION);
	        	mainApp.showAdministrarOrdenTrabajo();
			} catch (Exception e) {
    			e.printStackTrace();
    			Alerta.crearAlertaUsuario("Error", "Ocurrio un error, " + e.getMessage(), AlertType.ERROR);
    		}
		} else {
			return;
		}
	}

	private boolean validarFormulario() {
		String errorMessage = "";
		boolean esCorrecto = false;
		if(trabajosRealizados.getText() == Constantes.NULL || trabajosRealizados.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Trabajos Realizados no puede ir vac" + Constantes.i + "o.";
		} else if(trabajosRealizados.getText().length() > 300) {
			errorMessage = "El campo Trabajos Realizados no puede tener m" + Constantes.a + "s de 300 caracteres.";
		} else if(observaciones.getText().length() > 300) {
			errorMessage = "El campo Observaciones no puede tener m" + Constantes.a + "s de 300 caracteres.";
		} else {
			esCorrecto = true;
		}
		if(esCorrecto == false) {
			Alerta.crearAlertaUsuario("Validando Formulario", errorMessage, AlertType.WARNING);
		}
		return esCorrecto;
	}

	private void convertirCamposDTO() {
		seguimientoDTO.setFolio(ordenDTO.getFolio());
		seguimientoDTO.setTrabajosRealizados(trabajosRealizados.getText());
		seguimientoDTO.setObservaciones(observaciones.getText());
		seguimientoDTO.setDescripcionPU(descripcionPU.getText());
		seguimientoDTO.setDescripcionPS(descripcionPS.getText());
		if(reparacionMayor.getValue() == Constantes.NULL) {
			seguimientoDTO.setReparacionMayor(Constantes.CERO);
			seguimientoDTO.setFechaReparacionMayor(null);
		} else {
			seguimientoDTO.setReparacionMayor(Constantes.N1);
			seguimientoDTO.setFechaReparacionMayor(DateUtil.getDateFromLocalDate(reparacionMayor.getValue()));
		}
		if(seguimientoDTO.getIdSeguimiento() == Constantes.CERO) {
			seguimientoDTO.setFechaRegistro(new Date());
		}
		seguimientoDTO.setNominaRegistro(Authenticator.usuarioSesion.getNominaEmpleado());
		dtoPartesUsadas = new ArrayList<>();
		dtoPartesSolicitadas = new ArrayList<>();
		for(SeguimientoOrdenPartesProperty parteUsada : dtoTablaPartesUsadas) {
			if(parteUsada.getIdRefaccion() == Constantes.CERO) {
				dtoPartesUsadas.add(new SeguimientoOrdenPartesDTO(ordenDTO.getFolio(), Integer.parseInt(parteUsada.getCantidad().getValue()), parteUsada.getIdTipoRefaccion(),
						Constantes.N1, new Date(), Authenticator.usuarioSesion.getNominaEmpleado()));
			}
		}
		for(SeguimientoOrdenPartesProperty parteSolicitada : dtoTablaPartesSolicitadas) {
			if(parteSolicitada.getIdRefaccion() == Constantes.CERO) {
				dtoPartesSolicitadas.add(new SeguimientoOrdenPartesDTO(ordenDTO.getFolio(), Integer.parseInt(parteSolicitada.getCantidad().getValue()), parteSolicitada.getIdTipoRefaccion(),
						Constantes.N2, new Date(), Authenticator.usuarioSesion.getNominaEmpleado()));
			}
		}
		seguimientoDTO.setListaPartesUsadas(dtoPartesUsadas);
		seguimientoDTO.setListaPartesSolicitadas(dtoPartesSolicitadas);
	}

	public boolean eliminaRefaccion(int idRefaccion) {
		boolean isCorecto = true;
		if(Alerta.eliminarRegistro("Confirmaci" + Constantes.o + "n", Constantes.ELIMINAR_REGISTRO, AlertType.CONFIRMATION)) {
			if(idRefaccion != Constantes.CERO) {
				isCorecto = seguimientoOrdenBusiness.eliminaRefaccion(idRefaccion);
			}
		} else {
			isCorecto = false;
		}
		return isCorecto;
	}

	private void limpiaDatosDeRefaccion(int tipo) {
		if(tipo == Constantes.N1) {
			cantidadPU.setText("");
//			descripcionPU.setText("");
		} else {
			cantidadPS.setText("");
//			descripcionPS.setText("");
		}
	}

	private void llenarComboTipoRefaccion() {
		tipoRefaccionUsada.setItems(FXCollections.observableArrayList(catalogoBusiness.findAllCatTipoRefacciones()));
		tipoRefaccionUsada.setConverter(new StringConverter<CatTipoRefaccionesDTO>() {
			@Override
			public String toString(CatTipoRefaccionesDTO tipo) {
				if (tipo == null) {
		            return null;
		        } else {
		            return tipo.getDescripcion();
		        }
			}
			@Override
			public CatTipoRefaccionesDTO fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		tipoRefaccionSolicitada.setItems(FXCollections.observableArrayList(catalogoBusiness.findAllCatTipoRefacciones()));
		tipoRefaccionSolicitada.setConverter(new StringConverter<CatTipoRefaccionesDTO>() {
			@Override
			public String toString(CatTipoRefaccionesDTO tipo) {
				if (tipo == null) {
		            return null;
		        } else {
		            return tipo.getDescripcion();
		        }
			}
			@Override
			public CatTipoRefaccionesDTO fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
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
