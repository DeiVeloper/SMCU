package mx.com.desoft.hidrogas.dao;

import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatTipoRefaccion;

@Repository
public class CatTipoRefaccionImplDAO extends HibernateImplDAO<CatTipoRefaccion, Integer> implements ICatTipoRefaccionDAO {

}
