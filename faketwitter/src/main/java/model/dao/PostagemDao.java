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
    
    public static void save(Postagem postagem){
        String sql = "INSERT INTO postagens(titulo,mensagem,criacao,id_usuario) VALUES (?,?,?,?)";
        
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, postagem.getTitulo());
            preparedStatement.setString(2, postagem.getMensagem());
            preparedStatement.setObject(3, dateFormat.format(postagem.getCriacao()));
            preparedStatement.setLong(4, postagem.getCriador().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    public static void delete(Postagem postagem){
        String sql = "DELETE FROM postagens WHERE id = ?";
        
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1, postagem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    public static void update(Postagem postagem){
         String sql = "UPDATE postagens SET titulo=?, mensagem=? WHERE id=?";
        
        try (Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, postagem.getTitulo());
            preparedStatement.setString(2, postagem.getMensagem());
            preparedStatement.setLong(3, postagem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    public static List<Postagem> selectAll(){
        String sql = "SELECT id,titulo,mensagem,id_usuario FROM postagens";
        List<Postagem> postagens = null;
         
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);){
            postagens = new ArrayList<Postagem>();
            while(resultSet.next()){
                Usuario usuario = UsuarioDao.searchToId(resultSet.getLong("id_usuario"));
                postagens.add(
                    new Postagem(
                        resultSet.getLong("id"),
                        usuario,    
                        resultSet.getString("titulo"),
                        resultSet.getString("mensagem")
                    )
                );
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return postagens;
    }
    
    public static List<Postagem> searchToTitulo(String titulo){
        String sql = "SELECT id,titulo,mensagem,criacao,id_usuario FROM postagens WHERE titulo like ?";
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<Postagem> postagens = null;
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            postagens = new ArrayList<Postagem>();
            preparedStatement.setString(1,"%"+titulo+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Usuario usuario = UsuarioDao.searchToId(resultSet.getLong("id_usuario"));
                postagens.add(
                    new Postagem(
                        resultSet.getLong("id"),
                        usuario,
                        resultSet.getString("titulo"),
                        resultSet.getString("mensagem"),
                         new Date(resultSet.getString("criacao"))
                    )
                );
            }
            ConexaoFactory.fechar(resultSet);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return postagens;
    }
    
    public static Postagem searchToId(Long id){
        String sql = "SELECT id,titulo,mensagem,criacao,id_usuario FROM postagens WHERE id = ?";
        
        Postagem postagem = null;
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Usuario usuario = UsuarioDao.searchToId(resultSet.getLong("id_usuario"));
                    postagem = new Postagem(
                        resultSet.getLong("id"),
                        usuario,
                        resultSet.getString("titulo"),
                        resultSet.getString("mensagem")
                );
            }
            ConexaoFactory.fechar(resultSet);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return postagem;
    }
    
    public static List<Postagem> searchToUsuario(Usuario usuario){
        String sql = "SELECT postagens.id as id,postagens.titulo as titulo,postagens.mensagem as mensagem,"
                + "postagens.criacao as criacao,postagens.id_usuario as id_usuario,usuarios.email as email"
                + " FROM postagens INNER JOIN usuarios ON postagens.id_usuario = usuarios.id"
                + " WHERE usuarios.id = ?";
        
        List<Postagem> postagens = null;
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1,usuario.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Usuario u = UsuarioDao.searchToId(usuario.getId());
             if(u != null){
            	postagens = new ArrayList<Postagem>();
                while(resultSet.next()){
                    postagens.add(
                        new Postagem(
                            resultSet.getLong("id"),
                            null,
                            resultSet.getString("titulo"),
                            resultSet.getString("mensagem")
                        )
                    );
                }
             }
            ConexaoFactory.fechar(resultSet);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return postagens;
    }
        
}
