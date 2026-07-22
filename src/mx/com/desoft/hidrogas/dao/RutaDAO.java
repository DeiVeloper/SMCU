package mx.com.desoft.hidrogas.dao;

import java.util.List;

import mx.com.desoft.hidrogas.dto.RutaDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.Ruta;

public interface RutaDAO extends HibernateDAO<Ruta, Integer> {

	List<Ruta> getRutasByView(RutaDTO rutaDTO);

}
