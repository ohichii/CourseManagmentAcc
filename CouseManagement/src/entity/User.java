package entity;

public class User {

	//attributi
	private String nome;
	private String cognome;
	private String mail;
	private String password;
	private char sesso;
	
	//costruttore
	public User(){
		
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [nome=" + nome + ", cognome=" + cognome + ", mail=" + mail + ", password=" + password + "]";
	}
	
	
	//metodi
}
