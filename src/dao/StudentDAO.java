package dao;

import java.util.Collection;
import java.util.List;

import beans.Students;

public interface StudentDAO {
	
	public void addStudent(Students student);
	public Collection<Students> listStudents();
	public void removeStudent(Integer id);
	public List<Students> getStudentsFromGroup(Integer id) ;
	List<Students> getStudentsFromGroup2(Integer id);
	List<Students> getSvid();
}
