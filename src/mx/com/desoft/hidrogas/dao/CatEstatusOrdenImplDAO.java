package mx.com.desoft.hidrogas.dao;

import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatEstatusOrden;

@Repository
public class CatEstatusOrdenImplDAO extends HibernateImplDAO<CatEstatusOrden, Integer> implements CatEstatusOrdenDAO {

}
