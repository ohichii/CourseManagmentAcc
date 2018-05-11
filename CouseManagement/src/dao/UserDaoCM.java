package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.UserCM;

public class UserDaoCM {

	protected Connection con;
	protected ResultSet res;
	private final String QUERY_1 = "SELECT * from user WHERE username= ? ";
	private final String QUERY = "SELECT * from user WHERE username=? AND password=? ";
	private final String QUERY_INSERT = "INSERT INTO cm.user (nome, cognome, username, e_mail, password, tipo_utente) VALUES ( ? , ? , ? , ? , ? , ?) ";

	public UserDaoCM() {
		super();
		con = ConnessioneSingleton.getIstanza().getCon();
	}

	public void chiudiCon() {

		ConnessioneSingleton.chiudiEannullaStatico();
	}

	public UserCM getUtente(String username, String password) {
		UserCM userCM = new UserCM();
		try {
			PreparedStatement stm = con.prepareStatement(QUERY);
			stm.setString(1, username);
			stm.setString(2, password);
			res = stm.executeQuery();
			if (res.next()) {
				userCM.setId(res.getInt(1));
				userCM.setNome(res.getString(2));
				userCM.setCognome(res.getString(3));
				userCM.setUsername(res.getString(4));
				userCM.setEmail(res.getString(5));
				userCM.setPassword(res.getString(6));
				userCM.setTipo_utente(res.getInt(7));
				
			} else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userCM;
	}
	public UserCM selectByUsername(String username) {
		UserCM userCM = new UserCM();
		try {
			PreparedStatement stm = con.prepareStatement(QUERY_1);
			stm.setString(1, username);
			res = stm.executeQuery();
			if (res.next()) {
				userCM.setId(res.getInt(1));
				userCM.setNome(res.getString(2));
				userCM.setCognome(res.getString(3));
				userCM.setUsername(res.getString(4));
				userCM.setEmail(res.getString(5));
				userCM.setPassword(res.getString(6));
				userCM.setTipo_utente(res.getInt(7));
				
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userCM;
	}

	public void insertUtente(UserCM userCM) {
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(QUERY_INSERT);
			stm.setString(1, userCM.getNome());
			stm.setString(2, userCM.getCognome());
			stm.setString(3, userCM.getUsername());
			stm.setString(4, userCM.getEmail());
			stm.setString(5, userCM.getPassword());
			stm.setInt(6, 3);
			stm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
