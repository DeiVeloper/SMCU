package mx.com.desoft.hidrogas.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.business.AdministrarOrdenBusinessImpl;
import mx.com.desoft.hidrogas.business.CatalogoBusinessImpl;
import mx.com.desoft.hidrogas.business.IAdministrarOrdenBusiness;
import mx.com.desoft.hidrogas.business.ICatalogoBusiness;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.TipoReporteDTO;
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
	private DatePicker datePickerFechaRegistroFin;
	
	@FXML
	private DatePicker datePickerFechaRegistroInicio;

	@FXML
	private ComboBox<CatTipoNecesidadDTO> comboBoxTipoNecesidad;

	private ICatalogoBusiness catalogoBusinessImpl = Login.appContext.getBean(CatalogoBusinessImpl.class);
	private IAdministrarOrdenBusiness administrarOrdenBusinessImpl = Login.appContext.getBean(AdministrarOrdenBusinessImpl.class);
	private OrdenTrabajoDTO ordenTrabajoDTO;

	public AdministrarReportesController(){
//		textFiedlNofolio = new TextField();
//		comboBoxTipoReporte = new ComboBox<>();
//		textFieldEmpleado = new TextField();
//		textFieldEconomico = new TextField();
//		comboBoxEstatus = new ComboBox<>();
//		datePickerFechaRegistro = new DatePicker();
//		comboBoxTipoNecesidad = new ComboBox<>();
	}

	@FXML
    private void initialize() {
		this.llenarComboTipoReorte();
		this.llenarComboEmpleado();
		this.llenarComboEconomico();
		this.llenarComboEstatus();
		this.llenarComboTipoNecesidad();

	}

	@FXML
	private void imprimirReporte(){
		Reportes reporte = new Reportes();
		if(validarCamposView())	{
			this.convertirCamosToDTO();
//			reporte.generarReporteIncidencias(catalogoBusinessImpl.imprimirReporte(ordenTrabajoDTO));
		}
	}


	@FXML
	private void limpiarCamposView(){
		textFiedlNofolio.clear();
		comboBoxTipoReporte.getSelectionModel().clearSelection();
		comboBoxEmpleado.getSelectionModel().clearSelection();
		comboBoxEconomico.getSelectionModel().clearSelection();
		comboBoxEstatus.getSelectionModel().clearSelection();
		comboBoxTipoNecesidad.getSelectionModel().clearSelection();
		datePickerFechaRegistroInicio.setValue(null);
		datePickerFechaRegistroFin.setValue(null);
	}
	
	@FXML
	private void seleccionarTipoReporte(){
		switch (comboBoxTipoReporte.getSelectionModel().getSelectedItem().getDescripcion()) {
		case Constantes.ORDEN:
			
			break;
			
		case Constantes.INCIDENCIAS:
			
			break;
		case Constantes.REPARACIONES:
			
			break;

		default:
			break;
		}
	}
	
	private boolean validarCamposView(){

		return true;

	}
	
	
	private void convertirCamosToDTO(){
		ordenTrabajoDTO = new OrdenTrabajoDTO();
		System.out.println("textFiedlNofolio.getText()" + textFiedlNofolio.getText() != null && !textFiedlNofolio.getText().isEmpty());
		System.out.println("textFiedlNofolio.getText()" + textFiedlNofolio.getText().length());
		System.out.println("textFiedlNofolio.getText().isEmpty()" + !textFiedlNofolio.getText().isEmpty());
		ordenTrabajoDTO.setFolio((textFiedlNofolio.getText() != null && !textFiedlNofolio.getText().isEmpty()) ? Integer.parseInt(textFiedlNofolio.getText()) : 0);
//		ordenTrabajoDTO.setNominaOperador(textFieldEmpleado.getText().length() > 0  ? Integer.parseInt(textFieldEconomico.getText()) : null);
//		ordenTrabajoDTO.setEstatusOrden(comboBoxEstatus.getSelectionModel().getSelectedItem().getId());
//		ordenTrabajoDTO.setFechaRegistro(datePickerFechaRegistro.getValue() != null ? java.sql.Date.valueOf(datePickerFechaRegistro.getValue()) : null);
//		ordenTrabajoDTO.setTipoNecesidadId(comboBoxTipoNecesidad.getSelectionModel().getSelectedItem().getTipoNecesidadId());
	}

	private void llenarComboTipoReorte(){
		comboBoxTipoReporte.setItems(FXCollections.observableArrayList(catalogoBusinessImpl.findAllTipoReporte()));
		comboBoxTipoReporte.setConverter(new StringConverter<TipoReporteDTO>() {
			@Override
			public String toString(TipoReporteDTO tipo) {
				if (tipo == null) {
					return null;
        		} else {
        			return tipo.getDescripcion();
            	}
            }

			@Override
			public TipoReporteDTO fromString(String string) {
				return null;
            }
    	});
	}

	private void llenarComboEstatus(){
		comboBoxEstatus.setItems(FXCollections.observableArrayList(administrarOrdenBusinessImpl.buscarTiposEstatus()));
		comboBoxEstatus.setConverter(new StringConverter<CatEstatusOrdenDTO>() {
			@Override
			public String toString(CatEstatusOrdenDTO tipo) {
				if (tipo == null) {
					return null;
        		} else {
        			return tipo.getDescripcion();
            	}
            }

			@Override
			public CatEstatusOrdenDTO fromString(String string) {
				return null;
            }
    	});
	}

	private void llenarComboTipoNecesidad(){
		comboBoxTipoNecesidad.setItems(FXCollections.observableArrayList(catalogoBusinessImpl.findAllTipoNecesidad()));
		comboBoxTipoNecesidad.setConverter(new StringConverter<CatTipoNecesidadDTO>() {
			@Override
			public String toString(CatTipoNecesidadDTO tipo) {
				if (tipo == null) {
					return null;
        		} else {
        			return tipo.getDescripcion().toString();
            	}
            }

			@Override
			public CatTipoNecesidadDTO fromString(String string) {
				return null;
            }
    	});
	}
	
	private void llenarComboEmpleado(){
		comboBoxEmpleado.setItems(FXCollections.observableArrayList(catalogoBusinessImpl.findAllOperadores()));
		comboBoxEmpleado.setConverter(new StringConverter<EmpleadoDTO>() {
			@Override
			public String toString(EmpleadoDTO tipo) {
				if (tipo == null) {
					return null;
        		} else {
        			return tipo.getNominaEmpleado().toString().concat(" - ")
        					.concat(tipo.getNombreEmpleado()).concat(" ")
        					.concat(tipo.getApellidoPatEmpleado()).concat(" ")
        					.concat(tipo.getApellidoMatEmpleado()).toString();
            	}
            }

			@Override
			public EmpleadoDTO fromString(String string) {
				return null;
            }
    	});
	}
	
	private void llenarComboEconomico(){
		comboBoxEconomico.setItems(FXCollections.observableArrayList(catalogoBusinessImpl.findAllEconomicos()));
		comboBoxEconomico.setConverter(new StringConverter<EconomicoDTO>() {
			@Override
			public String toString(EconomicoDTO tipo) {
				if (tipo == null) {
					return null;
        		} else {
        			return tipo.getEconomicoId().toString();
            	}
            }

			@Override
			public EconomicoDTO fromString(String string) {
				return null;
            }
    	});
	}
}
