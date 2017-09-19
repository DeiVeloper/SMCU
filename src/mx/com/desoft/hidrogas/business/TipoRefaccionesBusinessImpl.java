package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import mx.com.desoft.hidrogas.dao.CatTipoRefaccionesDAO;
import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.model.CatTipoListaRefaccion;
import mx.com.desoft.hidrogas.property.TipoRefaccionProperty;

@Service
public class TipoRefaccionesBusinessImpl implements ITipoRefaccionesBusiness {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CatTipoRefaccionesDAO catTipoRefaccionesImplDAO;

	@Override
	public void guardarTipoRefaccion(CatTipoRefaccionesDTO catTipoRefaccionesDTO) {
		catTipoRefaccionesImplDAO.saveOrUpdate(this.convertirDTOToEntidad(catTipoRefaccionesDTO));
	}

	@Override
	public List<TipoRefaccionProperty> getTipoRefaccionByView(CatTipoRefaccionesDTO catTipoRefaccionesDTO) {
		List<TipoRefaccionProperty> listaDTO = new ArrayList<>();
		for (CatTipoListaRefaccion lts : catTipoRefaccionesImplDAO.getTipoRefaccionByView(catTipoRefaccionesDTO)) {
			TipoRefaccionProperty dto = new TipoRefaccionProperty(new SimpleStringProperty(String.valueOf(lts.getTipoRefaccionId())),
					new SimpleStringProperty(lts.getDescripcion()));
			listaDTO.add(dto);
		}
		return listaDTO;
	}


	private CatTipoListaRefaccion convertirDTOToEntidad(CatTipoRefaccionesDTO catTipoRefaccionesDTO) {
		return new CatTipoListaRefaccion(1,catTipoRefaccionesDTO.getDescripcion());
	}

	@Override
	public void eliminarTipoRefaccionById(Integer id) {
		catTipoRefaccionesImplDAO.delete(id);
	}

}
