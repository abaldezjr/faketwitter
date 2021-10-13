package model.service;

import java.util.List;

import model.dao.PostagemDao;
import model.entity.Postagem;
import model.entity.Usuario;

public class PostagemService {

	private PostagemDao postagemDao;
	
	public PostagemService() {
		postagemDao = new PostagemDao();
	}

	public boolean adicionar(Postagem postagem) {
		return this.postagemDao.adicionar(postagem);
	}

	public boolean atualizar(Postagem postagem) {
		return this.postagemDao.atualizar(postagem);
	}

	public boolean remover(Postagem postagem) {
		return this.postagemDao.remover(postagem);
	}

	public List<Postagem> listarTodos() {
		return this.postagemDao.listarTodos();
	}

	public Postagem filtrarPorId(long id) {
		return this.postagemDao.filtrarPorId(id);
	}

	public List<Postagem> filtrarPostagemPorUsuario(Usuario usuario) {
		return this.postagemDao.filtrarPostagemPorUsuario(usuario);
	}

	public List<Postagem> filtrarPostagemPorLikeTitulo(String titulo) {
		return this.postagemDao.filtrarPostagemPorLikeTitulo(titulo);
	}

	public List<Postagem> filtrarPostagemPorLikeUsuarioNome(String nome) {
		return this.postagemDao.filtrarPostagemPorLikeUsuarioNome(nome);
	}
	
}
