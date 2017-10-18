package mx.com.desoft.hidrogas.view;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.business.CatalogoBusinessImpl;
import mx.com.desoft.hidrogas.business.ICatalogoBusiness;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.TipoReporteDTO;
import mx.com.desoft.hidrogas.helper.CatalogosHelper;
import mx.com.desoft.hidrogas.helper.CatalogosHelperImpl;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;
import mx.com.desoft.hidrogas.util.DateUtil;
import mx.com.desoft.hidrogas.util.IReportes;
import mx.com.desoft.hidrogas.util.Reportes;

public class AdministrarReportesController {

	private static final Logger log = Logger.getLogger(AdministrarReportesController.class);

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
	private ICatalogoBusiness catalogoBusiness = Login.appContext.getBean(CatalogoBusinessImpl.class);
	private IReportes reporte = Login.appContext.getBean(Reportes.class);
	private OrdenTrabajoDTO ordenTrabajoDTO;
	private String mensaje = "";

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
		if (validarCamposView()) {
			try {
				switch (comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion()) {
					case Constantes.ORDEN:
						OrdenTrabajoDTO orden = catalogoBusiness.getOrdenById(Integer.parseInt(textFiedlNofolio.getText()));
						if	(orden != null)	{
							reporte.generarTicketOrdenServicio(orden);
							Alerta.crearAlertaUsuario(Constantes.INFORMACION, Constantes.TICKET, AlertType.CONFIRMATION);
						}	else	{
							Alerta.crearAlertaUsuario(Constantes.INFORMACION, Constantes.RESULTADO_CONSULTA_REPORTE, AlertType.INFORMATION);
						}
					break;

					case Constantes.INCIDENCIAS:
						List<OrdenTrabajoDTO> listaIncidencias = catalogoBusiness.getIncidenciasOrdenes(ordenTrabajoDTO);
						if	(listaIncidencias != null && listaIncidencias.size() != Constantes.CERO)	{
							reporte.generarReporteIncidencias(listaIncidencias);
							Alerta.crearAlertaUsuario(Constantes.INFORMACION, Constantes.REPORTE_EXITOSO, AlertType.CONFIRMATION);
						}	else	{
							Alerta.crearAlertaUsuario(Constantes.INFORMACION, Constantes.RESULTADO_CONSULTA_REPORTE, AlertType.INFORMATION);
						}
					break;

					case Constantes.REPARACIONES:
						List<OrdenTrabajoDTO> listaReparaciones = catalogoBusiness.getOrdenByTipoNecesidad(ordenTrabajoDTO);
						if	(listaReparaciones != null && listaReparaciones.size() != Constantes.CERO)	{
							reporte.generarReporteTipoReparacion(listaReparaciones);
							Alerta.crearAlertaUsuario(Constantes.INFORMACION, Constantes.REPORTE_EXITOSO, AlertType.CONFIRMATION);
						}	else	{
							Alerta.crearAlertaUsuario(Constantes.INFORMACION, Constantes.RESULTADO_CONSULTA_REPORTE, AlertType.INFORMATION);
						}
					break;

					default:
						break;
				}
			}catch (IOException e) {
				log.error("Error al generar reporte", e);
				Alerta.crearAlertaUsuario(Constantes.ERROR, e.getMessage(), AlertType.ERROR);
			}
		}	else	{
			Alerta.crearAlertaUsuario(Constantes.VALIDANDO_FORMULARIO, mensaje, AlertType.INFORMATION);
		}
	}

	@FXML
	private void limpiarCamposView() {
		textFiedlNofolio.clear();
		textFiedlNofolio.setDisable(false);
		comboBoxTipoReporte.getSelectionModel().clearSelection();
		comboBoxTipoReporte.setDisable(false);
		comboBoxEmpleado.getSelectionModel().clearSelection();
		comboBoxEmpleado.setDisable(false);
		comboBoxEconomico.getSelectionModel().clearSelection();
		comboBoxEconomico.setDisable(false);
		comboBoxEstatus.getSelectionModel().clearSelection();
		comboBoxEstatus.setDisable(false);
		comboBoxTipoNecesidad.getSelectionModel().clearSelection();
		comboBoxTipoNecesidad.setDisable(false);
		datePickerFechaRegistroInicio.setValue(null);
		datePickerFechaRegistroInicio.setDisable(false);
		datePickerFechaRegistroFin.setValue(null);
		datePickerFechaRegistroFin.setDisable(false);
	}

	@FXML
	private void seleccionarTipoReporte() {
		if(comboBoxTipoReporte.getSelectionModel().getSelectedItem() != null){
			switch (comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion()) {
				case Constantes.ORDEN:
					this.componentesOrden();
					break;

				case Constantes.INCIDENCIAS:
					this.componentesIncidencias();
					break;

				case Constantes.REPARACIONES:
					this.componentesReparaciones();
					break;

				default:
					break;
			}
		}
	}

	private boolean validarCamposView() {
		if	(!comboBoxTipoReporte.getSelectionModel().isEmpty()){

			if	(comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion().equals(Constantes.ORDEN)){
				if(textFiedlNofolio.getText().isEmpty()){
					mensaje = "Favor de capturar el folio de la orden.";
					return false;
				}
			}else if(comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion().equals(Constantes.INCIDENCIAS) ||
					comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion().equals(Constantes.REPARACIONES)){
				if(datePickerFechaRegistroInicio.getValue() == null){
					mensaje = "Favor de capturar una fecha de inicio.";
					return false;
				}
				if(datePickerFechaRegistroFin.getValue() == null){
					mensaje = "Favor de capturar una fecha fin.";
					return false;
				}
			}

		}	else	{
			mensaje = "Favor de seleccionar un tipo de reporte.";
			return false;
		}
		this.convertirCamosToDTO();
		return true;
	}

	private void convertirCamosToDTO() {
		ordenTrabajoDTO = new OrdenTrabajoDTO();
		if (comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion().equals(Constantes.ORDEN)) {
			ordenTrabajoDTO.setFolio(Integer.parseInt(textFiedlNofolio.getText()));

		}else if(comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion().equals(Constantes.INCIDENCIAS)){
//			System.out.println(comboBoxEmpleado.getSelectionModel().isEmpty());
//			System.out.println(comboBoxEconomico.getSelectionModel().isEmpty());
//			System.out.println(comboBoxEstatus.getSelectionModel().isEmpty());
//			System.out.println(comboBoxTipoNecesidad.getSelectionModel().isEmpty());
//			ordenTrabajoDTO.setNominaOperador(!comboBoxEmpleado.getSelectionModel().isEmpty() ?
//					comboBoxEmpleado.getSelectionModel().getSelectedItem().getNominaEmpleado() : null);
//			ordenTrabajoDTO.setEconomicoId(!comboBoxEconomico.getSelectionModel().isEmpty() ?
//					comboBoxEconomico.getSelectionModel().getSelectedItem().getEconomicoId() : null);
//			ordenTrabajoDTO.setEstatusOrden(!comboBoxEstatus.getSelectionModel().isEmpty()  ?
//					comboBoxEstatus.getSelectionModel().getSelectedItem().getId() : null);
//			ordenTrabajoDTO.setTipoNecesidadId(!comboBoxTipoNecesidad.getSelectionModel().isEmpty()  ?
//					comboBoxTipoNecesidad.getSelectionModel().getSelectedItem().getTipoNecesidadId() : null);
			ordenTrabajoDTO.setFechaInicio(DateUtil.getFechaDatePicker(datePickerFechaRegistroInicio));
			ordenTrabajoDTO.setFechaFin(DateUtil.getFechaDatePicker(datePickerFechaRegistroFin));

		}else if(comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion().equals(Constantes.REPARACIONES)){
			ordenTrabajoDTO.setFechaInicio(DateUtil.getFechaDatePicker(datePickerFechaRegistroInicio));
			ordenTrabajoDTO.setFechaFin(DateUtil.getFechaDatePicker(datePickerFechaRegistroFin));
		}
	}

	private void inicializarCombos(){
		catalogosHelperImpl.llenarComboTipoReorte(comboBoxTipoReporte);
		catalogosHelperImpl.llenarComboOperador(comboBoxEmpleado);
		catalogosHelperImpl.llenarComboEconomico(comboBoxEconomico);
		catalogosHelperImpl.llenarComboEstatus(comboBoxEstatus);
		catalogosHelperImpl.llenarComboTipoNecesidad(comboBoxTipoNecesidad);

	}

	private void componentesOrden(){
		textFiedlNofolio.setDisable(false);
		comboBoxEmpleado.setDisable(true);
		comboBoxEconomico.setDisable(true);
		comboBoxEstatus.setDisable(true);
		comboBoxTipoNecesidad.setDisable(true);
		datePickerFechaRegistroInicio.setDisable(true);
		datePickerFechaRegistroFin.setDisable(true);
	}

	private void componentesIncidencias(){
		textFiedlNofolio.clear();
		textFiedlNofolio.setDisable(true);
		comboBoxEmpleado.setDisable(false);
		comboBoxEconomico.setDisable(false);
		comboBoxEstatus.setDisable(false);
		comboBoxTipoNecesidad.setDisable(false);
		datePickerFechaRegistroInicio.setDisable(false);
		datePickerFechaRegistroFin.setDisable(false);
	}

	private void componentesReparaciones(){
		textFiedlNofolio.clear();
		textFiedlNofolio.setDisable(true);
		comboBoxEmpleado.setDisable(true);
		comboBoxEconomico.setDisable(true);
		comboBoxEstatus.setDisable(true);
		comboBoxTipoNecesidad.setDisable(true);
		datePickerFechaRegistroInicio.setDisable(false);
		datePickerFechaRegistroFin.setDisable(false);

	}
}
