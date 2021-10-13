package view;

import java.util.Scanner;

import controller.UsuarioController;

public class MainView {

	private Scanner teclado = new Scanner(System.in);
	private UsuarioController usuarioController;
	private UsuarioCRUD usuarioCRUD;
	private PostagemCRUD postagemCRUD;
	
	public MainView() {
		usuarioController = new UsuarioController();
		usuarioCRUD = new UsuarioCRUD();
		postagemCRUD = new PostagemCRUD();
	}

	public void telaInicial() {
		int op = -1;
		do {
			System.out.println("----------------------------------");
			System.out.println("           TELA INICIAL           ");
			System.out.println("----------------------------------");
			System.out.println("1 - Logar                         ");
			System.out.println("0 - Sair                          ");
			System.out.println("----------------------------------");
			System.out.print(" -> ");
			op = Integer.parseInt(this.teclado.nextLine());
			switch (op) {
			case 1:
				menuLogin();
				break;
			case 0:
				System.out.println("Saindo do sistema.");
				break;
			}
		} while (op != 0);
	}

	public void menuLogin() {
		System.out.println("----------------------------------");
		System.out.println("           TELA LOGIN             ");
		System.out.println("----------------------------------");
		System.out.print("Email:");
		String email = teclado.nextLine();
		System.out.print("Senha:");
		String senha = teclado.nextLine();
		System.out.println("----------------------------------");
		if (this.usuarioController.validaLogin(email, senha)) {
			this.postagemCRUD.setUsuario(this.usuarioController.getLogado());
			if (this.usuarioController.ehAdmin()) {
				menuAdmin();
			} else {
				this.postagemCRUD.menuPostagens();
			}
		}
	}

	public void menuAdmin() {
		int op = -1;
		do {
			System.out.println("----------------------------------");
			System.out.println("       ADMINISTRAÇÃO              ");
			System.out.println("----------------------------------");
			System.out.println("1 - Usuarios                      ");
			System.out.println("2 - Postagens                     ");
			System.out.println("0 - Sair                          ");
			System.out.println("----------------------------------");
			System.out.print(" -> ");
			op = Integer.parseInt(teclado.nextLine());
			switch (op) {
			case 1:
				this.usuarioCRUD.menuUsuarios();
				break;
			case 2:
				this.postagemCRUD.menuPostagens();
				break;
			}
		} while (op != 0);
	}
}
