package entity;

import java.sql.Date;

public class StudenteEsame {

	private int id_es;
	private int id_studente;
	private int id_esame;
	private int voto;
	private Date data;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getId_es() {
		return id_es;
	}

	/**
	 * @param id_es
	 *            the id_es to set
	 */
	public void setId_es(int id_es) {
		this.id_es = id_es;
	}

	/**
	 * @return the id_studente
	 */
	public int getId_studente() {
		return id_studente;
	}

	/**
	 * @param id_studente
	 *            the id_studente to set
	 */
	public void setId_studente(int id_studente) {
		this.id_studente = id_studente;
	}

	/**
	 * @return the id_esame
	 */
	public int getId_esame() {
		return id_esame;
	}

	/**
	 * @param id_esame
	 *            the id_esame to set
	 */
	public void setId_esame(int id_esame) {
		this.id_esame = id_esame;
	}

	/**
	 * @return the voto
	 */
	public int getVoto() {
		return voto;
	}

	/**
	 * @param voto
	 *            the voto to set
	 */
	public void setVoto(int voto) {
		this.voto = voto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudenteEsame [id_es=" + id_es + ", id_studente=" + id_studente + ", id_esame=" + id_esame + ", voto="
				+ voto + "]\n";
	}

}
