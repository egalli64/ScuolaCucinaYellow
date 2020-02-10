package dao;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import org.junit.jupiter.api.Test;
import entity.Utente;


class RegistrazioneUtenteDAOImplTest {

	@Test
	void test1() {
		try {
			RegistrazioneUtenteDAO dao = new RegistrazioneUtenteDAOImpl();
			Utente u = new Utente("ub", "12345670", "ugo", "bianchi", new java.util.Date(), "ubianchi@gmail.com",
					"348333558", false);

			u.setCognome("Bianchi");
			dao.delete("ub");
			dao.insert(u);
//			dao.update(u);
			System.out.println();
			dao.select("ub");

		} catch (Exception ex) {
			fail("UnExceptionex " + ex.getMessage());
		}

	}

}