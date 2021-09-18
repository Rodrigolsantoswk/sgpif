package classesDAO;

import Conexao.Conexao;
import Interfaces.InterfaceCategoriaDAO;
import classes.categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class categoriaDAO implements InterfaceCategoriaDAO {
    
    Connection con;
    
    @Override
    public List<categoria> selecionarCategorias() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from categoria");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<categoria> categs = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                categoria c = new categoria();

                c.setIdCategoria(rs.getInt("idCategoria"));
                c.setNomeCategoria(rs.getString("nomeCategoria"));

                categs.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return categs;
    }

    @Override
    public boolean deletarCategoria(int idCategoria) {
        String sql = "delete from categoria where idCategoria = ?";
        
        con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(categoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirCategoria(categoria Categoria) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO categoria ")
                  .append("(nomeCategoria) ")
                  .append("VALUES ")
                  .append("(?);");
        
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, Categoria.getNomeCategoria());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(categoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarNomeCategoria(categoria Categoria) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE categoria" )
                  .append("SET nomeCategoria = ? ")
                  .append("WHERE idCategoria = ?");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, Categoria.getNomeCategoria());
            stmt.setInt(2, Categoria.getIdCategoria());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(categoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
     
}
