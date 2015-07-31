package dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.TypeEducationDAO;

import beans.TypeEducation;

import util.HibernateUtil;


public class TypeEducationDAOImpl implements TypeEducationDAO {
	
	private static final transient Logger log = LoggerFactory.getLogger(TypeEducationDAOImpl.class);
	
	public void addTypeEducation(TypeEducation typeEducation)  {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(typeEducation);
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
	public Collection<TypeEducation> listTypeEducations()  {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List typeEducation = new ArrayList<TypeEducation>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       typeEducation = session.createCriteria(TypeEducation.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return typeEducation;
	}
	
	public void removeTypeEducation(Integer typeEducationId)  {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       TypeEducation typeEducation = (TypeEducation)session.get(TypeEducation.class, typeEducationId);
	       session.delete(typeEducation);
	       session.getTransaction().commit();
	       if (typeEducation   != null) {
	    	   
	    	  System.out.println(typeEducation.toString());
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
