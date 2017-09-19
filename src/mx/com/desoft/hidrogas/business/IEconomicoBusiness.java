package mx.com.desoft.hidrogas.business;

import java.io.Serializable;
import java.util.List;

import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.property.EconomicoProperty;

public interface IEconomicoBusiness extends Serializable {

	List<EconomicoProperty> getEconomicoByView(EconomicoDTO economicoDTO);

	void guardarEconomico(EconomicoDTO economicoDTO);

	void eliminarEconomicoById(Integer id);
}
