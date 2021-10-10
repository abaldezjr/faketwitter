package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Usuario;

public class UsuarioDao {
    

	public static void save(Usuario usuario){
        String sql = "INSERT INTO usuarios(email,senha,nome) VALUES (?,?,?)";
        
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNome());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    public static void delete(Usuario usuario){
        if(usuario==null)return;
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1, usuario.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    public static void update(Usuario usuario){
        if(usuario==null) return;
         String sql = "UPDATE usuarios SET email=?, senha=?, nome=? WHERE id=?";
        
        try (Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNome());
            preparedStatement.setLong(4, usuario.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    public static List<Usuario> selectAll(){
        String sql = "SELECT id,email,senha,nome FROM usuarios";
        List<Usuario> usuarios = null;
         
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);){
            usuarios = new ArrayList<Usuario>();
            while(resultSet.next()){
                usuarios.add(
                    new Usuario(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("senha"),
                        resultSet.getString("nome")
                    )
                );
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return usuarios;
    }
    
    public static List<Usuario> searchToEmail(String email){
        String sql = "SELECT id,email,senha,nome FROM usuarios WHERE email like ?";
        
        List<Usuario> usuarios = null;
        try(Connection connection = ConexaoFactory.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            usuarios = new ArrayList<Usuario>();
            preparedStatement.setString(1,"%"+email+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                usuarios.add(
                    new Usuario(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("senha"),
                        resultSet.getString("nome")
                    )
                );
            }
            ConexaoFactory.fechar(resultSet);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usuarios;
    }  
    
    public static Usuario searchToEqualsEmail(String email){
        String sql = "SELECT id,email,senha,nome FROM usuarios WHERE email = ?";
         
         Usuario usuario = null;
         try(Connection connection = ConexaoFactory.getConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){
             preparedStatement.setString(1,email);
             ResultSet resultSet = preparedStatement.executeQuery();
             if(resultSet.next()){
                 usuario = new Usuario(
                         resultSet.getLong("id"),
                         resultSet.getString("email"),
                         resultSet.getString("senha"),
                         resultSet.getString("nome")
                 );
             }
             ConexaoFactory.fechar(resultSet);
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return usuario;
     }
     
     public static Usuario searchToId(Long id){
         String sql = "SELECT id,email,senha,nome FROM usuarios WHERE id = ?";
         
         Usuario usuario = null;
         try(Connection connection = ConexaoFactory.getConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){
             preparedStatement.setLong(1,id);
             ResultSet resultSet = preparedStatement.executeQuery();
             if(resultSet.next()){
                 usuario = new Usuario(
                         resultSet.getLong("id"),
                         resultSet.getString("email"),
                         resultSet.getString("senha"),
                         resultSet.getString("nome")
                 );
             }
             ConexaoFactory.fechar(resultSet);
         } catch (SQLException ex) {
             ex.getMessage();
         }
         return usuario;
     }
    
}
