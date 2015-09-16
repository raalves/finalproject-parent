package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;

@Named
@SessionScoped
public class NewUserRegisterMB implements Serializable {

	private static final long serialVersionUID = 1065309372563194418L;
	private static final Logger log = LoggerFactory
			.getLogger(NewUserRegisterMB.class);

	@Inject
	private ActiveUserMB actUser;

	@Inject
	private ApplicationMB applicationMB;
	@EJB
	private RoleFacade rolefacade;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String repeatPassword;
	private UserEntity creator;

	private ArrayList<String> selectedRoles;
	private ArrayList<RoleEntity> idSelectedRoles = new ArrayList<RoleEntity>();

	public NewUserRegisterMB() {

	}

	public void newUser() {
		creator = actUser.getCurrentUser();
		log.info(creator.getId() + ": admin creating a new User/Employee");
		log.debug("Creating new user " + email);
		findIdSelectRoles();

		if (email
				.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			if (password.equals(repeatPassword)) {
				if (applicationMB.addUser(creator, firstName, lastName, email,
						password, idSelectedRoles) == null) {
					String errorMsg = "This email already exists!";
					log.error(errorMsg);
					FacesContext.getCurrentInstance().addMessage(
							"msgNewUser",
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									errorMsg, null));
				} else {
					String infoMsg = "Successfully created user";
					log.error(infoMsg);
					FacesContext.getCurrentInstance().addMessage(
							"growlNewUser",
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									infoMsg, null));
				}
			} else {
				String errorMsg = "Passwords don't macth";
				log.error(errorMsg);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
								null));
			}
		} else {
			String errorMsg = "This email is not valid!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	public void findIdSelectRoles() {
		ArrayList<RoleEntity> allRoles = (ArrayList<RoleEntity>) rolefacade
				.findAllByIdOrder();

		for (RoleEntity re : allRoles) {

			for (String r : selectedRoles) {

				if (re.getRole().toString().equals(r)) {
					System.out.println(re + " " + r);
					idSelectedRoles.add(re);

				}
			}
		}

	}

	// Getters and Setters
	public Role[] getRoles() {
		return Role.values();
	}

	public ArrayList<String> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(ArrayList<String> selectedRoles) {
		System.out.println("selectedroles " + selectedRoles);
		this.selectedRoles = selectedRoles;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public ActiveUserMB getActUser() {
		return actUser;
	}

	public void setActUser(ActiveUserMB actUser) {
		this.actUser = actUser;
	}

	public UserEntity getCreator() {
		return creator;
	}

	public void setCreator(UserEntity creator) {
		this.creator = creator;
	}

	public ArrayList<RoleEntity> getIdSelectedRoles() {
		return idSelectedRoles;
	}

	public void setIdSelectedRoles(ArrayList<RoleEntity> idSelectedRoles) {
		this.idSelectedRoles = idSelectedRoles;
	}

}
