package entity;

import java.util.Date;

public class UserCM {

	//attributi
	private int id_utente;
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	private int tipo_utente;
	private String sesso;
	private Date datanas;
	private String città;
	private String codFiscale;//utile nel caso di rilascio di un esame boh
	private int numTel;
	private String titoloStudi;
	
	
	//costruttore
		public UserCM(){
			
		}
	
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
	
	public int getId() {
		return id_utente;
	}

	public void setId(int id_utente) {
		this.id_utente = id_utente;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public int getTipo_utente() {
		return tipo_utente;
	}

	public void setTipo_utente(int tipo_utente) {
		this.tipo_utente = tipo_utente;
	}

	@Override
	public String toString() {
		return "User [id_utente=" + id_utente + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", username="
				+ username + ", password=" + password + ", tipo_utente=" + tipo_utente + "]\n";
	}

	
	
	//metodi
}
