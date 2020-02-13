package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;

public class FeedBackDAOImpl implements FeedbackDAO {

	private Connection conn;

	public FeedBackDAOImpl() throws ConnessioneException {
		conn = SingletonConnection.getInstance();
	}

	/**
	 * inserimento di un singolo feedbak relativo ad una edizione di un corso da
	 * parte di un utente se un utente ha già inserito un feedback per una certa
	 * edizione si solleva una eccezione
	 */
	@Override
	public void insert(Feedback feedback) throws SQLException {

		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO feedback (id_edizione, id_utente, descrizione, voto) VALUES (?,?,?,?)");
		ps.setInt(1, feedback.getIdEdizione());
		ps.setString(2, feedback.getIdUtente());
		ps.setString(3, feedback.getDescrizione());
		ps.setInt(4, feedback.getVoto());
		ps.executeUpdate();
	}

	/**
	 * modifica di tutti i dati di un singolo feedback un feedback viene individuato
	 * attraverso l'idFeedback se il feedback non esiste si solleva una eccezione
	 */
	@Override
	public void update(Feedback feedback) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE feedback SET id_edizione=?, id_utente=?, descrizione=?, voto=? where id_feedback=?");
		ps.setInt(1, feedback.getIdEdizione());
		ps.setString(2, feedback.getIdUtente());
		ps.setString(3, feedback.getDescrizione());
		ps.setInt(4, feedback.getVoto());
		ps.executeUpdate();
	}

	/**
	 * cancellazione di un feedback se il feedback non esiste si solleva una
	 * eccezione
	 */
	@Override
	public void delete(int idFeedback) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM feedback WHERE id_feedback=?");
		ps.setInt(1, idFeedback);
		int n = ps.executeUpdate();
		if (n == 0) {
			throw new SQLException("Feedback " + idFeedback + " non esiste");
		}

	}

	/**
	 * lettura di un singolo feedback scritto da un utente per una certa edizione se
	 * il feedback non esiste si solleva una eccezione
	 */
	@Override
	public Feedback selectSingoloFeedback(String idUtente, int idEdizione) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT descrizione, voto FROM feedback where id_utente=? and id_edizione=?");
		ps.setString(1,idUtente);
		ps.setInt(2, idEdizione);
		ResultSet rs = ps.executeQuery();
		
		
		if (rs.next()) {
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");

			return new Feedback (idEdizione, idUtente, descrizione, voto);
		}
		
		throw new SQLException ("il Feedback dell'utente " + idUtente + " non esiste");
	}

	
	

	
	
	
	
	
	
	
	
	
	
	/*
	 * lettura di tutti i feedback di una certa edizione se non ci sono feedback o
	 * l'edizione non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectPerEdizione(int idEdizione) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * lettura di tutti i feedback scritti da un certo utente se non ci sono
	 * feedback o l'utente non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectPerUtente(String idUtente) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * lettura di tutti i feedback scritti per un certo corso (nota: non edizione ma
	 * corso) se non ci sono feedback o il corso non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectFeedbackPerCorso(int idCorso) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
