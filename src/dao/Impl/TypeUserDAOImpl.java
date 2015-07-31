package dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.Students;
import beans.TypeUser;

import dao.TypeUserDAO;

import util.HibernateUtil;


public class TypeUserDAOImpl implements TypeUserDAO {
	
	private static final transient Logger log = LoggerFactory.getLogger(TypeUserDAOImpl.class);
	
	public void addTypeUser(TypeUser typeUser) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(typeUser);
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
	public Collection<TypeUser> listTypeUsers()  {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List typeUsers = new ArrayList<TypeUser>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       typeUsers = session.createCriteria(TypeUser.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return typeUsers;
	}
	
	public void removeTypeUser(Integer usertypeId)  {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       TypeUser typeUser = (TypeUser)session.get(TypeUser.class, usertypeId);
	       session.delete(typeUser);
	       session.getTransaction().commit();
	       if (typeUser   != null) {
	    	   
	    	  System.out.println(typeUser .toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }
	
	public void updateUser(TypeUser s) {
		
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

	@SuppressWarnings("unchecked")
	public TypeUser getRolesById(int i) {
		Session session = null;
		List <TypeUser> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       students = session.createQuery ("from TypeUser s where s.usertypeId = :var1").setParameter("var1", i).list();	
		       
		       session.getTransaction().commit();
		     } catch (Exception e) {
		    	 log.info("Проблемы с сессией! " + e.getMessage());
		     } finally {
		       if (session != null && session.isOpen()) {
		    	   session.close();
		       }
		     }
	    
		     return students.get(0);
	}

}
