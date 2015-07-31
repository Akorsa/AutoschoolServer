package dao;

import java.util.Collection;

import beans.LinkTeacherSubject;


public interface LinkTeacherSubjectDAO {
	
	public void addLinkTeacherSubject(LinkTeacherSubject typeUser);
	public Collection<LinkTeacherSubject> listLinkTeacherSubjects();
	public void removeLinkTeacherSubject(Integer id);


}
