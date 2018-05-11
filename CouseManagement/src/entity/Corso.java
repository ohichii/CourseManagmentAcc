package entity;

import java.sql.Date;

public class Corso {
private int id_corso;
private String nome_corso;
private Date data_inizio;
private Date data_fine;
private String Descr;
private int id_prof;


public int getId_prof() {
	return id_prof;
}
public void setId_prof(int id_prof) {
	this.id_prof = id_prof;
}
public int getId_corso() {
	return id_corso;
}
public void setId_corso(int id_corso) {
	this.id_corso = id_corso;
}

public String getNome_corso() {
	return nome_corso;
}
public void setNome_corso(String nome_corso) {
	this.nome_corso = nome_corso;
}
public Date getData_inizio() {
	return data_inizio;
}
public void setData_inizio(Date data_inizio) {
	this.data_inizio = data_inizio;
}
public Date getData_fine() {
	return data_fine;
}
public void setData_fine(Date data_fine) {
	this.data_fine = data_fine;
}
public String getDescr() {
	return Descr;
}
public void setDescr(String descr) {
	Descr = descr;
}
@Override
public String toString() {
	return "Corso [id_corso=" + id_corso + ", nome_corso=" + nome_corso + ", data_inizio=" + data_inizio
			+ ", data_fine=" + data_fine + ", Descr=" + Descr + ", id_prof=" + id_prof + "]\n";
}



}
