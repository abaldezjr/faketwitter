package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.entity.Usuario;

public class UsuarioDao {

	private static final String SQL_ADICIONAR = "INSERT INTO usuarios(email,senha,nome) VALUES (?,?,?)";
	private static final String SQL_ATUALIZAR = "UPDATE usuarios SET email=?, senha=?, nome=? WHERE id=?";
	private static final String SQL_REMOVER = "DELETE FROM usuarios WHERE id = ?";
	private static final String SQL_LISTARTODOS = "SELECT id,email,senha,nome FROM usuarios";

	private static final String SQL_FILTRARPORID = "SELECT id,email,senha,nome FROM usuarios WHERE id = ?";
	private static final String SQL_FILTRARPORLIKEEMAIL = "SELECT id,email,senha,nome FROM usuarios WHERE email like ?";
	private static final String SQL_FILTRARPOREQUALSEMAIL = "SELECT id,email,senha,nome FROM usuarios WHERE email = ?";
	private static final String SQL_FILTRARPORLIKENOME = "SELECT id,email,senha,nome FROM usuarios WHERE nome like ?";

	public boolean adicionar(Usuario usuario) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_ADICIONAR, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, usuario.getEmail());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			long result = 0;
			if (resultSet.next()) {
				result = resultSet.getLong(1);
			}
			usuario.setId(result);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConexaoFactory.fechar(connection,preparedStatement, resultSet);
		}
		return true;
	}

	public boolean atualizar(Usuario usuario) {
		if (usuario == null)
			return false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_ATUALIZAR);
			preparedStatement.setString(1, usuario.getEmail());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setLong(4, usuario.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConexaoFactory.fechar(connection,preparedStatement);
		}
		return true;
	}

	public boolean remover(Usuario usuario) {
		if (usuario == null)
			return false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_REMOVER);
			preparedStatement.setLong(1, usuario.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConexaoFactory.fechar(connection,preparedStatement);
		}
		return true;
	}

	public List<Usuario> listarTodos() {
		List<Usuario> usuarios = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_LISTARTODOS);
			resultSet = preparedStatement.executeQuery();
			usuarios = new ArrayList<Usuario>();
			while (resultSet.next()) {
				usuarios.add(new Usuario(resultSet.getLong("id"), resultSet.getString("email"),
						resultSet.getString("senha"), resultSet.getString("nome")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return usuarios;
	}

	public Usuario filtrarPorId(Long id) {
		Usuario usuario = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_FILTRARPORID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				usuario = new Usuario(resultSet.getLong("id"), resultSet.getString("email"),
						resultSet.getString("senha"), resultSet.getString("nome"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return usuario;
	}

	public List<Usuario> filtrarPorLikeEmail(String email) {
		List<Usuario> usuarios = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_FILTRARPORLIKEEMAIL);
			usuarios = new ArrayList<Usuario>();
			preparedStatement.setString(1, "%" + email + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				usuarios.add(new Usuario(resultSet.getLong("id"), resultSet.getString("email"),
						resultSet.getString("senha"), resultSet.getString("nome")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return usuarios;
	}

	public Usuario filtrarPorEqualsEmail(String email) {
		Usuario usuario = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			if (connection == null) System.out.println("Conexao nao estabelecida.");
			preparedStatement = connection.prepareStatement(SQL_FILTRARPOREQUALSEMAIL);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				usuario = new Usuario(resultSet.getLong("id"), resultSet.getString("email"),
						resultSet.getString("senha"), resultSet.getString("nome"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return usuario;
	}
	
	public List<Usuario> filtrarPorLikeNome(String nome) {
		List<Usuario> usuarios = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConexaoFactory.getConexao();
			preparedStatement = connection.prepareStatement(SQL_FILTRARPORLIKENOME);
			usuarios = new ArrayList<Usuario>();
			preparedStatement.setString(1, "%" + nome + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				usuarios.add(new Usuario(resultSet.getLong("id"), resultSet.getString("email"),
						resultSet.getString("senha"), resultSet.getString("nome")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConexaoFactory.fechar(connection, preparedStatement, resultSet);
		}
		return usuarios;
	}

}
