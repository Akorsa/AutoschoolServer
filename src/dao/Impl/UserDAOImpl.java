package dao.Impl;

import java.util.Collection;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.Students;
import beans.Users;
import util.HibernateUtil;
import dao.UserDAO;

public class UserDAOImpl implements UserDAO {
	
	private static final transient Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	public void addUser(Users user) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
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
	public Collection<Users > listUsers() {
		Session session = null;
	    List<Users > users = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	    	 session.beginTransaction();
	       users = session.createCriteria(Users.class).list();
	       for (Users a : users) {
	            Hibernate.initialize(a.getTypeUser());
	        }
	       session.getTransaction().commit();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return users;
	}
	
	public void removeUser(Integer userid) {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Users user = (Users)session.get(Users.class, userid);
	       session.delete(user);
	       session.getTransaction().commit();
	       if (user != null) {
	    	   
	    	  System.out.println(user.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }
	
public void updateUser(Users s) {
		
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
public List<Users> getUserByLogin(String text) {
	Session session = null;
	 List<Users> students = null;
   try {
   	session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
	       //students = session.createQuery ("select s from Students s").list();
	       students = session.createQuery ("from Users s where s.login = :var1").setParameter("var1", text).list();	
	       for (Users a : students) {
	            Hibernate.initialize(a.getTypeUser());
	          
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
