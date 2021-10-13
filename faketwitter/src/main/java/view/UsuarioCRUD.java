package view;

import java.util.List;
import java.util.Scanner;

import controller.UsuarioController;
import model.entity.Usuario;

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
		Usuario usuario = new Usuario();
		usuario.setEmail(utilidadesView.leia("Email -> "));
		usuario.setSenha(utilidadesView.leia("Senha -> "));
		usuario.setNome(utilidadesView.leia("Nome -> "));
		if (this.usuarioController.adicionar(usuario)) {
			System.out.println("Usuario adicionado com sucesso.");
		} else {
			System.out.println("Não foi possível adicionar o usuario.");
		}
	}

	public void atualizar() {
		utilidadesView.titulo("ATUALIZAR USUARIO");
		List<Usuario> usuarios = this.usuarioController.listarTodos();
		listar(usuarios);
		if (!usuarios.isEmpty()) {
			Usuario usuario = usuarios.get(Integer.parseInt(utilidadesView.leia("Selecione um dos usuarios:\n -> ")));
			System.out.println("(Deixe o campo vazio para manter o antigo)");
			String email = utilidadesView.leia("Novo email -> ");
			String senha = utilidadesView.leia("Nova senha -> ");
			String nome  = utilidadesView.leia("Novo nome -> ");
			if (!email.isEmpty())
				usuario.setEmail(email);
			if (!senha.isEmpty())
				usuario.setSenha(senha);
			if (!nome.isEmpty())
				usuario.setNome(nome);
			if (this.usuarioController.atualizar(usuario)) {
				System.out.println("Usuario atualizado com sucesso.");
			} else {

				System.out.println("Não foi possível atualizar o usuario.");
			}
		}
	}

	public void remover() {
		utilidadesView.titulo("REMOVER USUARIO");
		List<Usuario> usuarios = this.usuarioController.listarTodos();
		listar(usuarios);
		if (!usuarios.isEmpty()) {
			Integer index = Integer.parseInt(utilidadesView.leia("Selecione um dos usuarios:\n -> "));
			String op = utilidadesView.leia("Tem certeza S/N?");
			if (op.toUpperCase().startsWith("S")) {
				if (this.usuarioController.remover(usuarios.get(index))) {
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

	public void listar(List<Usuario> usuarios) {
		if (usuarios.isEmpty()) {
			System.out.println("Nenhum usuario encontrado.");
		} else {
			for (int i = 0; i < usuarios.size(); i++) {
				Usuario u = usuarios.get(i);
				System.out.print(i + " -> ");
				mostrarUsuario(u);
			}
		}
	}

	public void mostrarUsuario(Usuario u) {
		System.out.println("(EMAIL: " + u.getEmail() + " SENHA: " + u.getSenha() + " NOME: " + u.getNome() + " )");
	}

	public void filtrarUsuarioPorId() {
		utilidadesView.titulo("BUSCAR USUARIO POR ID");
		Long id = Long.parseLong(utilidadesView.leia("Id -> "));
		Usuario usuario = this.usuarioController.filtrarUsuarioPorId(id);
		if (usuario == null) {
			System.out.println("Não encontrado.");
		} else {
			mostrarUsuario(usuario);
		}
	}

	public void filtrarUsuarioPorLikeEmail() {
		utilidadesView.titulo("BUSCAR USUARIO POR EMAIL");
		String email = utilidadesView.leia("Email -> ");
		listar(this.usuarioController.filtrarPorLikeEmail(email));
	}
}