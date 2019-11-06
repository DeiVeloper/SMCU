package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.Authenticator;
import mx.com.desoft.hidrogas.business.IAgregarEditarOrdenBusinessApp;
import mx.com.desoft.hidrogas.dao.AgregarEditarOrdenDAO;
import mx.com.desoft.hidrogas.dao.CatEstatusOrdenDAO;
import mx.com.desoft.hidrogas.dao.CatTipoNecesidadDAO;
import mx.com.desoft.hidrogas.dao.EconomicoDAO;
import mx.com.desoft.hidrogas.dao.EmpleadosDAO;
import mx.com.desoft.hidrogas.dao.IListaRefaccionesDAO;
import mx.com.desoft.hidrogas.dao.IOrdenTrabajoDAO;
import mx.com.desoft.hidrogas.dao.ISeguimientoEmpleadosDAO;
import mx.com.desoft.hidrogas.dao.ISeguimientoOrdenDAO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.model.CatEstatusOrden;
//import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.model.CatTipoNecesidad;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.Empleado;
import mx.com.desoft.hidrogas.model.ListaRefacciones;
//import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.model.SeguimientoOrden;
import mx.com.desoft.hidrogas.model.SeguimientosEmpleado;
import mx.com.desoft.hidrogas.util.Constantes;

@Service
public class AgregarEditarOrdenBusinessImpl implements IAgregarEditarOrdenBusinessApp {

	private static final Logger log = Logger.getLogger(AgregarEditarOrdenBusinessImpl.class);

	@Autowired
	private CatTipoNecesidadDAO catTipoNecesidadDAO;
	@Autowired
	private AgregarEditarOrdenDAO agregarEditarOrdenImplDAO;
	@Autowired
	private EmpleadosDAO empleadosImplDAO;
	@Autowired
	private EconomicoDAO economicoImplDAO;
	@Autowired
	private CatEstatusOrdenDAO catEstatusImplDAO;
	@Autowired
	private CatTipoNecesidadDAO catTipoNecesidadImplDAO;
	@Autowired
	private ISeguimientoOrdenDAO seguimientoDAO;
	@Autowired
	private IListaRefaccionesDAO listaRefaccionesDAO;
	@Autowired
	private ISeguimientoEmpleadosDAO seguimientoEmpleadosDAO;
	@Autowired
	private IOrdenTrabajoDAO ordenTrabajoDAO;

	@Override
	public void AgregarOrden() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CatTipoNecesidadDTO> buscarTiposNecesidad() {
		List<CatTipoNecesidadDTO> listaTiposNecesidad = new ArrayList<CatTipoNecesidadDTO>();
		List<CatTipoNecesidad> listaTipoNecesidadModel = catTipoNecesidadDAO.findAll();
		for (final CatTipoNecesidad tipoNecesidad : listaTipoNecesidadModel) {
			listaTiposNecesidad.add(new CatTipoNecesidadDTO(tipoNecesidad.getTipoNecesidadId(), tipoNecesidad.getDescripcion()));
		}
		return listaTiposNecesidad;
	}

	@Override
	public void guardarOrden(OrdenTrabajoDTO ordenTrabajoTO) {
		agregarEditarOrdenImplDAO.saveOrUpdate(this.convertirDTOToEntidad(ordenTrabajoTO));
	}

	private OrdenTrabajo convertirDTOToEntidad(OrdenTrabajoDTO ordenTrabajoTO) {
		final Empleado empleadoRegistro = empleadosImplDAO.get(Authenticator.usuarioSesion.getNominaEmpleado());
		final Economico economico = economicoImplDAO.get(ordenTrabajoTO.getEconomicoId());
		final CatEstatusOrden estatus = catEstatusImplDAO.get(Constantes.N1);
		final CatTipoNecesidad necesidad = catTipoNecesidadImplDAO.get(ordenTrabajoTO.getTipoNecesidadId());
		final OrdenTrabajo ordenTrabajo = new OrdenTrabajo(
				ordenTrabajoTO.getNominaOperador(), 
				ordenTrabajoTO.getNombreOperador(), 
				ordenTrabajoTO.getApellidoPatOperador(), 
				ordenTrabajoTO.getApellidoMatOperador(), 
				ordenTrabajoTO.getFallaMecanica(), 
				ordenTrabajoTO.getKilometraje(), 
				new Date(),
				new Date(),
				new Date(),
				empleadoRegistro, 
				economico, 
				estatus, 
				necesidad);
		return ordenTrabajo;
	}

	public Empleado getEmpleadoByNomina(int nomina) {
		Empleado empleado = empleadosImplDAO.getEmpleadoByNomina(nomina);
		return empleado;
	}

	public CatTipoNecesidadDAO getCatTipoNecesidadDAO() {
		return catTipoNecesidadDAO;
	}

	public void setCatTipoNecesidadDAO(CatTipoNecesidadDAO catTipoNecesidadDAO) {
		this.catTipoNecesidadDAO = catTipoNecesidadDAO;
	}

	public AgregarEditarOrdenDAO getAgregarEditarOrdenImplDAO() {
		return agregarEditarOrdenImplDAO;
	}

	public void setAgregarEditarOrdenImplDAO(AgregarEditarOrdenDAO agregarEditarOrdenImplDAO) {
		this.agregarEditarOrdenImplDAO = agregarEditarOrdenImplDAO;
	}

	@Override
	public boolean eliminaOrden(int folioOrden) {
		boolean isEliminado = true;
		try {
			List<ListaRefacciones> listaPartesModelUsadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folioOrden, Constantes.N1);
			List<ListaRefacciones> listaPartesModelSolicitadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folioOrden, Constantes.N2);
			for(ListaRefacciones refaccion : listaPartesModelUsadas) {
				listaRefaccionesDAO.delete(refaccion.getId_refaccion());
			}
			for(ListaRefacciones refaccion : listaPartesModelSolicitadas) {
				listaRefaccionesDAO.delete(refaccion.getId_refaccion());
			}
			SeguimientoOrden seguimiento = seguimientoDAO.getSeguimientoByFolio(folioOrden);
			if(seguimiento != null) {
				List<SeguimientosEmpleado> listaEmpleados = seguimientoEmpleadosDAO.getListaEmpleadosBySeguimiento(seguimiento.getId_seguimiento());
				for(SeguimientosEmpleado seguimientoEmpleado : listaEmpleados) {
					seguimientoEmpleadosDAO.delete(seguimientoEmpleado.getId());
				}
				seguimientoDAO.delete(seguimiento.getId_seguimiento());
			}
			agregarEditarOrdenImplDAO.delete(folioOrden);
		} catch (Exception e) {
			isEliminado = false;
			log.error("Error: No se pudo eliminar la orden.", e);
		}
		return isEliminado;
	}

	@Override
	public boolean cerrarOrden(int folioOrden) {
		boolean isCerrada = true;
		try {
			OrdenTrabajo orden = ordenTrabajoDAO.get(folioOrden);
			CatEstatusOrden estatusOrden = catEstatusImplDAO.get(2);
			orden.setCatEstatusOrden(estatusOrden);
			ordenTrabajoDAO.saveOrUpdate(orden);
		} catch (Exception e) {
			isCerrada = false;
			log.error("Error: No se pudo cerrar la orden.");
		}
		return isCerrada;
	}

}
