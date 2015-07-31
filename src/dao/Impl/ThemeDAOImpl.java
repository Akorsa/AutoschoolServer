package dao.Impl;


import java.util.Collection;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.ThemeDAO;
import util.HibernateUtil;
import beans.Themes;


public class ThemeDAOImpl implements ThemeDAO {
	
	private static final transient Logger log = LoggerFactory.getLogger(ThemeDAOImpl.class);
	
	public void addTheme(Themes theme) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(theme);
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
	public Collection<Themes> listThemes() {
		Session session = null;
		List<Themes> themes = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       themes = session.createCriteria(Themes.class).list();
	       for (Themes a : themes) {
	            Hibernate.initialize(a.getSubjects());
	        }
	       session.getTransaction().commit();
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	     return themes;
	}
	
	public void removeTheme(Integer themeId) {
	     Session session = null;
	     try {
	    	 session = HibernateUtil.getSessionFactory().openSession();
	       session.beginTransaction();
	       Themes theme = (Themes)session.get(Themes.class, themeId);
	       session.delete(theme);
	       session.getTransaction().commit();
	       if (theme   != null) {
	    	   
	    	  System.out.println(theme.toString());
	     }
	     } catch (Exception e) {
	    	 log.info("Проблемы с сессией! " + e.getMessage());
	     } finally {
	       if (session != null && session.isOpen()) {
	    	   session.close();
	       }
	     }
	   }

	public void updateTheme(Themes s1) {
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
	public List<Themes> listThemesBySubj(int id) {
		Session session = null;
		 List<Themes> themes = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       themes = session.createQuery ("from Themes s where s.subjects.subjectId = :var1").setParameter("var1", id).list();	
		       for (Themes a : themes) {
		            Hibernate.initialize(a.getSubjects());
		        }
		       session.getTransaction().commit();
		     } catch (Exception e) {
		    	 log.info("Проблемы с сессией! " + e.getMessage());
		     } finally {
		       if (session != null && session.isOpen()) {
		    	   session.close();
		       }
		     }
		     return themes;
	}

	@SuppressWarnings("unchecked")
	public Themes getThemeById(int themeId) {
		Session session = null;
		 List<Themes> themes = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
		       session.beginTransaction();
		       //students = session.createCriteria(Students.class).add(Restrictions.eq("groups.groupid", id)).list();	
		       //students = session.createQuery ("select s from Students s").list();
		       themes = session.createQuery ("from Themes s where s.themeId = :var1").setParameter("var1", themeId).list();	
		       for (Themes a : themes) {
		            Hibernate.initialize(a.getSubjects());
		        }
		       session.getTransaction().commit();
		     } catch (Exception e) {
		    	 log.info("Проблемы с сессией! " + e.getMessage());
		     } finally {
		       if (session != null && session.isOpen()) {
		    	   session.close();
		       }
		     }
		     return themes.get(0);
	}

}
