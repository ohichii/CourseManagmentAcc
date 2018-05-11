package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Corso;
import entity.Esame;
import entity.Studente;
import entity.StudenteCorso;
import entity.StudenteEsame;

public class FunzionalitaDao {

	ConnessioneSingleton is = ConnessioneSingleton.getIstanza();
	Connection con = is.getCon();
	private ResultSet res;

	/**
	 * metodo che verifica se uno studente è iscritto al corso e nel caso lo iscrive
	 * 
	 * @param studente
	 * @param corso
	 */
	public boolean IscizioneCorso(Studente studente, Corso corso) {
		int idStudente = studente.getId();// prendo id utente che vuole iscriversi
		int idCorso = corso.getId_corso();// prendo id corso a cui ci si vuole iscrivere

		StudenteCorso studentecorso = new StudenteCorso(); // creo oggetto studentecorso
		studentecorso.setId_studente(idStudente);// setto le colonne id_studente
		studentecorso.setId_corso(idCorso);// e id_corso
		try {// provo a fare un controllo se lo studente è gia iscritto al corso
			StudenteCorsoDao dao = new StudenteCorsoDao();// recupero i dao per leggere i database
			// StudenteDao stDao= new StudenteDao();
			StudenteCorso var = dao.getOneBy(idCorso, idStudente);
			if (var == null) {
				dao.add(studentecorso);
				return true;
			} else
				return false;

		} catch (DaoException e) {

			e.printStackTrace();
			return false;
		}

	}

	public boolean iscrizioneEsame(Studente studente, Esame esame) {

		int idStudente = studente.getId();
		int idEsame = esame.getId_esame();
		StudenteEsame studenteesame = new StudenteEsame();
		studenteesame.setId_studente(idStudente);
		studenteesame.setId_esame(idEsame);

		try {
			StudenteEsameDao stDao = new StudenteEsameDao();
			ArrayList<StudenteEsame> var = stDao.getAll();

			for (int i = 0; i < var.size(); i++) {
				if (var.get(i).getId_esame() == idEsame && var.get(i).getId_studente() == idStudente) {
					return false;
				}

			}

			stDao.add(studenteesame);
			return true;

		} catch (DaoException e) {

			e.printStackTrace();
			return false;
		}

	}

	public boolean valutazioneCorso(int val, Studente studente, Corso corso) {
		if (val > 0 && val < 6) {
			try {
				StudenteCorsoDao dao = new StudenteCorsoDao();// recupero il dao
				int idstudente = studente.getId();// recupero id studente
				int idcorso = corso.getId_corso();// recupero id corso
				StudenteCorso studentecorso = new StudenteCorso(); // creo oggetto studentecorso
				studentecorso.setId_studente(idstudente);// setto le colonne id_studente
				studentecorso.setId_corso(idcorso);// e id_corso utili per fare update della tabella
				studentecorso.setValutazione(val);

				StudenteCorso var = dao.getOneBy(idcorso, idstudente);// cerco lo studente nella tabella e nel corso
				System.out.println("questo è il risultato di var---" + var);
				if (var == null) {

					return false;
				} else {
					int x = var.getId_sc();
					studentecorso.setId_sc(x);
					dao.update(studentecorso);
					return true;
				}

			} catch (DaoException e) {

				e.printStackTrace();
				return false;
			}
		}
		return false;// qui torna false se la valutazione dello studente ha un valore errato

	}

	public ArrayList<Studente> visualizzaIscrittiCorso(int idcorso) throws DaoException {
		ArrayList<Studente> lista = new ArrayList<Studente>();
		StudenteDao dao1 = new StudenteDao();
		StudenteCorsoDao dao2 = new StudenteCorsoDao();

		String sql = "SELECT id_utente,nome,cognome,username,e_mail,password,tipo_utente " + "FROM studente_corso "
				+ "JOIN user on id_studente= id_utente " + "WHERE id_corso=? ";

		try (PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, idcorso); // sostituisce il placeHolder (?)
			dao1.res = pst.executeQuery();
			while (dao1.res.next()) {
				lista.add(dao1.creaEntity());

			}
		} catch (SQLException e) {
			throw new DaoException("ERRORE selectby. Causa.." + e.getMessage());
		}
		return lista;

	}

	public boolean valutazioneEsame(Studente studente, Esame esame, int voto) {

		if (voto > 30 || voto < 0) {

			return false;
		}

		int idStudente = studente.getId();
		int idEsame = esame.getId_esame();
		int x = -1;

		StudenteEsame studenteesame = new StudenteEsame();
		studenteesame.setId_studente(idStudente);
		studenteesame.setId_esame(idEsame);
		studenteesame.setVoto(voto);

		try {
			StudenteEsameDao stDao = new StudenteEsameDao();
			ArrayList<StudenteEsame> var = stDao.getAll();

			for (int i = 0; i < var.size(); i++) {
				if (var.get(i).getId_esame() == idEsame && var.get(i).getId_studente() == idStudente) {

					x = var.get(i).getId_es();
					studenteesame.setId_es(x);
				}

			}

			if (x > 0) {
				stDao.update(studenteesame);
				return true;
			}
			return false;

		} catch (DaoException e) {

			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Studente> selectbyEsame(int idesame) throws DaoException { // visualizzazione iscritti esame

		ArrayList<Studente> lista2 = new ArrayList<Studente>();
		StudenteDao dao = new StudenteDao();
		String sql = "SELECT id_utente,nome,cognome,username,e_mail,password,tipo_utente  FROM esame_studente join user on id_studente = id_utente "
				+ "WHERE id_esame = ?";
		try (PreparedStatement pst = con.prepareStatement(sql)) {// a cosa serve?
			pst.setInt(1, idesame);
			dao.res = pst.executeQuery();

			while (dao.res.next()) {
				lista2.add(dao.creaEntity());
			}

		} catch (SQLException e) {
			throw new DaoException("ERRORE getAll.Causa.." + e.getMessage());
		}
		return lista2;

	}

}
