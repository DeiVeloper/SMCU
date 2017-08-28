package mx.com.desoft.hidrogas.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.MainApp;

public class OrdenTrabajoController {

	public static Stage stageOrden;
	private MainApp mainApp;

	public OrdenTrabajoController () {

	}

	@FXML
	public void inicializaOrdenTrabajo () {
//		mainApp.showOrdenTrabajo();
	}

	/**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void agregarOrden() {
        mainApp.showOrdenTrabajo();
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void seguimientoOrden() {
        mainApp.showSeguimientoOrdenTrabajo();
    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
}
