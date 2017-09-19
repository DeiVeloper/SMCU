package mx.com.desoft.hidrogas.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import mx.com.desoft.hidrogas.dao.EconomicoDAO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.property.EconomicoProperty;

@Service
public class EconomicoBusinessImpl implements IEconomicoBusiness {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EconomicoDAO economicoImplDAO;

	@Override
	public List<EconomicoProperty> getEconomicoByView(EconomicoDTO economicoDTO) {
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		List<EconomicoProperty> listaEconomicos = new ArrayList<>();
		for (Economico lista : economicoImplDAO.getEconomicosByView(economicoDTO)) {
			EconomicoProperty dto = new EconomicoProperty(
			new SimpleStringProperty(lista.getEconomicoId().toString()),
			new SimpleStringProperty(formato.format(lista.getFechaRegistro())));
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

	@Override
	public void eliminarEconomicoById(Integer id) {
		economicoImplDAO.delete(id);
	}

}
