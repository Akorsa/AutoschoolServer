package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.Subjects;

public interface SubjectDAO {
	
	public void addSubject(Subjects subject) throws SQLException;
	public Collection<Subjects> listSubjects() throws SQLException;
	public void removeSubject(Integer id) throws SQLException;


}
