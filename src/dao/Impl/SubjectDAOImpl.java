package dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.HibernateUtil;

import beans.Students;
import beans.Subjects;
import dao.SubjectDAO;

public class SubjectDAOImpl implements SubjectDAO {
	
	private static final transient Logger log = LoggerFactory.getLogger(SubjectDAOImpl.class);
	
	public void addSubject(Subjects subject) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(subject);
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
	public Collection<Subjects> listSubjects() {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List subjects = new ArrayList<Subjects>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       subjects = session.createCriteria(Subjects.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return subjects;
	}
	
	public void removeSubject(Integer subjectId) {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Subjects subject = (Subjects)session.get(Subjects.class, subjectId);
	       session.delete(subject);
	       session.getTransaction().commit();
	       if (subject  != null) {
	    	   
	    	  System.out.println(subject.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }

	public void updateSubj(Subjects s1) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(s1);
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
	public List<Subjects> getSubjectByName(String string) {
		Session session = null;
		List<Subjects> students = null;
			try {
			    session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				students = session.createQuery ("from Subjects s where s.nameOfSubject = :var1").setParameter("var1", string).list();	
				      
				session.getTransaction().commit();
				} catch (Exception e) {
				    log.info("Проблемы с сессией! " + e.getMessage());
				} finally {
				   if (session != null && session.isOpen()) {
				    session.close();
				   }
				}
			
			 return students;
	}
		
}
	


