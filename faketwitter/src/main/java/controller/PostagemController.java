package controller;

import java.util.List;

import model.entity.Postagem;
import model.entity.Usuario;
import model.service.PostagemService;
import model.service.UsuarioService;
import view.PostagemCRUD;

public class PostagemController {

	private PostagemService postagemGerente;
	
	public PostagemController() {
		postagemGerente = new PostagemService();
	}
	
	public boolean adicionar(Postagem postagem) {
		if (postagemGerente.adicionar(postagem)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean atualizar(Postagem postagem) {
		if (this.postagemGerente.atualizar(postagem)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean remover(Postagem postagem) {
		if (this.postagemGerente.remover(postagem)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Postagem> listarTodos() {
		return this.postagemGerente.listarTodos();
	}
	
	public List<Postagem> filtrarPostagemPorUsuario(Usuario usuario) {
		return this.postagemGerente.filtrarPostagemPorUsuario(usuario);
	}
	
//	public List<Postagem> filtrarPostagemPorLikeTitulo(String titulo) {
//		 return this.postagemGerente.filtrarPostagemPorLikeTitulo(titulo);
//	}
//	
	public List<Postagem> filtrarPostagemPorLikeUsuarioNome(String titulo) {
		 return this.postagemGerente.filtrarPostagemPorLikeUsuarioNome(titulo);
	}

}
