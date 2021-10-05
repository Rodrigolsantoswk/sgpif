package classesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Interfaces.InterfaceBolsistaDAO;
import classes.Area;
import classes.Bolsista;
import classes.Curso;
import classes.NivelCurso;

public class BolsistaDAO implements InterfaceBolsistaDAO {

    Connection con;
    
    @Override
    public List<Bolsista> selecionarBolsista() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select b.idBolsista, b.matricula, b.nomeBolsista ")
                .append(", b.endereco, b.idCurso, b.codBanco, b.codAgencia, b.numConta ")
                .append(", c.idCurso, c.nomeCurso, a.idArea, a.nomeArea, nc.idNivel, nc.nomeNivel ")
                .append("from bolsista b ")
                .append("inner join curso c on b.idCurso = c.idCurso ")
                .append("inner join area a ON a.idArea = c.idArea ")
                .append("inner join nivelcurso nc on nc.idNivel = c.idNivelCurso; ");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bolsista> lb = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bolsista b = new Bolsista();
                Curso c = new Curso();
                Area a = new Area();
                NivelCurso nc = new NivelCurso();
                
                nc.setIdNivelCurso(rs.getInt("idNivel"));
                nc.setNomeNivel(rs.getString("nomeNivel"));
                
                a.setIdArea(rs.getInt("idArea"));
                a.setNomeArea(rs.getString("nomeArea"));
                
                c.setIdCurso(rs.getInt("idCurso"));
                c.setNomeCurso(rs.getString("nomeCurso"));
                c.setArea(a);
                c.setNivelCurso(nc);
                
                b.setIdBolsista(rs.getInt("idBolsista"));
                b.setMatricula(rs.getLong("matricula"));
                b.setNomeBolsista(rs.getString("nomeBolsista"));
                b.setEndereco(rs.getString("endereco"));
                b.setCurso(c);
                
                lb.add(b);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lb;
    }

    @Override
    public boolean deletarBolsista(int idBolsista) {
        String query = "delete from bolsista where idBolsista = ?";

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idBolsista);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BolsistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirBolsista(Bolsista bolsista) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO bolsista ")
                .append("(matricula, nomeBolsista, endereco, idCurso, codBanco, codAgencia, numConta) ")
                .append("VALUES ")
                .append("( ?, ?, ?, ?, ?, ?, ? );");

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setLong(1, bolsista.getMatricula());
            stmt.setString(2, bolsista.getNomeBolsista());
            stmt.setString(3, bolsista.getEndereco());
            stmt.setInt(4, bolsista.getCurso().getIdCurso());
            stmt.setInt(5, bolsista.getCodBanco());
            stmt.setInt(6, bolsista.getCodAgencia());
            stmt.setString(7, bolsista.getNumConta());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BolsistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarCursoBolsista(Bolsista bolsista) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE bolsista ")
                .append("SET idCurso = ? ")
                .append("WHERE idBolsista = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, bolsista.getCurso().getIdCurso());
            stmt.setInt(2, bolsista.getIdBolsista());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BolsistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
        
    }

    @Override
    public boolean alterarCodBancoBolsista(Bolsista bolsista) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE bolsista ")
                .append("SET codBanco = ? ")
                .append("WHERE idBolsista = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, bolsista.getCodBanco());
            stmt.setInt(2, bolsista.getIdBolsista());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BolsistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarCodAgenciaBolsista(Bolsista bolsista) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE bolsista ")
                .append("SET codAgencia = ? ")
                .append("WHERE idBolsista = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, bolsista.getCodAgencia());
            stmt.setInt(2, bolsista.getIdBolsista());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BolsistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarNumContaBolsista(Bolsista bolsista) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE bolsista ")
                .append("SET numConta = ? ")
                .append("WHERE idBolsista = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, bolsista.getNumConta());
            stmt.setInt(2, bolsista.getIdBolsista());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BolsistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
