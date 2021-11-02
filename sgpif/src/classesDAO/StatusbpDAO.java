package classesDAO;

import Conexao.Conexao;
import classes.Statusbp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Interfaces.InterfaceStatusbpDAO;

public class StatusbpDAO implements InterfaceStatusbpDAO{
    
    Connection con;
    
    @Override
    public List<Statusbp> selecionarStatusbp() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from statusbp");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Statusbp> lstatusbp = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Statusbp sbp = new Statusbp();

                sbp.setIdStatusbp(rs.getInt("idStatusBP"));
                sbp.setNomeStatus(rs.getString("nomeStatus"));

                lstatusbp.add(sbp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StatusbpDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lstatusbp;
    }

    @Override
    public boolean deletarStatusbp(int idStatusbp) {
        String sql = "delete from statusbp where idStatusBP = ?";
        
        con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idStatusbp);
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusbpDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirStatusbp(Statusbp sbp) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO statusbp ")
                  .append("(nomeStatus) ")
                  .append("VALUES ")
                  .append("(?);");
        
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, sbp.getNomeStatus());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusbpDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarStatusbp(Statusbp sbp) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE statusbp" )
                  .append("SET nomeStatus = ? ")
                  .append("WHERE idStatusBP = ?");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, sbp.getNomeStatus());
            stmt.setInt(2, sbp.getIdStatusbp());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusbpDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
}
