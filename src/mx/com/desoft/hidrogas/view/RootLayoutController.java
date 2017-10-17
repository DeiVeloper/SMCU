package mx.com.desoft.hidrogas.view;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import mx.com.desoft.hidrogas.Authenticator;
import mx.com.desoft.hidrogas.Login;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.business.ISeguimientoOrdenBusiness;
import mx.com.desoft.hidrogas.business.SeguimientoOrdenBusinessImpl;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenDTO;
import mx.com.desoft.hidrogas.util.Alerta;
import mx.com.desoft.hidrogas.util.Constantes;
import mx.com.desoft.hidrogas.util.DateUtil;

import java.util.List;

import org.apache.log4j.Logger;

public class RootLayoutController {

	private static final Logger log = Logger.getLogger(RootLayoutController.class);

	private ISeguimientoOrdenBusiness seguimientoOrdenBusiness = Login.appContext.getBean(SeguimientoOrdenBusinessImpl.class);

	@FXML
	private MenuItem administradorItem;

	@FXML
	private MenuItem reportesItem;

	@FXML
	private ImageView imagenCentral;

    private MainApp mainApp;

    public RootLayoutController(){
    	this.inicializarComponentes();
    }

    @FXML
    private void initialize() {
    	this.validarUsuarioLogin();
    	imagenCentral.setImage(new Image("file:resources/images/presentacion.png"));
    	this.muestraOrdenesConReparacionMayor();
	}

    @FXML
    private void showEmpleadosOverView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminEmpleados.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.getStylesheets().add("file:resources/css/style.css");
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (Exception e) {
           log.error("Error: No se puedo iniciar la pantalla de Aministracion de Empleados.", e);
        }
    }

    @FXML
    private void showEconomicosOverView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminEconomicos.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.getStylesheets().add("file:resources/css/style.css");
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (Exception e) {
        	log.error("Error: No se puedo iniciar la pantalla de Aministracion de Economicos.", e);
        }
    }

    @FXML
    private void showTipoNecesidadView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminTipoNecesidad.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.getStylesheets().add("file:resources/css/style.css");
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (Exception e) {
        	log.error("Error: No se puedo iniciar la pantalla de Aministracion de Tipo de Necesidad.", e);
        }
    }

    @FXML
    private void showTipoRefaccionesView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminTipoRefacciones.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.getStylesheets().add("file:resources/css/style.css");
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (Exception e) {
        	log.error("Error: No se puedo iniciar la pantalla de Aministracion de Tipo de Refacciones.", e);
        }
    }

    @FXML
    private void showReportesView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminReportes.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            personOverview.getStylesheets().add("file:resources/css/style.css");
            personOverview.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            mainApp.getRootLayout().setCenter(personOverview);
        } catch (Exception e) {
        	log.error("Error: No se puedo iniciar la pantalla de Aministracion de Reportes.", e);
        }
    }

    /**
     * Abre pantalla de orden de trabajo
     */
    @FXML
    public void nuevaOrden() {
    	mainApp.showAdministrarOrdenTrabajo();
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	String mensaje = "Sitema de Mantenimiento y Control de Unidades \n"
    			+ "Powered by DeSoft.inc\n"
    			+ "\n"
    			+ "Admnistradores: \n"
    			+ "Ing. Erick Martinez Veneros, cirek_18@hotmail.com \n"
    			+ "Ing. Carlos David Castro Aguilar, david.c13@hotmail.com"
    			+ "\n";
    	Alerta.crearAlertaUsuario("DeSoft.inc", mensaje, AlertType.INFORMATION);
    }

    @FXML
    private void cerrarSesion(){
    	try {
    		Login login = new Login();
    		mainApp.getPrimaryStage().close();
    		login.inicializarLogin();
		} catch (Exception e) {
			log.error("Error: No se puedo cerrar la sesion.", e);
		}
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private void inicializarComponentes(){
    	administradorItem = new MenuItem();
    	reportesItem = new MenuItem();
    	imagenCentral = new ImageView();
    }

    private void muestraOrdenesConReparacionMayor(){
    	List<SeguimientoOrdenDTO> listaOrdenesConReparacionMayor = seguimientoOrdenBusiness.getOrdenesConReparacionMayor();
    	if(listaOrdenesConReparacionMayor.isEmpty()) {
    		return;

    	} else {
    		StringBuilder mensaje = new StringBuilder();
        	mensaje.append(Constantes.ORDENES_REPARACION_MAYOR + ": \n");
        	mensaje.append("Folio\t\tFecha\n");
    		for(SeguimientoOrdenDTO seguimiento : listaOrdenesConReparacionMayor) {
    			mensaje.append(seguimiento.getFolio() + "\t\t" + DateUtil.getStringFromDate(seguimiento.getFechaReparacionMayor()) +"\n");
    		}
    		Alerta.crearAlertaUsuario(Constantes.NOTIFICACIONES, mensaje.toString(), AlertType.INFORMATION);
    	}
    }

    private void validarUsuarioLogin(){
    	if(Authenticator.usuarioSesion.getDescripcionTipoEmpleado().equals(Constantes.ADMINISTRADOR))	{
    		administradorItem.setVisible(true);
    		reportesItem.setVisible(true);
    	}else	{
    		administradorItem.setVisible(false);
    		reportesItem.setVisible(false);
    	}
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
