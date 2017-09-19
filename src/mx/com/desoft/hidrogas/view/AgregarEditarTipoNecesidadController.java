package mx.com.desoft.hidrogas.view;

import org.apache.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.business.ITipoNecesidadBusiness;
import mx.com.desoft.hidrogas.business.TipoNecesidadBusinessImpl;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;

public class AgregarEditarTipoNecesidadController {

	private static final Logger log = Logger.getLogger(AgregarEditarTipoNecesidadController.class);

	@FXML
	private TextField descripcionField;

	private ITipoNecesidadBusiness tipoNecesidadBusinessImpl = Login.appContext.getBean(TipoNecesidadBusinessImpl.class);
	private CatTipoNecesidadDTO catTipoNecesidadDTO;
	private Stage dialogStage;
	private String mensaje = "";

	public AgregarEditarTipoNecesidadController() {

	}

	@FXML
    private void initialize() {

    }

	@FXML
    private void guardarTipoNecesidad() {
    	if	(validarFormulario())	{
    		try {
    			tipoNecesidadBusinessImpl.guardarTipoNecesidad(catTipoNecesidadDTO);
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
		 catTipoNecesidadDTO = new CatTipoNecesidadDTO(descripcionField.getText());
    }

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
