package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

@Entity
@Table(name = "position")
public class PositionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "adminCreator_id", nullable = false)
	private UserEntity adminCreator;

	@ManyToOne
	@JoinColumn(name = "manager_id", nullable = false)
	private UserEntity manager;

	@ManyToOne
	@JoinColumn(name = "guide_id", nullable = false)
	private GuideEntity guide;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String title;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Location location;

	@NotNull
	@NotBlank
	@Column(name = "position_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private PositionStatus positionStatus;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private int quantity;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String company;

	@NotNull
	@NotBlank
	@Column(name = "technical_area", nullable = false)
	@Enumerated(EnumType.STRING)
	private TechnicalArea technicalArea;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String description;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Source source;

	@NotNull
	@NotBlank
	@Temporal(TemporalType.DATE)
	@Column(name = "openning_date", nullable = false)
	private Date openningDate;

	@NotNull
	@NotBlank
	@Temporal(TemporalType.DATE)
	@Column(name = "closing_date", nullable = false)
	private Date closingDate;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private int sla;

	@OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
	private List<CandidatureEntity> candidatures = new ArrayList<>();

	// ************************ CONSTRUCTORS *************************

	public PositionEntity() {
		super();
	}

	public PositionEntity(String title, Location location,
			PositionStatus positionStatus, int quantity, String company,
			TechnicalArea technicalArea, String description, Source source,
			Date openningDate, Date closingDate, int sla) {
		super();
		this.title = title;
		this.location = location;
		this.positionStatus = positionStatus;
		this.quantity = quantity;
		this.company = company;
		this.technicalArea = technicalArea;
		this.description = description;
		this.source = source;
		this.openningDate = openningDate;
		this.closingDate = closingDate;
		this.sla = sla;
	}

	// *************************** METHODS ***************************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getAdminCreator() {
		return adminCreator;
	}

	public void setAdminCreator(UserEntity adminCreator) {
		this.adminCreator = adminCreator;
	}

	public UserEntity getManager() {
		return manager;
	}

	public void setManager(UserEntity manager) {
		this.manager = manager;
	}

	public GuideEntity getGuide() {
		return guide;
	}

	public void setGuide(GuideEntity guide) {
		this.guide = guide;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public PositionStatus getPositionStatus() {
		return positionStatus;
	}

	public void setPositionStatus(PositionStatus positionStatus) {
		this.positionStatus = positionStatus;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public TechnicalArea getTechnicalArea() {
		return technicalArea;
	}

	public void setTechnicalArea(TechnicalArea technicalArea) {
		this.technicalArea = technicalArea;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Date getOpenningDate() {
		return openningDate;
	}

	public void setOpenningDate(Date openningDate) {
		this.openningDate = openningDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public int getSla() {
		return sla;
	}

	public void setSla(int sla) {
		this.sla = sla;
	}

	public List<CandidatureEntity> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<CandidatureEntity> candidatures) {
		this.candidatures = candidatures;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PositionEntity other = (PositionEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
