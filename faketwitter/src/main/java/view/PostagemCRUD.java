package view;

import java.util.List;

import controller.PostagemController;
import dto.PostagemDto;
import dto.UsuarioDto;

public class PostagemCRUD {

	private UtilidadesView utilidadesView;
	
	private PostagemController postagemController;
	private UsuarioDto usuarioDto;

	public PostagemCRUD() {
		utilidadesView = new UtilidadesView();
		postagemController = new PostagemController();
	}

	public void setUsuario(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
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
		PostagemDto postagemDto = new PostagemDto();
		postagemDto.setCriador(usuarioDto);
		System.out.println("Data de criação:" + postagemDto.getCriacao());
		postagemDto.setTitulo(utilidadesView.leia("Titulo:"));
		postagemDto.setMensagem(utilidadesView.leia("Mensagem:"));
		if (this.postagemController.adicionar(postagemDto)) {
			System.out.println("Postagem adicionada com sucesso.");
		} else {
			System.out.println("Não foi possível adicionar a postagem.");
		}
	}

	public void atualizar() {
		utilidadesView.titulo("ATUALIZAR POSTAGEM");
		List<PostagemDto> postagensDto = this.postagemController.filtrarPostagemPorUsuario(usuarioDto);
		listar(postagensDto);
		System.out.println("Selecione uma das postagens abaixo parar atualizar:");
		PostagemDto postagemDto = postagensDto.get(Integer.parseInt(utilidadesView.leia(" -> ")));
		System.out.println("(Deixe o campo vazio para manter o antigo)");
		String titulo = utilidadesView.leia("Titulo:");
		String mensagem = utilidadesView.leia("Mensagem:");
		if (!titulo.isEmpty())
			postagemDto.setTitulo(titulo);
		if (!mensagem.isEmpty())
			postagemDto.setMensagem(mensagem);
		if (postagemController.atualizar(postagemDto)) {
			System.out.println("Postagem atualizada com sucesso.");
		} else {
			System.out.println("Não foi possível atualizar a postagem.");
		}
	}

	public void remover() {
		utilidadesView.titulo("REMOVER POSTAGEM");
		List<PostagemDto> postagensDto = this.postagemController.filtrarPostagemPorUsuario(usuarioDto);
		listar(postagensDto);
		System.out.println("Selecione uma das postagens abaixo parar remover:");
		PostagemDto postagemDto = postagensDto.get(Integer.parseInt(utilidadesView.leia(" -> ")));
		String op = utilidadesView.leia("Tem certeza S/N?\n -> ");
		if (op.toUpperCase().startsWith("S")) {
			if (postagemController.remover(postagemDto)) {
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

	public void listar(List<PostagemDto> postagensDto) {
		if (postagensDto.isEmpty()) {
			System.out.println("Nenhuma postagem encontrada.");
		} else {
			for (int i = 0; i < postagensDto.size(); i++) {
				PostagemDto postagemDto = postagensDto.get(i);
				System.out.println(i + " -> (TITULO: " + postagemDto.getTitulo() + " CRIADOR: " + postagemDto.getCriador() + " MENSAGEM: "
						+ postagemDto.getMensagem() + " )");
			}
		}
	}

	public void listarMinhasPostagens() {
		utilidadesView.titulo("MINHAS POSTAGENS");
		listar(this.postagemController.filtrarPostagemPorUsuario(usuarioDto));
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
