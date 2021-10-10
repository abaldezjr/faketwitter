package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.Usuario;

public class UsuarioDao {
    
    
    /*--------------------------------------------------
        ADICIONAR USUARIO COM STATEMENT
    ----------------------------------------------------*/
    public static void save(Usuario usuario){
        String sql = "insert into usuarios(email,senha,nome) values ('"+usuario.getEmail()+"','"+usuario.getSenha()+"','"+usuario.getNome()+"')";
        Connection connection = ConexaoFactory.getConexao();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            ConexaoFactory.fechar(connection,statement);
        } catch (SQLException ex) {
            System.out.println("erro:"+ex.getMessage());
        }
    }
   
     /*--------------------------------------------------
        REMOVER USUARIO COM STATEMENT
    ----------------------------------------------------*/
    public static void delete(Usuario usuario){
        if(usuario == null){
            System.out.println("Não foi possível remover o registro.");
            return;
        }
        String sql = "DELETE FROM usuarios WHERE id='"+usuario.getId()+"'";
        Connection connection = ConexaoFactory.getConexao();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            ConexaoFactory.fechar(connection,statement);
            System.out.println("Registro removido com sucesso.");
        } catch (SQLException ex) {
            System.out.println("erro:"+ex.getMessage());
        }
    }
    
     /*--------------------------------------------------
        ATUALIZAR USUARIO COM STATEMENT
    ----------------------------------------------------*/
    public static void update(Usuario usuario){
        if(usuario == null){
            System.out.println("Não foi possível atualizar o registro.");
            return;
        }
        String sql = "UPDATE usuarios SET email='"+usuario.getEmail()+"', senha='"+usuario.getSenha()+"', nome='"+usuario.getNome()+"' WHERE id='"+usuario.getId()+"';";
        Connection connection = ConexaoFactory.getConexao();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            ConexaoFactory.fechar(connection,statement);
            System.out.println("Registro atualizado com sucesso.");
        } catch (SQLException ex) {
            System.out.println("erro:"+ex.getMessage());
        }
    }
    
     /*--------------------------------------------------
        SELECIONAR TODOS USUARIOS COM STATEMENT E RESULTSET
    ----------------------------------------------------*/
    public static List<Usuario> selectAll(){
        String sql = "SELECT id,email,senha,nome FROM usuarios";
        Connection connection = ConexaoFactory.getConexao();
         List<Usuario> usuarios = null;
        try {
            usuarios = new ArrayList<Usuario>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
            ConexaoFactory.fechar(connection,statement);
        } catch (SQLException ex) {
            System.out.println("erro:"+ex.getMessage());
        }
        return usuarios;
    }
    
     /*-----------------------------------------------------------
        SELECIONAR USUARIOS POR LOGIN USANDO STATEMENT E RESULTSET
    --------------------------------------------------------------*/
    public static List<Usuario> searchToLogin(String nome){
        String sql = "SELECT id,email,senha,nome FROM usuarios WHERE nome like '%"+nome+"%'";
        Connection connection = ConexaoFactory.getConexao();
         List<Usuario> usuarios = null;
        try {
            usuarios = new ArrayList<Usuario>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
            ConexaoFactory.fechar(connection,statement);
        } catch (SQLException ex) {
            System.out.println("erro:"+ex.getMessage());
        }
        return usuarios;
    }
    
     /*--------------------------------------------------
        USANDO META DADOS
    ----------------------------------------------------*/
    public static void metaDados(){
        String sql = "SELECT * FROM usuarios";
        Connection connection = ConexaoFactory.getConexao();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            resultSet.next();
            for(int i=1;i<=metaData.getColumnCount();i++){
                System.out.println("tabela="+metaData.getTableName(i)
                        +" coluna="+metaData.getColumnName(i)
                        +" tipo="+metaData.getColumnTypeName(i)
                        +" catalog="+metaData.getCatalogName(i));
            }
            ConexaoFactory.fechar(connection,statement);
        } catch (SQLException ex) {
            System.out.println("erro:"+ex.getMessage());
        }
    }
    
     public static void saveTransaction(){
        Connection connection = ConexaoFactory.getConexao();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            connection.commit();
            ConexaoFactory.fechar(connection,statement);
        } catch (SQLException ex) {
            System.out.println("erro:"+ex.getMessage());
        }
    }
    
    
}
