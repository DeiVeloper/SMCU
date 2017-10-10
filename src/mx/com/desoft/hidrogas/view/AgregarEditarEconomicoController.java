package mx.com.desoft.hidrogas.view;

import java.util.Date;

import org.apache.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.business.EconomicoBusinessImpl;
import mx.com.desoft.hidrogas.business.IEconomicoBusiness;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Authenticator;
import mx.com.desoft.hidrogas.util.Constantes;

public class AgregarEditarEconomicoController {

	private static final Logger log = Logger.getLogger(AgregarEditarEconomicoController.class);

	@FXML
	private TextField economicoField;

	@FXML
	private TextField noNominaRegistroField;

	private IEconomicoBusiness economicoBussinesServiceImpl = Login.appContext.getBean(EconomicoBusinessImpl.class);
	private EconomicoDTO economicoDTO;
	private Stage dialogStage;
	private String mensaje = "";

	public AgregarEditarEconomicoController() {
		economicoField = new TextField();
		noNominaRegistroField = new TextField();
	}

	@FXML
    private void initialize() {
		noNominaRegistroField.setText(Authenticator.noNominaRegistro.toString());
		noNominaRegistroField.setDisable(true);
    }

	@FXML
    private void guardarEconomico() {
    	if	(validarFormulario())	{
    		try {
    			economicoBussinesServiceImpl.guardarEconomico(economicoDTO);
    			dialogStage.close();
    			Alerta.crearAlertaUsuario("Guardando", Constantes.MENSAJE_EXITOSO, AlertType.CONFIRMATION);
    		} catch (Exception e) {
    			log.error("Ocurrio un error al guardar un economico", e);
    			Alerta.crearAlertaUsuario("Error", e.getMessage(), AlertType.ERROR);
    		}
    	}	else	{
    		Alerta.crearAlertaUsuario("Validando Formulario", mensaje, AlertType.WARNING);
    	}

    }

	@FXML
    private void handleCancel() {
        dialogStage.close();
    }

	private boolean validarFormulario() {
        if	(economicoField.getText() == Constantes.NULL || economicoField.getText().length() == Constantes.CERO) {
        	mensaje = "El campo Economico no puede ir vacio";
        	return false;
        }
//        if	(noNominaRegistroField.getText() == Constantes.NULL || noNominaRegistroField.getText().length() == Constantes.CERO) {
//        	mensaje =  "El campo No. Nomina Registro no puede ir vacio ";
//        	return false;
//        }
        this.convertirCamposViewToDTO();
        return true;
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
