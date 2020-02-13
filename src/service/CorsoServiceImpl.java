package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CatalogoDAO;
import dao.CatalogoDAOImpl;
import dao.CategoriaDAO;
import dao.CategoriaDAOImpl;
import dao.RegistrazioneUtenteDAOImpl;
import dto.CorsoDTO;
import entity.Categoria;
import entity.Corso;
import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;

public class CorsoServiceImpl implements CorsoService {

	// dichiarare qui tutti i dao di cui si ha bisogno
	private CatalogoDAO daoC;
	private CategoriaDAO daoCat;
	private CorsoDTO dtoC;
//	private EdizioneDTO dtoE;

	// costruire qui tutti i dao di cui si ha bisogno
	public CorsoServiceImpl() throws ConnessioneException {
		daoC = new CatalogoDAOImpl();
		daoCat = new CategoriaDAOImpl();
		dtoC = new CorsoDTO();
//		dtoE = new EdizioneDTO();
	}

	/**
	 * @author ORIGINALE
	 * 
	 *         il metodo mostra tutti i corsi offerti dalla scuola se il metodo
	 *         del/dei DAO invocati sollevano una eccezione, il metodo deve tornare
	 *         una DAOException con all'interno l'exception originale
	 */
	@Override
	public ArrayList<Corso> visualizzaCatalogoCorsi() throws DAOException {
		try {
			return daoC.select();
		} catch (SQLException e) {
			throw new DAOException("errore nel recuperare o leggere i dati", e);

		}
	}

	/**
	 * @author DA CONTROLLARE il metodo mostra l'elenco dei corsi di una certa
	 *         categoria dal catalogo se il metodo del/dei DAO invocati sollevano
	 *         una eccezione, il metodo deve tornare una DAOException con
	 *         all'interno l'exception originale
	 */
	@Override
	public ArrayList<Corso> visualizzaCorsiPerCategoria(int idCategoria) throws DAOException {
		try {
			return daoC.select();
		} catch (SQLException e) {
			throw new DAOException("errore nella lettura dei corsi", e);
		}
	}

	/**
	 * @author COMPLETO lettura di tutte le categorie se i metodi del/dei DAO
	 *         invocati sollevano una eccezione, il metodo deve tornare una
	 *         DAOException con all'interno l'exception originale
	 */
	@Override
	public ArrayList<Categoria> visualizzaCategorie() throws DAOException {
		try {
			return daoCat.select();
		} catch (SQLException e) {
			throw new DAOException("errore nella lettura delle categorie", e);
		}
	}

	/**
	 * @author COMPLETO il metodo (invocabile solo da un amministratore) crea una
	 *         nuova categoria
	 * 
	 *         se i metodi del/dei DAO invocati sollevano una eccezione, il metodo
	 *         deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public void creaNuovaCategoria(String descrizione) throws DAOException {
		try {
			daoCat.insert(descrizione);
		} catch (SQLException e) {
			throw new DAOException("Non è stata creata nessuna categoria", e);
		}
	}

	/*
	 * un oggetto CorsoDTO con tutti i dati di un singolo corso tutte le edizioni di
	 * quel corso con relativi feedbacks (classe EdizioneDTO) il corso è individuato
	 * tramite idCorso
	 * 
	 * se i metodi del/dei DAO invocati sollevano una eccezione, il metodo deve
	 * tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public CorsoDTO visualizzaSchedaCorso(int idCorso) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ritorna una lista con tutti i feedbacks relativi ad un corso il corso è
	 * individuato tramite idCorso se il metodi del/dei DAO invocati sollevano una
	 * eccezione, il metodo deve tornare una DAOException con all'interno
	 * l'exception originale
	 */
	@Override
	public ArrayList<Feedback> visualizzaFeedbackCorso(int idCorso) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * modifica tutti i dati di un corso se il metodi del/dei DAO invocati sollevano
	 * una eccezione, il metodo deve tornare una DAOException con all'interno
	 * l'exception originale
	 */
	@Override
	public void modificaDatiCorso(Corso corso) throws DAOException {
		// TODO Auto-generated method stub

	}

	/*
	 * inserisce un nuovo corso a catalogo (invocabile solo dall'amministratore) se
	 * il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve
	 * tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public void inserisciCorso(Corso corso) throws DAOException {
		// TODO Auto-generated method stub

	}

	/*
	 * cancella un singolo corso dal catalogo dei corsi se il metodi del/dei DAO
	 * invocati sollevano una eccezione, il metodo deve tornare una DAOException con
	 * all'interno l'exception originale
	 */
	@Override
	public void cancellaCorso(int codiceCorso) throws DAOException {
		// TODO Auto-generated method stub

	}

	/*
	 * legge i dati di un singolo corso (senza edizioni) se il metodi del/dei DAO
	 * invocati sollevano una eccezione, il metodo deve tornare una DAOException con
	 * all'interno l'exception originale
	 */
	@Override
	public Corso visualizzaCorso(int codiceCorso) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
