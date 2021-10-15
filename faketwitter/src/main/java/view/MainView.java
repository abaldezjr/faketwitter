package view;

import controller.UsuarioController;
import dto.UsuarioDto;

public class MainView {
	
	private UtilidadesView utilidadesView;
	private UsuarioController usuarioController;
	private UsuarioCRUD usuarioCRUD;
	private PostagemCRUD postagemCRUD;
	
	public MainView() {
		utilidadesView = new UtilidadesView();
		usuarioController = new UsuarioController();
		usuarioCRUD = new UsuarioCRUD();
		postagemCRUD = new PostagemCRUD();
	}

	public void telaInicial() {
		String[] opcoes =  {"Logar","Sair"};
		int op = -1;
		do {
			op = utilidadesView.menu("TELA INICIAL", opcoes);
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
		utilidadesView.titulo("TELA LOGIN");
		String email = utilidadesView.leia("Email:");
		String senha = utilidadesView.leia("Senha:");
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setEmail(email);
		usuarioDto.setSenha(senha);
		if (this.usuarioController.validaLogin(usuarioDto)) {
			this.postagemCRUD.setUsuario(this.usuarioController.getLogado());
			if (this.usuarioController.ehAdmin()) {
				menuAdmin();
			} else {
				this.postagemCRUD.menuPostagens();
			}
		}
	}

	public void menuAdmin() {
		String[] opcoes =  {"Usuarios","Postagens","Sair"};
		int op = -1;
		do {
			op = utilidadesView.menu("ADMINISTRAÇÃO", opcoes);
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
