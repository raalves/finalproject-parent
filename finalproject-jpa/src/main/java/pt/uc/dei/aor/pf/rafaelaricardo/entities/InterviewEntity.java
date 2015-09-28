package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pt.uc.dei.aor.pf.rafaelaricardo.enums.InterviewStatus;

@Entity
@Table(name = "interview")
@NamedQueries({
		@NamedQuery(name = "InterviewEntity.findInterviewById", query = "SELECT i FROM InterviewEntity i WHERE i.id = :id"),
		@NamedQuery(name = "InterviewEntity.findInterviewByCandidature", query = "SELECT i FROM InterviewEntity i WHERE i.candidature = :candidature"),
		@NamedQuery(name = "InterviewEntity.findInterviewsByCandidatures", query = "SELECT i FROM InterviewEntity i WHERE i.candidature = :candidature"),
		@NamedQuery(name = "InterviewEntity.findInterviewByDate", query = "SELECT i FROM InterviewEntity i WHERE i.interviewDate = :interviewDate"),
		@NamedQuery(name = "InterviewEntity.findInterviewByStatus", query = "SELECT i FROM InterviewEntity i WHERE i.interviewStatus = :interviewStatus"),
		@NamedQuery(name = "InterviewEntity.findAllByIdOrder", query = "SELECT i FROM InterviewEntity i ORDER BY i.id"),
		@NamedQuery(name = "InterviewEntity.findInterviewByUser", query = "SELECT i FROM InterviewEntity i INNER JOIN i.interviewers intvs WHERE intvs = :user") })
public class InterviewEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private UserEntity userEntity;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "candidature_id", nullable = false)
	private CandidatureEntity candidature;

	@Temporal(TemporalType.DATE)
	@Column(name = "interview_date", nullable = false)
	private Date interviewDate;

	// @NotNull
	// @NotBlank
	@Column(name = "interview_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private InterviewStatus interviewStatus;

	@Column(columnDefinition = "text")
	private String feedback;

	@ManyToMany(mappedBy = "interviews")
	private List<UserEntity> interviewers = new ArrayList<>();

	// ************************ CONSTRUCTORS *************************

	public InterviewEntity() {
		super();
	}

	public InterviewEntity(Date interviewDate, InterviewStatus interviewStatus) {
		super();
		this.interviewDate = interviewDate;
		this.interviewStatus = interviewStatus;
	}

	// *************************** METHODS ***************************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CandidatureEntity getCandidature() {
		return candidature;
	}

	public void setCandidature(CandidatureEntity candidature) {
		this.candidature = candidature;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public InterviewStatus getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(InterviewStatus interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public List<UserEntity> getInterviewers() {
		return interviewers;
	}

	public void setInterviewers(List<UserEntity> interviewers) {
		this.interviewers = interviewers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterviewEntity other = (InterviewEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterviewEntity [id=" + id + ", candidature=" + candidature
				+ ", interviewDate=" + interviewDate + ", interviewStatus="
				+ interviewStatus + ", feedback=" + feedback + "]";
	}

}
