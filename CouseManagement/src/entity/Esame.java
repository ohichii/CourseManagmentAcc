package entity;

import java.sql.Date;

public class Esame {

	
	
	private int id_esame;
	private String tipo;
	private Date date1;
	private Date date2;
	private Date date3;
	private int fk_id_corso;
	
	
	
	
	/**
	 * @return the id_corso
	 */




	public Esame() {
		super();
		// TODO Auto-generated constructor stub
	}




	/**
	 * @return the fk_id_corso
	 */
	public int getFk_id_corso() {
		return fk_id_corso;
	}




	/**
	 * @param fk_id_corso the fk_id_corso to set
	 */
	public void setFk_id_corso(int fk_id_corso) {
		this.fk_id_corso = fk_id_corso;
	}




	/**
	 * @return the id_esame
	 */
	public int getId_esame() {
		return id_esame;
	}




	/**
	 * @param id_esame the id_esame to set
	 */
	public void setId_esame(int id_esame) {
		this.id_esame = id_esame;
	}




	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}




	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}




	/**
	 * @return the date1
	 */
	public Date getDate1() {
		return date1;
	}




	/**
	 * @param date1 the date1 to set
	 */
	public void setDate1(Date date1) {
		this.date1 = date1;
	}




	/**
	 * @return the date2
	 */
	public Date getDate2() {
		return date2;
	}




	/**
	 * @param date2 the date2 to set
	 */
	public void setDate2(Date date2) {
		this.date2 = date2;
	}




	/**
	 * @return the date3
	 */
	public Date getDate3() {
		return date3;
	}




	/**
	 * @param date3 the date3 to set
	 */
	public void setDate3(Date date3) {
		this.date3 = date3;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Esame : [id_esame=" + id_esame + ", tipo=" + tipo + ", date1=" + date1 + ", date2=" + date2 + ", date3="
				+ date3 + ", fk_id_corso=" + fk_id_corso + "]\n";
	}
	
}
