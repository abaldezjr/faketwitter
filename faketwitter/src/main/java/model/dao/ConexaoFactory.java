package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoFactory {
    
    private static final String URL = "jdbc:mysql://localhost/faketwitter?useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "ludovic";
    
    public static Connection getConexao(){
        try {
            return DriverManager.getConnection(URL,USUARIO,SENHA);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return null;
    }
    
    public static boolean fechar(Statement statement) {
        if (statement == null) return false;
        try {
            statement.close();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public static boolean fechar(Connection connection) {
        if (connection == null) return false;
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public static boolean fechar(ResultSet resultSet) {
        if (resultSet == null) return false;
        try {
            resultSet.close();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public static void fechar(Connection connection,Statement statement) {
        fechar(statement);
        fechar(connection);
    }

    public static void fechar(Connection connection,Statement statement, ResultSet resultSet) {
            fechar(resultSet);
            fechar(connection,statement);
    }
    
}
