package model.entity;

public class Usuario {
	
	private long id;
	private String email;
	private String senha;
	private String nome;
	
	public Usuario() {
		
	}
	
	public Usuario(String email, String senha, String nome) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}

	public Usuario(long id, String email, String senha, String nome) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", nome=" + nome + "]";
	}
	

}
