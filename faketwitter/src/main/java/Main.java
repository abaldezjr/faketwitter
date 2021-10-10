import java.util.List;

import model.dao.UsuarioDao;
import model.entity.Usuario;

public class Main {

	public static void adicionar(){
        Usuario usuario = new Usuario("fer@gmail.com","1234","fer");
        UsuarioDao.save(usuario);
    }
    
    public static void remover(){
        Usuario usuario = new Usuario();
        usuario.setId(8L);
        UsuarioDao.delete(usuario);
    }
    
    public static void atualizar(){
        Usuario usuario = new Usuario(2L,"Fefe","12345","fefe@hotmail.com");
        UsuarioDao.update(usuario);
    }
    
    public static void listarUsuarios(){
        List<Usuario> usuarios = UsuarioDao.selectAll();
        for(int i=0;i<usuarios.size();i++){
            System.out.println("usuario:"+usuarios.get(i).getEmail());
        }
    }
    
    public static void listarUsuariosToString(){
        List<Usuario> usuarios = UsuarioDao.selectAll();
        System.out.println(usuarios);
    }
    
    public static void listarUsuariosPorNomeToString(String login){
        List<Usuario> usuarios = UsuarioDao.searchToLogin(login);
        System.out.println(usuarios);
    }

    public static void main(String[] args) {
    	listarUsuarios();
        UsuarioDao.saveTransaction();
    }
}
