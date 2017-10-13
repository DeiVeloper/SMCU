package mx.com.desoft.hidrogas.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.TipoReporteDTO;
import mx.com.desoft.hidrogas.helper.CatalogosHelper;
import mx.com.desoft.hidrogas.helper.CatalogosHelperImpl;
import mx.com.desoft.hidrogas.util.Constantes;
import mx.com.desoft.hidrogas.util.Reportes;

public class AdministrarReportesController {

	@FXML
	private TextField textFiedlNofolio;

	@FXML
	private ComboBox<TipoReporteDTO> comboBoxTipoReporte;

	@FXML
	private ComboBox<EmpleadoDTO> comboBoxEmpleado;

	@FXML
	private ComboBox<EconomicoDTO> comboBoxEconomico;

	@FXML
	private ComboBox<CatEstatusOrdenDTO> comboBoxEstatus;

	@FXML
	private DatePicker datePickerFechaRegistroInicio;

	@FXML
	private DatePicker datePickerFechaRegistroFin;

	@FXML
	private ComboBox<CatTipoNecesidadDTO> comboBoxTipoNecesidad;

	private CatalogosHelper catalogosHelperImpl = Login.appContext.getBean(CatalogosHelperImpl.class);
	private OrdenTrabajoDTO ordenTrabajoDTO;

	public AdministrarReportesController() {
		textFiedlNofolio = new TextField();
		comboBoxTipoReporte = new ComboBox<>();
		comboBoxEmpleado = new ComboBox<>();
		comboBoxEconomico = new ComboBox<>();
		comboBoxEstatus = new ComboBox<>();
		datePickerFechaRegistroInicio = new DatePicker();
		datePickerFechaRegistroFin = new DatePicker();
		comboBoxTipoNecesidad = new ComboBox<>();
	}

	@FXML
	private void initialize() {
		this.inicializarCombos();

	}

	@FXML
	private void imprimirReporte() {
		Reportes reporte = new Reportes();
		if (validarCamposView()) {
			this.convertirCamosToDTO();
			reporte.generarReporteTipoReparacion();
			// reporte.generarReporteIncidencias(catalogoBusinessImpl.imprimirReporte(ordenTrabajoDTO));
		}
	}

	@FXML
	private void limpiarCamposView() {
		textFiedlNofolio.clear();
		textFiedlNofolio.setDisable(false);
		comboBoxTipoReporte.getSelectionModel().clearSelection();
		comboBoxEmpleado.getSelectionModel().clearSelection();
		comboBoxEconomico.getSelectionModel().clearSelection();
		comboBoxEstatus.getSelectionModel().clearSelection();
		comboBoxTipoNecesidad.getSelectionModel().clearSelection();
		datePickerFechaRegistroInicio.setValue(null);
		datePickerFechaRegistroFin.setValue(null);
	}

	@FXML
	private void seleccionarTipoReporte() {
		if(comboBoxTipoReporte.getSelectionModel().getSelectedItem() != null){
			switch (comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion()) {
				case Constantes.ORDEN:
					this.deshabilitarcomponentes();
					break;

				case Constantes.INCIDENCIAS:
					this.habilitarComponentes();
					break;
				case Constantes.REPARACIONES:

					break;

				default:
					break;
			}
		}
	}

	private boolean validarCamposView() {
		return true;
	}

	private void convertirCamosToDTO() {
		ordenTrabajoDTO = new OrdenTrabajoDTO();
		System.out.println("textFiedlNofolio.getText()" + textFiedlNofolio.getText() != null
				&& !textFiedlNofolio.getText().isEmpty());
		System.out.println("textFiedlNofolio.getText()" + textFiedlNofolio.getText().length());
		System.out.println("textFiedlNofolio.getText().isEmpty()" + !textFiedlNofolio.getText().isEmpty());
		ordenTrabajoDTO.setFolio((textFiedlNofolio.getText() != null && !textFiedlNofolio.getText().isEmpty())
				? Integer.parseInt(textFiedlNofolio.getText()) : 0);
		// ordenTrabajoDTO.setNominaOperador(textFieldEmpleado.getText().length()
		// > 0 ? Integer.parseInt(textFieldEconomico.getText()) : null);
		// ordenTrabajoDTO.setEstatusOrden(comboBoxEstatus.getSelectionModel().getSelectedItem().getId());
		// ordenTrabajoDTO.setFechaRegistro(datePickerFechaRegistro.getValue()
		// != null ? java.sql.Date.valueOf(datePickerFechaRegistro.getValue()) :
		// null);
		// ordenTrabajoDTO.setTipoNecesidadId(comboBoxTipoNecesidad.getSelectionModel().getSelectedItem().getTipoNecesidadId());
	}

	private void inicializarCombos(){
		catalogosHelperImpl.llenarComboTipoReorte(comboBoxTipoReporte);
		catalogosHelperImpl.llenarComboOperador(comboBoxEmpleado);
		catalogosHelperImpl.llenarComboEconomico(comboBoxEconomico);
		catalogosHelperImpl.llenarComboEstatus(comboBoxEstatus);
		catalogosHelperImpl.llenarComboTipoNecesidad(comboBoxTipoNecesidad);

	}

	private void deshabilitarcomponentes(){
		textFiedlNofolio.setDisable(false);
		comboBoxEmpleado.setDisable(true);
		comboBoxEconomico.setDisable(true);
		comboBoxEstatus.setDisable(true);
		comboBoxTipoNecesidad.setDisable(true);
		datePickerFechaRegistroInicio.setDisable(true);
		datePickerFechaRegistroFin.setDisable(true);
	}

	private void habilitarComponentes(){
		textFiedlNofolio.clear();
		textFiedlNofolio.setDisable(true);
		comboBoxEmpleado.setDisable(false);
		comboBoxEconomico.setDisable(false);
		comboBoxEstatus.setDisable(false);
		comboBoxTipoNecesidad.setDisable(false);
		datePickerFechaRegistroInicio.setDisable(false);
		datePickerFechaRegistroFin.setDisable(false);
	}
}
