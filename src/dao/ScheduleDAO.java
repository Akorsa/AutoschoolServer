package dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import beans.Schedules;

public interface ScheduleDAO {
	
	public void addSchedule(Schedules schedule);
	public Collection<Schedules> listSchedule();
	public void removeSchedule(Integer id);
	List<Schedules> getScheduleFromGroup(Integer id, Date dt1, Date dt2);

}
