package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	protected Connection con;
	protected ResultSet res;
	private final String QUERY_1 = "SELECT id_utente, username, password from utente WHERE username= ? ";
	private final String QUERY = "SELECT id_utente, username, password from utente WHERE username=? AND password=? ";
	private final String QUERY_INSERT = "INSERT INTO utente.utente (`username`, `password`) VALUES ( ? , ? ) ";

	public UserDao() {
		super();
		con = ConnessioneSingleton.getIstanza().getCon();
	}

	public void chiudiCon() {

		ConnessioneSingleton.chiudiEannullaStatico();
	}

	public User getUtente(String username, String password) {
		User user = new User();
		try {
			PreparedStatement stm = con.prepareStatement(QUERY);
			stm.setString(1, username);
			stm.setString(2, password);
			res = stm.executeQuery();
			if (res.next()) {
				user.setId_utente(res.getInt(1));
				user.setUsername(res.getString(2));
				user.setPassword(res.getString(3));
			} else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public User selectByUsername(String username) {
		User user = new User();
		try {
			PreparedStatement stm = con.prepareStatement(QUERY_1);
			stm.setString(1, username);
			res = stm.executeQuery();
			if (res.next()) {
				System.out.println("true");
				user.setId_utente(res.getInt(1));
				user.setUsername(res.getString(2));
				user.setPassword(res.getString(3));
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void insertUtente(String username, String password) {
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(QUERY_INSERT);
			stm.setString(1, username);
			stm.setString(2, password);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
