package dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.Students;
import beans.Tests;

import util.HibernateUtil;

import dao.TestDAO;

public class TestDAOImpl implements TestDAO {
	
	private static final transient Logger log = LoggerFactory.getLogger(TestDAOImpl.class);
	
	public void addTest(Tests test)  {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(test);
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
	public Collection<Tests> listTests()  {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List tests = new ArrayList<Tests>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       tests = session.createCriteria(Tests.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return tests;
	}
	
	public void removeTest(Integer testId)  {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Tests test = (Tests)session.get(Tests.class, testId);
	       session.delete(test);
	       session.getTransaction().commit();
	       if (test   != null) {
	    	   
	    	  System.out.println(test.toString());
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
	public List<Tests> getStat(int idstudent)  {
		Session session = null;
	    List<Tests> tests = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	    	 session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
	    	 tests = session.createQuery ("from Tests s where s.students.idstudent = :var1 order by s.timeOfTest").setParameter("var1", idstudent).list();	
		   
		       session.getTransaction().commit();
	       //tests = session.createCriteria(Tests.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return tests;
	}
	

}
