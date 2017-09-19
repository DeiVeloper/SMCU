package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.dao.AgregarEditarOrdenDAO;
import mx.com.desoft.hidrogas.dao.CatEstatusOrdenDAO;
import mx.com.desoft.hidrogas.dao.CatTipoNecesidadDAO;
import mx.com.desoft.hidrogas.dao.EconomicoDAO;
import mx.com.desoft.hidrogas.dao.EmpleadosDAO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.model.CatEstatusOrden;
//import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.model.CatTipoNecesidad;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.Empleado;
//import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;

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

}
