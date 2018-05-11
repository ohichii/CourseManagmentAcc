package controller;

public class UserCM {

	//attributi
	private int idUtente;
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;
	private int tipoUtente;
	
	private char sesso;

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTipoUtente() {
		return tipoUtente;
	}

	public void setTipoUtente(int tipoUtente) {
		this.tipoUtente = tipoUtente;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	@Override
	public String toString() {
		return "UserCM [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", tipoUtente=" + tipoUtente + ", sesso=" + sesso
				+ "]";
	}
}
	