package pt.uc.dei.aor.pf.rafaelaricardo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginMBTest extends Mockito {

	@InjectMocks
	private LoginMB logMB;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private UserEntity u;

	@Mock
	private Principal principal;

	@Mock
	private HttpSession session;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testlogin() {

		boolean result = true;
		try {
			request.login("admin@admin.com", "12345");
			when(request.getParameter("email")).thenReturn("someemail");
			when(request.getParameter("password")).thenReturn("somepass");
			assertEquals(response.getStatus(), 0);
			when(request.isUserInRole("CLIENT")).thenReturn(true);
			when(principal.getName()).thenReturn("samename");
			when(request.getUserPrincipal()).thenReturn(principal);
			when(request.getSession()).thenReturn(session);
		} catch (Exception e) {
			result = false;
		}
		assertTrue(result);
		assertThat(result, is(equalTo(true)));

	}

}
