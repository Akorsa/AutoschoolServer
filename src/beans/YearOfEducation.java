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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * YearOfEducation generated by hbm2java
 */
@Entity
@Table(name = "year_of_education", schema = "public")
public class YearOfEducation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8406358426791772701L;
	private int yearOfEducationId;
	private String numberOfYear;
	private Set<Groups> groupses = new HashSet<Groups>(0);

	public YearOfEducation() {
	}

	public YearOfEducation(String numberOfYear) {
		this.numberOfYear = numberOfYear;
	}

	public YearOfEducation(int yearOfEducationId, String numberOfYear,
			Set<Groups> groupses) {
		this.yearOfEducationId = yearOfEducationId;
		this.numberOfYear = numberOfYear;
		this.groupses = groupses;
	}
	
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="year_of_education_year_of_education_id_seq", name="year_of_education_year_of_education_id_seq")
	@GeneratedValue(generator="year_of_education_year_of_education_id_seq", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name = "year_of_education_id", unique = true, nullable = false)
	public int getYearOfEducationId() {
		return this.yearOfEducationId;
	}

	public void setYearOfEducationId(int yearOfEducationId) {
		this.yearOfEducationId = yearOfEducationId;
	}

	@Column(name = "number_of_year", nullable = false)
	public String getNumberOfYear() {
		return this.numberOfYear;
	}

	public void setNumberOfYear(String numberOfYear) {
		this.numberOfYear = numberOfYear;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "yearOfEducation")
	public Set<Groups> getGroupses() {
		return this.groupses;
	}

	public void setGroupses(Set<Groups> groupses) {
		this.groupses = groupses;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return numberOfYear;
	}

}