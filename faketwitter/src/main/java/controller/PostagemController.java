package controller;

import java.util.List;

import dto.PostagemDto;
import dto.UsuarioDto;
import model.service.PostagemService;

public class PostagemController {

	private PostagemService postagemGerente;
	
	public PostagemController() {
		postagemGerente = new PostagemService();
	}
	
	public boolean adicionar(PostagemDto postagem) {
		if (postagemGerente.adicionar(postagem)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean atualizar(PostagemDto postagemDto) {
		if (this.postagemGerente.atualizar(postagemDto)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean remover(PostagemDto postagemDto) {
		if (this.postagemGerente.remover(postagemDto)) {
			return true;
		} else {
			return false;
		}
	}

	public List<PostagemDto> listarTodos() {
		return this.postagemGerente.listarTodos();
	}
	
	public List<PostagemDto> filtrarPostagemPorUsuario(UsuarioDto usuarioDto) {
		return this.postagemGerente.filtrarPostagemPorUsuario(usuarioDto);
	}
	
//	public List<Postagem> filtrarPostagemPorLikeTitulo(String titulo) {
//		 return this.postagemGerente.filtrarPostagemPorLikeTitulo(titulo);
//	}
//	
	public List<PostagemDto> filtrarPostagemPorLikeUsuarioNome(String titulo) {
		 return this.postagemGerente.filtrarPostagemPorLikeUsuarioNome(titulo);
	}

}
