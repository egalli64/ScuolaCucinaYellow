package dao;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import entity.Utente;

class RegistrazioneUtenteDAOImplTest {

//	@Test
//	void insert() {
//		RegistrazioneUtenteDAO dao = null;
//		try {
//			dao = new RegistrazioneUtenteDAOImpl();
//			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//			Date date = df.parse("1975-02-25");
//
//			Utente u = new Utente("ub", "xyzashka", "ugo", "bianchi", date, "ubianchi@gmail.com", "3483335587", true);
//			dao.insert(u);
//
//			Utente result = dao.select("ub");
//
//			assertEquals(u.getIdUtente(), result.getIdUtente());
//			assertEquals(u.getPassword(), result.getPassword());
//			assertEquals(u.getNome(), result.getNome());
//			assertEquals(u.getCognome(), result.getCognome());
//			assertEquals(u.getEmail(), result.getEmail());
//			assertEquals(u.getTelefono(), result.getTelefono());
//		} catch (Exception ex) {
//			fail("Unexpected Exception: " + ex.getMessage());
//		} finally {
//			try {
//				//dao.delete("ub");
//			} catch (SQLException e) {
//			}
//		}
//	}
//}

	@Test
	void delete() {
		RegistrazioneUtenteDAO dao = null;
		try {
			dao = new RegistrazioneUtenteDAOImpl();
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			Date date = df.parse("1975-02-25");

			Utente u = new Utente("ub", "xyzashka", "ugo", "bianchi", date, "ubianchi@gmail.com", "3483335587", true);
			dao.insert(u);
			dao.delete("ub");
		} catch (Exception ex) {
			fail("Unexpected Exception: " + ex.getMessage());
		}

		try {
			dao.select("ub");
			fail("Select have to fail here!");
		} catch (SQLException e) {
			// as expected
		}
	}
}