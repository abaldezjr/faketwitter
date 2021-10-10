package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoFactory {
    
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private DriverManager driverManager;
    
    private static final String URL = "jdbc:mysql://localhost/faketwitter?useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "ludovic";
    
    public static Connection getConexao(){
            try {
				return DriverManager.getConnection(URL,USUARIO,SENHA);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;    
    }
    
    public static void fechar(Connection connection){
        try {
            if(connection != null){
                if(!connection.isClosed()){
                    connection.close();
                }
            }
        } catch (SQLException ex) {
             System.out.println("erro:"+ex.getMessage());
        }
    }
    
    public static void fechar(Connection connection,Statement statement){
        fechar(connection);
        try {
            if(statement != null){
                if(!statement.isClosed()){
                    statement.close();
                }
            }
        } catch (SQLException ex) {
             System.out.println("erro:"+ex.getMessage());
        }
    }
    
}
