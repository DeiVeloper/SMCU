package mx.com.desoft.hidrogas.view;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.property.SeguimientoOrdenPartesProperty;

public class SeguimientoOrdenTrabajoController {

	//variables para tabla partes usadas
	@FXML
    private TableView<SeguimientoOrdenPartesProperty> tablaPartesUsadas;
	@FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> cantidadUsadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> parteUsadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> marcaUsadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> descripcionUsadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> eliminarUsadaColumn;

    //variables para tabla partes solicitadas
    @FXML
    private TableView<SeguimientoOrdenPartesProperty> tablaPartesSolicitadas;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> cantidadSolicitadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> marcaSolicitadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> descripcionSolicitadaColumn;
    @FXML
    private TableColumn<SeguimientoOrdenPartesProperty, String> eliminarSolicitadaColumn;

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
	private ObservableList<SeguimientoOrdenPartesProperty> dataPartesUsadas = FXCollections.observableArrayList();
	private ObservableList<SeguimientoOrdenPartesProperty> dataPartesSolicitadas = FXCollections.observableArrayList();
	private List<SeguimientoOrdenPartesProperty> dtoPartesUsadas;
	private List<SeguimientoOrdenPartesProperty> dtoPartesSolicitadas;

	public SeguimientoOrdenTrabajoController () {

	}

	@FXML
	public void initialize () {
		System.out.println("entra a seguimiento");
		dtoPartesUsadas = new ArrayList<>();
		dtoPartesSolicitadas = new ArrayList<>();
	}

	public void cargarInformacion() {
		folio.setText(String.valueOf(ordenDTO.getFolio()));
		economico.setText(String.valueOf(ordenDTO.getEconomicoId()));
		empleado.setText(String.valueOf(ordenDTO.getNominaRegistro()));
	}

	public void agregarPartesUsadas() {
		dtoPartesUsadas.add(new SeguimientoOrdenPartesProperty(new SimpleStringProperty(cantidadPU.getText()), new SimpleStringProperty(noPU.getText()),
				new SimpleStringProperty(marcaPU.getText()), new SimpleStringProperty(descripcionPU.getText()), new Button("Eliminar")));
		dataPartesUsadas.clear();
    	dataPartesUsadas.addAll(dtoPartesUsadas);
		tablaPartesUsadas.setItems(getDataPartesUsadas());
    	cantidadUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
    	parteUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getParte());
    	marcaUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getMarca());
    	descripcionUsadaColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcion());
    	eliminarUsadaColumn.setCellValueFactory(new PropertyValueFactory<SeguimientoOrdenPartesProperty, String> ("Eliminar"));
	}

	public void agregarPartesSolicitadas() {
		dtoPartesSolicitadas.add(new SeguimientoOrdenPartesProperty(new SimpleStringProperty(cantidadPS.getText()), new SimpleStringProperty(marcaPS.getText()),
				new SimpleStringProperty(descripcionPS.getText()), new Button("Eliminar")));
		dataPartesSolicitadas.clear();
    	dataPartesSolicitadas.addAll(dtoPartesSolicitadas);
		tablaPartesSolicitadas.setItems(getDataPartesSolicitadas());
    	cantidadSolicitadaColumn.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
    	marcaSolicitadaColumn.setCellValueFactory(cellData -> cellData.getValue().getMarca());
    	descripcionSolicitadaColumn.setCellValueFactory(cellData -> cellData.getValue().getDescripcion());
    	eliminarSolicitadaColumn.setCellValueFactory(new PropertyValueFactory<SeguimientoOrdenPartesProperty, String> ("Eliminar"));

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

	public ObservableList<SeguimientoOrdenPartesProperty> getDataPartesUsadas() {
		return dataPartesUsadas;
	}

	public void setDataPartesUsadas(ObservableList<SeguimientoOrdenPartesProperty> dataPartesUsadas) {
		this.dataPartesUsadas = dataPartesUsadas;
	}

	public ObservableList<SeguimientoOrdenPartesProperty> getDataPartesSolicitadas() {
		return dataPartesSolicitadas;
	}

	public void setDataPartesSolicitadas(ObservableList<SeguimientoOrdenPartesProperty> dataPartesSolicitadas) {
		this.dataPartesSolicitadas = dataPartesSolicitadas;
	}

	public List<SeguimientoOrdenPartesProperty> getDtoPartesUsadas() {
		return dtoPartesUsadas;
	}

	public void setDtoPartesUsadas(List<SeguimientoOrdenPartesProperty> dtoPartesUsadas) {
		this.dtoPartesUsadas = dtoPartesUsadas;
	}

	public List<SeguimientoOrdenPartesProperty> getDtoPartesSolicitadas() {
		return dtoPartesSolicitadas;
	}

	public void setDtoPartesSolicitadas(List<SeguimientoOrdenPartesProperty> dtoPartesSolicitadas) {
		this.dtoPartesSolicitadas = dtoPartesSolicitadas;
	}

}
