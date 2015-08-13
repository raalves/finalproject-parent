package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pt.uc.dei.aor.pf.rafaelaricardo.enums.InterviewStatus;

@Entity
@Table(name = "interview")
public class InterviewEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	private CandidatureEntity candidature;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "interview_date", nullable = false)
	private Date interviewDate;
	
	@Enumerated(EnumType.STRING)
	private InterviewStatus interviewStatus;
	
	@Column(columnDefinition = "text")
	private String feedback;
	
	private List<UserEntity> interviewers = new ArrayList<>();
	
	// *************************** METHODS ***************************
	
}
