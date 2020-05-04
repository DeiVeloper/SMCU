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
import mx.com.desoft.hidrogas.dao.EmpleadosDAO;
import mx.com.desoft.hidrogas.dao.ICatTipoRefaccionDAO;
import mx.com.desoft.hidrogas.dao.IOrdenTrabajoDAO;
import mx.com.desoft.hidrogas.dao.ISeguimientoOrdenDAO;
import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenDTO;
import mx.com.desoft.hidrogas.dto.SeguimientoOrdenPartesDTO;
import mx.com.desoft.hidrogas.dto.TipoReporteDTO;
import mx.com.desoft.hidrogas.model.CatTipoEmpleado;
import mx.com.desoft.hidrogas.model.CatTipoListaRefaccion;
import mx.com.desoft.hidrogas.model.CatTipoNecesidad;
import mx.com.desoft.hidrogas.model.CatTipoRefaccion;
import mx.com.desoft.hidrogas.model.CatTipoReporte;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.Empleado;
import mx.com.desoft.hidrogas.model.ListaRefacciones;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.util.Constantes;

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

	@Autowired
	private ICatTipoRefaccionDAO catTipoRefaccion;

	@Autowired
	private EmpleadosDAO empleadoImplDAO;

	@Autowired
	private ISeguimientoOrdenDAO seguimientoDAO;

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

	@Override
	public List<CatTipoRefaccionesDTO> findAllCatTipoRefacciones() {
		List<CatTipoRefaccionesDTO> listaRefacciones = new ArrayList<>();
		for(CatTipoRefaccion refaccion : catTipoRefaccion.getAllTipoRefaccion()) {
			listaRefacciones.add(new CatTipoRefaccionesDTO(refaccion.getIdTipoRefaccion(), refaccion.getDescripcion(), 
					refaccion.getCantidad()));
		}
		return listaRefacciones;
	}

	@Override
	public List<EmpleadoDTO> findAllOperadores() {
		List<EmpleadoDTO> listaOperadores = new ArrayList<>();
		for (Empleado lista : empleadoImplDAO.getAllOperadores()) {
			EmpleadoDTO dto = new EmpleadoDTO(lista.getNominaEmpleado(),
					lista.getNombreEmpleado(),
					lista.getApellidoPatEmpleado(),
					lista.getApellidoMatEmpleado(),
					null,
					lista.getEconomico().getEconomicoId(),
					lista.getFechaRegistro(),
					lista.getNominaRegistro(),
					lista.getCatTipoEmpleado().getTipoEmpleadoId(),
					lista.getCatTipoEmpleado().getDescripcion());
			listaOperadores.add(dto);
		}
		return listaOperadores;
	}

	@Override
	public OrdenTrabajoDTO getOrdenById(Integer folio) {
		OrdenTrabajo orden = ordenTrabajoImplDAO.get(folio);
		List<ListaRefacciones> listaRefaccionesUtilizadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folio, Constantes.N1);
		List<ListaRefacciones> listaRefaccionesSolicitadas = seguimientoDAO.getListaRefaccionesByFolioTipo(folio, Constantes.N2);
		List<SeguimientoOrdenPartesDTO> listaRefaccionesDTOUtilizadas = new ArrayList<SeguimientoOrdenPartesDTO>();
		for(ListaRefacciones refaccion : listaRefaccionesUtilizadas) {
			listaRefaccionesDTOUtilizadas.add(new SeguimientoOrdenPartesDTO(refaccion.getCantidad(), refaccion.getCatTipoRefaccion().getDescripcion()));
		}
		List<SeguimientoOrdenPartesDTO> listaRefaccionesDTOSolicitadas = new ArrayList<SeguimientoOrdenPartesDTO>();
		for(ListaRefacciones refaccion : listaRefaccionesSolicitadas) {
			listaRefaccionesDTOSolicitadas.add(new SeguimientoOrdenPartesDTO(refaccion.getCantidad(), refaccion.getCatTipoRefaccion().getDescripcion()));
		}
		if(orden == null){
			return null;
		}
		return new OrdenTrabajoDTO(orden.getFolio(),
				orden.getEconomico().getEconomicoId(),
				orden.getNominaOperador(),
				orden.getNombreOperador(),
				orden.getApellidoPatOperador(),
				orden.getApellidoMatOperador(),
				orden.getCatTipoNecesidad().getTipoNecesidadId(),
				orden.getCatTipoNecesidad().getDescripcion(),
				orden.getKilometraje(),
				orden.getFallaMecanica(),
				orden.getCatEstatusOrden().getEstatusOrdenId(),
				orden.getFechaRegistro(),
				orden.getSeguimientoOrden() != null ? 
						new SeguimientoOrdenDTO(orden.getSeguimientoOrden().getTrabajosRealizados(), 
								listaRefaccionesDTOUtilizadas, 
								listaRefaccionesDTOSolicitadas)
		: new SeguimientoOrdenDTO("", listaRefaccionesDTOUtilizadas, listaRefaccionesDTOSolicitadas),
		orden.getFechaOrden(),orden.getFechaTerminacion());
	}

	@Override
	public List<OrdenTrabajoDTO> getIncidenciasOrdenes(OrdenTrabajoDTO ordenTrabajoDTO) {
		List<OrdenTrabajoDTO> listaDTO = new ArrayList<>();
		List<OrdenTrabajo> listaOrden = ordenTrabajoImplDAO.getIncidenciasOrden(ordenTrabajoDTO);
		for (OrdenTrabajo lista : listaOrden) {
			OrdenTrabajoDTO dto = null;
			if(lista.getSeguimientoOrden() == null) {
				dto = new OrdenTrabajoDTO(lista.getFolio(),
						lista.getEconomico().getEconomicoId(),
						lista.getNominaOperador(),
						lista.getNombreOperador(),
						lista.getApellidoPatOperador(),
						lista.getApellidoMatOperador(),
						lista.getCatTipoNecesidad().getDescripcion(),
						lista.getKilometraje(),
						lista.getFallaMecanica(),
						lista.getCatEstatusOrden().getEstatusOrdenId(),
						lista.getFechaRegistro(),
						new EmpleadoDTO(lista.getEmpleado2().getNominaEmpleado(),lista.getEmpleado2().getNombreEmpleado().concat(" ").concat(lista.getEmpleado2().getApellidoPatEmpleado()).concat(" ").concat(lista.getEmpleado2().getApellidoMatEmpleado()),0,
								lista.getEmpleado2().getCatTipoEmpleado().getTipoEmpleadoId()), null);
			} else {
				dto = new OrdenTrabajoDTO(lista.getFolio(),
						lista.getEconomico().getEconomicoId(),
						lista.getNominaOperador(),
						lista.getNombreOperador(),
						lista.getApellidoPatOperador(),
						lista.getApellidoMatOperador(),
						lista.getCatTipoNecesidad().getDescripcion(),
						lista.getKilometraje(),
						lista.getFallaMecanica(),
						lista.getCatEstatusOrden().getEstatusOrdenId(),
						lista.getFechaRegistro(),
						new EmpleadoDTO(lista.getEmpleado2().getNominaEmpleado(),lista.getEmpleado2().getNombreEmpleado().concat(" ").concat(lista.getEmpleado2().getApellidoPatEmpleado()).concat(" ").concat(lista.getEmpleado2().getApellidoMatEmpleado()),0,
								lista.getEmpleado2().getCatTipoEmpleado().getTipoEmpleadoId()),
						new SeguimientoOrdenDTO(lista.getSeguimientoOrden().getId_seguimiento(), lista.getSeguimientoOrden().getOrdenTrabajo().getFolio(), lista.getSeguimientoOrden().getTrabajosRealizados(), lista.getSeguimientoOrden().getObservaciones(), lista.getSeguimientoOrden().getReparacionMayor(), lista.getSeguimientoOrden().getFechaReparaMayor(), lista.getSeguimientoOrden().getFechaRegistro(), lista.getSeguimientoOrden().getNominaRegistro()));
			}
			listaDTO.add(dto);
		}
		return  listaDTO;
	}

	@Override
	public List<OrdenTrabajoDTO> getOrdenByTipoNecesidad(OrdenTrabajoDTO ordenTrabajoDTO) {
		List<OrdenTrabajoDTO> listaDTO = new ArrayList<>();
		for(Object[] lista : ordenTrabajoImplDAO.getOrdenByTipoNecesidad(ordenTrabajoDTO)){
			OrdenTrabajoDTO dto = new OrdenTrabajoDTO();
			dto.setEconomicoId(Integer.parseInt(lista[0].toString()));
			dto.setDescripcionTipoNecesidad(lista[1].toString());
			dto.setTotal(Integer.parseInt(lista[2].toString()));
			listaDTO.add(dto);
		}
		return listaDTO;
	}
	
	@Override
	public CatTipoRefaccion obtieneTipoRefaccion(int idTipoRefaccion) {
		return catTipoRefaccion.get(idTipoRefaccion);
	}
}
