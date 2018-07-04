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
	private TextField idRefaccionField;
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
		idRefaccionField.setVisible(false);
    }

	@FXML
    private void guardarTipoRefaccion() {
    	if	(validarFormulario())	{
    		try {
    			tipoRefaccionesBusinessImpl.guardarRefaccion(catTipoRefaccionesDTO);
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
	
	public void setearEdicionRefaccion(CatTipoRefaccionesDTO refaccion) {
		idRefaccionField.setText(Integer.toString(refaccion.getTipoRefaccionId()));
		descripcionField.setText(refaccion.getDescripcion().toString());
    }

	@FXML
    private void handleCancel() {
        dialogStage.close();
    }

	private boolean validarFormulario() {
        if	(descripcionField.getText() == Constantes.NULL || descripcionField.getText().isEmpty()) {
        	mensaje = "El Campo Descripcion no puede ir vacio";
        	return false;
        }
        this.convertirCamposViewToDTO();
        return true;
    }
				 
	 public CatTipoRefaccionesDTO convertirCamposViewToDTO() {
		 System.out.println("id: " + idRefaccionField.getText());
		 if(idRefaccionField.getText() != null && !idRefaccionField.getText().isEmpty()) {
			 catTipoRefaccionesDTO = new CatTipoRefaccionesDTO(Integer.parseInt(idRefaccionField.getText()), descripcionField.getText()); 
		 } else {
			 catTipoRefaccionesDTO = new CatTipoRefaccionesDTO(descripcionField.getText()); 
		 }
		 return catTipoRefaccionesDTO;
    }

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
