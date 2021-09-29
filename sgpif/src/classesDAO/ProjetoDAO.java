package classesDAO;

import Interfaces.InterfaceProjetoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Conexao.Conexao;
import classes.AgenciaFomento;
import classes.Area;
import classes.Categoria;
import classes.Coordenador;
import classes.Edital;
import classes.GrupoPesquisa;
import classes.Modalidade;
import classes.NivelCurso;
import classes.Projeto;
import classes.Situacao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjetoDAO implements InterfaceProjetoDAO{

    Connection con;
    
    @Override
    public List<Projeto> selecionarProjeto() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from allofproject order by dataInicial desc");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Projeto> lp = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Projeto p = new Projeto();
                AgenciaFomento af = new AgenciaFomento();
                GrupoPesquisa gp = new GrupoPesquisa();
                Situacao s = new Situacao();
                Edital e = new Edital();
                Modalidade m = new Modalidade();
                NivelCurso nc = new NivelCurso();
                Categoria cat = new Categoria();
                Area ar = new Area();
                Coordenador coord = new Coordenador();
                
                s.setIdSituacao(rs.getInt("idSituacao"));
                s.setNomeSituacao(rs.getString("nomeSituacao"));
                
                gp.setIdGrupoPesquisa(rs.getInt("idGrupoPesquisa"));
                gp.setNomeGrupo(rs.getString("nomeGrupoPesquisa"));
                
                af.setIdAgFomento(rs.getInt("idAgFomento"));
                af.setNomeAgFomento(rs.getString("nomeAgFomento"));
                
                nc.setIdNivelCurso(rs.getInt("idNivel"));
                nc.setNomeNivel(rs.getString("nomeNivel"));
                
                m.setIdModalidade(rs.getInt("idModalidade"));
                m.setNomeModalidade(rs.getString("nomeModalidade"));
                m.setNivelCurso(nc);
                
                e.setIdEdital(rs.getInt("idEdital"));
                e.setNomeEdital(rs.getString("nomeEdital"));
                e.setAnoLetivo(rs.getInt("anoLetivo"));
                e.setModalidade(m);
                
                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setNomeCategoria(rs.getString("nomeCategoria"));
                
                ar.setIdArea(rs.getInt("idArea"));
                ar.setNomeArea(rs.getString("nomeArea"));
                
                coord.setSIAPE(rs.getInt("SIAPE"));
                coord.setNome(rs.getString("nomeCoordenador"));
                coord.setEndereco(rs.getString("endereco"));
                coord.setArea(ar);
                coord.setCategoria(cat);
                
                p.setIdProjeto(rs.getInt("idProjeto"));
                p.setNomeProjeto(rs.getString("nomeProjeto"));
                p.setDataInicial(rs.getString("dataInicial"));
                p.setDataFinal(rs.getString("dataFinal"));
                p.setMotivoCancelamento(rs.getString("motivoCancelamento"));
                p.setDataRelatorio1(rs.getString("dataRelatorio1"));
                p.setDataRelatorio2(rs.getString("dataRelatorio2"));
                p.setEdital(e);
                p.setCoordenador(coord);
                p.setAgFomento(af);
                p.setSituacao(s);
                p.setGrupoPesquisa(gp);
                
                lp.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lp;
    }

    @Override
    public boolean deletarProjeto(int idProjeto) {
        String query = "delete from projeto where idProjeto = ?";

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idProjeto);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirProjeto(Projeto projeto) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO projeto ")
                .append("insert into projeto(nomeProjeto, idAgFomento, dataInicial, dataFinal, idSituacao, motivoCancelamento, idEdital, idGrupoPesquisa, siapeCoordenador, dataRelatorio1, dataRelatorio2) ")
                .append("VALUES ")
                .append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ;");

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setInt(2, projeto.getAgFomento().getIdAgFomento());
            stmt.setString(3, projeto.getDataInicial());
            stmt.setString(4, projeto.getDataFinal());
            stmt.setInt(5, projeto.getSituacao().getIdSituacao());
            stmt.setString(6, projeto.getMotivoCancelamento());
            stmt.setInt(7, projeto.getEdital().getIdEdital());
            stmt.setInt(8, projeto.getGrupoPesquisa().getIdGrupoPesquisa());
            stmt.setInt(9, projeto.getCoordenador().getSiape());
            stmt.setString(10, projeto.getDataRelatorio1());
            stmt.setString(11, projeto.getDataRelatorio2());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarNomeProjeto(Projeto projeto) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE projeto ")
                .append("SET nomeProjeto = ? ")
                .append("WHERE idProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setInt(2, projeto.getIdProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarSituacaoProjeto(Projeto projeto) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE projeto ")
                .append("SET idSituacao = ? ")
                .append("WHERE idProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, projeto.getSituacao().getIdSituacao());
            stmt.setInt(2, projeto.getIdProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarDataFinalProjeto(Projeto projeto) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE projeto ")
                .append("SET dataFinal = ? ")
                .append("WHERE idProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, projeto.getDataFinal());
            stmt.setInt(2, projeto.getIdProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarDataRelatorio1Projeto(Projeto projeto) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE projeto ")
                .append("SET dataRelatorio1 = ? ")
                .append("WHERE idProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, projeto.getDataRelatorio1());
            stmt.setInt(2, projeto.getIdProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarDataRelatorio2Projeto(Projeto projeto) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE projeto ")
                .append("SET dataRelatorio2 = ? ")
                .append("WHERE idProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, projeto.getDataRelatorio2());
            stmt.setInt(2, projeto.getIdProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarCoordenadorProjeto(Projeto projeto) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE projeto ")
                .append("SET siapeCoordenador = ? ")
                .append("WHERE idProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, projeto.getCoordenador().getSiape());
            stmt.setInt(2, projeto.getIdProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarMotivoCancelamentoProjeto(Projeto projeto) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE projeto ")
                .append("SET motivoCancelamento = ? ")
                .append("WHERE idProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, projeto.getMotivoCancelamento());
            stmt.setInt(2, projeto.getIdProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarAgenciaFomentoProjeto(Projeto projeto) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE projeto ")
                .append("SET idAgFomento = ? ")
                .append("WHERE idProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, projeto.getAgFomento().getIdAgFomento());
            stmt.setInt(2, projeto.getIdProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
}
