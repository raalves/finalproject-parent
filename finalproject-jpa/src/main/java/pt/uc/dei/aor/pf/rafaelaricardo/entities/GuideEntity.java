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
	
	private String guideTitle;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "guide_date", nullable = false)
	private Date guideDate;
	
	private String filePath;
	
	@OneToMany(mappedBy = "guide", cascade = CascadeType.ALL)
	private List<PositionEntity> positions = new ArrayList<>();
	
	// *************************** METHODS ***************************
}
