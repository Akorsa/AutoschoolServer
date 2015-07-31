package dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ScheduleDAO;

import util.HibernateUtil;
import beans.Schedules;

public class ScheduleDAOImpl implements ScheduleDAO{
	
	private static final transient Logger log = LoggerFactory.getLogger(ScheduleDAOImpl.class);
	
	public void addSchedule(Schedules schedule) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(schedule);
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
	@Override
	public List<Schedules> getScheduleFromGroup(Integer id, Date dt1, Date dt2) {
		Session session = null;
		 List<Schedules> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       Query query = session.createQuery ("from Schedules s where s.groups.groupid = :var1 and " +
		       		"s.beginTime between :var2 and :var3 order by s.beginTime");
		       query.setParameter("var1", id);	
		       query.setParameter("var2", dt1);
		       query.setParameter("var3", dt2);	
		       students = query.list();
		       for (Schedules a : students) {
		            Hibernate.initialize(a.getGroups());
		            Hibernate.initialize(a.getTeachers());
		            Hibernate.initialize(a.getThemes().getSubjects());
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
	
	@SuppressWarnings("unchecked")
	public Collection<Schedules> listSchedule() {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List schedules = new ArrayList<Schedules>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       schedules = session.createCriteria(Schedules.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return schedules;
	}
	
	public void removeSchedule(Integer scheduleId) {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Schedules schedule = (Schedules)session.get(Schedules.class, scheduleId);
	       session.delete(schedule);
	       session.getTransaction().commit();
	       if (schedule  != null) {
	    	   
	    	  System.out.println(schedule.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }

	public void updateSchedule(Schedules sh) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(sh);
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
