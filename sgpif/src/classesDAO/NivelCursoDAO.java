package classesDAO;

import Conexao.Conexao;
import Interfaces.InterfaceNivelCursoDAO;
import classes.NivelCurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NivelCursoDAO implements InterfaceNivelCursoDAO{
    
    Connection con;
    
    @Override
    public List<NivelCurso> selecionarNivelCurso() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from nivelcurso");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<NivelCurso> lnc = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                NivelCurso nc = new NivelCurso();

                nc.setIdNivelCurso(rs.getInt("idNivel"));
                nc.setNomeNivel(rs.getString("nomeNivel"));

                lnc.add(nc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NivelCursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lnc;
    }

    @Override
    public boolean deletarNivelCurso(int idNivelCurso) {
        String sql = "delete from nivelcusro where idNivel = ?";
        
        con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idNivelCurso);
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NivelCursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirNivelCurso(NivelCurso nivelCurso) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO nivelcurso ")
                  .append("(nomeNivel) ")
                  .append("VALUES ")
                  .append("(?);");
        
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, nivelCurso.getNomeNivel());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NivelCursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarNomeNivelCurso(NivelCurso nivelCurso) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE nivelcurso" )
                  .append("SET nomeNivel = ? ")
                  .append("WHERE idNivel = ?");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, nivelCurso.getNomeNivel());
            stmt.setInt(2, nivelCurso.getIdNivel());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NivelCursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
}
