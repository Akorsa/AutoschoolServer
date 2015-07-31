package dao;

import java.sql.SQLException;
import java.util.Collection;

import beans.Themes;

public interface ThemeDAO {
	
	public void addTheme(Themes theme) throws SQLException;
	public Collection<Themes> listThemes() throws SQLException;
	public void removeTheme(Integer id) throws SQLException;

}
