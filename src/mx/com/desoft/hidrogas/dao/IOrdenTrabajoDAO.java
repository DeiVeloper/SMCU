package mx.com.desoft.hidrogas.dao;

import java.util.List;

import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;

public interface IOrdenTrabajoDAO extends HibernateDAO<OrdenTrabajo, Integer> {

	List<OrdenTrabajo> getOrdenByVista(OrdenTrabajoDTO ordenTrabajoDTO);

}
