package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "guide")
public class GuideEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private UserEntity author;

	@NotNull
	@NotBlank
	@Column(name = "guide_title", nullable = false)
	private String guideTitle;

	@NotNull
	@NotBlank
	@Temporal(TemporalType.DATE)
	@Column(name = "guide_date", nullable = false)
	private Date guideDate;

	@NotNull
	@NotBlank
	@Column(name = "file_path", nullable = false)
	private String filePath;

	@OneToMany(mappedBy = "guide", cascade = CascadeType.ALL)
	private List<PositionEntity> positions = new ArrayList<>();

	// ************************ CONSTRUCTORS *************************

	public GuideEntity() {
		super();
	}

	public GuideEntity(String guideTitle, Date guideDate, String filePath) {
		super();
		this.guideTitle = guideTitle;
		this.guideDate = guideDate;
		this.filePath = filePath;
	}

	// *************************** METHODS ***************************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public void setAuthor(UserEntity author) {
		this.author = author;
	}

	public String getGuideTitle() {
		return guideTitle;
	}

	public void setGuideTitle(String guideTitle) {
		this.guideTitle = guideTitle;
	}

	public Date getGuideDate() {
		return guideDate;
	}

	public void setGuideDate(Date guideDate) {
		this.guideDate = guideDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<PositionEntity> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionEntity> positions) {
		this.positions = positions;
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
		GuideEntity other = (GuideEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
