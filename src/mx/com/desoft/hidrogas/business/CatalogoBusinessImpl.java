package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.dao.CatTipoRefaccionesDAO;
import mx.com.desoft.hidrogas.dao.CatTipoReporteDAO;
import mx.com.desoft.hidrogas.dao.CatTipoEmpleadoDAO;
import mx.com.desoft.hidrogas.dao.CatTipoNecesidadDAO;
import mx.com.desoft.hidrogas.dao.EconomicoDAO;
import mx.com.desoft.hidrogas.dao.IOrdenTrabajoDAO;
import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.TipoReporteDTO;
import mx.com.desoft.hidrogas.model.CatTipoEmpleado;
import mx.com.desoft.hidrogas.model.CatTipoListaRefaccion;
import mx.com.desoft.hidrogas.model.CatTipoNecesidad;
import mx.com.desoft.hidrogas.model.CatTipoReporte;
import mx.com.desoft.hidrogas.model.Economico;

@Service
public class CatalogoBusinessImpl implements ICatalogoBusiness {

	@Autowired
	private CatTipoEmpleadoDAO catTipoEmpleadoImplDAO;

	@Autowired
	private EconomicoDAO economicoImplDAO;

	@Autowired
	private CatTipoNecesidadDAO catTipoNecesidadImplDAO;

	@Autowired
	private CatTipoRefaccionesDAO catTipoRefaccionesImplDAO;

	@Autowired
	private CatTipoReporteDAO catTipoReporteImplDAO;

	@Autowired
	private IOrdenTrabajoDAO ordenTrabajoImplDAO;

	@Override
	public List<CatTipoEmpleadoDTO> findAllTipoEmpleados() {
		List<CatTipoEmpleadoDTO> listCatTipoEmpleado = new ArrayList<>();
		for (CatTipoEmpleado lista : catTipoEmpleadoImplDAO.findAll()) {
			CatTipoEmpleadoDTO dto = new CatTipoEmpleadoDTO();
			dto.setTipoEmpleadoId(lista.getTipoEmpleadoId());
			dto.setDescripcion(lista.getDescripcion());
			dto.setFechaRegistro(lista.getFechaRegistro());
			dto.setNominaRegistro(lista.getNominaRegistro());
			listCatTipoEmpleado.add(dto);
		}
		return listCatTipoEmpleado;
	}

	@Override
	public List<EconomicoDTO> findAllEconomicos() {
		List<EconomicoDTO> listaEconomico = new ArrayList<>();
		for(Economico lista : economicoImplDAO.findAll()) {
			EconomicoDTO dto = new EconomicoDTO();
			dto.setEconomicoId(lista.getEconomicoId());
			listaEconomico.add(dto);
		}
		return listaEconomico;
	}

	@Override
	public List<CatTipoNecesidadDTO> findAllTipoNecesidad() {
		List<CatTipoNecesidadDTO> listaTipoNecesidad = new ArrayList<>();
		for(CatTipoNecesidad lista : catTipoNecesidadImplDAO.findAll()) {
			CatTipoNecesidadDTO dto = new CatTipoNecesidadDTO();
			dto.setTipoNecesidadId(lista.getTipoNecesidadId());
			dto.setDescripcion(lista.getDescripcion());
			listaTipoNecesidad.add(dto);
		}
		return listaTipoNecesidad;
	}

	@Override
	public List<CatTipoRefaccionesDTO> findAllTipoRefacciones() {
		List<CatTipoRefaccionesDTO> listaRefacciones = new ArrayList<>();
		for(CatTipoListaRefaccion lista : catTipoRefaccionesImplDAO.findAll()) {
			CatTipoRefaccionesDTO dto = new CatTipoRefaccionesDTO();
			dto.setTipoRefaccionId(lista.getTipoRefaccionId());
			dto.setDescripcion(lista.getDescripcion());
			listaRefacciones.add(dto);
		}
		return listaRefacciones;
	}

	@Override
	public CatTipoEmpleadoDTO getById(int tipoEmpleadoId) {
		CatTipoEmpleado tipoEmpleado = catTipoEmpleadoImplDAO.get(tipoEmpleadoId);
		return new CatTipoEmpleadoDTO(tipoEmpleado.getTipoEmpleadoId(), tipoEmpleado.getDescripcion());
	}

	@Override
	public List<TipoReporteDTO> findAllTipoReporte() {
		List<TipoReporteDTO> listaTipoReporte = new ArrayList<>();
		for (CatTipoReporte lista : catTipoReporteImplDAO.findAll()) {
			TipoReporteDTO dto = new TipoReporteDTO(lista.getTipoReporteId(), lista.getDescripcion());
			listaTipoReporte.add(dto);
		}
		return listaTipoReporte;
	}

	public void imprimirReporte(OrdenTrabajoDTO ordenTrabajoDTO){
		ordenTrabajoImplDAO.getOrdenByVista(ordenTrabajoDTO);
	}

}
