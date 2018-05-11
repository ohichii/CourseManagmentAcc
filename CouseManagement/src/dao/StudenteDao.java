package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Studente;

public class StudenteDao implements Dao {
	
//il tipo utente ha valore =3 per gli studenti

	private final String SCHEMA_TUPLA="id_utente,nome,cognome,username,e_mail,password,tipo_utente ";
	ConnessioneSingleton is= ConnessioneSingleton.getIstanza();
	Connection con=is.getCon();
	ResultSet res;
	public StudenteDao() throws DaoException {
		super();
	}
	
	public Studente creaEntity() throws DaoException {
		Studente entity = new Studente();
		try {
			entity.setId(res.getInt(1));		
			entity.setNome(res.getString(2));
			entity.setCognome(res.getString(3));
			entity.setUsername(res.getString(4));
			entity.setEmail(res.getString(5));
			entity.setPassword(res.getString(6));
			entity.setTipo_utente(res.getInt(7));
			return entity;
		}catch(SQLException e) {
			throw new DaoException("errore in creaEntity. causa.."+e.getMessage());

		}
	}
	
	
	@Override
	public void add(Object studente) throws DaoException {
		String sql = "INSERT INTO user (nome,cognome,username,e_mail,password,tipo_utente) VALUES(?,?,?,?,?,?)";
		Studente nuovoStudente=null;
		if (studente instanceof Studente) {
			nuovoStudente=(Studente)studente;
		}else {
			throw new DaoException("ERRORE l'oggetto: "+studente+" non è un oggetto studente");
		}
		try (PreparedStatement pst = con.prepareStatement(sql)) {
			 String var = nuovoStudente.getUsername();//controlla che l'id usato non sia già in database
			if(this.getBy(var).isEmpty()) {			//se metti id (ai) non serve
				
			
			//sostituisco i placeHolder (?)
			//			pst.setInt(1, user.getIdUtente()); 
			
			pst.setString(1, nuovoStudente.getNome()); 
			pst.setString(2, nuovoStudente.getCognome());
			pst.setString(3, nuovoStudente.getUsername());
			pst.setString(4, nuovoStudente.getEmail());
			pst.setString(5, nuovoStudente.getPassword());
			pst.setInt(6, 3);//dovrebbe essere sempre 3
			pst.executeUpdate(); //esegue la query preparata!
			}else throw new DaoException("l'id studente: "+var+" che si sta utilizzando è già presente");
		} catch (SQLException e) {
			throw new DaoException("ERRORE insert. Causa: "+e.getMessage());
		} 

	}

	@Override
	public ArrayList<Studente> getAll() throws DaoException {
		ArrayList<Studente> lista = new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM user "	
				+"WHERE tipo_utente=3"
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

		Studente lista = new Studente();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM user "
				+ "WHERE id_utente=? AND tipo_utente=3";				

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


	public Studente getOneBy(String nome,String cognome) throws DaoException {
		Studente cor = null;
		String sql="SELECT "+SCHEMA_TUPLA
				+ " FROM user "
				+"WHERE tipo_utente=3 AND nome=? AND cognome=?";


		try (PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nome); //sostituisce il placeHolder (?)
			pst.setString(2, cognome);
			res = pst.executeQuery(); //esegue la query preparata!
			if (res.next()) {
				cor=creaEntity();
			}
			else
				return null;

		} catch (SQLException e) {
			throw new DaoException
			("ERRORE selectBy x stringa="+nome+ " e "+cognome+". Causa: "+e.getMessage());
		}
		return cor; 



	}
	@Override
	public ArrayList<Studente> getBy(String str1) throws DaoException {
		
		ArrayList<Studente> lista= new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA
				+ " FROM user "
				+"WHERE tipo_utente=3 AND nome like ? OR cognome like ? OR username like ? OR e_mail like ? ";

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
		return lista;

	}

	@Override
	public void update(Object tuplaNew) throws DaoException {
		Studente nuovoStudente=null;
		if (tuplaNew instanceof Studente) {
			nuovoStudente=(Studente)tuplaNew;
		}else {
			throw new DaoException("errore l'oggetto: "+tuplaNew+" non è un oggetto studente");
		}

		String sql="UPDATE user " 
				+"SET nome=?,cognome=?,username=?,e_mail=?,password=? "
				+"WHERE tipo_studente=3 AND id_utente=? ";
		

		try (PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {//a cosa serve?
			
			pst.setString(1,nuovoStudente.getNome()) ;	
			pst.setString(2, nuovoStudente.getCognome());
			pst.setString(3, nuovoStudente.getUsername());
			pst.setString(4, nuovoStudente.getEmail());
			pst.setString(5, nuovoStudente.getPassword());
			pst.setInt(6,nuovoStudente.getId()) ;//PK in WHERE

			//eseguo lo statement così preparato:
			pst.executeUpdate();



		} catch (SQLException e) {
			throw new DaoException("ERRORE UPDATE x pk="+nuovoStudente.getId()+". Causa: "+e.getMessage());

		}

	}

	@Override
	public void delete(Object studente) throws DaoException {
		Studente delStudente=null;
		if (studente instanceof Studente) {
			delStudente=(Studente)studente;
			//prima cosa da fare:reperire la tupla della pk in argomento
			int tupla = delStudente.getId();
			//ora DELETE la tupla dal DB
			String sql="DELETE FROM user WHERE tipo_studente=3 AND id_utente=? ";

			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, tupla);
				//eseguo lo statement così preparato:
				pst.executeUpdate();



			} catch (SQLException e) {
				throw new DaoException("ERRORE DELETE x object= "+studente+". Causa: "+e.getMessage());

			}

		}
	}

	



}
