package controller;

import java.util.List;

import dto.UsuarioDto;
import model.service.UsuarioService;

public class UsuarioController {

	private UsuarioService usuarioService;
	private UsuarioDto logado = null;
	
	public UsuarioController() {
		usuarioService = new UsuarioService();
	}
	
	public UsuarioDto getLogado() {
		return logado;
	}
	
	public boolean ehAdmin() {
		return "admin@gmail.com".equals(this.logado.getEmail());
	}
	
	public boolean validaLogin(UsuarioDto usuarioDto) {
		UsuarioDto uDto = this.usuarioService.validaLogin(usuarioDto);
		if(uDto != null) {
			logado = uDto;
			return true;
		}
		return false;	
	}
	
	public boolean adicionar(UsuarioDto usuarioDto) {
		if (this.usuarioService.adicionar(usuarioDto)) {
			return true;
		}
		return false;
	}

	public boolean atualizar(UsuarioDto usuarioDto) {
		if (this.usuarioService.atualizar(usuarioDto)) {
			return true;
		}
		return false;
	}

	public boolean remover(UsuarioDto usuarioDto) {
		if (this.usuarioService.remover(usuarioDto)) {
			return true;
		}
		return false;
	}

	public List<UsuarioDto> listarTodos() {
		return this.usuarioService.listarTodos();
	}

	public UsuarioDto filtrarUsuarioPorId(Long id) {
		return this.usuarioService.filtrarPorId(id);
	}

	public List<UsuarioDto> filtrarPorLikeEmail(String email) {
		return this.usuarioService.filtrarPorLikeEmail(email);
	}

}
