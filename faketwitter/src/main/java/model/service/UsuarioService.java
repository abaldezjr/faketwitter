package model.service;

import java.util.List;

import model.dao.UsuarioDao;
import model.entity.Usuario;

public class UsuarioService {
	
	private UsuarioDao usuarioDao;
		
    public UsuarioService() {
        usuarioDao = new UsuarioDao();    
    }
	
	public boolean adicionar(Usuario usuario) {
        return this.usuarioDao.adicionar(usuario);
    }

    public boolean atualizar(Usuario usuario) {
        return this.usuarioDao.atualizar(usuario);
    }

    public boolean remover(Usuario usuario) {
        return this.usuarioDao.remover(usuario);
    }

    public List<Usuario> listarTodos() {
       return this.usuarioDao.listarTodos();
    }

    public Usuario filtrarPorId(long id) {
        return this.usuarioDao.filtrarPorId(id);
    }
	
	public List<Usuario> filtrarPorLikeEmail(String email){
        return this.usuarioDao.filtrarPorLikeEmail(email);
    }
    
    public Usuario filtrarPorEqualsEmail(String email){
        return this.usuarioDao.filtrarPorEqualsEmail(email);
    }

}
