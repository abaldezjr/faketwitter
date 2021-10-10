import java.util.Scanner;

import model.dao.UsuarioDao;
import model.entity.Usuario;
import view.PostagemCRUD;
import view.UsuarioCRUD;

public class Main {
	  
	private static Scanner teclado = new Scanner(System.in);
	private static Usuario logado;
	 
	private static void home(){
        System.out.println("-----------------------------");
        System.out.println("         TELA INICIAL        ");
        System.out.println("-----------------------------");
        System.out.println("1 - Logar");
        System.out.println("0 - Sair");
        System.out.println("-----------------------------");
        System.out.print(" -> ");
    }
	
	private static void menuLogin(){
        System.out.println("-----------------------------");
        System.out.println("         TELA LOGIN          ");
        System.out.println("-----------------------------");
        System.out.print("Email:");
        String email = teclado.nextLine();
        System.out.print("Senha:");
        String senha = teclado.nextLine();
        System.out.println("-----------------------------");
        if(!email.isEmpty() && !senha.isEmpty()){
            Usuario usuario = UsuarioDao.searchToEqualsEmail(email);
            if(usuario.getEmail().equals(usuario.getEmail()) && senha.equals(usuario.getSenha())){
                logado = usuario;
                System.out.println("Seja bem vindo(a)"+logado.getNome());
            }
            if(logado.getEmail().equals("admin@gmail.com")){
                menuAdmin();
            }else{
                menuPostagens();
            }
        }else{
            System.out.println("Digite email e senha.");
        }
    }
	
	private static void menuAdmin(){
        int op = -1;
        while(op!=0){
            System.out.println("-----------------------------");
            System.out.println("       ADMINISTRAÇÃO         ");
            System.out.println("-----------------------------");
            System.out.println("1 - Usuarios                 ");
            System.out.println("2 - Postagens                ");
            System.out.println("0 - Sair                     ");
            System.out.println("-----------------------------");
            System.out.print(" -> ");
            op = Integer.parseInt(teclado.nextLine());
            switch(op){
                case 1:menuUsuarios();break;
                case 2:menuPostagens();break;
            }
        }
    }
	
	private static void menuUsuarios(){
        int op = -1;
        while(op!=0){
            System.out.println("-----------------------------");
            System.out.println("         MENU USUARIO        ");
            System.out.println("-----------------------------");
            System.out.println("Digite a opção para começar:");
            System.out.println("1- Adicionar usuario");
            System.out.println("2- Atualizar usuario");
            System.out.println("3- Listar todos usuarios");
            System.out.println("4- Buscar usuario por id");
            System.out.println("5- Buscar usuario por email");
            System.out.println("6- Remover usuario");
            System.out.println("0- Voltar");
            System.out.println("-----------------------------");
            System.out.print(" -> ");
            op = Integer.parseInt(teclado.nextLine());
            switch(op){
                case 1:UsuarioCRUD.adicionar();break;
                case 2:UsuarioCRUD.atualizar();break;
                case 3:UsuarioCRUD.listarUsuarios();break;
                case 4:UsuarioCRUD.listarUsuariosPorId();break;
                case 5:UsuarioCRUD.listarUsuariosPorEmail();break;
                case 6:UsuarioCRUD.remover();break;
            }
        }
    }
	
	private static void menuPostagens(){
        int op = -1;
        while(op!=0){
        System.out.println("-----------------------------");
        System.out.println("         MENU POSTAGENS      ");
        System.out.println("-----------------------------");
        System.out.println("Digite a opção para começar:");
        System.out.println("1- Adicionar postagem");
        System.out.println("2- Atualizar postagem");
        System.out.println("3- Listar todas postagens");
        System.out.println("4- Buscar postagem por usuario");
        System.out.println("5- Deletar postagem");
        System.out.println("0- Voltar");
        System.out.println("-----------------------------");
        System.out.print(" -> ");
        op = Integer.parseInt(teclado.nextLine());
            switch(op){
                case 1:PostagemCRUD.adicionar(logado);break;
                case 2:PostagemCRUD.atualizar(logado);break;
                case 3:PostagemCRUD.listarPostagens();break;
                case 4:PostagemCRUD.listarMinhasPostagens(logado);break;
                case 5:PostagemCRUD.remover(logado);break;
            }
        }
    }
	
    public static void main(String[] args) {
    	
    	int op = 0;
        while(true){
            home();
            op = Integer.parseInt(teclado.nextLine());
            if(op == 1){
                menuLogin();
            }
            if(op == 0){
                System.out.println("Saindo do sistema.");
                break;
            }
        }
    
    }
}
