package dao;
import beans.Categories;
import java.util.Collection;
import java.sql.SQLException;

public interface CategoriesDAO {

	public void addCategories(Categories categories) throws SQLException;
	public Collection<Categories> listCategories();
	public void removeCategories(Integer id) throws SQLException;
}