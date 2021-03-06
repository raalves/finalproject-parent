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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.DescriptionPosition;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

@ManagedBean
@ViewScoped
public class PositionsMB implements Serializable {

	private static final long serialVersionUID = -6744394530622360545L;
	private static final Logger log = LoggerFactory
			.getLogger(PositionsMB.class);

	@PersistenceContext(unitName = "FinalProject")
	protected EntityManager manager;

	@EJB
	private PositionFacade positionFacade;
	@Inject
	private ApplyMB applyMB;
	@Inject
	private ActiveUserMB activeUserMB;
	@Inject
	private WriteEmails writeEmails;
	@Inject
	private EnvioMail enviolMail;

	private String id;
	private String title;
	private String location;
	private String positionStatus;
	private int quantity;
	private String company;
	private String technicalArea;
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
	private String searchFree;
	private String emailTo;
	private String nameTo;
	private String nameFrom;
	private PositionStatus statusSelect;

	private List<PositionEntity> positions = new ArrayList<PositionEntity>();
	private List<PositionEntity> openPositions = new ArrayList<PositionEntity>();
	private List<PositionEntity> allPositions = new ArrayList<PositionEntity>();
	private List<PositionEntity> associatePositions = new ArrayList<PositionEntity>();

	public String changeSatus() {
		log.info("Change status of position: " + positionSelect.getTitle()
				+ ". Change made by: "
				+ activeUserMB.getCurrentUser().getEmail());

		if (positionFacade.changeStatus(positionSelect, statusSelect)) {
			String msg = "Position status changed";
			log.info(msg);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
		} else {
			String errorMsg = "An error ocurred when changing status of position.";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}

		return null;
	}

	public String sendToaFriend() {
		log.info("Sending position to a friend");
		String mailToFriend = writeEmails.mailToFriend(nameTo, nameFrom,
				positionSelect.getTitle());
		if (emailTo
				.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {

			if (mailToFriend != null) {
				enviolMail.sendMail(emailTo,
						"Suggestion of position - Critical Software",
						mailToFriend, null, null);

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Email Sent", null));

			}
		} else {
			String errorMsg = "This email is not valid!";

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
		return null;
	}

	@PostConstruct
	public void listPositions() {

		log.info("Listing positions");

		listOpenPositions();
		listAllPositions();

		if (activeUserMB.getCurrentUser() == null) {
			log.info("Candidate positions");
			try {
				positions = positionFacade.findAllByOrder();
			} catch (EJBException e) {
				String errorMessage = "Error getting positions"
						+ e.getMessage();
				log.error(errorMessage);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								errorMessage, null));
			}
		} else {
			log.info("User positions");
			List<RoleEntity> roles = activeUserMB.getCurrentUser().getRoles();
			boolean var = false;

			for (RoleEntity r : roles) {
				if (r.getRole().equals(Role.ADMIN)) {
					var = true;
				}
			}
			if (var) {
				log.info("Admin positions");
				try {
					positions = positionFacade.findAllByOrder();
					associatePositions = positionFacade
							.findPositionByPositionStatus(PositionStatus.OPEN);
				} catch (EJBException e) {
					String errorMessage = "Error getting positions"
							+ e.getMessage();
					log.error(errorMessage);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									errorMessage, null));
				}
			} else {
				log.info("Manager positions");
				try {
					positions = positionFacade
							.findPositionByManager(activeUserMB
									.getCurrentUser());

					List<PositionEntity> managedPositions = positions;
					for (PositionEntity p : managedPositions) {
						if (p.getPositionStatus().equals(PositionStatus.OPEN)) {
							associatePositions.add(p);
						}
					}
				} catch (EJBException e) {
					String errorMessage = "Error getting positions"
							+ e.getMessage();
					log.error(errorMessage);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									errorMessage, null));
				}
			}
		}
	}

	public void listOpenPositions() {
		log.info("Listing open positions");
		try {
			openPositions = positionFacade
					.findPositionByPositionStatus(PositionStatus.OPEN);
		} catch (EJBException e) {
			String errorMessage = "Error getting positions" + e.getMessage();
			log.error(errorMessage);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
							null));
		}
	}

	public void listAllPositions() {
		log.info("listing all positions");
		try {
			allPositions = positionFacade.findAllByOrder();
		} catch (EJBException e) {
			String errorMessage = "Error getting positions" + e.getMessage();
			log.error(errorMessage);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
							null));
		}
	}

	public void search() {
		log.info("Searching positions");
		allPositions = filterPosition();
		cleanFields();
	}

	public void freeSearch() {
		log.info("Free searching positions");
		allPositions = filterPositionFreeSearch();
	}

	@SuppressWarnings("unchecked")
	public List<PositionEntity> filterPosition() {

		log.info("Filtering positions");
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(PositionEntity.class);

		if (openningDate != null) {
			criteria.add(Restrictions.eq("openningDate", openningDate));
		}
		if (closingDate != null) {
			criteria.add(Restrictions.eq("closingDate", closingDate));
		}
		if (isNumeric(id)) {
			criteria.add(Restrictions.eq("id", Long.parseLong(id)));
		}
		if (StringUtils.isNotBlank(title)) {
			criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(location)) {
			for (Location l : Location.values()) {
				if (l.name().contains(location.toUpperCase())) {
					return positionFacade.findPositionByLocation(l);
				}
			}
		}
		if (StringUtils.isNotBlank(positionStatus)) {
			for (PositionStatus p : PositionStatus.values()) {
				if (p.name().contains(positionStatus.toUpperCase())) {
					criteria.add(Restrictions.eq("positionStatus", p));
				}
			}
		}
		if (StringUtils.isNotBlank(company)) {
			criteria.add(Restrictions.ilike("company", company,
					MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(technicalArea)) {
			for (TechnicalArea t : TechnicalArea.values()) {
				if (t.name().contains(technicalArea.toUpperCase())) {
					criteria.add(Restrictions.eq("technicalArea", t));
				}
			}
		}

		return criteria.addOrder(Order.asc("openningDate")).list();
	}

	@SuppressWarnings("unchecked")
	public List<PositionEntity> filterPositionFreeSearch() {

		log.info("Filtering positions by free search");

		Session session2 = manager.unwrap(Session.class);
		Criteria criteria2 = session2.createCriteria(PositionEntity.class);

		if (StringUtils.isNotBlank(searchFree)) {
			criteria2.add(Restrictions
					.disjunction()
					.add(Restrictions.ilike("title", searchFree,
							MatchMode.ANYWHERE))
							.add(Restrictions.ilike("company", searchFree,
									MatchMode.ANYWHERE)));

			if (isNumeric(searchFree)) {
				criteria2.add(Restrictions.disjunction().add(
						Restrictions.eq("id", Long.parseLong(searchFree))));
			}

			for (Location l : Location.values()) {
				if (l.name().contains(searchFree.toUpperCase())) {
					criteria2.add(Restrictions.disjunction().add(
							Restrictions.eq("location", l)));
				}
			}
			for (PositionStatus p : PositionStatus.values()) {
				if (p.name().contains(searchFree.toUpperCase())) {
					criteria2.add(Restrictions.disjunction().add(
							Restrictions.eq("positionStatus", p)));
				}
			}
			for (TechnicalArea t : TechnicalArea.values()) {
				if (t.name().contains(searchFree.toUpperCase())) {
					criteria2.add(Restrictions.disjunction().add(
							Restrictions.eq("technicalArea", t)));
				}
			}

		}
		return criteria2.addOrder(Order.asc("openningDate")).list();
	}

	public void cleanFields() {
		log.info("Cleaning fields");

		openningDate = null;
		closingDate = null;
		id = null;
		title = null;
		location = null;
		positionStatus = null;
		company = null;
		technicalArea = null;
	}

	public static boolean isNumeric(String str) {
		try {
			long d = Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	// Getters and Setters
	public PositionStatus[] getStatusPosition() {
		return PositionStatus.values();
	}

	public PositionStatus getStatusSelect() {
		return statusSelect;
	}

	public void setStatusSelect(PositionStatus statusSelect) {
		this.statusSelect = statusSelect;
	}

	public PositionEntity getPositionSelect() {

		return positionSelect;
	}

	public void setPositionSelect(PositionEntity positionSelect) {
		applyMB.setPositionSelect(positionSelect);
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

	public String getPositionStatus() {
		return positionStatus;
	}

	public void setPositionStatus(String positionStatus) {
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

	public String getTechnicalArea() {
		return technicalArea;
	}

	public void setTechnicalArea(String technicalArea) {
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
		return positions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PositionEntity> getOpenPositions() {
		return openPositions;
	}

	public void setOpenPositions(List<PositionEntity> openPositions) {
		this.openPositions = openPositions;
	}

	public String getSearchFree() {
		return searchFree;
	}

	public void setSearchFree(String searchFree) {
		this.searchFree = searchFree;
	}

	public List<PositionEntity> getAllPositions() {
		return allPositions;
	}

	public void setAllPositions(List<PositionEntity> allPositions) {
		this.allPositions = allPositions;
	}

	public List<PositionEntity> getAssociatePositions() {
		return associatePositions;
	}

	public void setAssociatePositions(List<PositionEntity> associatePositions) {
		this.associatePositions = associatePositions;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getnameTo() {
		return nameTo;
	}

	public void setnameTo(String nameTo) {
		this.nameTo = nameTo;
	}

	public String getnameFrom() {
		return nameFrom;
	}

	public void setnameFrom(String nameFrom) {
		this.nameFrom = nameFrom;
	}

}
