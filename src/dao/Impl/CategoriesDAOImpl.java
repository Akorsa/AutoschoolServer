package dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.HibernateUtil;

import beans.Categories;
import dao.CategoriesDAO;

public class CategoriesDAOImpl implements CategoriesDAO{
	
	private static final transient Logger log = LoggerFactory.getLogger(CategoriesDAOImpl.class);
	
	public void addCategories(Categories categories) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(categories);
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
	public Collection<Categories> listCategories() {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List categoriess = new ArrayList<Categories>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       categoriess = session.createCriteria(Categories.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return categoriess;
	}
	
	public void removeCategories(Integer kategoriesid) {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Categories category = (Categories)session.get(Categories.class, kategoriesid);
	       session.delete(category);
	       session.getTransaction().commit();
	       if (category != null) {
	    	   
	    	  System.out.println(category.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }

	public void updateCategories(Categories s1) {
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
