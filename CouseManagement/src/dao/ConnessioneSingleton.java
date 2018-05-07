package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnessioneSingleton {

	private Connection con; // oggetto che voglio creare in 'singleton'

	private static ConnessioneSingleton istanza;

	private ConnessioneSingleton() {
		super();
		// leggeiamo la nostra risorsa properties (bundle):
		ResourceBundle rb = ResourceBundle.getBundle("utente.info.dbinfo");
		String drv = rb.getString("drv");
		String sid = rb.getString("sid");
		String url = rb.getString("url");
		String usr = rb.getString("usr");
		String psw = rb.getString("psw");
		String ssl = rb.getString("ssl");
		try {
			Class.forName(drv);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url + sid + ssl, usr, psw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public static ConnessioneSingleton getIstanza() {
		if (istanza == null)
			istanza = new ConnessioneSingleton();
		return istanza;
	}

	public static void chiudiEannullaStatico() {

		// uso JDBC x chiudere la porta di connessione al DB':
		try {
			ConnessioneSingleton.getIstanza().con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnessioneSingleton.istanza = null; // annullo questa istanza
	}
}
