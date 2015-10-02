package pt.uc.dei.aor.pf.rafaelaricardo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class EncryptPassTest {

	@InjectMocks
	EncryptPass ep;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPassword() {

		String pass = "admin";
		String epass = "jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=";

		assertThat(ep.encrypt(pass), equalTo(epass));

		System.out.println("Checked successfully password encryptation.");
	}
}
