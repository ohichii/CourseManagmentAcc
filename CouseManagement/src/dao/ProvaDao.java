package dao;

import java.util.ArrayList;

import entity.Corso;
import entity.Insegnante;
import entity.Studente;
import entity.StudenteCorso;
import entity.User;

public class ProvaDao {

	public static void main(String[] args) {
		
		
		try {
			FunzionalitaDao dao = new FunzionalitaDao();
			StudenteCorso st= new StudenteCorso();
			StudenteCorsoDao dao1= new StudenteCorsoDao();
			StudenteDao dao2= new StudenteDao();
			CorsoDao dao3= new CorsoDao();
			
			System.out.println(dao1.getAll());
			
			System.out.println(dao.visualizzaIscrittiCorso(1));
			
//			
//			dao.valutazioneCorso(4, var.get(0), var1.get(0));//gli passo 5 e 3
//			
//			System.out.println(dao1.getById(3));
			
//			Object var = dao.getById(20);
//			dao.delete(var);
//			System.out.println(dao.getAll());
//			System.out.println("prova getbY stringa..."+dao.getBy("alessio"));
//			
////			System.out.println(dao.getOneBy("carta",""));
//			dao.add(nuovo);
//			System.out.println(dao.getBy("gianni"));
//			Insegnante agg=new Insegnante();
//			
//			agg.setNome("gianni2");
//			agg.setUsername("gianniBeddu");
//			agg.setPassword("4321");
//			agg.setTipo_utente(2);
//			ArrayList<Insegnante> var = dao.getBy("gianni");
//			int num = var.get(0).getId();
//			agg.setId(num);
//			dao.update(agg);
//			System.out.println(dao.getAll());
//			Object canc = dao.getById(num);
//			dao.delete(canc);
//			System.out.println(dao.getAll());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
