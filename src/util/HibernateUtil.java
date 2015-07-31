package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	 private static final SessionFactory sessionFactory;

	    static {
	        try {
	        	Configuration configuration = new Configuration();
	        	configuration.configure();
	        	ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
	                     configuration.getProperties()).buildServiceRegistry();
	        	// Create the SessionFactory from hibernate.cfg.xml
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	        } catch (Throwable ex) {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	      }

	    /*@SuppressWarnings("unchecked")
	public static Session currentSession() throws HibernateException {
	        Session s = (Session) session.get();
	        if (s == null) {
	            s = sessionFactory.openSession();
	            session.set(s);
	        }
	        return s;
	 }

	 @SuppressWarnings("unchecked")
	public static void closeSession() throws HibernateException {
	        Session s = (Session) session.get();
	        session.set(null);
	        if (s != null) {
	            s.close();
	        }
	        System.out.println("Сессия успешно закрыта! ");
	 }*/
	
	
}
