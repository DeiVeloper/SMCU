package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import mx.com.desoft.hidrogas.business.ISeguimientoOrdenBusiness;
import mx.com.desoft.hidrogas.dao.CatTipoRefaccionesDAO;
import mx.com.desoft.hidrogas.dao.IListaRefaccionesDAO;
import mx.com.desoft.hidrogas.dao.IOrdenTrabajoDAO;
import mx.com.desoft.hidrogas.dao.ISeguimientoOrdenDAO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenPartesDTO;
import mx.com.desoft.hidrogas.model.CatTipoListaRefaccion;
import mx.com.desoft.hidrogas.model.ListaRefacciones;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.model.SeguimientoOrden;
import mx.com.desoft.hidrogas.property.SeguimientoOrdenPartesProperty;
import mx.com.desoft.hidrogas.util.Constantes;

@Service
public class SeguimientoOrdenBusinessImpl implements ISeguimientoOrdenBusiness {

	@Autowired
	private ISeguimientoOrdenDAO seguimientoDAO;

	@Autowired
	private IListaRefaccionesDAO listaRefaccionesDAO;

	@Autowired
	private IOrdenTrabajoDAO ordenTrabajoDAO;

	@Autowired
	private CatTipoRefaccionesDAO catTipoRefacciones;

	@Override
	public void guardarSeguimiento(SeguimientoOrdenDTO seguimientoOrdenTO) {
		SeguimientoOrden seguimiento = this.convertirDTOToEntidad(seguimientoOrdenTO);
		seguimientoDAO.saveOrUpdate(seguimiento);
		for(SeguimientoOrdenPartesDTO parteUsada : seguimientoOrdenTO.getListaPartesUsadas()) {
			listaRefaccionesDAO.saveOrUpdate(this.convertirDTOToEntidadRefacciones(parteUsada, parteUsada.getTipoRefaccionId()));
		}
		for(SeguimientoOrdenPartesDTO parteSolicitada : seguimientoOrdenTO.getListaPartesSolicitadas()) {
			listaRefaccionesDAO.saveOrUpdate(this.convertirDTOToEntidadRefacciones(parteSolicitada, parteSolicitada.getTipoRefaccionId()));
		}
	}

	private SeguimientoOrden convertirDTOToEntidad(SeguimientoOrdenDTO seguimientoOrdenTO) {
		OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.get(seguimientoOrdenTO.getFolio());
		return seguimientoOrdenTO.getIdSeguimiento() == 0 ? new SeguimientoOrden(ordenTrabajo, seguimientoOrdenTO.getTrabajosRealizados(), seguimientoOrdenTO.getObservaciones(),
				seguimientoOrdenTO.getReparacionMayor(), seguimientoOrdenTO.getFechaReparacionMayor(), seguimientoOrdenTO.getFechaRegistro(), seguimientoOrdenTO.getNominaRegistro()) :
				new SeguimientoOrden(seguimientoOrdenTO.getIdSeguimiento(), ordenTrabajo, seguimientoOrdenTO.getTrabajosRealizados(), seguimientoOrdenTO.getObservaciones(),
						seguimientoOrdenTO.getReparacionMayor(), seguimientoOrdenTO.getFechaReparacionMayor(), seguimientoOrdenTO.getFechaRegistro(), seguimientoOrdenTO.getNominaRegistro());
	}

	private ListaRefacciones convertirDTOToEntidadRefacciones(SeguimientoOrdenPartesDTO listaRefacciones, int tipoRefeccion) {
		OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.get(listaRefacciones.getFolio());
		System.out.println("tipo refacicon dto: " + listaRefacciones.getTipoRefaccionId());
		CatTipoListaRefaccion catTipoRefaccion = catTipoRefacciones.get(listaRefacciones.getTipoRefaccionId());
		System.out.println("cantidad: " + listaRefacciones.getCantidad());
		System.out.println("tipo refacicon: " + catTipoRefaccion.getTipoRefaccionId());
		System.out.println("descripcion: " + listaRefacciones.getDescripcion());
		System.out.println("fecha: " + listaRefacciones.getFechaRegistro());
		System.out.println("marca: " + listaRefacciones.getMarca());
		System.out.println("parte: " + listaRefacciones.getParte());
		System.out.println("nomina: " + listaRefacciones.getNominaRegistro());
		return tipoRefeccion == 1 ? new ListaRefacciones(ordenTrabajo, listaRefacciones.getCantidad(), listaRefacciones.getParte(), listaRefacciones.getMarca(), listaRefacciones.getDescripcion(),
				catTipoRefaccion, listaRefacciones.getFechaRegistro(), listaRefacciones.getNominaRegistro())
				: new ListaRefacciones(ordenTrabajo, listaRefacciones.getCantidad(), listaRefacciones.getMarca(), listaRefacciones.getDescripcion(),
						catTipoRefaccion, listaRefacciones.getFechaRegistro(), listaRefacciones.getNominaRegistro());
	}

	public SeguimientoOrdenDTO getSeguimientoOrdenByFolio(int folio) {
		SeguimientoOrden seguimiento = seguimientoDAO.getSeguimientoByFolio(folio);
		List<SeguimientoOrdenPartesDTO> listaPartesUsadasDTO = new ArrayList<>();
		List<SeguimientoOrdenPartesDTO> listaPartesSolicitadasDTO = new ArrayList<>();
		SeguimientoOrdenDTO seguimientoDTO = new SeguimientoOrdenDTO();
		if(seguimiento != Constantes.NULL) {
			List<ListaRefacciones> listaPartesUsadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folio, 1);
			if(!listaPartesUsadas.isEmpty()) {
				for(ListaRefacciones refaccion : listaPartesUsadas) {
					listaPartesUsadasDTO.add(new SeguimientoOrdenPartesDTO(refaccion.getOrdenTrabajo().getFolio(), refaccion.getCantidad(), refaccion.getNoParte(), refaccion.getMarca(),
							refaccion.getDescripcion(), refaccion.getCatTipoListaRefaccion().getTipoRefaccionId(), refaccion.getFechaRegistro(), refaccion.getNominaRegistro()));
				}
			}
			List<ListaRefacciones> listaPartesSolicitadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folio, 2);
			if(!listaPartesSolicitadas.isEmpty()) {
				for(ListaRefacciones refaccion : listaPartesSolicitadas) {
					listaPartesSolicitadasDTO.add(new SeguimientoOrdenPartesDTO(refaccion.getOrdenTrabajo().getFolio(), refaccion.getCantidad(), refaccion.getNoParte(), refaccion.getMarca(),
							refaccion.getDescripcion(), refaccion.getCatTipoListaRefaccion().getTipoRefaccionId(), refaccion.getFechaRegistro(), refaccion.getNominaRegistro()));
				}
			}
			seguimientoDTO = new SeguimientoOrdenDTO(seguimiento.getId_seguimiento(), seguimiento.getOrdenTrabajo().getFolio(), seguimiento.getTrabajosRealizados(), seguimiento.getObservaciones(),
					seguimiento.getReparacionMayor(), seguimiento.getFechaReparaMayor(), seguimiento.getFechaRegistro(), seguimiento.getNominaRegistro(), listaPartesUsadasDTO, listaPartesSolicitadasDTO);
		}

		return seguimientoDTO;
	}

	public List<SeguimientoOrdenPartesProperty> getListaPartesByFolioTipo(int folio, int tipo) {
		List<SeguimientoOrdenPartesProperty> listaPartes = new ArrayList<>();
		List<ListaRefacciones> listaPartesModel = seguimientoDAO.getListaRefaccionesByFolioTipo(folio, tipo);
		for(ListaRefacciones refaccion : listaPartesModel) {
			listaPartes.add(tipo == 1 ? new SeguimientoOrdenPartesProperty(refaccion.getId_refaccion(), new SimpleStringProperty(String.valueOf(refaccion.getCantidad())),
					new SimpleStringProperty(refaccion.getNoParte()), new SimpleStringProperty(refaccion.getMarca()), new SimpleStringProperty(refaccion.getDescripcion())) :
					new SeguimientoOrdenPartesProperty(refaccion.getId_refaccion(), new SimpleStringProperty(String.valueOf(refaccion.getCantidad())),
							new SimpleStringProperty(refaccion.getMarca()), new SimpleStringProperty(refaccion.getDescripcion())));
		}
		return listaPartes;
	}
}
