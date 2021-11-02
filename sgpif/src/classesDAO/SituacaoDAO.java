package classesDAO;

import Conexao.Conexao;
import Interfaces.InterfaceSituacaoDAO;
import classes.Situacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SituacaoDAO implements InterfaceSituacaoDAO{

    Connection con;
    
    @Override
    public List<Situacao> selecionarSituacao() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from situacao");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Situacao> lsitua = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Situacao s = new Situacao();

                s.setIdSituacao(rs.getInt("idSituacao"));
                s.setNomeSituacao(rs.getString("nomeSituacao"));

                lsitua.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SituacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lsitua;
    }

    @Override
    public boolean deletarSituacao(int idSituacao) {
        String sql = "delete from situacao where idSituacao = ?";
        
        con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idSituacao);
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SituacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirSituacao(Situacao sit) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO situacao ")
                  .append("(nomeSituacao) ")
                  .append("VALUES ")
                  .append("(?);");
        
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, sit.getNomeSituacao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SituacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarNomeSituacao(Situacao s) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE situacao" )
                  .append("SET nomeSituacao = ? ")
                  .append("WHERE idSituacao = ?");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, s.getNomeSituacao());
            stmt.setInt(2, s.getIdSituacao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SituacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        } 
    }
    
}
