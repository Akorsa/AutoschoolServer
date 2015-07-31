package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.Drivingschedule;

public interface DrivingscheduleDAO {
	public void addDrivingschedule(Drivingschedule drivingschedule) throws SQLException;
	public Collection<Drivingschedule> listDrivingschedule() throws SQLException;
	public void removeDrivingschedule(Integer id) throws SQLException;

}
