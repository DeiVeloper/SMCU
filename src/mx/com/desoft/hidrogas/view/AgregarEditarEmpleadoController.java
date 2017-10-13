package mx.com.desoft.hidrogas.view;

import java.util.Date;

import org.apache.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.Authenticator;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.business.EmpleadoBusinessImpl;
import mx.com.desoft.hidrogas.business.IEmpleadoBusiness;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.helper.CatalogosHelper;
import mx.com.desoft.hidrogas.helper.CatalogosHelperImpl;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;

public class AgregarEditarEmpleadoController {

	private static final Logger log = Logger.getLogger(AgregarEditarEmpleadoController.class);

    @FXML
    private TextField noNomina;

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidoPaterno;

    @FXML
    private TextField apellidoMaterno;

    @FXML
    private ComboBox<EconomicoDTO> economico;

    @FXML
    private TextField noNominaRegistro;

    @FXML
    private ComboBox<CatTipoEmpleadoDTO> tipoEmpleado;

    @FXML
    private PasswordField password;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label economicoLabel;

    @FXML
    private Button guardarEmpleado;

    private CatalogosHelper catalogosHelperImpl = Login.appContext.getBean(CatalogosHelperImpl.class);
    private IEmpleadoBusiness empleadoBusinessImpl = Login.appContext.getBean(EmpleadoBusinessImpl.class);
    private EmpleadoDTO empleadoDTO;
    private CatTipoEmpleadoDTO catTipoEmpleadoDTO;
    private Stage dialogStage;
    private String mensaje = "";

    public AgregarEditarEmpleadoController(){
    	noNomina = new TextField();
    	noNomina.requestFocus();
    	nombre = new TextField();
    	apellidoPaterno = new TextField();
    	apellidoMaterno = new TextField();
    	economico = new ComboBox<>();
    	noNominaRegistro = new TextField();
    	tipoEmpleado = new ComboBox<>();
    	password = new PasswordField();
    	passwordLabel = new Label();
    	economicoLabel = new Label();

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	inciciarComponentes();
    }

    @FXML
    private void guardarEmpleado() {
    	if	(validarFormulario())	{
    		try {
    			empleadoBusinessImpl.guardarEmpleado(empleadoDTO);
    			dialogStage.close();
    			Alerta.crearAlertaUsuario("Guardando", Constantes.MENSAJE_EXITOSO, AlertType.CONFIRMATION);
    		} catch (Exception e) {
    			log.error("Error al momento de guardar un empleado" ,e);
    			Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
    		}
    	}	else	{
    		Alerta.crearAlertaUsuario("Validando", mensaje, AlertType.WARNING);
    	}

    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void eventoTipoEmpleado() {
    	catTipoEmpleadoDTO = tipoEmpleado.getSelectionModel().getSelectedItem();
    	if(catTipoEmpleadoDTO.getDescripcion().equals(Constantes.ADMINISTRADOR) || catTipoEmpleadoDTO.getDescripcion().equals(Constantes.MECANICO))	{
    		passwordLabel.setVisible(true);
    		password.setVisible(true);
    	}	else	{
    		passwordLabel.setVisible(false);
    		password.setVisible(false);
    	}

    	if(catTipoEmpleadoDTO.getDescripcion().equals(Constantes.ADMINISTRADOR)) {
    		economico.setVisible(false);
    		economicoLabel.setVisible(false);
    	}	else{
    		economico.setVisible(true);
    		economicoLabel.setVisible(true);
    	}

    }

    private boolean validarFormulario() {
    	if	(!noNomina.getText().matches("[0-9]*"))	{
    		mensaje = "El Campo No. Nomina debe de ser numerico, favor de validar ";
    		return false;
    	}

        if	(noNomina.getText() == Constantes.NULL || noNomina.getText().length() == Constantes.CERO) {
        	mensaje = "El Campo No. Nomina no puede ir vacio ";
        	return false;
        }

        if	(nombre.getText() == Constantes.NULL || nombre.getText().length() == Constantes.CERO) {
        	mensaje =  "El Campo Nombre(s) no puede ir vacio ";
        	return false;
        }

        if	(apellidoPaterno.getText() == Constantes.NULL || apellidoPaterno.getText().length() == Constantes.CERO) {
        	mensaje =  "El Campo Apellido Paterno no puede ir vacio ";
        	return false;
        }

        if	(apellidoMaterno.getText() == Constantes.NULL || apellidoMaterno.getText().length() == Constantes.CERO) {
        	mensaje =  "El Campo Apellido Materno no puede ir vacio ";
        	return false;
        }

        if	(tipoEmpleado.getSelectionModel().getSelectedItem() == Constantes.NULL) {
        	mensaje =  "Favor de seleccionar un Tipo de Empleado ";
        	return false;
        }

        if	(!economico.isDisabled() && !tipoEmpleado.getSelectionModel().getSelectedItem().getDescripcion().equals(Constantes.ADMINISTRADOR) &&(economico.getSelectionModel().getSelectedItem() == Constantes.NULL)) {
        	mensaje =  "Favor de seleccionar un Economico ";
        	return false;
        }

        if	(!password.isDisabled() && !tipoEmpleado.getSelectionModel().getSelectedItem().getDescripcion().equals(Constantes.OPERADOR) && (password.getText() == Constantes.NULL || password.getText().length() == Constantes.CERO)) {
        	mensaje =  "Favor de capturar su contraseÃ±a ";
        	return false;
        }
        this.convertirCamposViewToDTO();
        return true;
    }

    public EmpleadoDTO convertirCamposViewToDTO() {
    	empleadoDTO = new EmpleadoDTO();
    	empleadoDTO.setNominaEmpleado(Integer.parseInt(noNomina.getText().toString()));
    	empleadoDTO.setNombreEmpleado(nombre.getText());
    	empleadoDTO.setApellidoPatEmpleado(apellidoPaterno.getText());
    	empleadoDTO.setApellidoMatEmpleado(apellidoMaterno.getText());
    	if	(economico.getSelectionModel().getSelectedItem() != Constantes.NULL)	{
    		empleadoDTO.setEconomicoId(economico.getSelectionModel().getSelectedItem().getEconomicoId());
    	}
    	empleadoDTO.setTipoEmpleadoId(catTipoEmpleadoDTO.getTipoEmpleadoId());
    	empleadoDTO.setDescripcionTipoEmpleado(catTipoEmpleadoDTO.getDescripcion());
    	empleadoDTO.setFechaRegistro(new Date());
    	empleadoDTO.setNominaRegistro(Integer.parseInt(noNominaRegistro.getText().toString()));
    	if(password.getText() != Constantes.NULL && !password.getText().isEmpty()) {
    		empleadoDTO.setPassword(password.getText());
    	}
    	return empleadoDTO;

    }

    private void inciciarComponentes() {
    	noNominaRegistro.setText(Authenticator.usuarioSesion.getNominaEmpleado().toString());
    	noNominaRegistro.setDisable(true);
    	passwordLabel.setVisible(false);
    	password.setVisible(false);
    	catalogosHelperImpl.llenarComboEconomico(economico);
    	catalogosHelperImpl.llenarComboCatTipoEmpleado(tipoEmpleado);
    }

    public void setearEdicionEmpleado(EmpleadoDTO empleado) {
    	noNomina.setDisable(true);
    	noNomina.setText(empleado.getNominaEmpleado().toString());
        nombre.setText(empleado.getNombreEmpleado());
        apellidoPaterno.setText(empleado.getApellidoPatEmpleado());
        apellidoMaterno.setText(empleado.getApellidoMatEmpleado());
        tipoEmpleado.setValue(new CatTipoEmpleadoDTO(empleado.getTipoEmpleadoId(), empleado.getDescripcionTipoEmpleado()));
        economico.setValue(new EconomicoDTO(empleado.getEconomicoId()));
        noNominaRegistro.setText(empleado.getNominaRegistro().toString());
        password.setText(empleado.getPassword());
        eventoTipoEmpleado();
    }

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
