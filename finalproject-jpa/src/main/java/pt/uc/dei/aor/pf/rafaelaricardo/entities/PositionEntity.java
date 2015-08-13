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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	private String title;

	@Enumerated(EnumType.STRING)
	private List<Location> locations = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private PositionStatus positionStatus;

	private int quantity;

	private String company;

	@Enumerated(EnumType.STRING)
	@Column(name = "technical_area", nullable = false)
	private TechnicalArea technicalArea;

	private String description;

	@Enumerated(EnumType.STRING)
	private Source source;

	@Temporal(TemporalType.DATE)
	@Column(name = "openning_date", nullable = false)
	private Date openningDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "closing_date", nullable = false)
	private Date closingDate;

	private int sla;

	private List<CandidatureEntity> candidatures = new ArrayList<>();

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

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
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
