package classesDAO;

import Conexao.Conexao;
import Interfaces.InterfaceCursoDAO;
import classes.Area;
import classes.Curso;
import classes.NivelCurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CursoDAO implements InterfaceCursoDAO {

    Connection con;

    @Override
    public List<Curso> selecionarCursos() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from curso ")
                .append("inner join nivelcurso ON c.idNivelCurso = nc.idNivel ")
                .append("inner join area ON c.idArea = a.idArea; ");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Curso> lcurso = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Curso c = new Curso();
                NivelCurso nc = new NivelCurso();
                Area a = new Area();
                c.setIdCurso(rs.getInt("idCurso"));
                c.setNomeCurso(rs.getString("nomeCurso"));

                nc.setIdNivelCurso(rs.getInt("idNivelCurso"));
                nc.setNomeNivel(rs.getString("nomeNivel"));

                a.setIdArea(rs.getInt("idArea"));
                a.setNomeArea(rs.getString("nomeArea"));

                c.setNivelCurso(nc);
                c.setArea(a);

                lcurso.add(c);

            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lcurso;
    }

    @Override
    public boolean deletarCurso(int idCurso) {
        String query = "delete from curso where idCurso = ?";

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idCurso);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirCurso(Curso curso) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("insert into curso ")
                .append("(nomeCurso, idArea, idNivelCurso) ")
                .append("VALUES ")
                .append("( ?, ?, ?);");

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, curso.getNomeCurso());
            stmt.setInt(2, curso.getArea().getIdArea());
            stmt.setInt(3, curso.getNivelCurso().getIdNivel());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarNomeCurso(Curso c) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE curso ")
                .append("SET nomeCurso = ? ")
                .append("WHERE idCurso = ?");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, c.getNomeCurso());
            stmt.setInt(2, c.getIdCurso());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

}
