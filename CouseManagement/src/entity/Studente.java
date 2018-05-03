package entity;

import java.util.Date;

public class Studente extends User {
//attributi
	private Date datanas;
	private String città;
	private String codFiscale;//utile nel caso di rilascio di un esame boh
	private int numTel;
	private String titoloStudi;
	public Date getDatanas() {
		return datanas;
	}
	public void setDatanas(Date datanas) {
		this.datanas = datanas;
	}
	public String getCittà() {
		return città;
	}
	public void setCittà(String città) {
		this.città = città;
	}
	public String getCodFiscale() {
		return codFiscale;
	}
	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}
	public int getNumTel() {
		return numTel;
	}
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}
	public String getTitoloStudi() {
		return titoloStudi;
	}
	public void setTitoloStudi(String titoloStudi) {
		this.titoloStudi = titoloStudi;
	}
	
	
	//costruttore
	
	
	
}
