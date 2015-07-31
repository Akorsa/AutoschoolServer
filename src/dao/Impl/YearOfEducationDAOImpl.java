package dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.Themes;
import beans.YearOfEducation;

import dao.YearOfEducationDAO;
import util.HibernateUtil;

public class YearOfEducationDAOImpl implements YearOfEducationDAO{
private static final transient Logger log = LoggerFactory.getLogger(YearOfEducationDAOImpl.class);
	
	public void addYearOfEducation(YearOfEducation yearOfEducation) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(yearOfEducation);
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
	public Collection<YearOfEducation> listYearOfEducations() {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List yearOfEducations = new ArrayList<YearOfEducation>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       yearOfEducations = session.createCriteria(YearOfEducation.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return yearOfEducations;
	}
	
	
	public void removeYearOfEducation(Integer userid) throws SQLException {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       YearOfEducation yearOfEducation = (YearOfEducation)session.get(YearOfEducation.class, userid);
	       session.delete(yearOfEducation);
	       session.getTransaction().commit();
	       if (yearOfEducation != null) {
	    	   
	    	  System.out.println(yearOfEducation.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }

	@SuppressWarnings("unchecked")
	public YearOfEducation getYearById(int yearOfEducationId) {
		Session session = null;
		 List<YearOfEducation> themes = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       themes = session.createQuery ("from YearOfEducation s where s.yearOfEducationId = :var1").setParameter("var1", yearOfEducationId).list();	
		       session.getTransaction().commit();
		     } catch (Exception e) {
		    	 log.info("Проблемы с сессией! " + e.getMessage());
		     } finally {
		       if (session != null && session.isOpen()) {
		    	   session.close();
		       }
		     }
		     return themes.get(0);
	}

}
