package dto;

import java.util.Date;

import model.entity.Usuario;

public class PostagemDto {
	
	private Long id;
    private UsuarioDto criador;
    private String titulo;
    private String mensagem;
    private Date criacao;
    
    public PostagemDto() {
    	criacao = new Date();
    }
    
	public PostagemDto(Long id, UsuarioDto criador, String titulo, String mensagem, Date criacao) {
		super();
		this.id = id;
		this.criador = criador;
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.criacao = criacao;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public UsuarioDto getCriador() {
		return criador;
	}
	
	public void setCriador(UsuarioDto criador) {
		this.criador = criador;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Date getCriacao() {
		return criacao;
	}
	
	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}
    
}
