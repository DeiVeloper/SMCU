package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.business.IAgregarEditarOrdenBusinessApp;
import mx.com.desoft.hidrogas.dao.AgregarEditarOrdenDAO;
import mx.com.desoft.hidrogas.dao.CatEstatusOrdenDAO;
import mx.com.desoft.hidrogas.dao.CatTipoNecesidadDAO;
import mx.com.desoft.hidrogas.dao.EconomicoDAO;
import mx.com.desoft.hidrogas.dao.EmpleadosDAO;
import mx.com.desoft.hidrogas.dao.IListaRefaccionesDAO;
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

@Service
public class AgregarEditarOrdenBusinessImpl implements IAgregarEditarOrdenBusinessApp {

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
		final Empleado empleado = empleadosImplDAO.get(ordenTrabajoTO.getNominaOperador());
		final Economico economico = economicoImplDAO.get(ordenTrabajoTO.getEconomicoId());
		final CatEstatusOrden estatus = catEstatusImplDAO.get(1);
		final CatTipoNecesidad necesidad = catTipoNecesidadImplDAO.get(ordenTrabajoTO.getTipoNecesidadId());
		final OrdenTrabajo ordenTrabajo = new OrdenTrabajo(ordenTrabajoTO.getApellidoMatOperador(), ordenTrabajoTO.getApellidoPatOperador(), ordenTrabajoTO.getFallaMecanica(), new Date(), ordenTrabajoTO.getKilometraje(), ordenTrabajoTO.getNombreOperador(), economico, estatus, empleado, empleado, necesidad);
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
			List<ListaRefacciones> listaPartesModelUsadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folioOrden, 1);
			List<ListaRefacciones> listaPartesModelSolicitadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folioOrden, 2);
			for(ListaRefacciones refaccion : listaPartesModelUsadas) {
				listaRefaccionesDAO.delete(refaccion.getId_refaccion());
			}
			for(ListaRefacciones refaccion : listaPartesModelSolicitadas) {
				listaRefaccionesDAO.delete(refaccion.getId_refaccion());
			}
			SeguimientoOrden seguimiento = seguimientoDAO.getSeguimientoByFolio(folioOrden);
			List<SeguimientosEmpleado> listaEmpleados = seguimientoEmpleadosDAO.getListaEmpleadosBySeguimiento(seguimiento.getId_seguimiento());
			for(SeguimientosEmpleado seguimientoEmpleado : listaEmpleados) {
				seguimientoEmpleadosDAO.delete(seguimientoEmpleado.getId());
			}
			seguimientoDAO.delete(seguimiento.getId_seguimiento());
			agregarEditarOrdenImplDAO.delete(folioOrden);
		} catch (Exception e) {
			isEliminado = false;
			System.out.println(e);
		}
		return isEliminado;
	}

}
