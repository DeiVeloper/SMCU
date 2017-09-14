package mx.com.desoft.hidrogas.bussines;

import java.io.Serializable;
import java.util.List;

import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.property.EconomicosProperty;

public interface IEconomicoBusiness extends Serializable {

	List<EconomicosProperty> getEconomicoByView(EconomicoDTO economicoDTO);
	
	void guardarEconomico(EconomicoDTO economicoDTO);
}
