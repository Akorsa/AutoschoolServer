package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import beans.Categories;
import beans.Groups;
import beans.YearOfEducation;


public interface AutoschoolRmiInterface extends Remote {
	
	public Collection<Categories> getCategoryList() throws RemoteException;
	public Collection<YearOfEducation> getYearOfEducation() throws RemoteException;
	public Collection<Groups> getGroupYearEdu(YearOfEducation year) throws RemoteException;
		

}
