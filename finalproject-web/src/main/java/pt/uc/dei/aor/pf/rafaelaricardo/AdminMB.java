package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.DescriptionPosition;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

@ManagedBean
@ViewScoped
public class AdminMB implements Serializable {

	private static final long serialVersionUID = 8666955892442985554L;
	private static final Logger log = LoggerFactory.getLogger(AdminMB.class);

	@EJB
	private InterviewFacade interviewFacade;

	@EJB
	private RoleFacade roleFacade;

	@EJB
	private GuideFacade guideFacade;

	@EJB
	private PositionFacade positionFacade;

	@EJB
	private UserFacade userFacade;

	@Inject
	private ActiveUserMB activeUser;

	private List<InterviewEntity> allInterviewsList;

	private String title;
	private String technicalAreaWeb;
	private String description;
	private String desiredQualifications;
	private String keyResponsabilities;
	private String requiredQualifications;
	private String company;
	private ArrayList<Location> selectedLocation = new ArrayList<Location>();
	private ArrayList<String> selectedLocationWeb = new ArrayList<String>();
	private ArrayList<Source> selectedSource = new ArrayList<Source>();
	private ArrayList<String> selectedSourceWeb = new ArrayList<String>();
	private int quantity;
	private Date closingDate;
	private int sla;
	private String manager;
	private String guide;

	private TechnicalArea technicalArea;

	@PostConstruct
	public void listAllInterviews() {
		log.info("Search for all Interviews");
		try {
			allInterviewsList = interviewFacade.findAllByOrder();

		} catch (EJBException e) {
			String errorMsg = "Error gettin Interviews: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	public void createNewPosition() {
		log.info("Creating new Position");
		try {
			transformStringInEnums();
			System.out.println(">>>>>>" + manager);
			System.out.println(">>>>>>" + guide);
			System.out.println(">>>>>>" + technicalArea);
			System.out.println(">>>>>>" + selectedLocation);
			System.out.println(">>>>>>" + selectedSource);
			
			DescriptionPosition descriptionPosition = new DescriptionPosition();
			descriptionPosition.setDescription(description);
			descriptionPosition.setDesiredQualifications(desiredQualifications);
			descriptionPosition.setKeyResponsabilities(keyResponsabilities);
			descriptionPosition
					.setRequiredQualifications(requiredQualifications);
			
			System.out.println(">>>>>>" + descriptionPosition);

			if (positionFacade.addPosition(activeUser.getCurrentUser(),
					userFacade.findUserByEmail(manager),
					guideFacade.findGuideById(Long.parseLong(guide)), title,
					selectedLocation, quantity, company, technicalArea,
					descriptionPosition, selectedSource, new Date(),
					closingDate, sla) != null) {
				String infoMsg = "Position created successfully";
				log.info(infoMsg);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(infoMsg));
				cleanFields();
			} else {
				String errorMsg = "Error during creation of position";
				log.error(errorMsg);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(errorMsg));
			}
		} catch (EJBException e) {
			String errorMsg = "Error during creation of position"
					+ e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(errorMsg));

		}
	}

	public Source[] getSources() {
		return Source.values();
	}

	public Location[] getLocations() {
		return Location.values();
	}

	public TechnicalArea[] getTechnicalAreas() {
		return TechnicalArea.values();
	}

	public List<UserEntity> getAllManagers() {
		return roleFacade.findRoleByName(Role.MANAGER).getUsers();
	}

	public List<GuideEntity> getAllGuides() {
		return guideFacade.findAllByOrder();
	}

	public void transformStringInEnums() {
		Source[] allSources = Source.values();
		for (Source s : allSources) {
			for (String sweb : selectedSourceWeb) {
				if (s.name().equals(sweb)) {
					selectedSource.add(s);
				}
			}
		}
		Location[] allLocations = Location.values();
		for (Location l : allLocations) {
			for (String lweb : selectedLocationWeb) {
				if (l.name().equals(lweb)) {
					selectedLocation.add(l);
				}
			}
		}
		technicalArea = TechnicalArea.valueOf(technicalAreaWeb.toUpperCase());
	}

	public void cleanFields() {
		title = null;
		technicalAreaWeb = null;
		description = null;
		desiredQualifications = null;
		keyResponsabilities = null;
		requiredQualifications = null;
		company = null;
		selectedLocation.clear();
		selectedSource.clear();
		quantity = 1;
		closingDate = null;
		sla = 1;
		manager = null;
		guide = null;
	}

	// Getters and Setters

	public List<InterviewEntity> getAllInterviewsList() {
		return allInterviewsList;
	}

	public void setAllInterviewsList(List<InterviewEntity> allInterviewsList) {
		this.allInterviewsList = allInterviewsList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTechnicalArea() {
		return technicalAreaWeb;
	}

	public void setTechnicalArea(String technicalAreaWeb) {
		this.technicalAreaWeb = technicalAreaWeb;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDesiredQualifications() {
		return desiredQualifications;
	}

	public void setDesiredQualifications(String desiredQualifications) {
		this.desiredQualifications = desiredQualifications;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public ArrayList<Location> getSelectedLocation() {
		return selectedLocation;
	}

	public void setSelectedLocation(ArrayList<Location> selectedLocation) {
		this.selectedLocation = selectedLocation;
	}

	public ArrayList<Source> getSelectedSource() {
		return selectedSource;
	}

	public void setSelectedSource(ArrayList<Source> selectedSource) {
		this.selectedSource = selectedSource;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public ArrayList<String> getSelectedLocationWeb() {
		return selectedLocationWeb;
	}

	public void setSelectedLocationWeb(ArrayList<String> selectedLocationWeb) {
		this.selectedLocationWeb = selectedLocationWeb;
	}

	public ArrayList<String> getSelectedSourceWeb() {
		return selectedSourceWeb;
	}

	public void setSelectedSourceWeb(ArrayList<String> selectedSourceWeb) {
		this.selectedSourceWeb = selectedSourceWeb;
	}
}
