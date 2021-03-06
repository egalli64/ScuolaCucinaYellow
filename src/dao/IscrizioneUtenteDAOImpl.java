package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entity.Corso;
import entity.Edizione;
import entity.Utente;
import exceptions.ConnessioneException;

public class IscrizioneUtenteDAOImpl implements IscrizioneUtenteDAO {

	private Connection conn;

	public IscrizioneUtenteDAOImpl() throws ConnessioneException {
		conn = SingletonConnection.getInstance();
	}

	/**
	 * iscrizione di un certo utente ad una certa edizione di un corso. sia l'utente
	 * che l'edizione devono gi� essere stati registrati in precedenza se l'utente
	 * e/o l'edizione non esistono o l'utente � gi� iscritto a quella edizione si
	 * solleva una eccezione
	 */
	@Override
	public void iscriviUtente(int idEdizione, String idUtente) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO iscritti(id_edizione, id_utente) VALUES (?,?)");
		ps.setInt(1, idEdizione);
		ps.setString(2, idUtente);
		ps.executeUpdate();
	}

	/**
	 * cancellazione di una iscrizione ad una edizione nota: quando si cancella
	 * l'iscrizione, sia l'utente che l'edizione non devono essere cancellati se
	 * l'utente e/o l'edizione non esistono si solleva una eccezione
	 */
	@Override
	public void cancellaIscrizioneUtente(int idEdizione, String idUtente) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM iscritti WHERE id_edizione=?,id_utente=?");
		ps.setInt(1, idEdizione);
		ps.setString(2, idUtente);
		int n = ps.executeUpdate();
		if (n == 0) {
			throw new SQLException("utente " + idUtente + " cancellato");
		}
	}

	/**
	 * lettura di tutte le edizioni a cui � iscritto un utente se l'utente non
	 * esiste o non � iscritto a nessuna edizione si torna una lista vuota
	 */
	@Override
	public ArrayList<Edizione> selectIscrizioniUtente(String idUtente) throws SQLException {

		ArrayList<Edizione> result = new ArrayList<Edizione>();

		PreparedStatement ps = conn.prepareStatement(
				"SELECT id_edizione FROM registrati join iscritti using(id_utente) join calendario using (id_edizione) where id_utente=?");
		ps.setString(1, idUtente); // risolve i punti di domanda (?) messi nella query
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int idEdizione = rs.getInt("id_edizione");
			int idCorso = rs.getInt("id_corso");
			Date dataInizio = rs.getDate("dataInizio");
			int durata = rs.getInt("durata");
			String aula = rs.getString("aula");
			String docente = rs.getString("docente");

			Edizione iscritto = new Edizione(idEdizione, idCorso, dataInizio, durata, aula, docente);
			result.add(iscritto);
		}
		return result;
	}

	/**
	 * lettura di tutti gli utenti iscritti ad una certa edizione se l'edizione non
	 * esiste o non vi sono utenti iscritti si torna una lista vuota
	 */
	@Override
	public ArrayList<Utente> selectUtentiPerEdizione(int idEdizione) throws SQLException {

		ArrayList<Utente> result = new ArrayList<Utente>();
		PreparedStatement ps = conn.prepareStatement(
				"SELECT id_utente FROM registrati join iscritti using(id_utente) where id_edizione=?");
		ps.setInt(1, idEdizione);
		ResultSet rs = ps.executeQuery(); 
		
		while (rs.next()) {
			String idUtente = rs.getString("id_utente");
			String password = rs.getString("password");
			String nome = rs.getString("name");
			String cognome = rs.getString("cognome");
			Date dataNascita = rs.getDate("dataNascita");
			String email = rs.getString("email");
			String telefono = rs.getString("telefono");
			
			Utente iscritto = new Utente(idUtente, password, nome, cognome, dataNascita, email, telefono, false);
			result.add(iscritto);			
		} 
		return result;
	}

	/**
	 * ritorna il numero di utenti iscritti ad una certa edizione
	 */
	@Override
	public int getNumeroIscritti(int idEdizione) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(id_utente) FROM iscritti WHERE id_edizione=?");
		ps.setInt(1, idEdizione);
		ResultSet rs = ps.executeQuery();
		int result = 0;
		while (rs.next()) {
			result++;
		}
		return result;
	}
}
