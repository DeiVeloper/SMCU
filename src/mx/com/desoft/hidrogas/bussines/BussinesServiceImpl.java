package mx.com.desoft.hidrogas.bussines;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.dao.CatTipoEmpleadoDAO;
import mx.com.desoft.hidrogas.dao.EconomicoDAO;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.model.CatTipoEmpleado;
import mx.com.desoft.hidrogas.model.Economico;

@Service
public class BussinesServiceImpl implements BussinesService {
	
	@Autowired
	private CatTipoEmpleadoDAO catTipoEmpleadoImplDAO;
	
	@Autowired
	private EconomicoDAO economicoImplDAO;

	public List<CatTipoEmpleadoDTO> findAllTipoEmpleados() {
		List<CatTipoEmpleadoDTO> listCatTipoEmpleado = new ArrayList<>();
		List<CatTipoEmpleado> lista = catTipoEmpleadoImplDAO.findAll();
		for (CatTipoEmpleado catTipoEmpleado : lista) {
			CatTipoEmpleadoDTO dto = new CatTipoEmpleadoDTO();
			dto.setTipoEmpleadoId(catTipoEmpleado.getTipoEmpleadoId());
			dto.setDescripcion(catTipoEmpleado.getDescripcion());
			dto.setFechaRegistro(catTipoEmpleado.getFechaRegistro());
			dto.setNominaRegistro(catTipoEmpleado.getNominaRegistro());
			listCatTipoEmpleado.add(dto);
		}
		return listCatTipoEmpleado;
	}
	
	public List<EconomicoDTO> findAllEconomicos() {
		List<EconomicoDTO> listaEconomico = new ArrayList<>();
		for(Economico economicoDTO : economicoImplDAO.findAll()) {
			EconomicoDTO dto = new EconomicoDTO();
			dto.setEconomicoId(economicoDTO.getEconomicoId());
			listaEconomico.add(dto);
		}
		return listaEconomico;
	}

	
}
