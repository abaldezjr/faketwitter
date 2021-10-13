package view;

import java.util.List;
import java.util.Scanner;

import controller.UsuarioController;
import model.entity.Usuario;

public class UsuarioCRUD {

	private static Scanner teclado = new Scanner(System.in);
	private UsuarioController usuarioController;

	public UsuarioCRUD() {
		usuarioController = new UsuarioController();
	}
	
	public void menuUsuarios() {
		int op = -1;
		do {
			System.out.println("----------------------------------");
			System.out.println("         MENU USUARIOS            ");
			System.out.println("----------------------------------");
			System.out.println("Digite a opção para começar:");
			System.out.println("1- Adicionar usuario");
			System.out.println("2- Atualizar usuario");
			System.out.println("3- Remover usuario");
			System.out.println("4- Listar todos usuarios");
			System.out.println("5- Buscar usuario por id");
			System.out.println("6- Buscar usuario por email");
			System.out.println("0- Voltar");
			System.out.println("-----------------------------------");
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
		System.out.println("-----------------------------");
		if (this.usuarioController.adicionar(usuario)) {
			System.out.println("Usuario adicionado com sucesso.");
		} else {
			System.out.println("Não foi possível adicionar o usuario.");
		}
	}

	public void atualizar() {
		System.out.println("-----------------------------");
		System.out.println("      ATUALIZAR USUARIO      ");
		System.out.println("-----------------------------");
		List<Usuario> usuarios = this.usuarioController.listarTodos();
		listar(usuarios);
		if (!usuarios.isEmpty()) {
			System.out.println("Selecione um dos usuarios:");
			System.out.println(" -> ");
			Integer index = Integer.parseInt(teclado.nextLine());
			Usuario usuario = usuarios.get(index);
			System.out.println("(Deixe o campo vazio para manter o antigo)");
			System.out.print("Novo email -> ");
			String email = teclado.nextLine();
			System.out.print("Nova senha -> ");
			String senha = teclado.nextLine();
			System.out.print("Novo nome -> ");
			String nome = teclado.nextLine();
			System.out.println("-----------------------------");
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
		System.out.println("-----------------------------");
		System.out.println("       REMOVER USUARIO       ");
		System.out.println("-----------------------------");
		List<Usuario> usuarios = this.usuarioController.listarTodos();
		listar(usuarios);
		if (!usuarios.isEmpty()) {
			System.out.println("Selecione um dos usuarios:");
			System.out.println(" -> ");
			Integer index = Integer.parseInt(teclado.nextLine());
			System.out.println("Tem certeza S/N?");
			String op = teclado.nextLine();
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
		System.out.println("-----------------------------");
		System.out.println("       LISTAR USUARIOS       ");
		System.out.println("-----------------------------");
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
		System.out.println("-----------------------------");
		System.out.println("  BUSCAR USUARIO POR ID   ");
		System.out.println("-----------------------------");
		System.out.print("Id -> ");
		String id = teclado.nextLine();
		Usuario usuario = this.usuarioController.filtrarUsuarioPorId(Long.parseLong(id));
		if (usuario == null) {
			System.out.println("Não encontrado.");
		} else {
			mostrarUsuario(usuario);
		}
	}

	public void filtrarUsuarioPorLikeEmail() {
		System.out.println("-----------------------------");
		System.out.println("  BUSCAR USUARIO POR EMAIL   ");
		System.out.println("-----------------------------");
		System.out.print("Email -> ");
		String email = teclado.nextLine();
		listar(this.usuarioController.filtrarPorLikeEmail(email));
	}
}