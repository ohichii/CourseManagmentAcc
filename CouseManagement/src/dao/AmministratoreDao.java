package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Admin;

public class AmministratoreDao implements Dao{

	//il tipo utente ha valore =1 per gli amministratori


	private final String SCHEMA_TUPLA="id_utente,nome,cognome,username,e_mail,password,tipo_utente ";
	ConnessioneSingleton is= ConnessioneSingleton.getIstanza();
	Connection con=is.getCon();
	private ResultSet res;
	public AmministratoreDao() throws DaoException {
		super();
	}

	private Admin creaEntity() throws DaoException {
		Admin entity = new Admin();
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
	public void add(Object admin) throws DaoException {
		String sql = "INSERT INTO user (nome,cognome,username,e_mail,password,tipo_utente) VALUES(?,?,?,?,?,?)";
		Admin nuovoAdmin=null;
		if (admin instanceof Admin) {
			nuovoAdmin=(Admin)admin;
		}else {
			throw new DaoException("ERRORE l'oggetto: "+admin+" non � un oggetto insegnante");
		}
		try (PreparedStatement pst = con.prepareStatement(sql)) {
			String var = nuovoAdmin.getUsername();//controlla che l'id usato non sia gi� in database
			if(this.getBy(var).isEmpty()) {				

				//sostituisco i placeHolder (?)
				//			pst.setInt(1, user.getIdUtente()); 
				pst.setString(1, nuovoAdmin.getNome()); 
				pst.setString(2, nuovoAdmin.getCognome());
				pst.setString(3, nuovoAdmin.getUsername());			
				pst.setString(4, nuovoAdmin.getEmail());
				pst.setString(5, nuovoAdmin.getPassword());
				pst.setInt(6, 1);
				pst.executeUpdate(); //esegue la query preparata!
			}else throw new DaoException("l'id amministratore: "+var+" che si sta utilizzando � gi� presente");
		} catch (SQLException e) {
			throw new DaoException("ERRORE insert. Causa: "+e.getMessage());
		} 

	}

	@Override
	public ArrayList<Admin> getAll() throws DaoException {
		ArrayList<Admin> lista = new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM user "	
				+"WHERE tipo_utente=1 "
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

		Admin lista = new Admin();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM user "
				+ "WHERE id_utente=? AND tipo_utente=1 ";				

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


	public Admin getOneBy(String nome,String cognome) throws DaoException {
		Admin cor = null;
		String sql="SELECT "+SCHEMA_TUPLA
				+ " FROM user "
				+"WHERE tipo_utente=1 AND nome=? AND cognome=?";


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
	public ArrayList<Admin> getBy(String str1) throws DaoException {
		ArrayList<Admin> lista= new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA
				+ " FROM user "
				+"WHERE tipo_utente=1 AND cognome like ? OR nome like ? OR username like ? OR e_mail like ? ";


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
		Admin nuovoAdmin=null;
		if (tuplaNew instanceof Admin) {
			nuovoAdmin=(Admin)tuplaNew;
		}else {
			throw new DaoException("errore l'oggetto: "+tuplaNew+" non � un oggetto amministratore");
		}

		String sql="UPDATE user " 
				+"SET nome=?,cognome=?,username=?,e_mail=?,password=? "
				+"WHERE id_utente=? AND tipo_utente=1 ";


		try (PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {//a cosa serve?
			pst.setString(1,nuovoAdmin.getNome()) ;	
			pst.setString(2, nuovoAdmin.getCognome());
			pst.setString(3, nuovoAdmin.getUsername());
			pst.setString(4, nuovoAdmin.getEmail());
			pst.setString(5, nuovoAdmin.getPassword());
			pst.setInt(6,nuovoAdmin.getId()) ;//PK in WHERE

			//eseguo lo statement cos� preparato:
			pst.executeUpdate();



		} catch (SQLException e) {
			throw new DaoException("ERRORE UPDATE x pk="+nuovoAdmin.getId()+". Causa: "+e.getMessage());

		}

	}

	@Override
	public void delete(Object admin) throws DaoException {
		Admin delAdmin=null;
		if (admin instanceof Admin) {
			delAdmin=(Admin)admin;
			//prima cosa da fare:reperire la tupla della pk in argomento
			int tupla = delAdmin.getId();
			//ora DELETE la tupla dal DB
			String sql="DELETE FROM user WHERE tipo_utente=1 AND id_utente=? ";

			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, tupla);
				//eseguo lo statement cos� preparato:
				pst.executeUpdate();



			} catch (SQLException e) {
				throw new DaoException("ERRORE DELETE x object= "+admin+". Causa: "+e.getMessage());

			}

		}
	}







}
