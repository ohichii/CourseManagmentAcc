package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Admin;
import entity.StudenteEsame;

public class StudenteEsameDao implements Dao {

	private final String SCHEMA_TUPLA="id_es,id_studente,id_esame,voto ";
	ConnessioneSingleton is= ConnessioneSingleton.getIstanza();
	Connection con=is.getCon();
	private ResultSet res;
	
	
	
	public StudenteEsameDao() throws DaoException {
		super(); 
		// TODO Auto-generated constructor stub
	}
	
	private StudenteEsame creaEntity() throws DaoException {
		StudenteEsame entity = new StudenteEsame();
		try {
			entity.setId_es(res.getInt(1));
			entity.setId_studente(res.getInt(2));
			entity.setId_esame(res.getInt(3));
			entity.setVoto(res.getInt(4));
            return entity;
		}catch(SQLException e) {
			throw new DaoException("errore in creaEntity. causa.."+e.getMessage());

		}
	}
	
	
	
	@Override
	public ArrayList<StudenteEsame> getAll() throws DaoException {
		
		ArrayList<StudenteEsame> lista = new ArrayList<StudenteEsame>();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM esame_studente "				
				+ "ORDER by 1";
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
		StudenteEsame lista = new StudenteEsame();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM esame_studente "
				+ "WHERE id_es=? ";				

		try(PreparedStatement pst= con.prepareStatement(sql)){
			pst.setInt(1, id); //sostituisce il placeHolder (?)
			res=pst.executeQuery();
			if(res.next()) {
				lista=creaEntity();
			}else return null;

		}catch(SQLException e) {
			throw new DaoException("ERRORE getById. Causa.."+e.getMessage());
		}
		return lista;

	}
	@Override
	public ArrayList<StudenteEsame> getBy(String str1) throws DaoException {
		ArrayList<StudenteEsame> lista= new ArrayList<StudenteEsame>();
		String sql="SELECT "+SCHEMA_TUPLA
				+ " FROM esame_studente ";
				


		try (PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1,"%"+str1+"%"); //sostituisce il placeHolder (?)
			pst.setString(2,"%"+str1+"%");
			pst.setString(3,"%"+str1+"%");
			pst.setString(4,"%"+str1+"%");
			res = pst.executeQuery(); //esegue la query preparata!

			while(res.next()) {
				lista.add(creaEntity());
			}
		}catch(SQLException e) {
			throw new DaoException("ERRORE getby.Causa.."+e.getMessage());
		}
		return null;

	}

	@Override
	public void update(Object tuplaNew) throws DaoException {
		StudenteEsame nuovoStudenteEsame=null;
		if (tuplaNew instanceof StudenteEsame) {
			nuovoStudenteEsame =(StudenteEsame)tuplaNew;
		}else {
			throw new DaoException("errore l'oggetto: "+tuplaNew+" non è un oggetto StudenteEsame");
		}

		String sql="UPDATE esame_studente " 
				+"SET id_es=?,id_studente=?,id_esame=?,voto=? "
				+"WHERE id_es=? ";
		

		try (PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {//a cosa serve?
			pst.setInt(1,nuovoStudenteEsame.getId_es());
			pst.setInt(2, nuovoStudenteEsame.getId_studente());
			pst.setInt(3, nuovoStudenteEsame.getId_esame());
			pst.setInt(4, nuovoStudenteEsame.getVoto());
	       pst.setInt(5,nuovoStudenteEsame.getId_es()) ;//PK in WHERE

			//eseguo lo statement così preparato:
			pst.executeUpdate();



		} catch (SQLException e) {
			throw new DaoException("ERRORE UPDATE x pk="+nuovoStudenteEsame.getId_es()+". Causa: "+e.getMessage());

		}

	}
		

	@Override
	public void delete(Object tupla) throws DaoException {
		StudenteEsame delStudenteCorso =null;
		if (tupla instanceof StudenteEsame) {
			delStudenteCorso =(StudenteEsame) tupla;
			//prima cosa da fare:reperire la tupla della pk in argomento
			int idtupla = delStudenteCorso.getId_es();
			//ora DELETE la tupla dal DB
			String sql="DELETE FROM esame_studente WHERE id_es=? ";

			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, idtupla);
				//eseguo lo statement così preparato:
				pst.executeUpdate();



			} catch (SQLException e) {
				throw new DaoException("ERRORE DELETE x object= "+tupla+". Causa: "+e.getMessage());

			}

		}
	}
	
	
	
	@Override
	public void add(Object tuplaNew) throws DaoException {
		String sql = "INSERT INTO esame_studente (id_es,id_studente,id_esame,VOTO) VALUES(?,?,?,?)";
		
		StudenteEsame nuovoStudenteEsame=null;
		if (tuplaNew instanceof StudenteEsame) {
			nuovoStudenteEsame = (StudenteEsame) tuplaNew;
		}else {
			throw new DaoException("ERRORE l'oggetto: "+nuovoStudenteEsame+" non è un oggetto insegnante");
		}
		try (PreparedStatement pst = con.prepareStatement(sql)) {
	
                pst.setInt(1, nuovoStudenteEsame.getId_es());
				pst.setInt(2, nuovoStudenteEsame.getId_studente());
				pst.setInt(3, nuovoStudenteEsame.getId_esame());
				pst.setInt(4, nuovoStudenteEsame.getVoto());
	           pst.executeUpdate();
					
				

		} catch (SQLException e) {
			throw new DaoException("ERRORE insert. Causa: "+e.getMessage());
		} 

	}







}
