package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import beans.Questions;

public interface QuestionDAO {
	public void addQuestion(Questions question) throws SQLException;
	public Collection<Questions> listQuestions() throws SQLException;
	public void removeQuestion(Integer id) throws SQLException;
	List<Questions> getQuestByTheme(int id);

}
