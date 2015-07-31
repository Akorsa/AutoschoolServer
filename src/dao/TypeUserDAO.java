package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.TypeUser;

public interface TypeUserDAO {
	
	public void addTypeUser(TypeUser typeUser) throws SQLException;
	public Collection<TypeUser> listTypeUsers() throws SQLException;
	public void removeTypeUser(Integer id) throws SQLException;


}
