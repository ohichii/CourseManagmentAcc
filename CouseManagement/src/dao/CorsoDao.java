package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Corso;


public class CorsoDao  implements Dao {

	private final String SCHEMA_TUPLA="id_corso,nome_corso,data_inizio,data_fine,Descr,id_prof ";
	ConnessioneSingleton is= ConnessioneSingleton.getIstanza();
	Connection con=is.getCon();
	private ResultSet res;
	public CorsoDao() throws DaoException {
		super();
	}
	
	private Corso creaEntity() throws DaoException {
		Corso entity = new Corso();
		try {
			entity.setId_corso(res.getInt(1));				
			entity.setNome_corso(res.getString(2));
			entity.setData_inizio(res.getDate(3));
			entity.setData_fine(res.getDate(4));
			entity.setDescr(res.getString(5));
			entity.setId_prof(res.getInt(6));
			return entity;
		}catch(SQLException e) {
			throw new DaoException("errore in creaEntity. causa.."+e.getMessage());

		}
	}
	
	
	@Override
	public void add(Object corso) throws DaoException {
		String sql = "INSERT INTO corso (nome_corso,data_inizio,data_fine,Descr,id_prof) VALUES(?,?,?,?,?)";
		String sql2 = "INSERT INTO corso (nome_corso,data_inizio,data_fine,Descr) VALUES(?,?,?,?)";
		Corso nuovoCorso=null;
		if (corso instanceof Corso) {
			nuovoCorso=(Corso)corso;
		}else {
			throw new DaoException("ERRORE l'oggetto: "+corso+" non è un oggetto corso");
		}
		try  {
			String var = nuovoCorso.getNome_corso();//controlla che il corso  non sia già in database
			if(this.getBy(var).isEmpty()) {			//se se non è presente allora...			
			//sostituisco i placeHolder (?)	
				if(nuovoCorso.getId_prof()==0) {
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.setString(1, nuovoCorso.getNome_corso());
					pst.setDate(2, nuovoCorso.getData_inizio());
					pst.setDate(3, nuovoCorso.getData_fine());
					pst.setString(4, nuovoCorso.getDescr());
					pst.executeUpdate(); //esegue la query preparata!
				}
				else {
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, nuovoCorso.getNome_corso());
					pst.setDate(2, nuovoCorso.getData_inizio());
					pst.setDate(3, nuovoCorso.getData_fine());
					pst.setString(4, nuovoCorso.getDescr());
					pst.setInt(5, nuovoCorso.getId_prof());
					pst.executeUpdate(); //esegue la query preparata!
				}
			}else throw new DaoException("il corso: "+var+" che si sta inserendo è già presente");
		} catch (SQLException e) {
			throw new DaoException("ERRORE insert. Causa: "+e.getMessage());
		} 

	}

	@Override
	public ArrayList<Corso> getAll() throws DaoException {
		ArrayList<Corso> lista = new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM corso "				
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

		Corso lista = new Corso();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM corso "
				+ "WHERE id_corso=? ";				

		try(PreparedStatement pst= con.prepareStatement(sql)){
			pst.setInt(1, id); //sostituisce il placeHolder (?)
			res=pst.executeQuery();
			if(res.next()) {
				lista=creaEntity();
			}else return null;

		}catch(SQLException e) {
			throw new DaoException("ERRORE selectby. Causa.."+e.getMessage());
		}
		return lista;

	}


	public Corso getOneBy(String s) throws DaoException {
		Corso cor = null;
		String sql="SELECT "+SCHEMA_TUPLA
				+ " FROM corso "
				+ "WHERE nome_corso=? ";


		try (PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, s); //sostituisce il placeHolder (?)
			res = pst.executeQuery(); //esegue la query preparata!
			if (res.next()) {
				cor=creaEntity();
			}
			else
				return null;

		} catch (SQLException e) {
			throw new DaoException
			("ERRORE selectBy x stringa="+s+". Causa: "+e.getMessage());
		}
		return cor; 



	}
	@Override
	public ArrayList<Corso> getBy(String s) throws DaoException {
		ArrayList<Corso> lista= new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA
				+ " FROM corso "
				+ "WHERE nome_corso like ? ";


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

	@Override
	public void update(Object tuplaNew) throws DaoException {
		Corso nuovoCorso=null;
		if (tuplaNew instanceof Corso) {
			nuovoCorso=(Corso)tuplaNew;
		}else {
			throw new DaoException("errore l'oggetto: "+tuplaNew+" non è un oggetto corso");
		}

		String sql="UPDATE corso " 
				+"SET nome_corso=?,data_inizio=?,data_fine=?,Descr=?,id_prof=? "
				+"WHERE id_corso=? ";
		String sql2="UPDATE corso " 
				+"SET nome_corso=?,data_inizio=?,data_fine=?,Descr=? "
				+"WHERE id_corso=? ";
		//aggiornamento (con tutti i getter di tuplaNew, NB: nuovoCorso.getIdxxx è in WHERE)
		try  {if(nuovoCorso.getId_prof()==0) {
				PreparedStatement pst = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, nuovoCorso.getNome_corso());
				pst.setDate(2, nuovoCorso.getData_inizio());
				pst.setDate(3, nuovoCorso.getData_fine());
				pst.setString(4, nuovoCorso.getDescr());
				pst.setInt(5, nuovoCorso.getId_corso());//placeholder della WHERE
				//eseguo lo statement così preparato:
				pst.executeUpdate();

			}else {
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, nuovoCorso.getNome_corso());
				pst.setDate(2, nuovoCorso.getData_inizio());
				pst.setDate(3, nuovoCorso.getData_fine());
				pst.setString(4, nuovoCorso.getDescr());
				pst.setInt(5, nuovoCorso.getId_prof());
				pst.setString(6, nuovoCorso.getNome_corso());//placeholder della WHERE
				//eseguo lo statement così preparato:
				pst.executeUpdate();
			}

		} catch (SQLException e) {
			throw new DaoException("ERRORE UPDATE x pk="+nuovoCorso.getId_corso()+". Causa: "+e.getMessage());

		}

	}

	@Override
	public void delete(Object corso) throws DaoException {
		Corso delCorso=null;
		if (corso instanceof Corso) {
			delCorso=(Corso)corso;
			//prima cosa da fare:reperire la tupla della pk in argomento
			int tupla = delCorso.getId_corso();
			//ora DELETE la tupla dal DB
			String sql="DELETE FROM corso WHERE id_corso=? ";

			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, tupla);
				//eseguo lo statement così preparato:
				pst.executeUpdate();



			} catch (SQLException e) {
				throw new DaoException("ERRORE DELETE x object= "+corso+". Causa: "+e.getMessage());

			}

		}
	}

	

}
