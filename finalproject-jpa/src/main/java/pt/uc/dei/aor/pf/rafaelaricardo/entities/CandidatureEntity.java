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

import pt.uc.dei.aor.pf.rafaelaricardo.enums.CandidatureStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;

@Entity
@Table(name = "candidature")
public class CandidatureEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	private PositionEntity position;
	private CandidateEntity candidate;
	private String resumePath;
	private String coveringLetterPath;
	private Source publicSource;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "candidature_date", nullable = false)
	private Date candidatureDate;
	
	@Enumerated(EnumType.STRING)
	private CandidatureStatus candidatureStatus;
	private List<InterviewEntity> interviews = new ArrayList<>();
	
	// *************************** METHODS ***************************
}
