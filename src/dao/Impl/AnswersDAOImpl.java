package dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.AnswersDAO;
import util.HibernateUtil;
import beans.Answers;



public class AnswersDAOImpl implements AnswersDAO{
private static final transient Logger log = LoggerFactory.getLogger(AnswersDAOImpl.class);
	
	public void addAnswers(Answers answer)  {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(answer);
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
	public Collection<Answers> listAnswers()  {
		Session session = null;
	    @SuppressWarnings("rawtypes")
		List answers = new ArrayList<Answers>();
	     try {
	       session = HibernateUtil.getSessionFactory().openSession();
	       answers = session.createCriteria(Answers.class).list();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return answers;
	}
	
	public void removeAnswers(Integer answersId)  {
	     Session session = null;
	     try {
	       session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Answers answer = (Answers)session.get(Answers.class, answersId);
	       session.delete(answer);
	       session.getTransaction().commit();
	       if (answer != null) {
	    	   
	    	  System.out.println(answer.toString());
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
	public List<Answers> getAnswersByQId(Integer id) {
		Session session = null;
		 List<Answers> students = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       students = session.createQuery ("from Answers s where s.questions.questionsId = :var1").setParameter("var1", id).list();	
		       for (Answers a : students) {
		            Hibernate.initialize(a.getQuestions());
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
	
	public void updateAnswers(Answers s) {
		
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
	

}
