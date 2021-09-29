package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sgpif";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName(DRIVER);
            con = (java.sql.Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        return con;
    }
    
    public static void closeConnection(Connection con){
        if(con != null){
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }
    }
    
        public static void closeConnection(Connection con, PreparedStatement stmt){
        
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                 System.err.println("Erro: "+ex);
            }
        }
        closeConnection(con);
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                 System.err.println("Erro: "+ex);
            }
        }
        closeConnection(con, stmt);
    } 
    
}
