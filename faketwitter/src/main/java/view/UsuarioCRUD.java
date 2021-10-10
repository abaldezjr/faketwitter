package view;

import java.util.List;
import java.util.Scanner;

import model.dao.UsuarioDao;
import model.entity.Usuario;

public class UsuarioCRUD {

	private static Scanner teclado = new Scanner(System.in);

	public static void adicionar() {
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

	public static List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = UsuarioDao.selectAll();
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario u = usuarios.get(i);
			System.out.println(
					i + " -> (EMAIL: " + u.getEmail() + " SENHA: " + u.getSenha() + " NOME: " + u.getNome() + " )");
		}
		return usuarios;
	}

	public static void remover() {
		System.out.println("Selecione um dos usuarios abaixo para deletar:");
		List<Usuario> usuarios = listarUsuarios();
		System.out.print(" -> ");
		int index = Integer.parseInt(teclado.nextLine());
		System.out.println("Tem certeza S/N?");
		System.out.print(" -> ");
		String op = teclado.nextLine();
		if (op.toUpperCase().startsWith("S")) {
			UsuarioDao.delete(usuarios.get(index));
		}
	}

	public static void atualizar() {
		System.out.println("Selecione um dos usuarios abaixo parar atualizar:");
		List<Usuario> usuarios = listarUsuarios();
		System.out.print(" -> ");
		Usuario usuario = usuarios.get(Integer.parseInt(teclado.nextLine()));
		System.out.println("-----------------------------");
		System.out.println("      ATUALIZAR USUARIO      ");
		System.out.println("(Deixe o campo vazio para    ");
		System.out.println("manter o antigo)              ");
		System.out.println("-----------------------------");
		System.out.print("Novo email -> ");
		String email = teclado.nextLine();
		if (!email.isEmpty())
			usuario.setEmail(email);
		System.out.print("Nova senha -> ");
		String senha = teclado.nextLine();
		if (!senha.isEmpty())
			usuario.setSenha(senha);
		System.out.print("Novo email -> ");
		String nome = teclado.nextLine();
		if (!nome.isEmpty())
			usuario.setNome(nome);
		UsuarioDao.update(usuario);
		System.out.println("Usuario atualizado com sucesso.");
	}

	public static void listarUsuariosPorId() {
		System.out.println("-----------------------------");
		System.out.println("  BUSCAR USUARIO POR ID   ");
		System.out.println("-----------------------------");
		System.out.print("Id -> ");
		Long id = teclado.nextLong();
		Usuario u = UsuarioDao.searchToId(id);
		if (u == null) {
			System.out.println("Não encontrado.");
		} else {
			System.out.println("(EMAIL: " + u.getEmail() + " SENHA: " + u.getSenha() + " NOME: " + u.getNome() + " )");
		}
	}

	public static void listarUsuariosPorEmail() {
		System.out.println("-----------------------------");
		System.out.println("  BUSCAR USUARIO POR EMAIL   ");
		System.out.println("-----------------------------");
		System.out.print("Email -> ");
		String email = teclado.nextLine();
		List<Usuario> usuarios = UsuarioDao.searchToEmail(email);
		if (usuarios.isEmpty()) {
			System.out.println("Não encontrado.");
		} else {
			for (int i = 0; i < usuarios.size(); i++) {
				Usuario u = usuarios.get(i);
				System.out.println(
						i + " -> (EMAIL: " + u.getEmail() + " SENHA: " + u.getSenha() + " NOME: " + u.getNome() + " )");
			}
		}
	}

}
