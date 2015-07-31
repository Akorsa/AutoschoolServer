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

import util.HibernateUtil;
import beans.Groups;
import beans.Students;
import dao.StudentDAO;

public class StudentDAOImpl implements StudentDAO{
	
	private static final transient Logger log = LoggerFactory.getLogger(StudentDAOImpl.class);
	
	public void addStudent(Students student) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(student);
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
	public Collection<Students> listStudents() {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List students = new ArrayList<Students>();
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       students = session.createCriteria(Students.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return students;
	}
	
	public void removeStudent(Integer idstudent) {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Students student = (Students)session.get(Students.class, idstudent);
	       session.delete(student);
	       session.getTransaction().commit();
	       if (student  != null) {
	    	   
	    	  System.out.println(student.toString());
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
	@Override
	public List<Students> getStudentsFromGroup(Integer id) {
		Session session = null;
		 List<Students> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       students = session.createQuery ("from Students s where s.groups.groupid = :var1 order by s.surname").setParameter("var1", id).list();	
		       for (Students a : students) {
		            Hibernate.initialize(a.getGroups());
		            Hibernate.initialize(a.getTypeEducation());
		            Hibernate.initialize(a.getGroups().getYearOfEducation().getNumberOfYear());
		            Hibernate.initialize(a.getUsers());
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
	@Override
	public List<Students> getStudentsFromGroup2(Integer id) {
		Session session = null;
		 List<Students> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       students = session.createQuery ("from Students s where s.groups.groupid = :var1").setParameter("var1", id).list();	
		       for (Students a : students) {
		            Hibernate.initialize(a.getGroups());
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

	public void updateStudent(Students s) {
		
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

	public void deleteStudent(Students s) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(s);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.info("Проблемы с сессией! " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
		       }
		}
		
	}

	public void updateStGr(Groups gr, int id) {
		Session session = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       Query query = session.createQuery ("update Students set groups = :var1" +
	    				" where idstudent = :var2");
		       query.setParameter("var1", gr);	
		       query.setParameter("var2", id);	
		       query.executeUpdate();
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
	public List<Students> getStudentsByFIO(String[] s) {
		Session session = null;
		 List<Students> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       if (s[0].equals("")){
		    	   students = session.createQuery ("from Students s where s.surname like :var1").setParameter("var1", "%" + s[1]+ "%").list();	
		    	   for (Students a : students) {
		    		   Hibernate.initialize(a.getGroups());
		    		   Hibernate.initialize(a.getTypeEducation());
		    		   Hibernate.initialize(a.getGroups().getYearOfEducation().getNumberOfYear());
		    	   }
		    	   session.getTransaction().commit();
		       }
		       if (s[1].equals("")){
		    	   students = session.createQuery ("from Students s where s.name like :var1").setParameter("var1", "%" + s[0]+ "%").list();	
		    	   for (Students a : students) {
		    		   Hibernate.initialize(a.getGroups());
		    		   Hibernate.initialize(a.getTypeEducation());
		    		   Hibernate.initialize(a.getGroups().getYearOfEducation().getNumberOfYear());
		    	   }
		    	   session.getTransaction().commit();
		       }
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
	public List<Students> getStudentsByNomSvid(String s) {
		Session session = null;
		 List<Students> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       students = session.createQuery ("from Students s where s.docnumber = :var1").setParameter("var1", s).list();	
		       for (Students a : students) {
		            Hibernate.initialize(a.getGroups());
		            Hibernate.initialize(a.getTypeEducation());
		            Hibernate.initialize(a.getGroups().getYearOfEducation().getNumberOfYear());
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
	public List<Students> getStudentsByDate(Date d) {
		 Session session = null;
		 List<Students> students = null;
		    try {
		    	session = HibernateUtil.getSessionFactory().openSession();
			       session.beginTransaction();
			       students = session.createQuery ("from Students s where s.datebirth = :var1").setParameter("var1", d).list();	
			       for (Students a : students) {
			            Hibernate.initialize(a.getGroups());
			            Hibernate.initialize(a.getTypeEducation());
			            Hibernate.initialize(a.getGroups().getYearOfEducation().getNumberOfYear());
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
	public Students getStudentByUser(int id) {
	Session session = null;
	List<Students> students = null;
		try {
		    session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			students = session.createQuery ("from Students s where s.users.userid = :var1").setParameter("var1", id).list();	
			      
			session.getTransaction().commit();
			} catch (Exception e) {
			    log.info("Проблемы с сессией! " + e.getMessage());
			} finally {
			   if (session != null && session.isOpen()) {
			    session.close();
			   }
			}
		if(students.isEmpty()){
			return null;
		}else{
		 return students.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Students> getSvid() {
		Session session = null;
		 List<Students> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       students = session.createQuery ("from Students s where s.svidInfo is not null order by s.surname").list();	
		       for (Students a : students) {
		            Hibernate.initialize(a.getGroups());
		       
		            Hibernate.initialize(a.getGroups().getYearOfEducation().getNumberOfYear());
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
