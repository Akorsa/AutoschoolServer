package dao.Impl;

import java.util.Collection;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.QuestionDAO;
import util.HibernateUtil;
import beans.Questions;

public class QuestionDAOImpl  implements QuestionDAO {


	private static final transient Logger log = LoggerFactory.getLogger(QuestionDAOImpl.class);
	
	public void addQuestion(Questions question) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(question);
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
	public Collection<Questions> listQuestions()  {
		Session session = null;
	    List<Questions> questions = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	    	 session.beginTransaction();
	       questions = session.createCriteria(Questions.class).list();
	       for (Questions a : questions) {
	            Hibernate.initialize(a.getThemes());
	           
	        }
	       session.getTransaction().commit();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return questions;
	}
	
	public void removeQuestion(Integer questionsId)  {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Questions question = (Questions)session.get(Questions.class, questionsId);
	       session.delete(question);
	       session.getTransaction().commit();
	       if (question  != null) {
	    	   
	    	  System.out.println(question.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }
	
	public void updateQuestion(Questions s) {
		
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

	public Questions getQuestById(int id) {
		Session session = null;
		Questions q = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       q = (Questions) session.createQuery ("from Questions s where s.questionsId = :var1").setParameter("var1", id);
		       Hibernate.initialize(q.getThemes());
		       session.getTransaction().commit();
		     } catch (Exception e) {
		    	 log.info("Проблемы с сессией! " + e.getMessage());
		     } finally {
		       if (session != null && session.isOpen()) {
		    	   session.close();
		       }
		     }
		     return q;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Questions> getQuestByTheme(int id) {
		Session session = null;
	    List<Questions> questions = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	    	 session.beginTransaction();
	       questions = (List<Questions>) session.createQuery ("from Questions s where s.themes.themeId = :var1").setParameter("var1", id).list();
	       for (Questions a : questions) {
	            Hibernate.initialize(a.getThemes());
	           
	        }
	       session.getTransaction().commit();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return questions;
	}

	@SuppressWarnings("unchecked")
	public List<Questions> getQuestByLvl(double level) {
		Session session = null;
	    List<Questions> questions = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	    	 session.beginTransaction();
	       questions = (List<Questions>) session.createQuery ("from Questions s where s.difficult= :var1").setParameter("var1", level).list();
	       
	       session.getTransaction().commit();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return questions;
	}

}
