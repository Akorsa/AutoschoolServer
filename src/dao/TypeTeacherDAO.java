package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.TypeTeacher;

public interface TypeTeacherDAO {
	
	public void addTypeTeacher(TypeTeacher typeEducation) throws SQLException;
	public Collection<TypeTeacher> listTypeTeachers() throws SQLException;
	public void removeTypeTeacher(Integer id) throws SQLException;

}
