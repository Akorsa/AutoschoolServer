package dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.TypeTeacherDAO;

import beans.TypeTeacher;

import util.HibernateUtil;

public class TypeTeacherDAOImpl implements TypeTeacherDAO {
	
	private static final transient Logger log = LoggerFactory.getLogger(TypeTeacherDAOImpl.class);
	
	public void addTypeTeacher(TypeTeacher typeTeacher) throws SQLException {
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
	public Collection<TypeTeacher> listTypeTeachers() throws SQLException {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List typeTeachers = new ArrayList<TypeTeacher>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       typeTeachers = session.createCriteria(TypeTeacher.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return typeTeachers;
	}
	
	public void removeTypeTeacher(Integer typeTeachid) throws SQLException {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       TypeTeacher typeTeacher = (TypeTeacher)session.get(TypeTeacher.class, typeTeachid);
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

}
