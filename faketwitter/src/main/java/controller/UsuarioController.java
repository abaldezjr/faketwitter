package controller;

import java.util.List;

import model.entity.Usuario;
import model.service.UsuarioService;
import view.UsuarioCRUD;

public class UsuarioController {

	private UsuarioService usuarioGerente;
	private Usuario logado = null;
	
	public UsuarioController() {
		usuarioGerente = new UsuarioService();
	}
	
	public Usuario getLogado() {
		return logado;
	}
	
	public boolean ehAdmin() {
		return "admin@gmail.com".equals(this.logado.getEmail());
	}
	
	public boolean validaLogin(String email, String senha) {
		if (!email.isEmpty() && !senha.isEmpty()) {
			Usuario usuario = this.usuarioGerente.filtrarPorEqualsEmail(email);
			if (usuario.getEmail().equals(email)
					&& usuario.getSenha().equals(senha)) {
				logado = usuario;
				return true;
			}
		}
		return false;
	}
	
	public boolean adicionar(Usuario usuario) {
		if (usuarioGerente.adicionar(usuario)) {
			return true;
		}
		return false;
	}

	public boolean atualizar(Usuario usuario) {
		if (usuarioGerente.atualizar(usuario)) {
			return true;
		}
		return false;
	}

	public boolean remover(Usuario usuario) {
		if (usuarioGerente.remover(usuario)) {
			return true;
		}
		return false;
	}

	public List<Usuario> listarTodos() {
		return usuarioGerente.listarTodos();
	}

	public Usuario filtrarUsuarioPorId(long id) {
		return this.usuarioGerente.filtrarPorId(id);
	}

	public List<Usuario> filtrarPorLikeEmail(String email) {
		return this.usuarioGerente.filtrarPorLikeEmail(email);
	}

}
