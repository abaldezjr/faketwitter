package model.service;

import java.util.List;

import converter.PostagemConverter;
import converter.UsuarioConverter;
import dto.PostagemDto;
import dto.UsuarioDto;
import model.dao.PostagemDao;
import model.entity.Postagem;
import model.entity.Usuario;

public class PostagemService {

	private PostagemDao postagemDao;
	private UsuarioConverter usuarioConverter;
	private PostagemConverter postagemConverter;
	
	public PostagemService() {
		postagemDao = new PostagemDao();
		usuarioConverter = new UsuarioConverter();
		postagemConverter = new PostagemConverter();
	}

	public boolean adicionar(PostagemDto postagemDto) {
		return this.postagemDao.adicionar(postagemConverter.dtoToEntity(postagemDto));
	}

	public boolean atualizar(PostagemDto postagemDto) {
		return this.postagemDao.atualizar(postagemConverter.dtoToEntity(postagemDto));
	}

	public boolean remover(PostagemDto postagemDto) {
		return this.postagemDao.remover(postagemConverter.dtoToEntity(postagemDto));
	}

	public List<PostagemDto> listarTodos() {
		return postagemConverter.entityToDto(this.postagemDao.listarTodos());
	}

	public PostagemDto filtrarPorId(long id) {
		return postagemConverter.entityToDto(this.postagemDao.filtrarPorId(id));
	}

	public List<PostagemDto> filtrarPostagemPorUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = usuarioConverter.dtoToEntity(usuarioDto);
		List<Postagem> postagens = this.postagemDao.filtrarPostagemPorUsuario(usuario);
		return postagemConverter.entityToDto(postagens);
	}

	public List<PostagemDto> filtrarPostagemPorLikeTitulo(String titulo) {
		return postagemConverter.entityToDto(this.postagemDao.filtrarPostagemPorLikeTitulo(titulo));
	}

	public List<PostagemDto> filtrarPostagemPorLikeUsuarioNome(String nome) {
		return postagemConverter.entityToDto(this.postagemDao.filtrarPostagemPorLikeUsuarioNome(nome));
	}
	
}
