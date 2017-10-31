package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import mx.com.desoft.hidrogas.Authenticator;
import mx.com.desoft.hidrogas.dao.CatTipoRefaccionesDAO;
import mx.com.desoft.hidrogas.dao.ICatTipoRefaccionDAO;
import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.model.CatTipoListaRefaccion;
import mx.com.desoft.hidrogas.model.CatTipoRefaccion;
import mx.com.desoft.hidrogas.property.TipoRefaccionProperty;

@Service
public class TipoRefaccionesBusinessImpl implements ITipoRefaccionesBusiness {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CatTipoRefaccionesDAO catTipoRefaccionesImplDAO;

	@Autowired
	private ICatTipoRefaccionDAO catTipoRefaccionImplDAO;

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
		return new CatTipoListaRefaccion(catTipoRefaccionesDTO.getDescripcion());
	}

	@Override
	public void eliminarTipoRefaccionById(Integer id) {
		catTipoRefaccionesImplDAO.delete(id);
	}

	@Override
	public void guardarRefaccion(CatTipoRefaccionesDTO catTipoRefaccionesDTO) {
		catTipoRefaccionImplDAO.saveOrUpdate(this.convertirRefaccionDTOToEntidad(catTipoRefaccionesDTO));

	}

	@Override
	public List<TipoRefaccionProperty> getRefaccionByView(CatTipoRefaccionesDTO catTipoRefaccionesDTO) {
		List<TipoRefaccionProperty> listaDTO = new ArrayList<>();
		for (CatTipoRefaccion lts : catTipoRefaccionImplDAO.getTipoRefaccionByView(catTipoRefaccionesDTO)) {
			TipoRefaccionProperty dto = new TipoRefaccionProperty(new SimpleStringProperty(String.valueOf(lts.getIdTipoRefaccion())),
					new SimpleStringProperty(lts.getDescripcion()));
			listaDTO.add(dto);
		}
		return listaDTO;
	}

	@Override
	public void eliminarRefaccionById(Integer id) throws ConstraintViolationException {
		catTipoRefaccionImplDAO.delete(id);

	}

	private CatTipoRefaccion convertirRefaccionDTOToEntidad(CatTipoRefaccionesDTO catTipoRefaccionesDTO) {
		return new CatTipoRefaccion(catTipoRefaccionesDTO.getDescripcion(), new Date(), Authenticator.usuarioSesion.getNominaEmpleado());
	}

}
