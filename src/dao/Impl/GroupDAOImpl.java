package dao.Impl;


import java.util.Collection;
import java.util.List;


import org.hibernate.Hibernate;
import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.GroupDAO;

import util.HibernateUtil;
import beans.Groups;


public class GroupDAOImpl implements GroupDAO{

	private static final transient Logger log = LoggerFactory.getLogger(GroupDAOImpl.class);
	
	public void addGroup(Groups group)  {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(group);
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
	public Collection<Groups> listGroup()  {
		Session session = null;
	    List<Groups> groups = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	    	 session.beginTransaction();
	    	 groups = session.createQuery ("from Groups g order by g.gruopstartdate").list();
	       for (Groups a : groups) {
	            Hibernate.initialize(a.getCategories());
	            Hibernate.initialize(a.getEducationtypes());
	            Hibernate.initialize(a.getYearOfEducation().getNumberOfYear());
	        }
	       session.getTransaction().commit();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return groups;
	}
	
	public void removeGroup(Integer groupid)  {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Groups group = (Groups)session.get(Groups.class, groupid);
	       session.delete(group );
	       session.getTransaction().commit();
	       if (group  != null) {
	    	   
	    	  System.out.println(group.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Groups> getGroupOnYear(String year)  {
		Session session = null;
	    List<Groups>  groups = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		      //groups = session.createSQLQuery ("select g.group_name from groups g where g.year_of_education_id = 2").list();
		       groups = session.createQuery ("from Groups g where g.yearOfEducation.numberOfYear = :year1 order by g.gruopstartdate").setParameter("year1", year).list();	  
		       session.getTransaction().commit();
		     } catch (Exception e) {
		    	 log.info("Проблемы с сессией! " + e.getMessage());
		     } finally {
		       if (session != null && session.isOpen()) {
		    	   session.close();
		       }
		     }
		     return groups;
		
	}

	public void updateGrup(Groups s1) {
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
	public List<Groups> getCurrentGroupList() {
		Session session = null;
	    List<Groups>  groups = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		      //groups = session.createSQLQuery ("select g.group_name from groups g where g.year_of_education_id = 2").list();
		       groups = session.createQuery ("from Groups g where g.flagZakr = :year1").setParameter("year1", "Нет").list();	  
		       session.getTransaction().commit();
		     } catch (Exception e) {
		    	 log.info("Проблемы с сессией! " + e.getMessage());
		     } finally {
		       if (session != null && session.isOpen()) {
		    	   session.close();
		       }
		     }
		     return groups;
	}

}
