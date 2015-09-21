package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.DescriptionPosition;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

@Named
@RequestScoped
public class PositionsMB implements Serializable {

	private static final long serialVersionUID = -6744394530622360545L;
	private static final Logger log = LoggerFactory
			.getLogger(PositionsMB.class);

	@EJB
	private PositionFacade positionFacade;
	@Inject
	private ApplyMB applyMB;
	private int id;
	private String title;
	private String location;
	private PositionStatus positionStatus;
	private int quantity;
	private String company;
	private TechnicalArea technicalArea;
	private DescriptionPosition descriptionPosition;
	private String desiredQualifications;
	private String description;
	private String keyResponsabilities;
	private String requiredQualifications;
	private Source source;
	private Date openningDate;
	private Date closingDate;
	private int sla;
	private PositionEntity positionSelect;

	private List<PositionEntity> positions;

	// public PositionsMB() {
	// // String pagePath = path.charAt(0) + path.substring(1).toLowerCase();
	//
	// technicalArea = technicalArea.toString().charAt(0)
	// + technicalArea.toString().substring(1).toLowerCase();
	// }

	public void listAllPositions() {
		try {
			positions = positionFacade.findAllByOrder();
			System.out.println(positions.get(0).getTitle());
		} catch (EJBException e) {
			String errorMessage = "Error getting positions" + e.getMessage();
			log.error(errorMessage);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(errorMessage));
		}
		// return null;
	}

	// public void searchPosition()

	// Getters and Setters
	public PositionEntity getPositionSelect() {

		return positionSelect;
	}

	public void setPositionSelect(PositionEntity positionSelect) {

		this.positionSelect = positionSelect;
	}

	public PositionFacade getPositionFacade() {
		return positionFacade;
	}

	public void setPositionFacade(PositionFacade positionFacade) {

		this.positionFacade = positionFacade;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
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

	public DescriptionPosition getDescriptionPosition() {
		return descriptionPosition;
	}

	public void setDescriptionPosition(DescriptionPosition descriptionPosition) {
		this.descriptionPosition = descriptionPosition;
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

	public String getDesiredQualifications() {
		return desiredQualifications;
	}

	public void setDesiredQualifications(String desiredQualifications) {
		this.desiredQualifications = desiredQualifications;
	}

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

	public void setPositions(List<PositionEntity> positions) {
		this.positions = positions;
	}

	public List<PositionEntity> getPositions() {
		listAllPositions();
		return positions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
