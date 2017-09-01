package mx.com.desoft.hidrogas.view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;

public class SeguimientoOrdenTrabajoController {

	@FXML
	private Label folio;
	@FXML
	private Label economico;
	@FXML
	private Label empleado;
	@FXML
	private TextArea trabajosRealizados;
	@FXML
	private TextField cantidadPU;
	@FXML
	private TextField noPU;
	@FXML
	private TextField marcaPU;
	@FXML
	private TextArea descripcionPU;
	@FXML
	private TextField cantidadPS;
	@FXML
	private TextField marcaPS;
	@FXML
	private TextArea descripcionPS;
	@FXML
	private DatePicker reparacionMayor;
	@FXML
	private TextArea observaciones;

	public static Stage stageOrden;
	private MainApp mainApp;
	private OrdenTrabajoDTO ordenDTO;

	public SeguimientoOrdenTrabajoController () {

	}

	@FXML
	public void initialize () {
		System.out.println("entra a seguimiento");
	}

	public void cargarInformacion() {
		folio.setText(String.valueOf(ordenDTO.getFolio()));
		economico.setText(String.valueOf(ordenDTO.getEconomicoId()));
		empleado.setText(String.valueOf(ordenDTO.getNominaRegistro()));
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

}
