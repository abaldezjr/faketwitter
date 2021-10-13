package view;

import java.util.List;
import java.util.Scanner;

import controller.PostagemController;
import model.entity.Postagem;
import model.entity.Usuario;

public class PostagemCRUD {

	private UtilidadesView utilidadesView;
	
	private PostagemController postagemController;
	private Usuario usuario;

	public PostagemCRUD() {
		utilidadesView = new UtilidadesView();
		postagemController = new PostagemController();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void menuPostagens() {
		String[] opcoes = { "Adicionar postagem", "Atualizar postagem", "Remover postagem", "Listar todas postagens",
				"Listar minhas postagens", "Filtrar postagens por usuario", "Voltar" };
		int op = -1;
		do {
			op = utilidadesView.menu("MENU POSTAGENS", opcoes);
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
		utilidadesView.titulo("ADICIONAR POSTAGEM");
		Postagem postagem = new Postagem();
		postagem.setCriador(usuario);
		System.out.println("Data de criação:" + postagem.getCriacao());
		postagem.setTitulo(utilidadesView.leia("Titulo:"));
		postagem.setMensagem(utilidadesView.leia("Mensagem:"));
		if (this.postagemController.adicionar(postagem)) {
			System.out.println("Postagem adicionada com sucesso.");
		} else {
			System.out.println("Não foi possível adicionar a postagem.");
		}
	}

	public void atualizar() {
		utilidadesView.titulo("ATUALIZAR POSTAGEM");
		List<Postagem> postagens = this.postagemController.filtrarPostagemPorUsuario(usuario);
		listar(postagens);
		System.out.println("Selecione uma das postagens abaixo parar atualizar:");
		Postagem postagem = postagens.get(Integer.parseInt(utilidadesView.leia(" -> ")));
		System.out.println("(Deixe o campo vazio para manter o antigo)");
		String titulo = utilidadesView.leia("Titulo:");
		String mensagem = utilidadesView.leia("Mensagem:");
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
		utilidadesView.titulo("REMOVER POSTAGEM");
		List<Postagem> postagens = this.postagemController.filtrarPostagemPorUsuario(usuario);
		listar(postagens);
		System.out.println("Selecione uma das postagens abaixo parar remover:");
		Postagem postagem = postagens.get(Integer.parseInt(utilidadesView.leia(" -> ")));
		String op = utilidadesView.leia("Tem certeza S/N?\n -> ");
		if (op.toUpperCase().startsWith("S")) {
			if (postagemController.remover(postagem)) {
				System.out.println("Postagem removida com sucesso.");
			} else {
				System.out.println("Não foi possível remover a postagem.");
			}
		}
	}

	public void listarPostagens() {
		utilidadesView.titulo("LISTAR POSTAGENS");
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
		utilidadesView.titulo("MINHAS POSTAGENS");
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
		utilidadesView.titulo("FILTRAR POSTAGENS POR USUARIO");
		listar(this.postagemController.filtrarPostagemPorLikeUsuarioNome(utilidadesView.leia("Nome -> ")));
	}

}
