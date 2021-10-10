package view;

import java.util.List;
import java.util.Scanner;

import model.dao.PostagemDao;
import model.dao.UsuarioDao;
import model.entity.Postagem;
import model.entity.Usuario;

public class PostagemCRUD {
	
	private static Scanner teclado = new Scanner(System.in);
	
	public static void adicionar(Usuario criador){
        System.out.println("-----------------------------");
        System.out.println("     ADICIONAR POSTAGEM      ");
        System.out.println("-----------------------------");
        Postagem postagem = new Postagem();
        postagem.setCriador(criador);
        System.out.println("Data de criação -> "+ postagem.getCriacao());
        System.out.print("Titulo -> ");
        postagem.setTitulo(teclado.nextLine());
        System.out.print("Mensagem -> ");
        postagem.setMensagem(teclado.nextLine());
        PostagemDao.save(postagem);
        System.out.println("Obrigado por postar.");
    }
	
	public static void remover(Usuario criador){
        System.out.println("Selecione uma das postagens abaixo para deletar:"); 
        List<Postagem> postagens = listarMinhasPostagens(criador);
        System.out.print(" -> ");
        int index = Integer.parseInt(teclado.nextLine());
        System.out.println("Tem certeza S/N?");
        System.out.print(" -> ");
        String op = teclado.nextLine();
        if(op.toUpperCase().startsWith("S")){
            PostagemDao.delete(postagens.get(index));
        }
    }
    
    public static void atualizar(Usuario criador){
        System.out.println("Selecione uma das postagens abaixo parar atualizar:");
        List<Postagem> postagens = listarMinhasPostagens(criador);
        System.out.print(" -> ");
        Postagem postagem = postagens.get(Integer.parseInt(teclado.nextLine()));
        System.out.println("-----------------------------");
        System.out.println("      ATUALIZAR POSTAGEM     ");
        System.out.println("(Deixe o campo vazio para    ");
        System.out.println("manter o antigo)             ");
        System.out.println("-----------------------------");
        System.out.print("Titulo -> ");
        String titulo = teclado.nextLine();
        if(!titulo.isEmpty())postagem.setTitulo(titulo);
        System.out.print("Mensagem -> ");
        String mensagem = teclado.nextLine();
        if(!mensagem.isEmpty())postagem.setMensagem(mensagem);
        PostagemDao.update(postagem);
        System.out.println("Postagem atualizada com sucesso.");
    }
    
    public static List<Postagem> listarPostagens(){
       List<Postagem> postagens = PostagemDao.selectAll();
       for(int i=0;i<postagens.size();i++){
           Postagem p = postagens.get(i);
           System.out.println(
             i+" -> (TITULO: "+p.getTitulo()+
                   " CRIADOR: "+p.getCriador()+
                   " MENSAGEM: "+p.getMensagem()+" )");
       }
       return postagens;
    }
    
    public static List<Postagem> listarMinhasPostagens(Usuario criador){
        System.out.println("-----------------------------");
        System.out.println("      MINHAS POSTAGENS       ");
        System.out.println("-----------------------------");
        Usuario u = UsuarioDao.searchToEqualsEmail(criador.getEmail());
        List<Postagem> postagens = null;
        if(u == null){
           System.out.println("Usuario não encontrado.");
       }else{
            if(postagens == null){
                postagens = PostagemDao.searchToUsuario(u);
                for(int i=0;i<postagens.size();i++){
                Postagem p = postagens.get(i);
                System.out.println(
                     i+" -> (TITULO: "+p.getTitulo()+
                           " MENSAGEM: "+p.getMensagem()+" )");
               }
            }
        }
       return postagens;
    }
	

}
