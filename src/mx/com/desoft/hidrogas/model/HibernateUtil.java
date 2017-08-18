package mx.com.desoft.hidrogas.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

     private static final  SessionFactory sessionFactory;
     private static final  ServiceRegistry serviceRegistry;

     static {
          try {
               Configuration config = new Configuration();
               config.configure();
               serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
               config.setSessionFactoryObserver(new SessionFactoryObserver() {
                    private static final long  serialVersionUID = 1L;

                    @Override
                    public void sessionFactoryCreated(SessionFactory factory) {
                    }

                    @Override
                    public void sessionFactoryClosed(SessionFactory factory) {
                         ServiceRegistryBuilder.destroy(serviceRegistry);
                    }
               });
               sessionFactory = config.buildSessionFactory(serviceRegistry);
          } catch (Throwable ex) {
               System.err.println("Initial SessionFactory creation failed." + ex);
               throw new ExceptionInInitializerError(ex);
          }
     }

     public static  Session openSession() {
          return sessionFactory.openSession();
     }

     public static SessionFactory getSessionfactory() {
    	 return sessionFactory;
     }
}