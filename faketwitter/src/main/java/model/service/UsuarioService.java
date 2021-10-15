package model.service;

import java.util.List;

import converter.UsuarioConverter;
import dto.UsuarioDto;
import model.dao.UsuarioDao;
import model.entity.Usuario;

public class UsuarioService {
	
	private UsuarioDao usuarioDao;
	private UsuarioConverter usuarioConverter;
		
    public UsuarioService() {
        usuarioDao = new UsuarioDao();  
        usuarioConverter = new UsuarioConverter();
    }
    
	public UsuarioDto validaLogin(UsuarioDto usuarioDto) {
		if (!usuarioDto.getEmail().isEmpty() && !usuarioDto.getSenha().isEmpty()) {
			Usuario usuario = this.usuarioDao.filtrarPorEqualsEmail(usuarioDto.getEmail());
			if (usuario.getEmail().equals(usuarioDto.getEmail())
					&& usuario.getSenha().equals(usuarioDto.getSenha())) {
				return usuarioConverter.entityToDto(usuario);
			}
		}
		return null;
	}
	
	public boolean adicionar(UsuarioDto usuarioDto) {
		return this.usuarioDao.adicionar(this.usuarioConverter.dtoToEntity(usuarioDto));
    }

    public boolean atualizar(UsuarioDto usuarioDto) {
        return this.usuarioDao.atualizar(this.usuarioConverter.dtoToEntity(usuarioDto));
    }

    public boolean remover(UsuarioDto usuarioDto) {
        return this.usuarioDao.remover(this.usuarioConverter.dtoToEntity(usuarioDto));
    }

    public List<UsuarioDto> listarTodos() {
       return this.usuarioConverter.entityToDto(this.usuarioDao.listarTodos());
    }

    public UsuarioDto filtrarPorId(Long id) {
        return this.usuarioConverter.entityToDto(this.usuarioDao.filtrarPorId(id));
    }
	
	public List<UsuarioDto> filtrarPorLikeEmail(String email){
        return this.usuarioConverter.entityToDto(this.usuarioDao.filtrarPorLikeEmail(email));
    }
    
    public UsuarioDto filtrarPorEqualsEmail(String email){
        return this.usuarioConverter.entityToDto(this.usuarioDao.filtrarPorEqualsEmail(email));
    }

}
