package dto;

public class UsuarioDto {
	
	private Long id;
	private String email;
	private String senha;
	private String nome;
	
	public UsuarioDto() {
		
	}
	
	public UsuarioDto(Long id, String email, String senha, String nome) {
		super();
		this.setId(id);
		this.setEmail(email);
		this.setSenha(senha);
		this.setNome(nome);
	}
	
	public UsuarioDto(String email, String senha, String nome) {
		super();
		this.setEmail(email);
		this.setSenha(senha);
		this.setNome(nome);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return "UsuarioDto [id=" + id + ", email=" + email + ", nome=" + nome + "]";
	}
		
}
