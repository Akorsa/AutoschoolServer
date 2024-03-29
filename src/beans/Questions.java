package beans;

// Generated 12.12.2013 21:23:38 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Questions generated by hbm2java
 */
@Entity
@Table(name = "questions", schema = "public")
public class Questions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3051004327260810213L;
	private int questionsId;
	private Themes themes;
	private double difficult;
	private String questionText;
	private String ans1;
	private Boolean ans1flag;
	private String ans2;
	private String ans3;
	private Boolean ans2flag;
	private Boolean ans3flag;
	private String ans4;
	private Boolean ans4flag;
	private String imgpath;
	private Set<Answers> answerses = new HashSet<Answers>(0);

	public Questions() {
	}

	public Questions(int questionsId, Themes themes, double difficult) {
		this.questionsId = questionsId;
		this.themes = themes;
		this.difficult = difficult;
	}

	public Questions(int questionsId, Themes themes, double difficult,
			String questionText, Set<Answers> answerses) {
		this.questionsId = questionsId;
		this.themes = themes;
		this.difficult = difficult;
		this.questionText = questionText;
		this.answerses = answerses;
	}

	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="questions_questions_id_seq", name="questions_questions_id_seq")
	@GeneratedValue(generator="questions_questions_id_seq", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name = "questions_id", unique = true, nullable = false)
	public int getQuestionsId() {
		return this.questionsId;
	}

	public void setQuestionsId(int questionsId) {
		this.questionsId = questionsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theme_id", nullable = false)
	public Themes getThemes() {
		return this.themes;
	}

	public void setThemes(Themes themes) {
		this.themes = themes;
	}

	@Column(name = "difficult", nullable = false, precision = 17, scale = 17)
	public double getDifficult() {
		return this.difficult;
	}

	public void setDifficult(double difficult) {
		this.difficult = difficult;
	}

	@Column(name = "question_text")
	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	@Column(name = "ans1")
	public String getAns1() {
		return this.ans1;
	}

	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	@Column(name = "ans1flag")
	public Boolean getAns1flag() {
		return this.ans1flag;
	}

	public void setAns1flag(Boolean ans1flag) {
		this.ans1flag = ans1flag;
	}

	@Column(name = "ans2")
	public String getAns2() {
		return this.ans2;
	}

	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	@Column(name = "ans3")
	public String getAns3() {
		return this.ans3;
	}

	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}

	@Column(name = "ans2flag")
	public Boolean getAns2flag() {
		return this.ans2flag;
	}

	public void setAns2flag(Boolean ans2flag) {
		this.ans2flag = ans2flag;
	}

	@Column(name = "ans3flag")
	public Boolean getAns3flag() {
		return this.ans3flag;
	}

	public void setAns3flag(Boolean ans3flag) {
		this.ans3flag = ans3flag;
	}

	@Column(name = "ans4")
	public String getAns4() {
		return this.ans4;
	}

	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}

	@Column(name = "ans4flag")
	public Boolean getAns4flag() {
		return this.ans4flag;
	}

	public void setAns4flag(Boolean ans4flag) {
		this.ans4flag = ans4flag;
	}
	
	@Column(name = "imgpath")
	public String getImgpath() {
		return this.imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questions")
	public Set<Answers> getAnswerses() {
		return this.answerses;
	}

	public void setAnswerses(Set<Answers> answerses) {
		this.answerses = answerses;
	}

}
