package mx.com.desoft.hidrogas.view;

import java.time.LocalDate;
import java.util.Date;

import org.apache.log4j.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import mx.com.desoft.hidrogas.Authenticator;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.IAgregarEditarOrdenBusinessApp;
import mx.com.desoft.hidrogas.business.AgregarEditarOrdenBusinessImpl;
import mx.com.desoft.hidrogas.business.ICatalogoBusiness;
import mx.com.desoft.hidrogas.business.CatalogoBusinessImpl;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.model.Empleado;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;
import mx.com.desoft.hidrogas.util.DateUtil;

public class AgregarEditarOrdenController {

	public static Stage stageOrden;
	private static final Logger log = Logger.getLogger(AgregarEditarOrdenController.class);
	private IAgregarEditarOrdenBusinessApp agregarEditarOrdenBusinessApp = Login.appContext.getBean(AgregarEditarOrdenBusinessImpl.class);
	private OrdenTrabajoDTO ordenTrabajoTO;
	private MainApp mainApp;
	private ICatalogoBusiness bussinesServiceImpl = Login.appContext.getBean(CatalogoBusinessImpl.class);


	@FXML
	private ComboBox<EconomicoDTO> economico;

	@FXML
	private TextField nominaOperador;

	@FXML
	private TextField nombreOperador;

	@FXML
	private TextField apellidoPaterno;

	@FXML
	private TextField apellidoMaterno;

	@FXML
	private ComboBox<CatTipoNecesidadDTO> tipoNecesidadOrden;

	@FXML
	private TextField kilometrajeHoras;

	@FXML
	private TextArea fallaMecanica;
	
	@FXML
	private DatePicker fechaOrden;

	@FXML
	public void initialize () {
		ordenTrabajoTO = new OrdenTrabajoDTO();
		this.llenarComboNecesidad();
		this.llenarComboEconomico();
		this.fechaOrden.setValue(LocalDate.now());
		nominaOperador.focusedProperty().addListener(new ChangeListener<Boolean>(){
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue){
		        if (oldPropertyValue && nominaOperador.getText() != Constantes.NULL && nominaOperador.getText().length() != Constantes.CERO && nominaOperador.getText().matches("[0-9]*")){
			    	Empleado empleado = agregarEditarOrdenBusinessApp.getEmpleadoByNomina(Integer.parseInt(nominaOperador.getText()));
			    	if(empleado == Constantes.NULL) {
			    		nombreOperador.setText(null);
			    		apellidoPaterno.setText(null);
			    		apellidoMaterno.setText(null);
			    	} else {
			    		nombreOperador.setText(empleado.getNombreEmpleado());
			    		apellidoPaterno.setText(empleado.getApellidoPatEmpleado());
			    		apellidoMaterno.setText(empleado.getApellidoMatEmpleado());
			    	}
		        } else {
		        	nombreOperador.setText(null);
		    		apellidoPaterno.setText(null);
		    		apellidoMaterno.setText(null);
		        }
		    }
		});
	}

	public void guardarOrden() {
		if(this.validarFormulario()) {
			this.convertirCamposDTO();
			try {
				agregarEditarOrdenBusinessApp.guardarOrden(ordenTrabajoTO);
				this.limpiarCampos();
				Alerta.crearAlertaUsuario("Guardando Orden de Trabajo", Constantes.MENSAJE_EXITOSO, AlertType.INFORMATION);
	        	mainApp.showAdministrarOrdenTrabajo();
			} catch (Exception e) {
    			Alerta.crearAlertaUsuario("Error", "Ocurrio un error. Favor de intentar nuevamente, si persiste el problema contactar al administrador.", AlertType.ERROR);
    			log.error("Error: No se pudo guardar la orden");;
    		}
		} else {
			return;
		}
	}

	private boolean validarFormulario() {
		String errorMessage = "";
		if(economico.getSelectionModel().getSelectedItem() == Constantes.NULL) {
        	errorMessage = "Favor de seleccionar un Econ" + Constantes.o + "mico.";
        }
		if(nominaOperador.getText() == Constantes.NULL || nominaOperador.getText().length() == Constantes.CERO) {
			errorMessage = "El campo N" + Constantes.o + "mina de operador no puede ir vac" + Constantes.i + "o.";
		}
		if(!nominaOperador.getText().matches("[0-9]*")) {
			errorMessage = "El campo N" + Constantes.o + "mina de operador debe ser num" + Constantes.e + "rico.";
		}
		if(nombreOperador.getText() == Constantes.NULL || nombreOperador.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Nombre no puede ir vac" + Constantes.i + "o.";
		}
		if(apellidoPaterno.getText() == Constantes.NULL || apellidoPaterno.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Apellido paterno no puede ir vac" + Constantes.i + "o.";
		}
		if(apellidoMaterno.getText() == Constantes.NULL || apellidoMaterno.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Apellido materno de operador no puede ir vac" + Constantes.i + "o.";
		}
		if(kilometrajeHoras.getText() == Constantes.NULL || kilometrajeHoras.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Kilometraje/Hrs de trabajo de operador no puede ir vac" + Constantes.i + "o.";
		}
		if(!kilometrajeHoras.getText().matches("[0-9]*")) {
			errorMessage = "El campo Kilometraje/Hrs de trabajo debe ser num" + Constantes.e + "rico.";
		}
		if(tipoNecesidadOrden.getSelectionModel().getSelectedItem() == Constantes.NULL) {
        	errorMessage = "Favor de seleccionar un Tipo de necesidad ";
        }
		if(fallaMecanica.getText() == Constantes.NULL || fallaMecanica.getText().length() == Constantes.CERO) {
			errorMessage = "El campo Falla mec" + Constantes.a + "nica no puede ir vac" + Constantes.i + "o.";
		}
		if(fechaOrden.getValue() == Constantes.NULL) {
			errorMessage = "El campo Fecha no puede ir vac" + Constantes.i + "o.";
		}
		if(errorMessage.length() == Constantes.CERO) {
			return true;
		}
		Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle(Constantes.VALIDANDO_FORMULARIO);
    	alert.setHeaderText(null);
    	alert.setContentText(errorMessage);
    	alert.showAndWait();
		return false;
	}

	private void convertirCamposDTO() {
		ordenTrabajoTO.setEconomicoId(economico.getSelectionModel().getSelectedItem().getEconomicoId());
		ordenTrabajoTO.setNominaOperador(Integer.parseInt(nominaOperador.getText()));
		ordenTrabajoTO.setNombreOperador(nombreOperador.getText());
		ordenTrabajoTO.setApellidoPatOperador(apellidoPaterno.getText());
		ordenTrabajoTO.setApellidoMatOperador(apellidoMaterno.getText());
		ordenTrabajoTO.setTipoNecesidadId(tipoNecesidadOrden.getSelectionModel().getSelectedItem().getTipoNecesidadId());
		ordenTrabajoTO.setKilometraje(Integer.parseInt(kilometrajeHoras.getText()));
		ordenTrabajoTO.setFallaMecanica(fallaMecanica.getText());
		ordenTrabajoTO.setFechaRegistro(new Date());
		ordenTrabajoTO.setNominaRegistro(Authenticator.usuarioSesion.getNominaRegistro());
		ordenTrabajoTO.setFechaOrden(DateUtil.getDateFromLocalDate(fechaOrden.getValue()));
	}

	private void llenarComboNecesidad() {
		tipoNecesidadOrden.setItems(FXCollections.observableArrayList(agregarEditarOrdenBusinessApp.buscarTiposNecesidad()));
		tipoNecesidadOrden.setConverter(new StringConverter<CatTipoNecesidadDTO>() {
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

	private void llenarComboEconomico() {
		economico.setItems(FXCollections.observableArrayList(bussinesServiceImpl.findAllEconomicos()));
    	economico.setConverter(new StringConverter<EconomicoDTO>() {
    		@Override
            public String toString(EconomicoDTO tipo) {
            	if (tipo == null) {
            		return null;
            	} else {
            		return (tipo.getEconomicoId() + "");
            	}
            }

            @Override
            public EconomicoDTO fromString(String string) {
                   return null;
            }
    	});
	}

	private void limpiarCampos() {
		economico.getSelectionModel().getSelectedItem().setEconomicoId(null);
		nominaOperador.setText("");
		nombreOperador.setText("");
		apellidoPaterno.setText("");
		apellidoMaterno.setText("");
		tipoNecesidadOrden.getSelectionModel().getSelectedItem().setTipoNecesidadId(null);
		kilometrajeHoras.setText("");
		fallaMecanica.setText("");
		fechaOrden.setValue(LocalDate.now());
	}


	/**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void cancelarOrden() {
        mainApp.cancelarOrdenTrabajo();
    }

	public OrdenTrabajoDTO getOrdenTrabajoTO() {
		return ordenTrabajoTO;
	}

	public void setOrdenTrabajoTO(OrdenTrabajoDTO ordenTrabajoTO) {
		this.ordenTrabajoTO = ordenTrabajoTO;
	}

	public IAgregarEditarOrdenBusinessApp getAgregarEditarOrdenBusinessApp() {
		return agregarEditarOrdenBusinessApp;
	}

	public void setAgregarEditarOrdenBusinessApp(IAgregarEditarOrdenBusinessApp agregarEditarOrdenBusinessApp) {
		this.agregarEditarOrdenBusinessApp = agregarEditarOrdenBusinessApp;
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public ComboBox<EconomicoDTO> getEconomico() {
		return economico;
	}

	public void setEconomico(ComboBox<EconomicoDTO> economico) {
		this.economico = economico;
	}

	public TextField getNominaOperador() {
		return nominaOperador;
	}

	public void setNominaOperador(TextField nominaOperador) {
		this.nominaOperador = nominaOperador;
	}

	public TextField getNombreOperador() {
		return nombreOperador;
	}

	public void setNombreOperador(TextField nombreOperador) {
		this.nombreOperador = nombreOperador;
	}

	public TextField getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(TextField apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public TextField getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(TextField apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public ComboBox<CatTipoNecesidadDTO> getTipoNecesidadOrden() {
		return tipoNecesidadOrden;
	}

	public void setTipoNecesidadOrden(ComboBox<CatTipoNecesidadDTO> tipoNecesidadOrden) {
		this.tipoNecesidadOrden = tipoNecesidadOrden;
	}

	public TextField getKilometrajeHoras() {
		return kilometrajeHoras;
	}

	public void setKilometrajeHoras(TextField kilometrajeHoras) {
		this.kilometrajeHoras = kilometrajeHoras;
	}

	public TextArea getFallaMecanica() {
		return fallaMecanica;
	}

	public void setFallaMecanica(TextArea fallaMecanica) {
		this.fallaMecanica = fallaMecanica;
	}

}
