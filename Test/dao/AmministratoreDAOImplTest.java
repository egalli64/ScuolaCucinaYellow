package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import entity.Utente;

class AmministratoreDAOImplTest {

	@Test
	void testInsertDelete() {

		AmministratoreDAO dao = null;
		try {
			dao = new AmministratoreDAOImpl();
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			Date date = df.parse("1975-02-25");
			Utente u = new Utente("mr", "12345678", "mario", "rossi", date, "mrossi@gmail.com",
					"333333558", true);
			dao.insert(u);

			Utente result = dao.select("mr");

			assertEquals(u.getIdUtente(), result.getIdUtente());
			assertEquals(u.getPassword(), result.getPassword());
			assertEquals(u.getNome(), result.getNome());
			assertEquals(u.getCognome(), result.getCognome());
			// we should use internally java.sql.date!
//			assertEquals(u.getDataNascita()., result.getDataNascita());
			assertEquals(u.getEmail(), result.getEmail());
			assertEquals(u.getTelefono(), result.getTelefono());
		} catch (Exception ex) {
			fail("Unexpected Exception: " + ex.getMessage());
		} finally {
			try {
				dao.delete("mr");
			} catch (SQLException e) {
			}
		}
	}
}