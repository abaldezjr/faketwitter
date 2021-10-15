package converter;

import java.util.List;
import java.util.stream.Collectors;

import dto.UsuarioDto;
import model.entity.Usuario;

public class UsuarioConverter {
	
	public UsuarioDto entityToDto(Usuario usuario) {
		return new UsuarioDto(usuario.getId(),usuario.getEmail(),usuario.getSenha(),usuario.getNome());
	}
	
	public List<UsuarioDto> entityToDto(List<Usuario> usuarios){
		return usuarios.stream().map(u -> entityToDto(u)).collect(Collectors.toList());
	}
	
	public Usuario dtoToEntity(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario(usuarioDto.getId(),usuarioDto.getEmail(),usuarioDto.getSenha(),usuarioDto.getNome());
		return usuario;
	}
	
	public List<Usuario> dtoToEntity(List<UsuarioDto> usuariosDto){
		return usuariosDto.stream().map(u -> dtoToEntity(u)).collect(Collectors.toList());
	}

}
