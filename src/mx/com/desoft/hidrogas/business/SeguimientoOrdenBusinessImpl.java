package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import mx.com.desoft.hidrogas.business.ISeguimientoOrdenBusiness;
import mx.com.desoft.hidrogas.dao.CatTipoRefaccionesDAO;
import mx.com.desoft.hidrogas.dao.ICatTipoRefaccionDAO;
import mx.com.desoft.hidrogas.dao.IListaRefaccionesDAO;
import mx.com.desoft.hidrogas.dao.IOrdenTrabajoDAO;
import mx.com.desoft.hidrogas.dao.ISeguimientoEmpleadosDAO;
import mx.com.desoft.hidrogas.dao.ISeguimientoOrdenDAO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenPartesDTO;
import mx.com.desoft.hidrogas.model.CatTipoListaRefaccion;
import mx.com.desoft.hidrogas.model.CatTipoRefaccion;
import mx.com.desoft.hidrogas.model.ListaRefacciones;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.model.SeguimientoOrden;
import mx.com.desoft.hidrogas.model.SeguimientosEmpleado;
import mx.com.desoft.hidrogas.model.SeguimientosEmpleadoPK;
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

	@Autowired
	private ISeguimientoEmpleadosDAO seguimientoEmpleadosDAO;

	@Autowired
	private ICatTipoRefaccionDAO catTipoRefaccionDAO;

	@Override
	public void guardarSeguimiento(SeguimientoOrdenDTO seguimientoOrdenTO) {
		final SeguimientoOrden seguimiento = this.convertirDTOToEntidad(seguimientoOrdenTO);
		seguimientoDAO.saveOrUpdate(seguimiento);
		for(SeguimientoOrdenPartesDTO parteUsada : seguimientoOrdenTO.getListaPartesUsadas()) {
			listaRefaccionesDAO.saveOrUpdate(this.convertirDTOToEntidadRefacciones(parteUsada, parteUsada.getTipoRefaccionId()));
		}
		for(SeguimientoOrdenPartesDTO parteSolicitada : seguimientoOrdenTO.getListaPartesSolicitadas()) {
			listaRefaccionesDAO.saveOrUpdate(this.convertirDTOToEntidadRefacciones(parteSolicitada, parteSolicitada.getTipoRefaccionId()));
		}
		final SeguimientosEmpleadoPK seguimientoEmpleadoId = new SeguimientosEmpleadoPK(seguimiento.getId_seguimiento(), seguimiento.getNominaRegistro());
		if(seguimientoEmpleadosDAO.get(seguimientoEmpleadoId) == Constantes.NULL) {
			seguimientoEmpleadosDAO.saveOrUpdate(new SeguimientosEmpleado(seguimientoEmpleadoId, new Date()));
		}
	}

	private SeguimientoOrden convertirDTOToEntidad(SeguimientoOrdenDTO seguimientoOrdenTO) {
		OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.get(seguimientoOrdenTO.getFolio());
		return seguimientoOrdenTO.getIdSeguimiento() == 0 ? new SeguimientoOrden(ordenTrabajo, seguimientoOrdenTO.getTrabajosRealizados(), seguimientoOrdenTO.getObservaciones(),
				seguimientoOrdenTO.getReparacionMayor(), seguimientoOrdenTO.getFechaReparacionMayor(), seguimientoOrdenTO.getFechaRegistro(), seguimientoOrdenTO.getNominaRegistro()) :
				new SeguimientoOrden(seguimientoOrdenTO.getIdSeguimiento(), ordenTrabajo, seguimientoOrdenTO.getTrabajosRealizados(), seguimientoOrdenTO.getObservaciones(),
						seguimientoOrdenTO.getReparacionMayor(), seguimientoOrdenTO.getFechaReparacionMayor(), seguimientoOrdenTO.getFechaRegistro(), seguimientoOrdenTO.getNominaRegistro());
	}

	private ListaRefacciones convertirDTOToEntidadRefacciones(SeguimientoOrdenPartesDTO listaRefacciones, int tipoRefaccion) {
		OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.get(listaRefacciones.getFolio());
		CatTipoListaRefaccion catTipoListaRefaccion = catTipoRefacciones.get(listaRefacciones.getTipoRefaccionId());
		CatTipoRefaccion catTipoRefaccion = catTipoRefaccionDAO.get(listaRefacciones.getIdCatTipoRefaccion());
		return new ListaRefacciones(ordenTrabajo, listaRefacciones.getCantidad(), catTipoRefaccion, listaRefacciones.getDescripcion(),
				catTipoListaRefaccion, listaRefacciones.getFechaRegistro(), listaRefacciones.getNominaRegistro());
	}

	public SeguimientoOrdenDTO getSeguimientoOrdenByFolio(int folio) {
		SeguimientoOrden seguimiento = seguimientoDAO.getSeguimientoByFolio(folio);
		List<SeguimientoOrdenPartesDTO> listaPartesUsadasDTO = new ArrayList<>();
		List<SeguimientoOrdenPartesDTO> listaPartesSolicitadasDTO = new ArrayList<>();
		SeguimientoOrdenDTO seguimientoDTO = new SeguimientoOrdenDTO();
		if(seguimiento != Constantes.NULL) {
			List<ListaRefacciones> listaPartesUsadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folio, Constantes.N1);
			if(!listaPartesUsadas.isEmpty()) {
				for(ListaRefacciones refaccion : listaPartesUsadas) {
					listaPartesUsadasDTO.add(new SeguimientoOrdenPartesDTO(refaccion.getOrdenTrabajo().getFolio(), refaccion.getCantidad(), refaccion.getCatTipoRefaccion().getIdTipoRefaccion(),
							refaccion.getDescripcion(), refaccion.getCatTipoListaRefaccion().getTipoRefaccionId(), refaccion.getFechaRegistro(), refaccion.getNominaRegistro()));
				}
			}
			List<ListaRefacciones> listaPartesSolicitadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folio, Constantes.N2);
			if(!listaPartesSolicitadas.isEmpty()) {
				for(ListaRefacciones refaccion : listaPartesSolicitadas) {
					listaPartesSolicitadasDTO.add(new SeguimientoOrdenPartesDTO(refaccion.getOrdenTrabajo().getFolio(), refaccion.getCantidad(), refaccion.getCatTipoRefaccion().getIdTipoRefaccion(),
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
			listaPartes.add(new SeguimientoOrdenPartesProperty(refaccion.getId_refaccion(), new SimpleStringProperty(String.valueOf(refaccion.getCantidad())),
					refaccion.getCatTipoRefaccion().getIdTipoRefaccion(), new SimpleStringProperty(refaccion.getCatTipoRefaccion().getDescripcion()), new SimpleStringProperty(refaccion.getDescripcion())));
		}
		return listaPartes;
	}

	@Override
	public OrdenTrabajo getOrdenByFolio(int folio) {
		return ordenTrabajoDAO.get(folio);
	}

	@Override
	public boolean eliminaRefaccion(int idRefaccion) {
		boolean isEliminado = true;
		try {
			listaRefaccionesDAO.delete(idRefaccion);
		} catch (Exception e) {
			isEliminado = false;
			System.out.println(e);
		}
		return isEliminado;
	}
}
