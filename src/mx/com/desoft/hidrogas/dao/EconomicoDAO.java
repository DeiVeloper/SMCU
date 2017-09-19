package mx.com.desoft.hidrogas.dao;

import java.util.List;

import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.Economico;

public interface EconomicoDAO extends HibernateDAO<Economico, Integer> {

	List<Economico> getEconomicosByView(EconomicoDTO economicoDTO);

}
