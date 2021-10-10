package model.entity;

import java.util.Date;

public class Postagem {
	
	private Long id;
    private Usuario criador;
    private String titulo;
    private String mensagem;
    private Date criacao;
    
    public Postagem() {
        criacao = new Date();
    }
    
    public Postagem(Long id, Usuario criador, String titulo, String mensagem, Date criacao) {
        this.id = id;
        this.criador = criador;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.criacao = criacao;
    }

    public Postagem(Usuario criador, String titulo, String mensagem) {
        this.criador = criador;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public Postagem(Long id, Usuario criador, String titulo, String mensagem) {
        this(criador,titulo,mensagem);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
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

	@Override
	public String toString() {
		return "Postagem [id=" + id + ", criador=" + criador + ", titulo=" + titulo + ", mensagem=" + mensagem
				+ ", criacao=" + criacao + "]";
	}
    
}
