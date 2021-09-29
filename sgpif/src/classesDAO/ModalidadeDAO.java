package classesDAO;

import Conexao.Conexao;
import Interfaces.InterfaceModalidadeDAO;
import classes.Modalidade;
import classes.NivelCurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModalidadeDAO implements InterfaceModalidadeDAO{
    Connection con;

    @Override
    public List<Modalidade> selecionarModalidade() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from curso ")
                .append("inner join nivelcurso ");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Modalidade> lmodalidade = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Modalidade m = new Modalidade();
                NivelCurso nc = new NivelCurso();
                
                m.setIdModalidade(rs.getInt("idModalidade"));
                m.setNomeModalidade(rs.getString("nomeModalidade"));
                
                nc.setIdNivelCurso(rs.getInt("idNivelCurso"));
                nc.setNomeNivel(rs.getString("nomeNivel"));

                m.setNivelCurso(nc);

                lmodalidade.add(m);

            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lmodalidade;
    }

    @Override
    public boolean deletarModalidade(int idModalidade) {
        String sql = "delete from modalidade where idModalidade = ?";
        
        con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idModalidade);
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModalidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirModalidade(Modalidade modalidade) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO modalidade ")
                  .append("(nomeModalidade, idNivelCurso) ")
                  .append("VALUES ")
                  .append("(?, ?);");
        
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, modalidade.getNomeModalidade());
            stmt.setInt(2, modalidade.getNivelCurso().getIdNivel());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModalidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarNomeModalidade(Modalidade modalidade) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE modalidade " )
                  .append("SET nomeModalidade = ? ")
                  .append("WHERE idModalidade = ?");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, modalidade.getNomeModalidade());
            stmt.setInt(2, modalidade.getIdModalidade());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModalidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        } 
    }

    @Override
    public boolean alterarNivelModalidade(Modalidade modalidade) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE modalidade " )
                  .append("SET idNivelCurso = ? ")
                  .append("WHERE idModalidade = ?");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, modalidade.getNivelCurso().getIdNivel());
            stmt.setInt(2, modalidade.getIdModalidade());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModalidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        } 
    }
    
}
