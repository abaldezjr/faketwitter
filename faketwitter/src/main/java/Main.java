import java.util.List;
import java.util.Scanner;

import model.dao.UsuarioDao;
import model.entity.Usuario;

public class Main {
	
	private static Scanner teclado = new Scanner(System.in);
    
    private static void menu(){
        System.out.println("-----------------------------");
        System.out.println("         MENU USUARIO        ");
        System.out.println("-----------------------------");
        System.out.println("Digite a opção para começar:");
        System.out.println("1- Adicionar usuario");
        System.out.println("2- Atualizar usuario");
        System.out.println("3- Listar todos usuarios");
        System.out.println("4- Buscar usuario por email");
        System.out.println("5- Deletar");
        System.out.println("0- Sair");
        System.out.println("-----------------------------");
    }
    
    private static void executarMenu(int opcao){
        switch(opcao){
            case 1:adicionar();break;
            case 2:atualizar();break;
            case 3:listarUsuarios();break;
            case 4:listarUsuariosPorEmail();break;
            case 5:remover();break;
        }
    }
   
    public static void adicionar(){
        System.out.println("-----------------------------");
        System.out.println("      ADICIONAR USUARIO      ");
        System.out.println("-----------------------------");
        Usuario usuario = new Usuario();
        System.out.print("Email -> ");
        usuario.setEmail(teclado.nextLine());
        System.out.print("Senha -> ");
        usuario.setSenha(teclado.nextLine());
        System.out.print("Nome -> ");
        usuario.setNome(teclado.nextLine());
        UsuarioDao.save(usuario);
        System.out.println("Usuario adicionado com sucesso.");
    }
    
    public static void remover(){
        System.out.println("Selecione um dos usuarios abaixo para deletar:"); 
        List<Usuario> usuarios = listarUsuarios();
        int index = Integer.parseInt(teclado.nextLine());
        System.out.println("Tem certeza S/N?");
        String op = teclado.nextLine();
        if(op.startsWith("s")){
            UsuarioDao.delete(usuarios.get(index));
        }
    }
    
    public static void atualizar(){
        System.out.println("Selecione um dos usuarios abaixo parar atualizar:");
        List<Usuario> usuarios = listarUsuarios();
        Usuario usuario = usuarios.get(Integer.parseInt(teclado.nextLine()));
        System.out.println("-----------------------------");
        System.out.println("      ATUALIZAR USUARIO      ");
        System.out.println("(Deixe o campo vazio para    ");
        System.out.println("manter o antigo)              ");
        System.out.println("-----------------------------");
        System.out.print("Novo email -> ");
        String email = teclado.nextLine();
        if(!email.isEmpty())usuario.setEmail(email);
        System.out.print("Nova senha -> ");
        String senha = teclado.nextLine();
        if(!senha.isEmpty())usuario.setSenha(senha);
        System.out.print("Novo nome -> ");
        String nome = teclado.nextLine();
        if(!nome.isEmpty())usuario.setEmail(nome);
        UsuarioDao.update(usuario);
        System.out.println("Usuario atualizado com sucesso.");
    }
    
    public static List<Usuario> listarUsuarios(){
       List<Usuario> usuarios = UsuarioDao.selectAll();
       for(int i=0;i<usuarios.size();i++){
           Usuario u = usuarios.get(i);
           System.out.println(i+" -> (EMAIL: "+u.getEmail()+" SENHA: "+u.getSenha()+" NOME: "+u.getNome()+" )");
       }
       return usuarios;
    }
    
    public static void listarUsuariosPorEmail(){
        System.out.println("-----------------------------");
        System.out.println("  BUSCAR USUARIO POR EMAIL   ");
        System.out.println("-----------------------------");
        System.out.print("email -> ");
        String email =teclado.nextLine();
       List<Usuario> usuarios = UsuarioDao.searchToEmail(email);
       if(usuarios.isEmpty()){
           System.out.println("Não encontrado.");
       }else{
        for(int i=0;i<usuarios.size();i++){
            Usuario u = usuarios.get(i);
            System.out.println(i+" -> (EMAIL: "+u.getEmail()+" SENHA: "+u.getSenha()+" NOME: "+u.getNome()+" )");
        }
       }
    }

    public static void main(String[] args) {
    	int op = 0;
        while(true){
            menu();
            System.out.print(" -> ");
            op = Integer.parseInt(teclado.nextLine());
            if(op == 0){
                System.out.println("Saindo do sistema.");
                break;
            }
            executarMenu(op);
        }
    }
}
