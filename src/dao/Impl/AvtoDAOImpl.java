package dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.AvtoDAO;

import util.HibernateUtil;
import beans.Avto;

public class AvtoDAOImpl implements AvtoDAO{
	
	private static final transient Logger log = LoggerFactory.getLogger(AvtoDAOImpl.class);
	
	public void addAvto(Avto avto) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(avto);
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
	public Collection<Avto> listAvto() {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List avtos = new ArrayList<Avto>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       avtos = session.createCriteria(Avto.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return avtos;
	}
	
	public void removeAvto(Integer avtoid) {
	     Session session = null;
	     try {
	       session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Avto avto = (Avto)session.get(Avto.class, avtoid);
	       session.delete(avto );
	       session.getTransaction().commit();
	       if (avto  != null) {
	    	   
	    	  System.out.println(avto.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }

	public void updateAvto(Avto s1) {
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
	public List<Avto> getAvtoByTime(Date dt, Date dt2) {
		Session session = null;
		 List<Avto> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       Query query = session.createQuery ("from Avto s where " +
		       		"s.datanextto between :var2 and :var3 order by s.datanextto");
		       query.setParameter("var2", dt);
		       query.setParameter("var3", dt2);	
		       students = query.list();
		       
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
	
	@SuppressWarnings("unchecked")
	public List<Avto> getAvtoByTime2(Date dt, Date dt2) {
		Session session = null;
		 List<Avto> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       Query query = session.createQuery ("from Avto s where " +
		       		"s.datanextto not between :var2 and :var3 order by s.datanextto");
		       query.setParameter("var2", dt);
		       query.setParameter("var3", dt2);	
		       students = query.list();
		       
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
