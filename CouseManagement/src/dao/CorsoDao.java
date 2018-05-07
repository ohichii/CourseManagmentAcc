package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import entity.Corso;
import entity.Studente;
import entity.User;

public class CorsoDao extends ConnessioneSingleton implements Dao {

	private final String SCHEMA_TUPLA="id_corso,id_esame,nome_corso,data_inizio,data_fine,Descr ";
	@Override
	public ArrayList<Corso> getAll() throws DaoException {
		ArrayList<Corso> lista = new ArrayList<>();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM user "				
				+ "ORDER BY 1";
		try(Statement stm= con.createStatement()){
			res=stm.executeQuery(sql);
			while(res.next()){
				lista.add(creaEntity());
			}
			if(lista.size()==0) {
				throw new DaoVuotaException("WARNING: tabella user vuota");
			}
				
			}catch(SQLException e) {
				throw new DaoException("ERRORE selectAll.Causa.."+e.getMessage());
			}
		return lista;
		
	}

	private Corso creaEntity() throws DaoException {
Corso entity = new Corso();
		try {
		entity.setId_corso(res.getInt(1));		
		entity.setId_esame(res.getInt(2));
		entity.setNome_corso(res.getString(3));
		entity.setData_inizio(res.getDate(4));
		entity.setData_fine(res.getDate(5));
		entity.setDescr(res.getString(6));
		return entity;
		}catch(SQLException e) {
			throw new DaoException("errore in creaEntity. causa.."+e.getMessage());
			
		}
	}

	@Override
	public Corso getById(int id) throws DaoException {
				
		Corso lista = new Corso();
		String sql="SELECT "+SCHEMA_TUPLA+ "FROM user "
				+ "WHERE id_corso=? "				
				+ "ORDER BY 1";
		try(Statement stm= con.createStatement()){
			res=stm.executeQuery(sql);
			if(res.next()) {
				lista=creaEntity();
			}else return null;
				
			}catch(SQLException e) {
				throw new DaoException("ERRORE selectby. Causa.."+e.getMessage());
			}
		return lista;
		
		
		
		
		
	}

	@Override
	public ArrayList<Corso> getBy(String s) {
		
		return null;
	}

	@Override
	public void update(Object bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Object bean) {
		// TODO Auto-generated method stub
		
	}
	

}
