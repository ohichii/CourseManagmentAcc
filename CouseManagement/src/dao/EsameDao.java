package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Esame;

public class EsameDao implements Dao {

	private final String SCHEMA_TUPLA = "id_esame,tipo,data1,data2,data3,fk_id_corso ";
	private final String SCHEMA_TUPLA2 = "tipo,data1,data2,data3,fk_id_corso ";

	ConnessioneSingleton is = ConnessioneSingleton.getIstanza();
	Connection con = is.getCon();
	private ResultSet res;

	public EsameDao() throws DaoException {
		super();
		con = ConnessioneSingleton.getIstanza().getCon();
	}

	@Override
	public ArrayList<Esame> getAll() {

		ArrayList<Esame> lista = new ArrayList<Esame>();

		String sql = "SELECT " + SCHEMA_TUPLA + "FROM esame " + "ORDER BY 1 ";
		try (Statement stm = con.createStatement();) {

			res = stm.executeQuery(sql);
			while (res.next()) {
				lista.add(creaEntity());
			}
		} catch (SQLException | DaoException e) {
			e.printStackTrace();

		}
		return lista;

	}

	private Esame creaEntity() throws DaoException {
		Esame entity = new Esame();
		try {
			entity.setId_esame(res.getInt(1));
			entity.setTipo(res.getString(2));
			entity.setDate1(res.getDate(3));
			entity.setDate2(res.getDate(4));
			entity.setDate3(res.getDate(5));
			entity.setFk_id_corso(res.getInt(6));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Object getById(int id) {

		String sql = "SELECT "+ SCHEMA_TUPLA + "FROM esame " + "WHERE id_esame = ?";

		try (PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setInt(1, id);

			res = pst.executeQuery();

			if (res.next()) {
				return creaEntity();
			}

		} catch (SQLException | DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Esame> getBy(String st) {

		ArrayList<Esame> lista = new ArrayList<Esame>();
		String sql = "SELECT " + SCHEMA_TUPLA + "FROM cm.esame " + "WHERE tipo like ? " + "ORDER BY 2";

		try (PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, st); // sostituisce il placeHolder (?)

			res = pst.executeQuery(); // esegue la query preparata!

			while (res.next()) {
				lista.add(creaEntity());
			}

		} catch (SQLException | DaoException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void update(Object tuplaNew) {
		Esame nuovoEsame = null;
		if (tuplaNew instanceof Esame) {
			nuovoEsame = (Esame) tuplaNew;
		}
             
		ArrayList<Esame> lista = this.getAll();
		
		String sql = "UPDATE esame " + "SET id_esame = ?, tipo = ?, data1 = ?,data2=?,data3=?,fk_id_corso=? " + "WHERE id_esame = ? ";

		try (PreparedStatement pst = con.prepareStatement(sql)) {             
			
			pst.setInt(1, nuovoEsame.getId_esame());
			pst.setString(2, nuovoEsame.getTipo());
			pst.setDate(3, nuovoEsame.getDate1());
			pst.setDate(4, nuovoEsame.getDate2());
			pst.setDate(5, nuovoEsame.getDate3());
			pst.setInt(6, nuovoEsame.getFk_id_corso());
			pst.setInt(7,nuovoEsame.getId_esame());
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Object esame) throws DaoException {

		Esame delEsame = null;
		if (esame instanceof Esame) {
			delEsame = (Esame) esame;
		}
		else {
			  throw new DaoException();
		}
		int idTupla = delEsame.getId_esame();
		String sql = "DELETE FROM esame WHERE id_esame=? ";

		try (PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setInt(1, idTupla);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add(Object esame) throws DaoException  {

		String sql = "INSERT INTO cm.esame ("+SCHEMA_TUPLA2+")  VALUES(?,?,?,?,?)";
		Esame nuovoEsame = null;
		if (esame instanceof Esame) {
			nuovoEsame = (Esame) esame;
		}
		else {
			  throw new DaoException();
		}

		try (PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, nuovoEsame.getTipo());
			pst.setDate(2, nuovoEsame.getDate1());
			pst.setDate(3, nuovoEsame.getDate2());
			pst.setDate(4, nuovoEsame.getDate3());
			pst.setInt(5, nuovoEsame.getFk_id_corso());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
