package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.Teachers;

public interface TeacherDAO {
	
	public void addTeacher(Teachers teacher) throws SQLException;
	public Collection<Teachers> listTeachers() throws SQLException;
	public void removeTeacher(Integer id) throws SQLException;

}
