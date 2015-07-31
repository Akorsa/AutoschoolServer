package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.YearOfEducation;

public interface YearOfEducationDAO {
	public void addYearOfEducation(YearOfEducation yearOfEducation) throws SQLException;
	public Collection<YearOfEducation> listYearOfEducations();
	public void removeYearOfEducation(Integer id) throws SQLException;

}
