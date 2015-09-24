package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;

@ManagedBean
@ViewScoped
public class SearchCandidaturesMB implements Serializable {

	private static final long serialVersionUID = -6172703602355429115L;
	private static final Logger log = LoggerFactory
			.getLogger(SearchCandidaturesMB.class);

	@EJB
	private CandidateFacade candidateFacade;
	@PersistenceContext(unitName = "FinalProject")
	protected EntityManager manager;

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Date birthdate;
	private String address;
	private String city;
	private String country;
	private String phone;
	private String mobilePhone;
	private String course;
	private String school;
	private String searchFree;
	private boolean spontaneous;
	private CandidateEntity candidateSelect;

	private List<CandidateEntity> resultList;

	// public SearchCandidatures() {
	// candidateFacade.findAllByOrder();
	// }

	@PostConstruct
	public void listAllCandidates() {
		log.info("Search for all Candidates");
		try {
			resultList = candidateFacade.findAllByOrder();

		} catch (EJBException e) {
			String errorMsg = "Error gettin candidates: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	public void search() {

		resultList = filterCandidate();
	}

	public void freeSearch() {
		resultList = filterCandidateFreeSearch();
	}

	@SuppressWarnings("unchecked")
	public List<CandidateEntity> filterCandidate() {

		log.info("Filtering candidates");
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CandidateEntity.class);

		if (StringUtils.isNotBlank(firstName)) {
			criteria.add(Restrictions.ilike("firstName", firstName,
					MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(lastName)) {
			criteria.add(Restrictions.ilike("lastName", lastName,
					MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(email)) {
			criteria.add(Restrictions.ilike("email", email, MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(address)) {
			criteria.add(Restrictions.ilike("address", address,
					MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(city)) {
			criteria.add(Restrictions.ilike("city", city, MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(country)) {
			criteria.add(Restrictions.ilike("country", country,
					MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(phone)) {
			criteria.add(Restrictions.eq("phone", Long.parseLong(phone)));
		}
		if (StringUtils.isNotBlank(mobilePhone)) {
			criteria.add(Restrictions.eq("mobilePhone",
					Long.parseLong(mobilePhone)));
		}
		if (StringUtils.isNotBlank(course)) {
			criteria.add(Restrictions.ilike("course", course,
					MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(school)) {
			criteria.add(Restrictions.ilike("school", school,
					MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.asc("firstName")).list();
	}

	@SuppressWarnings("unchecked")
	public List<CandidateEntity> filterCandidateFreeSearch() {

		log.info("Filtering candidates by free search");

		Session session2 = manager.unwrap(Session.class);
		Criteria criteria2 = session2.createCriteria(CandidateEntity.class);

		if (StringUtils.isNotBlank(searchFree)) {
			criteria2.add(Restrictions
					.disjunction()
					.add(Restrictions.ilike("firstName", searchFree,
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("lastName", searchFree,
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("email", searchFree,
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("address", searchFree,
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("city", searchFree,
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("country", searchFree,
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("course", searchFree,
							MatchMode.ANYWHERE))
					.add(Restrictions.ilike("school", searchFree,
							MatchMode.ANYWHERE)));
		}
		return criteria2.addOrder(Order.asc("firstName")).list();
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public CandidateEntity getCandidateSelect() {
		return candidateSelect;
	}

	public void setCandidateSelect(CandidateEntity candidateSelect) {
		this.candidateSelect = candidateSelect;
	}

	public List<CandidateEntity> getResultList() {
		return resultList;
	}

	public void setResultList(List<CandidateEntity> resultList) {
		this.resultList = resultList;
	}

	public boolean isSpontaneous() {
		return spontaneous;
	}

	public void setSpontaneous(boolean spontaneous) {
		this.spontaneous = spontaneous;
	}

	public String getSearchFree() {
		return searchFree;
	}

	public void setSearchFree(String searchFree) {
		this.searchFree = searchFree;
	}

	// public Boolean getSpontaneous() {
	// return spontaneous;
	// }
	//
	// public void setSpontaneous(Boolean spontaneous) {
	// this.spontaneous = spontaneous;
	// }

}
