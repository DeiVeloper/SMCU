package mx.com.desoft.hidrogas.dao;

import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.Economico;

@Repository
public class EconomicoImplDAO extends HibernateImplDAO<Economico, Integer> implements EconomicoDAO {

	

}
