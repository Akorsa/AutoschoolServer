package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.Tests;

public interface TestDAO {
	
	public void addTest(Tests test) throws SQLException;
	public Collection<Tests> listTests() throws SQLException;
	public void removeTest(Integer id) throws SQLException;

}
