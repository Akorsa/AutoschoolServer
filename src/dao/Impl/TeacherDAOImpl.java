package dao.Impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import beans.Teachers;

import util.HibernateUtil;
import dao.TeacherDAO;

public class TeacherDAOImpl implements TeacherDAO {
	
	private static final transient Logger log = LoggerFactory.getLogger(TeacherDAOImpl.class);
	
	public void addTeacher(Teachers teacher) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(teacher);
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
	public Collection<Teachers> listTeachers() {
		Session session = null;
	    List<Teachers>  teachers = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	    	 session.beginTransaction();
	    	 teachers = session.createQuery ("from Teachers t where t.typeTeacher.typeTeachid = :var1").setParameter("var1", 1).list();	
	       for (Teachers a : teachers) {
	            Hibernate.initialize(a.getTypeEducation());
	           
	        }
	       session.getTransaction().commit();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return teachers;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Teachers> listMasters() {
		Session session = null;
	    List<Teachers>  teachers = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	    	 session.beginTransaction();
	    	 teachers = session.createQuery ("from Teachers t where t.typeTeacher.typeTeachid = :var1").setParameter("var1", 2).list();	
	       for (Teachers a : teachers) {
	            Hibernate.initialize(a.getTypeEducation());
	           
	        }
	       session.getTransaction().commit();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return teachers;
	}
	
	public void removeTeacher(Integer prepodid) {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Teachers teacher = (Teachers)session.get(Teachers.class, prepodid);
	       session.delete(teacher );
	       session.getTransaction().commit();
	       if (teacher   != null) {
	    	   
	    	  System.out.println(teacher.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }

	public void updateTeacher(Teachers s1) {
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

}
