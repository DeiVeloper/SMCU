package mx.com.desoft.hidrogas.bussines;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import mx.com.desoft.hidrogas.dao.EconomicoDAO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.Empleado;
import mx.com.desoft.hidrogas.property.EconomicosProperty;

@Service
public class EconomicoBusinessImpl implements IEconomicoBusiness {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EconomicoDAO economicoImplDAO;

	@Override
	public List<EconomicosProperty> getEconomicoByView(EconomicoDTO economicoDTO) {
		List<EconomicosProperty> listaEconomicos = new ArrayList<>();
		for (Empleado lista : economicoImplDAO.getEconomicosByView(economicoDTO)) {
			EconomicosProperty dto = new EconomicosProperty();
			dto.setEconomico(new SimpleStringProperty(lista.getEconomico().getEconomicoId().toString()));
			dto.setChofer(new SimpleStringProperty(lista.getNombreEmpleado().concat(" ")
					.concat(lista.getApellidoPatEmpleado().concat(" ").concat(lista.getApellidoMatEmpleado()))));
			dto.setBotonBorrar(new Button("Borrar"));
			dto.setBotonEditar(new Button("Editar"));
			listaEconomicos.add(dto);
		}
		return listaEconomicos;
	}

	@Override
	public void guardarEconomico(EconomicoDTO economicoDTO) {
		economicoImplDAO.saveOrUpdate(this.concertirDTOToEntidad(economicoDTO));
		
	}
	
	private Economico concertirDTOToEntidad(EconomicoDTO economicoDTO) {
		return new Economico(economicoDTO.getEconomicoId(), economicoDTO.getFechaRegistro(), economicoDTO.getNominaRegistro());
	}

}
