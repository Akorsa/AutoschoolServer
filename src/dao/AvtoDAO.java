package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.Avto;

public interface AvtoDAO {
	public void addAvto(Avto avto) throws SQLException;
	public Collection<Avto> listAvto() throws SQLException;
	public void removeAvto(Integer id) throws SQLException;

}
