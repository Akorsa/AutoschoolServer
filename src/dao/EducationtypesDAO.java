package dao;

import java.sql.SQLException;
import java.util.Collection;
import beans.Educationtypes;



public interface EducationtypesDAO {
	public void addEducationtype(Educationtypes educationtypes) throws SQLException;
	public Collection<Educationtypes> listEducationtype() throws SQLException;
	public void removeEducationtypes(Integer id) throws SQLException;

}
