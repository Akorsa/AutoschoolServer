package dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.EducationtypesDAO;

import util.HibernateUtil;
import beans.Educationtypes;

public class EducationtypesDAOImpl implements EducationtypesDAO{
	
	private static final transient Logger log = LoggerFactory.getLogger(EducationtypesDAOImpl.class);
	
	public void addEducationtype(Educationtypes educationtype) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(educationtype);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.info("Проблемы с сессией! " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
		       }
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Educationtypes> listEducationtype() {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List educationtypes = new ArrayList<Educationtypes>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       educationtypes = session.createCriteria(Educationtypes.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return educationtypes;
	}
	
	public void removeEducationtypes(Integer educationtypeid) {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Educationtypes educationtype = (Educationtypes)session.get(Educationtypes.class, educationtypeid);
	       session.delete(educationtype );
	       session.getTransaction().commit();
	       if (educationtype  != null) {
	    	   
	    	  System.out.println(educationtype.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }

}
