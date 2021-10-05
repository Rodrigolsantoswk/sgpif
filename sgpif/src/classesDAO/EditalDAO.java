package classesDAO;

import Conexao.Conexao;
import Interfaces.InterfaceEditalDAO;
import classes.Edital;
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

public class EditalDAO implements InterfaceEditalDAO{
    
    Connection con;
    
    @Override
    public List<Edital> selecionarEdital() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("SELECT * FROM Edital e ")
                .append("INNER JOIN modalidade m ")
                .append("ON e.idModalidade = m.idModalidade ")
                .append("INNER JOIN nivelcurso nc ")
                .append("ON m.idNivelCurso = nc.idNivel; ");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Edital> ledital = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Edital e = new Edital();
                Modalidade m = new Modalidade();
                NivelCurso nc = new NivelCurso();
                
                nc.setIdNivelCurso(rs.getInt("idNivel"));
                nc.setNomeNivel(rs.getString("nomeNivel"));
                
                m.setIdModalidade(rs.getInt("idModalidade"));
                m.setNomeModalidade(rs.getString("nomeModalidade"));
                m.setNivelCurso(nc);
                
                e.setIdEdital(rs.getInt("idEdital"));
                e.setNomeEdital(rs.getString("nomeEdital"));
                e.setModalidade(m);

                ledital.add(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return ledital;
    }

    @Override
    public boolean deletarEdital(int idEdital) {
        String query = "delete from edital where idEdital = ?";

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idEdital);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditalDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirEdital(Edital edital) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO edital ")
                .append("(nomeEdital, idModalidade, anoLetivo) ")
                .append("VALUES ")
                .append("( ?, ?, ?);");

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, edital.getNomeEdital());
            stmt.setInt(2, edital.getModalidade().getIdModalidade());
            stmt.setInt(3, edital.getAnoLetivo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditalDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarNomeEdital(Edital edital) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE edital ")
                .append("SET endereco = ? ")
                .append("WHERE idEdital = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, edital.getNomeEdital());
            stmt.setInt(2, edital.getIdEdital());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditalDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarModalidade(Edital edital) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE edital ")
                .append("SET idModalidade = ? ")
                .append("WHERE idEdital = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, edital.getModalidade().getIdModalidade());
            stmt.setInt(2, edital.getIdEdital());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditalDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
