package dao;

import beans.Answers;
import java.util.Collection;
import java.sql.SQLException;

public interface AnswersDAO {
	public void addAnswers(Answers answers) throws SQLException;
	public Collection<Answers> listAnswers() throws SQLException;
	public void removeAnswers(Integer id) throws SQLException;
}
