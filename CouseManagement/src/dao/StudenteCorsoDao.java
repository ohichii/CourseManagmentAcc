package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Studente;
import entity.StudenteCorso;
import entity.User;

public class StudenteCorsoDao implements Dao {

	private final String SCHEMA_TUPLA="id_sc,id_corso,id_studente,valutazione,commento ";
	ConnessioneSingleton is= ConnessioneSingleton.getIstanza();
	Connection con=is.getCon();
	private ResultSet res;
	public StudenteCorsoDao() throws DaoException {
		super();
	}
	private StudenteCorso creaEntity() throws DaoException {
		StudenteCorso entity = new StudenteCorso();
		try {
			entity.setId_sc(res.getInt(1));				
			entity.setId_corso(res.getInt(2));
			entity.setId_studente(res.getInt(3));
			entity.setValutazione(res.getInt(4));
			entity.setCommento(res.getString(5));	
			return entity;
		}catch(SQLException e) {
			throw new DaoException("errore in creaEntity. causa.."+e.getMessage());

		}

	}

	@Override
	public void add(Object studentecorso) throws DaoException {
		String sql = "INSERT INTO studente_corso (id_sc,id_corso,id_studente,valutazione,commento) VALUES(?,?,?,?,?)";
		String sql2 = "INSERT INTO studente_corso (id_corso,id_studente,valutazione,commento) VALUES(?,?,?,?)";
		StudenteCorso nuovoStCorso=null;
		if (studentecorso instanceof StudenteCorso) {
			nuovoStCorso=(StudenteCorso)studentecorso;
		}else {
			throw new DaoException("ERRORE l'oggetto: "+studentecorso+" non è un oggetto corso");
		}
		try  {

			//se se non è presente allora...			
			//sostituisco i placeHolder (?)	
			if(nuovoStCorso.getId_sc()==0) {
				PreparedStatement pst = con.prepareStatement(sql2);
				pst.setInt(1, nuovoStCorso.getId_corso());
				pst.setInt(2, nuovoStCorso.getId_studente());
				pst.setInt(3, nuovoStCorso.getValutazione());
				pst.setString(4, nuovoStCorso.getCommento());
				pst.executeUpdate(); //esegue la query preparata!
			}
			else {
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, nuovoStCorso.getId_sc());
				pst.setInt(2, nuovoStCorso.getId_corso());
				pst.setInt(3, nuovoStCorso.getId_studente());
				pst.setInt(4, nuovoStCorso.getValutazione());
				pst.setString(5, nuovoStCorso.getCommento());
				pst.executeUpdate(); //esegue la query preparata!

			}
		}
		catch (SQLException e) {
			throw new DaoException("lo studente che si sta inserendo è già presente...errore::"+e.getMessage());
		} 


	}
	@Override
	public ArrayList<StudenteCorso> getAll() throws DaoException {
		ArrayList<StudenteCorso> lista = new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM studente_corso "				
				+ "ORDER BY 1";
		try(Statement stm= con.createStatement()){
			res=stm.executeQuery(sql);
			while(res.next()){
				lista.add(creaEntity());
			}
			if(lista.size()==0) {
				throw new DaoException("WARNING: tabella user vuota");
			}

		}catch(SQLException e) {
			throw new DaoException("ERRORE getAll.Causa.."+e.getMessage());
		}
		return lista;

	}

	@Override
	public Object getById(int id) throws DaoException {

		ArrayList<StudenteCorso> lista = new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM studente_corso "
				+ "WHERE id_corso=? ";				

		try(PreparedStatement pst= con.prepareStatement(sql)){
			pst.setInt(1, id); //sostituisce il placeHolder (?)
			res=pst.executeQuery();
			while(res.next()) {
				lista.add(creaEntity());


			}
		}catch(SQLException e) {
			throw new DaoException("ERRORE selectby. Causa.."+e.getMessage());
		}
		return lista;

	}

	@Override
	public ArrayList<?> getBy(String s) throws DaoException {
		ArrayList<StudenteCorso> lista= new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA
				+ " FROM studente_corso "
				+ "WHERE commento like ? ";


		try (PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1,"%"+s+"%"); //sostituisce il placeHolder (?)
			res = pst.executeQuery(); //esegue la query preparata!

			while(res.next()) {
				lista.add(creaEntity());
			}


		}catch(SQLException e) {
			throw new DaoException("ERRORE getAllby.Causa.."+e.getMessage());
		}
		return lista;
	}
	
	
	
	public StudenteCorso getOneBy(int idcorso,int idstudente) throws DaoException {
		StudenteCorso cor = null;
		String sql="SELECT "+SCHEMA_TUPLA
				+ " FROM studente_corso "
				+ "WHERE id_corso=? AND id_studente=? ";


		try (PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, idcorso); //sostituisce il placeHolder (?)
			pst.setInt(2, idstudente);
			res = pst.executeQuery(); //esegue la query preparata!
			if (res.next()) {
				cor=creaEntity();
			}
			else
				return null;

		} catch (SQLException e) {
			throw new DaoException
			("ERRORE selectBy x idcorso="+idcorso+"e idstudente "+idstudente+". Causa: "+e.getMessage());
		}
		return cor; 
	}
	@Override
	public void update(Object tuplaNew) throws DaoException {
		StudenteCorso nuovoStCorso=null;
		if (tuplaNew instanceof StudenteCorso) {
			nuovoStCorso=(StudenteCorso)tuplaNew;
		}else {
			throw new DaoException("errore l'oggetto: "+tuplaNew+" non è un oggetto corso");
		}

		String sql="UPDATE studente_corso " 
				+"SET id_corso=?,id_studente=?,valutazione=?,commento=? "
				+"WHERE id_sc=? ";		
		//aggiornamento (con tutti i getter di tuplaNew, NB: nuovoCorso.getIdxxx è in WHERE)
		try  (PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){ {
			pst.setInt(1, nuovoStCorso.getId_corso());
			pst.setInt(2, nuovoStCorso.getId_studente());
			pst.setInt(3, nuovoStCorso.getValutazione());
			pst.setString(4, nuovoStCorso.getCommento());
			pst.setInt(5, nuovoStCorso.getId_sc());//placeholder della WHERE
			//eseguo lo statement così preparato:
			pst.executeUpdate();


		}

		} catch (SQLException e) {
			throw new DaoException("ERRORE UPDATE x pk="+nuovoStCorso.getId_corso()+". Causa: "+e.getMessage());

		}



	}

	@Override
	public void delete(Object studentecorso) throws DaoException {
		StudenteCorso delStCorso=null;
		if (studentecorso instanceof StudenteCorso) {
			delStCorso=(StudenteCorso)studentecorso;
			//prima cosa da fare:reperire la tupla della pk in argomento
			int tupla = delStCorso.getId_sc();
			//ora DELETE la tupla dal DB
			String sql="DELETE FROM studente_corso WHERE id_sc=? ";

			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, tupla);
				//eseguo lo statement così preparato:
				pst.executeUpdate();



			} catch (SQLException e) {
				throw new DaoException("ERRORE DELETE x object= "+studentecorso+". Causa: "+e.getMessage());

			}



		}



	}
}
