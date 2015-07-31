package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import beans.Groups;
import beans.YearOfEducation;

public interface GroupDAO {
	
	public void addGroup(Groups group) throws SQLException;
	public Collection<Groups> listGroup() throws SQLException;
	public void removeGroup(Integer id) throws SQLException;
	public List<Groups> getGroupOnYear(String year) throws SQLException;

}
