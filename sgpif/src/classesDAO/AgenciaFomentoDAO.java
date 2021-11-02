package classesDAO;

import Conexao.Conexao;
import Interfaces.InterfaceAgenciaFomentoDAO;
import classes.AgenciaFomento;
import classes.Area;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgenciaFomentoDAO implements InterfaceAgenciaFomentoDAO{
    
    Connection con;
    
    @Override
    public List<AgenciaFomento> selecionarAgenciaFomento() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from agenciafomento");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AgenciaFomento> lag = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                AgenciaFomento ag = new AgenciaFomento();

                ag.setIdAgFomento(rs.getInt("idAgFomento"));
                ag.setNomeAgFomento(rs.getString("nomeAgFomento"));

                lag.add(ag);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AgenciaFomentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lag;
    }

    @Override
    public boolean deletarAgenciaFomento(int idAgFomento) {
        String sql = "delete from agenciafomento where idAgFomento = ?";
        
        con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idAgFomento);
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaFomentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirAgenciaFomento(AgenciaFomento ag) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO agenciafomento ")
                  .append("(nomeAgFomento) ")
                  .append("VALUES ")
                  .append("(?);");
        
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, ag.getNomeAgFomento());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaFomentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarAgenciaFomento(AgenciaFomento ag) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE agenciafomento " )
                  .append("SET nomeAgFomento = ? ")
                  .append("WHERE idAgFomento = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, ag.getNomeAgFomento());
            stmt.setInt(2, ag.getIdAgFomento());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaFomentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        } 
    }
    
}
