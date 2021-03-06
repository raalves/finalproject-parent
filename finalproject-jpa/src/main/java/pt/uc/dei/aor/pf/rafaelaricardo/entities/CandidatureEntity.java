package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import pt.uc.dei.aor.pf.rafaelaricardo.enums.CandidatureStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;

@Entity
@Table(name = "candidature")
@NamedQueries({
	@NamedQuery(name = "CandidatureEntity.findCandidatureById", query = "SELECT c FROM CandidatureEntity c WHERE c.id = :id"),
	@NamedQuery(name = "CandidatureEntity.findCandidatureByPosition", query = "SELECT c FROM CandidatureEntity c WHERE c.position = :position"),
	@NamedQuery(name = "CandidatureEntity.findCandidatureByCandidate", query = "SELECT c FROM CandidatureEntity c WHERE c.candidate = :candidate"),
	@NamedQuery(name = "CandidatureEntity.findCandidatureBySource", query = "SELECT c FROM CandidatureEntity c WHERE c.publicSource = :publicSource"),
	@NamedQuery(name = "CandidatureEntity.findCandidatureByDate", query = "SELECT c FROM CandidatureEntity c WHERE c.candidatureDate = :candidatureDate"),
	@NamedQuery(name = "CandidatureEntity.findCandidatureByStatus", query = "SELECT c FROM CandidatureEntity c WHERE c.candidatureStatus = :candidatureStatus"),
	@NamedQuery(name = "CandidatureEntity.findAllByIdOrder", query = "SELECT c FROM CandidatureEntity c ORDER BY c.id") })
public class CandidatureEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "position_id")
	private PositionEntity position;

	@ManyToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private CandidateEntity candidate;

	@NotNull
	@NotBlank
	@Column(name = "cv_path", nullable = false)
	private String cvPath;

	@NotNull
	@NotBlank
	@Column(name = "motivation_letter", nullable = false)
	private String motivationLetter;

	@ElementCollection(targetClass = Source.class)
	@Column(name = "public_source")
	@Enumerated(EnumType.STRING)
	private Set<Source> publicSource = new HashSet<Source>();

	@Temporal(TemporalType.DATE)
	@Column(name = "candidature_date", nullable = false)
	private Date candidatureDate;

	@Column(name = "candidature_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private CandidatureStatus candidatureStatus;

	@OneToMany(mappedBy = "candidature", cascade = CascadeType.ALL)
	private List<InterviewEntity> interviews = new ArrayList<>();

	// ************************ CONSTRUCTORS *************************

	public CandidatureEntity() {
		super();
	}

	public CandidatureEntity(String cvPath, String motivationLetter,
			Date candidatureDate, CandidatureStatus candidatureStatus) {
		super();
		this.cvPath = cvPath;
		this.motivationLetter = motivationLetter;
		this.candidatureDate = candidatureDate;
		this.candidatureStatus = candidatureStatus;
	}

	// *************************** METHODS ***************************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	public CandidateEntity getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateEntity candidate) {
		this.candidate = candidate;
	}

	public String getResumePath() {
		return cvPath;
	}

	public void setResumePath(String resumePath) {
		this.cvPath = resumePath;
	}

	public String getMotivationLetter() {
		return motivationLetter;
	}

	public void setMotivationLetter(String motivationLetter) {
		this.motivationLetter = motivationLetter;
	}

	public Set<Source> getPublicSource() {
		return publicSource;
	}

	public void setPublicSource(Set<Source> sourcesSelect) {
		this.publicSource = sourcesSelect;
	}

	public Date getCandidatureDate() {
		return candidatureDate;
	}

	public void setCandidatureDate(Date candidatureDate) {
		this.candidatureDate = candidatureDate;
	}

	public CandidatureStatus getCandidatureStatus() {
		return candidatureStatus;
	}

	public void setCandidatureStatus(CandidatureStatus candidatureStatus) {
		this.candidatureStatus = candidatureStatus;
	}

	public List<InterviewEntity> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<InterviewEntity> interviews) {
		this.interviews = interviews;
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
		CandidatureEntity other = (CandidatureEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CandidatureEntity [id=" + id + ", position=" + position
				+ ", candidate=" + candidate + ", candidatureDate="
				+ candidatureDate + ", candidatureStatus=" + candidatureStatus
				+ "]";
	}

}
