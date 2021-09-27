package classesDAO;

import Conexao.Conexao;
import Interfaces.InterfaceGrupoPesquisaDAO;
import classes.GrupoPesquisa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrupoPesquisaDAO implements InterfaceGrupoPesquisaDAO{
    Connection con;

    @Override
    public List<GrupoPesquisa> selecionarGrupoPesquisa() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from grupopesquisa");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<GrupoPesquisa> lgp = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                GrupoPesquisa gp = new GrupoPesquisa();

                gp.setIdGrupoPesquisa(rs.getInt("idGrupoPesquisa"));
                gp.setNomeGrupo(rs.getString("nome"));

                lgp.add(gp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GrupoPesquisaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lgp;
    }

    @Override
    public boolean deletarGrupoPesquisa(int idGrupoPesquisa) {
        String sql = "delete from grupopesquisa where idGrupoPesquisa = ?";
        
        con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idGrupoPesquisa);
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GrupoPesquisaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }       
    }

    @Override
    public boolean inserirGrupoPesquisa(GrupoPesquisa gp) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO grupopesquisa ")
                  .append("(nome) ")
                  .append("VALUES ")
                  .append("(?);");
        
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, gp.getNomeGrupo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GrupoPesquisaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarNomeGrupoPesquisa(GrupoPesquisa gp) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE grupopesquisa" )
                  .append("SET nome = ? ")
                  .append("WHERE idGrupoPesquisa = ?");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, gp.getNomeGrupo());
            stmt.setInt(2, gp.getIdGrupoPesquisa());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GrupoPesquisaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
