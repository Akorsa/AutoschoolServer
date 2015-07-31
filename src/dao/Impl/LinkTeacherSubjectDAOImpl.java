package dao.Impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.LinkTeacherSubjectDAO;

import beans.LinkTeacherSubject;
import beans.Teachers;
import beans.TypeTeacher;

import util.HibernateUtil;

public class LinkTeacherSubjectDAOImpl implements LinkTeacherSubjectDAO {
	
	private static final transient Logger log = LoggerFactory.getLogger(LinkTeacherSubjectDAOImpl.class);
	
	public void addLinkTeacherSubject(LinkTeacherSubject typeTeacher) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(typeTeacher);
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
	public Collection<LinkTeacherSubject> listLinkTeacherSubjects() {
		Session session = null;
	    List<LinkTeacherSubject> typeTeachers = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       typeTeachers = session.createCriteria(LinkTeacherSubject.class).list();
	       for (LinkTeacherSubject a : typeTeachers) {
	            Hibernate.initialize(a.getSubjects());
	            Hibernate.initialize(a.getTeachers());
	        }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return typeTeachers;
	}
	
	public void updateLinkTeacherSubject(LinkTeacherSubject s) {
		
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(s);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.info("Проблемы с сессией! " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
		       }
		}
	}
	
	public void removeLinkTeacherSubject(Integer typeTeachid) {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       LinkTeacherSubject typeTeacher = (LinkTeacherSubject)session.get(TypeTeacher.class, typeTeachid);
	       session.delete(typeTeacher);
	       session.getTransaction().commit();
	       if (typeTeacher   != null) {
	    	   
	    	  System.out.println(typeTeacher.toString());
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
	public List<LinkTeacherSubject> getZakrByTeacher(Teachers string) {
		Session session = null;
		List<LinkTeacherSubject> students = null;
			try {
			    session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				students = session.createQuery ("from LinkTeacherSubject s where s.teachers = :var1").setParameter("var1", string).list();	
				for (LinkTeacherSubject a : students) {
		            Hibernate.initialize(a.getSubjects());
		            //Hibernate.initialize(a.getTeachers());
		        }      
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
