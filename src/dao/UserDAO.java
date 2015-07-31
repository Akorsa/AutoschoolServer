package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.Users;

public interface UserDAO {
	
	public void addUser(Users User) throws SQLException;
	public Collection<Users> listUsers() throws SQLException;
	public void removeUser(Integer id) throws SQLException;

}
