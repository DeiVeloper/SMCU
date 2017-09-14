package mx.com.desoft.hidrogas.view;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.bussines.IEconomicoBusiness;
import mx.com.desoft.hidrogas.bussines.EconomicoBusinessImpl;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;

public class AgregarEditarEconomicoController {
	
	@FXML
	private TextField economicoField;
	    
	@FXML
	private TextField noNominaRegistroField;
	
	
	
	private EconomicoDTO economicoDTO;
	private Stage dialogStage;
	private IEconomicoBusiness economicoBussinesServiceImpl = Login.appContext.getBean(EconomicoBusinessImpl.class);
	
	
	public AgregarEditarEconomicoController() {
		
	}
	
	@FXML
    private void initialize() {
		 
    }
	
	
	@FXML
    private void guardarEconomico() {
    	if	(validarFormulario())	{
    		convertirCamposViewToDTO();
    		try {
    			economicoBussinesServiceImpl.guardarEconomico(economicoDTO);
    		} catch (Exception e) {
    			Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.WARNING);
    		}
    		Alerta.crearAlertaUsuario("Guardando Empleado", "El registro se guardo exitosamente!", AlertType.CONFIRMATION);
        	dialogStage.close();
    	}
        
    }
	 
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
	
	
	
	private boolean validarFormulario() {
        String errorMessage = "";
        if	(economicoField.getText() == Constantes.NULL || economicoField.getText().length() == Constantes.CERO) {
        	errorMessage += "El campo Economico no puede ir vacio";
        }
        if	(noNominaRegistroField.getText() == Constantes.NULL || noNominaRegistroField.getText().length() == Constantes.CERO) {
        	errorMessage += "El campo No. Nomina Registro no puede ir vacio ";
        }

        if (errorMessage.length() == Constantes.CERO) {
            return true;
        } else {
        	Alerta.crearAlertaUsuario("Validando Formulario", errorMessage, AlertType.WARNING);
            return false;
        }
    }
	
	 private void convertirCamposViewToDTO() {
		 economicoDTO = new EconomicoDTO();
		 economicoDTO.setEconomicoId(economicoField.getText() != Constantes.NULL ? Integer.parseInt(economicoField.getText()) : null);
		 economicoDTO.setFechaRegistro(new Date());
		 economicoDTO.setNominaRegistro(noNominaRegistroField.getText() != Constantes.NULL ? Integer.parseInt(noNominaRegistroField.getText()) : null);
    }

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
