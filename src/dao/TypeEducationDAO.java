package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.TypeEducation;

public interface TypeEducationDAO {
	
	public void addTypeEducation(TypeEducation typeEducation) throws SQLException;
	public Collection<TypeEducation> listTypeEducations() throws SQLException;
	public void removeTypeEducation(Integer id) throws SQLException;

}
