package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entity.Postagem;
import model.entity.Usuario;

public class PostagemDao {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private static final String SQL_ADICIONAR = "INSERT INTO postagens(titulo,mensagem,criacao,id_usuario) VALUES (?,?,?,?)";
	private static final String SQL_ATUALIZAR = "UPDATE postagens SET titulo=?, mensagem=? WHERE id=?";
	private static final String SQL_REMOVER = "DELETE FROM postagens WHERE id = ?";
	private static final String SQL_LISTARTODOS = "SELECT id,titulo,mensagem,id_usuario FROM postagens";

	private static final String SQL_FILTRARPORID = "SELECT id,titulo,mensagem,criacao,id_usuario FROM postagens WHERE id = ?";
	private static final String SQL_FILTRARPORUSUARIO = "SELECT postagens.id as id,postagens.titulo as titulo,postagens.mensagem as mensagem, "
			+ " postagens.criacao as criacao,postagens.id_usuario as id_usuario,usuarios.email as email "
			+ " FROM postagens INNER JOIN usuarios ON postagens.id_usuario = usuarios.id " + " WHERE usuarios.id = ?";
	// private static final String SQL_FILTRARPORLIKETITULO = "";
	private static final String SQL_FILTRARPORLIKEUSUARIONOME = "SELECT postagens.id as id, postagens.titulo as titulo, "
			+ "postagens.mensagem as mensagem, postagens.criacao as criacao, postagens.id_usuario as id_usuario, usuarios.email as email "
			+ " FROM postagens INNER JOIN usuarios " + " ON postagens.id_usuario = usuarios.id "
			+ " WHERE usuarios.nome LIKE ?";

	public boolean adicionar(Postagem postagem) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_ADICIONAR, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, postagem.getTitulo());
			preparedStatement.setString(2, postagem.getMensagem());
			preparedStatement.setObject(3, postagem.getCriacao());
			preparedStatement.setLong(4, postagem.getCriador().getId());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			long result = 0;
			if (resultSet.next()) {
				result = resultSet.getLong(1);
			}
			postagem.setId(result);
		} catch (SQLException ex) {

		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return true;
	}

	public boolean atualizar(Postagem postagem) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_ATUALIZAR);
			preparedStatement.setString(1, postagem.getTitulo());
			preparedStatement.setString(2, postagem.getMensagem());
			preparedStatement.setLong(3, postagem.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {

		} finally {
			ConexaoFactory.fechar(connection, preparedStatement);
		}
		return true;
	}

	public boolean remover(Postagem postagem) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_REMOVER);
			preparedStatement.setLong(1, postagem.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {

		} finally {
			ConexaoFactory.fechar(connection, preparedStatement);
		}
		return true;
	}

	public List<Postagem> listarTodos() {
		List<Postagem> postagens = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_LISTARTODOS);
			resultSet = preparedStatement.executeQuery();
			postagens = new ArrayList<Postagem>();
			while (resultSet.next()) {
				UsuarioDao usuarioDao = new UsuarioDao();
				Usuario usuario = usuarioDao.filtrarPorId(resultSet.getLong("id_usuario"));
				postagens.add(new Postagem(resultSet.getLong("id"), usuario, resultSet.getString("titulo"),
						resultSet.getString("mensagem")));
			}
		} catch (SQLException ex) {

		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return postagens;
	}

	public Postagem filtrarPorId(Long id) {
		Postagem postagem = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_FILTRARPORID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UsuarioDao usuarioDao = new UsuarioDao();
				Usuario usuario = usuarioDao.filtrarPorId(resultSet.getLong("id_usuario"));
				postagem = new Postagem(resultSet.getLong("id"), usuario, resultSet.getString("titulo"),
						resultSet.getString("mensagem"));
			}
		} catch (SQLException ex) {

		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return postagem;
	}

	public List<Postagem> filtrarPostagemPorUsuario(Usuario usuario) {
		List<Postagem> postagens = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_FILTRARPORUSUARIO);
			preparedStatement.setLong(1, usuario.getId());
			resultSet = preparedStatement.executeQuery();
			UsuarioDao usuarioDAO = new UsuarioDao();
			Usuario u = usuarioDAO.filtrarPorId(usuario.getId());
			if (u != null) {
				postagens = new ArrayList<Postagem>();
				while (resultSet.next()) {
					postagens.add(new Postagem(resultSet.getLong("id"), u, resultSet.getString("titulo"),
							resultSet.getString("mensagem")));
				}
			}
		} catch (SQLException ex) {
		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return postagens;
	}

	public List<Postagem> filtrarPostagemPorLikeTitulo(String titulo) {
//		List<Postagem> postagens = null;
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		try {
//			connection = ConexaoFactory.getConexao();
//			preparedStatement = connection.prepareStatement(SQL_FILTRARPORLIKETITULO);
//			preparedStatement.setString(1, titulo);
//			resultSet = preparedStatement.executeQuery();
//			UsuarioDao usuarioDAO = new UsuarioDao();
//			Usuario u = usuarioDAO.filtrarPorId(usuario.getId());
//			if (u != null) {
//				postagens = new ArrayList<Postagem>();
//				while (resultSet.next()) {
//					postagens.add(new Postagem(resultSet.getLong("id"), u, resultSet.getString("titulo"),
//							resultSet.getString("mensagem")));
//				}
//			}
//		} catch (SQLException ex) {
//		} finally {
//			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
//		}
//		return postagens;
		return null;

	}

	public List<Postagem> filtrarPostagemPorLikeUsuarioNome(String nome) {
		List<Postagem> postagens = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_FILTRARPORLIKEUSUARIONOME);
			preparedStatement.setString(1, "%"+ nome +"%");
			resultSet = preparedStatement.executeQuery();
			UsuarioDao usuarioDao = new UsuarioDao();
			postagens = new ArrayList<Postagem>();
			while (resultSet.next()) {
				postagens.add(
						new Postagem(resultSet.getLong("id"), usuarioDao.filtrarPorId(resultSet.getLong("id_usuario")),
								resultSet.getString("titulo"), resultSet.getString("mensagem")));
			}
		} catch (SQLException ex) {
		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return postagens;

	}

}
