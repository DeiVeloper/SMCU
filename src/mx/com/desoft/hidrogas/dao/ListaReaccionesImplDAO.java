package mx.com.desoft.hidrogas.dao;

import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.ListaRefacciones;

@Repository
public class ListaReaccionesImplDAO extends HibernateImplDAO<ListaRefacciones, Integer> implements IListaRefaccionesDAO {

}
