package mx.com.desoft.hidrogas.view;

import org.apache.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.business.ITipoRefaccionesBusiness;
import mx.com.desoft.hidrogas.business.TipoRefaccionesBusinessImpl;
import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;

public class AgregarEditarTipoRefaccionesController {

	private static final Logger log = Logger.getLogger(AgregarEditarTipoNecesidadController.class);

	@FXML
	private TextField descripcionField;

	private ITipoRefaccionesBusiness tipoRefaccionesBusinessImpl = Login.appContext.getBean(TipoRefaccionesBusinessImpl.class);
	private CatTipoRefaccionesDTO catTipoRefaccionesDTO;
	private Stage dialogStage;
	private String mensaje = "";

	public AgregarEditarTipoRefaccionesController() {

	}

	@FXML
    private void initialize() {

    }

	@FXML
    private void guardarTipoRefaccion() {
    	if	(validarFormulario())	{
    		try {
    			tipoRefaccionesBusinessImpl.guardarTipoRefaccion(catTipoRefaccionesDTO);
    			dialogStage.close();
    			Alerta.crearAlertaUsuario("Guardando", Constantes.MENSAJE_EXITOSO, AlertType.CONFIRMATION);
    		} catch (Exception e) {
    			log.error("Error al guardar tipo de necesidad", e);
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

	private boolean validarFormulario() {
        if	(descripcionField.getText() == Constantes.NULL || descripcionField.getText().isEmpty()) {
        	mensaje = "El Campo Descripción no puede ir vacio";
        	return false;
        }
        this.convertirCamposViewToDTO();
        return true;
    }

	 private void convertirCamposViewToDTO() {
		 catTipoRefaccionesDTO = new CatTipoRefaccionesDTO(descripcionField.getText());
    }

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
