package dao.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.DrivingscheduleDAO;

import util.HibernateUtil;
import beans.Drivingschedule;
import beans.Students;

public class DrivingscheduleDAOImpl implements DrivingscheduleDAO{
	
	private static final transient Logger log = LoggerFactory.getLogger(DrivingscheduleDAOImpl.class);
	
	public void addDrivingschedule(Drivingschedule drivingschedule)  {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(drivingschedule);
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
	public Collection<Drivingschedule> listDrivingschedule()  {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List drivingschedules = new ArrayList<Drivingschedule>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       drivingschedules = session.createCriteria(Drivingschedule.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return drivingschedules;
	}
	
	public void removeDrivingschedule(Integer avtoid)  {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Drivingschedule drivingschedule = (Drivingschedule)session.get(Drivingschedule.class, avtoid);
	       session.delete(drivingschedule );
	       session.getTransaction().commit();
	       if (drivingschedule  != null) {
	    	   
	    	  System.out.println(drivingschedule.toString());
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
	public List<Drivingschedule> getDriveByGroup(int groupid, Date dt1, Date dt2) {
		Session session = null;
		 List<Drivingschedule> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       Query query  = session.createQuery ("from Drivingschedule s where s.students.groups.groupid = :var1 and " +
		       		"s.datebegin between :var2 and :var3 order by s.datebegin");
		       query.setParameter("var1", groupid);	
		       query.setParameter("var2", dt1);
		       query.setParameter("var3", dt2);
		       students = query.list();
		       for (Drivingschedule a : students) {
		            Hibernate.initialize(a.getTeachers());
		            Hibernate.initialize(a.getAvto());
		            Hibernate.initialize(a.getStudents());
		            //Hibernate.initialize(a.ge);
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
	public List<Drivingschedule> getDriveByTeacherAndTime(int teacherid,Date t1,Date t2) {
		Session session = null;
		 List<Drivingschedule> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       Query query = session.createQuery ("from Drivingschedule s where s.teachers.prepodid = :var1" +
		       		" and s.datebegin between :var2 and :var3 " +
		       		"or s.dateend between :var2 and :var3");
		       query.setParameter("var1", teacherid);	
		       query.setParameter("var2", t1);
		       query.setParameter("var3", t2);
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

	public void updateDrivingschedule(Drivingschedule sh) {
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
	
	@SuppressWarnings("rawtypes")
	public List getDriveStat(int id,Date t1,Date t2) {
		Session session = null;
		 List students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       SQLQuery query = session.createSQLQuery ("select s.surname,sum(d.numberhours) as summ from drivingschedule d," +
		       		"students s where idstudent = studentid and s.groupid= :var1 and d.done_flag = true and d.dateend between :var2 and :var3 group by surname order by summ ");
		       query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		       		//"or s.dateend between :var2 and :var3");
		       query.setParameter("var1", id);	
		       query.setParameter("var2", t1);
		       query.setParameter("var3", t2);
		       students = query.list();
		       /*for(Object object : students)
		         {
		            Map row = (Map)object;
		            System.out.print("First Name: " + row.get("surname")); 
		            System.out.println(", Salary: " + row.get("summ")); 
		         }*/
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
	
	@SuppressWarnings("rawtypes")
	public List<Integer> getHours(int id) {
		Session session = null;
		 List<Integer> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       SQLQuery query = session.createSQLQuery ("select sum(d.numberhours) as summ from drivingschedule d" +
		       		" where studentid =  :var1 ");
		       query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		       		//"or s.dateend between :var2 and :var3");
		       query.setParameter("var1", id);	

		       students = query.list();
		       /*for(Object object : students)
		         {
		            Map row = (Map)object;
		            System.out.print("First Name: " + row.get("surname")); 
		            System.out.println(", Salary: " + row.get("summ")); 
		         }*/
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
