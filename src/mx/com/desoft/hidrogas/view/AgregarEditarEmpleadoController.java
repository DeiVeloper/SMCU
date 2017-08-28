package mx.com.desoft.hidrogas.view;

import java.util.Date;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.bussines.BussinesService;
import mx.com.desoft.hidrogas.bussines.BussinesServiceImpl;
import mx.com.desoft.hidrogas.bussines.EmpleadoBussinesService;
import mx.com.desoft.hidrogas.bussines.EmpleadoBussinesServiceImpl;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.util.Constantes;

public class AgregarEditarEmpleadoController {
	
	@SuppressWarnings("unused")
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


    private Stage dialogStage;
    private EmpleadoDTO empleadoDTO;
    private CatTipoEmpleadoDTO catTipoEmpleadoDTO;
    private EconomicoDTO economicoDTO;
    private BussinesService bussinesServiceImpl = Login.appContext.getBean(BussinesServiceImpl.class);
    private EmpleadoBussinesService empleadoBussinesServiceImpl = Login.appContext.getBean(EmpleadoBussinesServiceImpl.class);

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	this.inciciarComponentes();
    	this.llenarComboEconomico();
    	this.llenarComboCatTipoEmpleado();
    }

    @FXML
    private void guardarEmpleado() {
    	if	(this.validarFormulario())	{
    		this.convertirCamposADTO();
    		try {
    			empleadoBussinesServiceImpl.guardarEmpleado(empleadoDTO);
    		} catch (Exception e) {
    			e.printStackTrace();
    			Alert alert = new Alert(AlertType.WARNING);
            	alert.setTitle("Error");
            	alert.setHeaderText(null);
            	alert.setContentText("Ocurrio un error, " + e.getMessage());
            	alert.showAndWait();
    		}
    		
    		Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Guardando Empleado");
        	alert.setHeaderText(null);
        	alert.setContentText("El registro se guardo exitosamente!");
        	alert.showAndWait();
        	dialogStage.close();
    	}
        
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    @FXML
    private void eventoTipoEmpleado() {
    	catTipoEmpleadoDTO = tipoEmpleado.getSelectionModel().getSelectedItem();
    	if(catTipoEmpleadoDTO.getDescripcion().equals(Constantes.ADMINISTRADOR))	{
    		passwordLabel.setVisible(true);
    		password.setVisible(true);
    	}	else	{
    		passwordLabel.setVisible(false);
    		password.setVisible(false);
    	}
    	
    }
    
    @FXML
    private void seleccionarEconomico() {
    	economicoDTO = economico.getSelectionModel().getSelectedItem();
    }
   
    private boolean validarFormulario() {
        String errorMessage = "";
        if	(noNomina.getText() == Constantes.NULL || noNomina.getText().length() == Constantes.CERO) {
        	errorMessage += "El campo No. Nomina no puede ir vacio ";
        }
        if	(nombre.getText() == Constantes.NULL || nombre.getText().length() == Constantes.CERO) {
        	errorMessage += "El campo Nombre no puede ir vacio ";
        }
        if	(apellidoPaterno.getText() == Constantes.NULL || apellidoPaterno.getText().length() == Constantes.CERO) {
        	errorMessage += "El campo Apellido Paterno no puede ir vacio ";
        }
        if	(apellidoMaterno.getText() == Constantes.NULL || apellidoMaterno.getText().length() == Constantes.CERO) {
        	errorMessage += "El campo Apellido Materno no puede ir vacio ";
        }
        if	(economico.getSelectionModel().getSelectedItem() == Constantes.NULL) {
        	errorMessage += "Favor de seleccionar un Economico ";
        }
        if	(noNominaRegistro.getText() == Constantes.NULL || noNominaRegistro.getText().length() == Constantes.CERO) {
        	errorMessage += "El campo No. Nomina Registro no puede ir vacio ";
        }
        if	(tipoEmpleado.getSelectionModel().getSelectedItem() == Constantes.NULL) {
        	errorMessage += "Favor de seleccionar un Tipo de Empleado ";
        }
//        if	(password.getText() == Constantes.NULL || password.getText().length() == Constantes.CERO) {
//        	errorMessage += "El campo No. Nomina no puede ir vacio ";
//        }
        

        if (errorMessage.length() == Constantes.CERO) {
            return true;
        } else {
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Validando Formulario");
        	alert.setHeaderText(null);
        	alert.setContentText(errorMessage);
        	alert.showAndWait();
            return false;
        }
    }
    
    private void convertirCamposADTO() {
    	empleadoDTO = new EmpleadoDTO();
    	empleadoDTO.setNominaEmpleado(Integer.parseInt(noNomina.getText().toString()));
    	empleadoDTO.setNombreEmpleado(nombre.getText());
    	empleadoDTO.setApellidoPatEmpleado(apellidoPaterno.getText());
    	empleadoDTO.setApellidoMatEmpleado(apellidoMaterno.getText());
    	empleadoDTO.setEconomicoId(economicoDTO.getEconomicoId());
    	empleadoDTO.setTipoEmpleadoId(catTipoEmpleadoDTO.getTipoEmpleadoId());
    	empleadoDTO.setFechaRegistro(new Date());
    	empleadoDTO.setNominaRegistro(Integer.parseInt(noNominaRegistro.getText().toString()));
    	empleadoDTO.setPassword(password.getText());
    }

    private void llenarComboCatTipoEmpleado() {
    	tipoEmpleado.setItems(FXCollections.observableArrayList(bussinesServiceImpl.findAllTipoEmpleados()));
    	tipoEmpleado.setConverter(new StringConverter<CatTipoEmpleadoDTO>() {
    		@Override
            public String toString(CatTipoEmpleadoDTO tipo) {
            	if (tipo == null) {
            		return null;
            	} else {
            		return tipo.getDescripcion();
            	}
            }

            @Override
            public CatTipoEmpleadoDTO fromString(String string) {
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
    
    private void inciciarComponentes() {
    	passwordLabel.setVisible(false);
    	password.setVisible(false);
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
	public EmpleadoDTO getEmpleadoDTO() {
		return empleadoDTO;
	}

	public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
		this.empleadoDTO = empleadoDTO;
	}

}