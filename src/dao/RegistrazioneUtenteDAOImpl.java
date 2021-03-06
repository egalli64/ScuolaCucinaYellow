package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Utente;
import exceptions.ConnessioneException;

public class RegistrazioneUtenteDAOImpl implements RegistrazioneUtenteDAO {

	private Connection conn;

	String connectionString = "jdbc:mysql://localhost:3306/RegistrazioneUtente?user=root&password=password";

	Connection connection = null;

	public RegistrazioneUtenteDAOImpl() throws ConnessioneException {
		conn = SingletonConnection.getInstance();
	}

	/**
	 * registrazione di un nuovo utente alla scuola di formazione, se l'utente gi�
	 * esiste si solleva una eccezione
	 */
	@Override
	public void insert(Utente u) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO registrati(id_utente,password,nome,cognome,dataNascita,email,telefono) VALUES (?,?,?,?,?,?,?)");
		ps.setString(1, u.getIdUtente());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getNome());
		ps.setString(4, u.getCognome());
		ps.setDate(5, new java.sql.Date(u.getDataNascita().getTime()));
		ps.setString(6, u.getEmail());
		ps.setString(7, u.getTelefono());
		ps.executeUpdate();

	}

	/**
	 * modifica di tutti i dati di un utente, l'utente viene individuato dal suo
	 * idUtente, se l'utente non esiste si solleva una exception
	 */
	@Override
	public void update(Utente u) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE registrati SET password=?, nome=?, cognome=?, dataNascita=?, email=?, telefono=? where id_utente=?");
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getNome());
		ps.setString(4, u.getCognome());
		ps.setDate(5, new java.sql.Date(u.getDataNascita().getTime()));
		ps.setString(6, u.getEmail());
		ps.setString(7, u.getTelefono());
		ps.executeUpdate();

	}

	/**
	 * cancellazione di un singolo utente l'utente si pu� cancellare solo se non �
	 * correlato ad altri dati. se l'utente non esiste o non � cancellabile si
	 * solleva una eccezione
	 */
	@Override
	public void delete(String idUtente) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM registrati WHERE id_utente=?");

		ps.setString(1, idUtente);
		int n = ps.executeUpdate();
		if (n == 0) {
			throw new SQLException("utente " + idUtente + " non presente");
		}
	}

	/**
	 * lettura di tutti gli utenti registrati se non ci sono utenti registrati il
	 * metodo ritorna una lista vuota
	 */
	@Override
	public ArrayList<Utente> select() throws SQLException {

		ArrayList<Utente> result = new ArrayList<Utente>();

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM registrati");

		if (ps == null) {
			return result;
		}

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String idUtente = rs.getString("id_utente");
			String password = rs.getString("password");
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			Date dataNascita = rs.getDate("dataNascita");
			String email = rs.getString("email");
			String telefono = rs.getString("telefono");

			Utente registrato = new Utente(idUtente, password, nome, cognome, dataNascita, email, telefono, true);
			result.add(registrato);
		}
		return result;
	}

	/**
	 * lettura dei dati di un singolo utente se l'utente non esiste si solleva una
	 * eccezione
	 */
	@Override
	public Utente select(String idUtente) throws SQLException {

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM registrati where id_utente=?");

		ps.setString(1, idUtente);

		ResultSet rs = ps.executeQuery();
		Utente result = null;
		while (rs.next()) {
			String id = rs.getString("id_utente");
			String password = rs.getString("password");
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			Date dataNascita = rs.getDate("dataNascita");
			String email = rs.getString("email");
			String telefono = rs.getString("telefono");

			result = new Utente(id, password, nome, cognome, dataNascita, email, telefono, true);
		}
		return result;
	}
}
