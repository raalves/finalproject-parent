package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class DescriptionPosition implements Serializable {

	private static final long serialVersionUID = 2872070494671373272L;

	@NotBlank
	@Column(nullable = false, length = 2000)
	private String description;

	@NotBlank
	@Column(nullable = false, length = 2000)
	private String keyResponsabilities;

	@NotBlank
	@Column(nullable = false, length = 2000)
	private String requiredQualifications;

	@NotBlank
	@Column(nullable = false, length = 2000)
	private String desiredQualifications;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyResponsabilities() {
		return keyResponsabilities;
	}

	public void setKeyResponsabilities(String keyResponsabilities) {
		this.keyResponsabilities = keyResponsabilities;
	}

	public String getRequiredQualifications() {
		return requiredQualifications;
	}

	public void setRequiredQualifications(String requiredQualifications) {
		this.requiredQualifications = requiredQualifications;
	}

	public String getDesiredQualifications() {
		return desiredQualifications;
	}

	public void setDesiredQualifications(String desiredQualifications) {
		this.desiredQualifications = desiredQualifications;
	}

}
