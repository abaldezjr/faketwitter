package view;

import java.util.List;
import java.util.Scanner;

import controller.PostagemController;
import model.entity.Postagem;
import model.entity.Usuario;

public class PostagemCRUD {

	private static Scanner teclado = new Scanner(System.in);
	private PostagemController postagemController;
	private Usuario usuario;

	public PostagemCRUD() {
		postagemController = new PostagemController();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void menuPostagens() {
		int op = -1;
		do {
			System.out.println("----------------------------------");
			System.out.println("         MENU POSTAGENS           ");
			System.out.println("----------------------------------");
			System.out.println("Digite a opção para começar:      ");
			System.out.println("1 - Adicionar postagem            ");
			System.out.println("2 - Atualizar postagem            ");
			System.out.println("3 - Remover postagem              ");
			System.out.println("4 - Listar todas postagens        ");
			System.out.println("5 - Listar minhas postagens       ");
			System.out.println("6 - Filtrar postagens por usuario ");
			System.out.println("0 - Voltar                        ");
			System.out.println("----------------------------------");
			System.out.print(" -> ");
			op = Integer.parseInt(teclado.nextLine());
			switch (op) {
			case 1:
				adicionar();
				break;
			case 2:
				atualizar();
				break;
			case 3:
				remover();
				break;
			case 4:
				listarPostagens();
				break;
			case 5:
				listarMinhasPostagens();
				break;
			case 6:
				filtrarPostagensPorLikeUsuarioNome();
				break;
			}
		} while (op != 0);
	}

	public void adicionar() {
		System.out.println("-----------------------------");
		System.out.println("     ADICIONAR POSTAGEM      ");
		System.out.println("-----------------------------");
		Postagem postagem = new Postagem();
		postagem.setCriador(usuario);
		System.out.println("Data de criação -> " + postagem.getCriacao());
		System.out.print("Titulo -> ");
		postagem.setTitulo(teclado.nextLine());
		System.out.print("Mensagem -> ");
		postagem.setMensagem(teclado.nextLine());
		System.out.println("-----------------------------");
		if (this.postagemController.adicionar(postagem)) {
			System.out.println("Postagem adicionada com sucesso.");
		} else {
			System.out.println("Não foi possível adicionar a postagem.");
		}
	}

	public void atualizar() {
		System.out.println("-----------------------------");
		System.out.println("      ATUALIZAR POSTAGEM     ");
		System.out.println("-----------------------------");
		List<Postagem> postagens = this.postagemController.filtrarPostagemPorUsuario(usuario);
		listar(postagens);
		System.out.println("Selecione uma das postagens abaixo parar atualizar:");
		System.out.println(" -> ");
		Integer index = Integer.parseInt(teclado.nextLine());
		Postagem postagem = postagens.get(index);
		System.out.println("(Deixe o campo vazio para manter o antigo)");
		System.out.print("Titulo -> ");
		String titulo = teclado.nextLine();
		System.out.print("Mensagem -> ");
		String mensagem = teclado.nextLine();
		if (!titulo.isEmpty())
			postagem.setTitulo(titulo);
		if (!mensagem.isEmpty())
			postagem.setMensagem(mensagem);
		if (postagemController.atualizar(postagem)) {
			System.out.println("Postagem atualizada com sucesso.");
		} else {
			System.out.println("Não foi possível atualizar a postagem.");
		}
	}

	public void remover() {
		System.out.println("-----------------------------");
		System.out.println("       REMOVER POSTAGEM      ");
		System.out.println("-----------------------------");
		List<Postagem> postagens = this.postagemController.filtrarPostagemPorUsuario(usuario);
		listar(postagens);
		System.out.println("Selecione uma das postagens abaixo parar remover:");
		System.out.println(" -> ");
		Integer index = Integer.parseInt(teclado.nextLine());
		System.out.println("Tem certeza S/N?");
		String op = teclado.nextLine();
		if (op.toUpperCase().startsWith("S")) {
			if (postagemController.remover(postagens.get(index))) {
				System.out.println("Postagem removida com sucesso.");
			} else {
				System.out.println("Não foi possível remover a postagem.");
			}
		}
	}

	public void listarPostagens() {
		System.out.println("-----------------------------");
		System.out.println("       LISTAR POSTAGENS       ");
		System.out.println("-----------------------------");
		listar(this.postagemController.listarTodos());
	}

	public void listar(List<Postagem> postagens) {
		if (postagens.isEmpty()) {
			System.out.println("Nenhuma postagem encontrada.");
		} else {
			for (int i = 0; i < postagens.size(); i++) {
				Postagem p = postagens.get(i);
				System.out.println(i + " -> (TITULO: " + p.getTitulo() + " CRIADOR: " + p.getCriador() + " MENSAGEM: "
						+ p.getMensagem() + " )");
			}
		}
	}

	public void listarMinhasPostagens() {
		System.out.println("-----------------------------");
		System.out.println("      MINHAS POSTAGENS       ");
		System.out.println("-----------------------------");
		listar(this.postagemController.filtrarPostagemPorUsuario(usuario));
	}

//	public void filtrarPorLikeTitulo() {
//		System.out.println("-------------------------------");
//		System.out.println(" FILTRAR POSTAGENS POR TITULO  ");
//		System.out.println("-------------------------------");
//		System.out.print("titulo -> ");
//		String titulo = teclado.nextLine();
//		listar(this.postagemController.filtrarPostagemPorLikeTitulo(titulo));
//	}

	public void filtrarPostagensPorLikeUsuarioNome() {
		System.out.println("-------------------------------");
		System.out.println(" FILTRAR POSTAGENS POR USUARIO ");
		System.out.println("-------------------------------");
		System.out.print("Nome -> ");
		String nome = teclado.nextLine();
		listar(this.postagemController.filtrarPostagemPorLikeUsuarioNome(nome));
	}

}
