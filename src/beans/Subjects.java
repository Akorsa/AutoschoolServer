package beans;
// Generated 02.12.2013 21:28:10 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Subjects generated by hbm2java
 */
@Entity
@Table(name = "subjects", schema = "public")
public class Subjects implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6331109086924004719L;
	private int subjectId;
	private String nameOfSubject;
	private Set<Themes> themeses = new HashSet<Themes>(0);
	private Set<LinkTeacherSubject> linkTeacherSubjects = new HashSet<LinkTeacherSubject>(
			0);

	public Subjects() {
	}


	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="subjects_subject_id_seq", name="subjects_subject_id_seq")
	@GeneratedValue(generator="subjects_subject_id_seq", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name = "subject_id", nullable = false)
	public int getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name = "name_of_subject", nullable = false)
	public String getNameOfSubject() {
		return this.nameOfSubject;
	}

	public void setNameOfSubject(String nameOfSubject) {
		this.nameOfSubject = nameOfSubject;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
	public Set<Themes> getThemeses() {
		return this.themeses;
	}

	public void setThemeses(Set<Themes> themeses) {
		this.themeses = themeses;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
	public Set<LinkTeacherSubject> getLinkTeacherSubjects() {
		return this.linkTeacherSubjects;
	}
	public void setLinkTeacherSubjects(
			Set<LinkTeacherSubject> linkTeacherSubjects) {
		this.linkTeacherSubjects = linkTeacherSubjects;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nameOfSubject;
	}

}
