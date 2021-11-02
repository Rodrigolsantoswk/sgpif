package classesDAO;

import Interfaces.InterfaceAreaDAO;
import classes.Area;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Conexao.Conexao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AreaDAO implements InterfaceAreaDAO{
    
    Connection con;
    
    @Override
    public List<Area> selecionarAreas() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from area");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Area> areas = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Area a = new Area();

                a.setIdArea(rs.getInt("idArea"));
                a.setNomeArea(rs.getString("nomeArea"));

                areas.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return areas;
    }

    @Override
    public boolean deletarArea(int idArea) {
        String sql = "delete from area where idArea = ?";
        
        con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idArea);
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirArea(Area Area) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO area ")
                  .append("(nomeArea) ")
                  .append("VALUES ")
                  .append("(?);");
        
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, Area.getNomeArea());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
        
    }

    @Override
    public boolean alterarNomeArea(Area area) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE area" )
                  .append("SET nomeArea = ? ")
                  .append("WHERE idArea = ?");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, area.getNomeArea());
            stmt.setInt(2, area.getIdArea());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        } 
    }
    
}
