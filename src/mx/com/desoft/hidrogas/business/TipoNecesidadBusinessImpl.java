package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import mx.com.desoft.hidrogas.dao.CatTipoNecesidadDAO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.model.CatTipoNecesidad;
import mx.com.desoft.hidrogas.property.TipoNecesidadProperty;

@Service
public class TipoNecesidadBusinessImpl implements ITipoNecesidadBusiness {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CatTipoNecesidadDAO catTipoNecesidadImplDAO;

	@Override
	public void guardarTipoNecesidad(CatTipoNecesidadDTO catTipoNecesidadDTO) {
		catTipoNecesidadImplDAO.saveOrUpdate(this.convertirDTOToEntidad(catTipoNecesidadDTO));

	}

	private CatTipoNecesidad convertirDTOToEntidad(CatTipoNecesidadDTO catTipoNecesidadDTO) {
		return new CatTipoNecesidad(catTipoNecesidadDTO.getDescripcion());
	}

	@Override
	public List<TipoNecesidadProperty> getTipoNecesidadByView(CatTipoNecesidadDTO catTipoNecesidadDTO) {
		List<TipoNecesidadProperty> listaDTO = new ArrayList<>();
		for (CatTipoNecesidad lts : catTipoNecesidadImplDAO.getTipoNecesidadByView(catTipoNecesidadDTO)) {
			TipoNecesidadProperty dto = new TipoNecesidadProperty(new SimpleStringProperty(String.valueOf(lts.getTipoNecesidadId())),
					new SimpleStringProperty(lts.getDescripcion()));
			listaDTO.add(dto);
		}
		return listaDTO;
	}

	@Override
	public void eliminarTipoNecesidadById(Integer id) {
		catTipoNecesidadImplDAO.delete(id.longValue());
	}
}
