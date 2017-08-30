package mx.com.desoft.hidrogas.dao;

import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatTipoNecesidad;

@Repository
public class CatTipoNecesidadImplDAO extends HibernateImplDAO<CatTipoNecesidad, Long> implements CatTipoNecesidadDAO {

}
