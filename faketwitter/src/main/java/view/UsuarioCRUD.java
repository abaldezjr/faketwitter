package view;

import java.util.List;

import controller.UsuarioController;
import dto.UsuarioDto;

public class UsuarioCRUD {

	private UtilidadesView utilidadesView;
	private UsuarioController usuarioController;

	public UsuarioCRUD() {
		utilidadesView = new UtilidadesView();
		usuarioController = new UsuarioController();
	}

	public void menuUsuarios() {
		String[] opcoes = { "Adicionar usuario", "Atualizar usuario", "Remover usuario", "Listar todos usuarios",
				"Buscar usuario por id", "Buscar usuario por email", "Voltar" };
		int op = -1;
		do {
			op = utilidadesView.menu("MENU USUARIOS", opcoes);
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
				listarUsuarios();
				break;
			case 5:
				filtrarUsuarioPorId();
				break;
			case 6:
				filtrarUsuarioPorLikeEmail();
				break;
			}
		} while (op != 0);
	}

	public void adicionar() {
		utilidadesView.titulo("ADICIONAR USUARIO");
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setEmail(utilidadesView.leia("Email -> "));
		usuarioDto.setSenha(utilidadesView.leia("Senha -> "));
		usuarioDto.setNome(utilidadesView.leia("Nome -> "));
		if (this.usuarioController.adicionar(usuarioDto)) {
			System.out.println("Usuario adicionado com sucesso.");
		} else {
			System.out.println("Não foi possível adicionar o usuario.");
		}
	}

	public void atualizar() {
		utilidadesView.titulo("ATUALIZAR USUARIO");
		List<UsuarioDto> usuariosDto = this.usuarioController.listarTodos();
		listar(usuariosDto);
		if (!usuariosDto.isEmpty()) {
			UsuarioDto usuarioDto = usuariosDto.get(Integer.parseInt(utilidadesView.leia("Selecione um dos usuarios:\n -> ")));
			System.out.println("(Deixe o campo vazio para manter o antigo)");
			String email = utilidadesView.leia("Novo email -> ");
			String senha = utilidadesView.leia("Nova senha -> ");
			String nome  = utilidadesView.leia("Novo nome -> ");
			if (!email.isEmpty())
				usuarioDto.setEmail(email);
			if (!senha.isEmpty())
				usuarioDto.setSenha(senha);
			if (!nome.isEmpty())
				usuarioDto.setNome(nome);
			if (this.usuarioController.atualizar(usuarioDto)) {
				System.out.println("Usuario atualizado com sucesso.");
			} else {

				System.out.println("Não foi possível atualizar o usuario.");
			}
		}
	}

	public void remover() {
		utilidadesView.titulo("REMOVER USUARIO");
		List<UsuarioDto> usuariosDto = this.usuarioController.listarTodos();
		listar(usuariosDto);
		if (!usuariosDto.isEmpty()) {
			Integer index = Integer.parseInt(utilidadesView.leia("Selecione um dos usuarios:\n -> "));
			String op = utilidadesView.leia("Tem certeza S/N?");
			if (op.toUpperCase().startsWith("S")) {
				if (this.usuarioController.remover(usuariosDto.get(index))) {
					System.out.println("Usuario removido com sucesso.");
				} else {
					System.out.println("Não foi possível remover o usuario.");
				}
			}
		}
	}

	public void listarUsuarios() {
		utilidadesView.titulo("LISTAR USUARIOS");
		listar(this.usuarioController.listarTodos());
	}

	public void listar(List<UsuarioDto> usuariosDto) {
		if (usuariosDto.isEmpty()) {
			System.out.println("Nenhum usuario encontrado.");
		} else {
			for (int i = 0; i < usuariosDto.size(); i++) {
				UsuarioDto usuarioDto = usuariosDto.get(i);
				System.out.print(i + " -> ");
				mostrarUsuario(usuarioDto);
			}
		}
	}

	public void mostrarUsuario(UsuarioDto usuarioDto) {
		System.out.println("(EMAIL: " + usuarioDto.getEmail() + " NOME: " + usuarioDto.getNome() + " )");
	}

	public void filtrarUsuarioPorId() {
		utilidadesView.titulo("BUSCAR USUARIO POR ID");
		Long id = Long.parseLong(utilidadesView.leia("Id -> "));
		UsuarioDto usuarioDto = this.usuarioController.filtrarUsuarioPorId(id);
		if (usuarioDto == null) {
			System.out.println("Não encontrado.");
		} else {
			mostrarUsuario(usuarioDto);
		}
	}

	public void filtrarUsuarioPorLikeEmail() {
		utilidadesView.titulo("BUSCAR USUARIO POR EMAIL");
		String email = utilidadesView.leia("Email -> ");
		listar(this.usuarioController.filtrarPorLikeEmail(email));
	}
}