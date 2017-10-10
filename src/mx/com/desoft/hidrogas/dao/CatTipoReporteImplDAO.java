package mx.com.desoft.hidrogas.dao;

import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatTipoReporte;

@Repository
public class CatTipoReporteImplDAO extends HibernateImplDAO<CatTipoReporte, Integer> implements CatTipoReporteDAO {

}
