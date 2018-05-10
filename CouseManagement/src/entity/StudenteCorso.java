package entity;

public class StudenteCorso {
private int id_sc;
private int id_corso;
private int id_studente;
private int valutazione;
private String commento;
public int getId_sc() {
	return id_sc;
}
public void setId_sc(int id_sc) {
	this.id_sc = id_sc;
}
public int getId_corso() {
	return id_corso;
}
public void setId_corso(int id_corso) {
	this.id_corso = id_corso;
}
public int getId_studente() {
	return id_studente;
}
public void setId_studente(int id_studente) {
	this.id_studente = id_studente;
}
public int getValutazione() {
	return valutazione;
}
public void setValutazione(int valutazione) {
	this.valutazione = valutazione;
}
public String getCommento() {
	return commento;
}
public void setCommento(String commento) {
	this.commento = commento;
}
@Override
public String toString() {
	return "StudenteCorso [id_sc=" + id_sc + ", id_corso=" + id_corso + ", id_studente=" + id_studente
			+ ", valutazione=" + valutazione + ", commento=" + commento + "]\n";
}


}
