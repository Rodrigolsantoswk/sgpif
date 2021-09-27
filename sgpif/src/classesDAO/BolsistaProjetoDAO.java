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
import Interfaces.InterfaceBolsistaProjetoDAO;
import classes.AgenciaFomento;
import classes.Area;
import classes.Bolsista;
import classes.BolsistaProjeto;
import classes.Categoria;
import classes.Coordenador;
import classes.Curso;
import classes.Edital;
import classes.GrupoPesquisa;
import classes.Modalidade;
import classes.NivelCurso;
import classes.Projeto;
import classes.Situacao;
import classes.Statusbp;

public class BolsistaProjetoDAO implements InterfaceBolsistaProjetoDAO{
    Connection con;

    @Override
    public List<BolsistaProjeto> selecionarBolsistaProjeto() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select * from allofbolsistaprojeto ");
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BolsistaProjeto> lbp = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                BolsistaProjeto bp = new BolsistaProjeto();
                Projeto p = new Projeto();
                Statusbp sbp = new Statusbp();
                Bolsista bo = new Bolsista();
                Curso cb = new Curso();
                Area acb = new Area();
                NivelCurso nccb = new NivelCurso();
                Coordenador cp = new Coordenador();
                Area aco = new Area();
                Categoria ccop = new Categoria();
                AgenciaFomento af = new AgenciaFomento();
                Edital e = new Edital();
                Modalidade m = new Modalidade();
                NivelCurso ncmep = new NivelCurso();
                GrupoPesquisa gp = new GrupoPesquisa();
                Situacao s = new Situacao();

                s.setIdSituacao(rs.getInt("idSituacao"));
                s.setNomeSituacao(rs.getString("nomeSituacao"));
                
                gp.setIdGrupoPesquisa(rs.getInt("idGrupoPesquisa"));
                gp.setNomeGrupo(rs.getString("nomeGrupoPesquisa"));
                
                ncmep.setIdNivelCurso(rs.getInt("nivelCursoModalidadeProjeto"));
                ncmep.setNomeNivel(rs.getString("nomeNivelModalidadeProjeto"));
                
                m.setIdModalidade(rs.getInt("idModalidade"));
                m.setNomeModalidade(rs.getString("nomeModalidade"));
                m.setNivelCurso(ncmep);
                
                e.setIdEdital(rs.getInt("idModalidade"));
                e.setNomeEdital(rs.getString("nomeEdital"));
                e.setAnoLetivo(rs.getInt("anoLetivo"));
                e.setModalidade(m);
                
                af.setIdAgFomento(rs.getInt("idAgFomento"));
                af.setNomeAgFomento(rs.getString("nomeAgFomento"));
                
                ccop.setIdCategoria(rs.getInt("idCategoriaCoordenadorProjeto"));
                ccop.setNomeCategoria(rs.getString("nomeCategoriaCoordenadorProjeto"));
                
                aco.setIdArea(rs.getInt("areaCoordenadorProjeto"));
                aco.setNomeArea(rs.getString("nomeAreaCoordenadorProjeto"));
                
                cp.setSIAPE(rs.getInt("SIAPE"));
                cp.setNome(rs.getString("nomeCoordenador"));
                cp.setEndereco(rs.getString("enderecoCoordenador"));
                cp.setCategoria(ccop);
                cp.setArea(aco);
                
                nccb.setIdNivelCurso(rs.getInt("idNivelCursoBolsista"));
                nccb.setNomeNivel(rs.getString("nomeNivelCursoBolsista"));
                
                acb.setIdArea(rs.getInt("idAreaCursoBolsista"));
                acb.setNomeArea(rs.getString("nomeAreaCursoBolsista"));
                
                cb.setIdCurso(rs.getInt("idCursoBolsista"));
                cb.setNomeCurso(rs.getString("nomeCursoBolsista"));
                
                p.setIdProjeto(rs.getInt("idProjeto"));
                p.setNomeProjeto(rs.getString("nomeProjeto"));
                p.setMotivoCancelamento(rs.getString("motivoCancelamento"));
                p.setDataInicial(rs.getString("dataInicial"));
                p.setDataFinal(rs.getString("datFinal"));
                p.setDataRelatorio1(rs.getString("dataRelatorio1"));
                p.setDataRelatorio2(rs.getString("dataRelatorio2"));
                p.setCoordenador(cp);
                p.setEdital(e);
                p.setGrupoPesquisa(gp);
                p.setSituacao(s);
                p.setAgFomento(af);
                
                sbp.setIdStatusbp(rs.getInt("idStatusBP"));
                sbp.setNomeStatus(rs.getString("nomeStatus"));
                
                bo.setIdBolsista(rs.getInt("idBolsista"));
                bo.setMatricula(rs.getInt("matricula"));
                bo.setNomeBolsista(rs.getString("nomeBolsista"));
                bo.setEndereco(rs.getString("enderecoBolsista"));
                bo.setCodBanco(rs.getInt("codBanco"));
                bo.setCodAgencia(rs.getInt("codAgencia"));
                bo.setNumConta(rs.getString("numConta"));
                bo.setCurso(cb);
                
                bp.setIdBolsistaProjeto(rs.getInt("idBolsistaProjeto"));
                bp.setDataInicio(rs.getString("dataInicio"));
                bp.setDataFim(rs.getString("dataFim"));
                bp.setBolsista(bo);
                bp.setProjeto(p); 
                
                lbp.add(bp);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lbp;
    }

    @Override
    public boolean deletarBolsistaProjeto(int idBolsistaProjeto) {
        String query = "delete from bolsistaprojeto where idBolsistaProjeto = ?";

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idBolsistaProjeto);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BolsistaProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean inserirBolsistaProjeto(BolsistaProjeto bp) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("INSERT INTO bolsistaprojeto ")
                .append("(idProjeto, idBolsista, idStatusBP, dataInicio, dataFim) ")
                .append("VALUES ")
                .append("( ?, ?, ?, ?, ?);");

        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, bp.getProjeto().getIdProjeto());
            stmt.setInt(2, bp.getBolsista().getIdBolsista());
            stmt.setInt(3, bp.getStatusbp().getIdStatusbp());
            stmt.setString(4, bp.getDataInicio());
            stmt.setString(5, bp.getDataFim());
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
    public boolean alterarStatusBolsistaProjeto(BolsistaProjeto bp) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE bolsistaprojeto ")
                .append("SET idStatusBP = ? ")
                .append("WHERE idBolsistaProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setInt(1, bp.getStatusbp().getIdStatusbp());
            stmt.setInt(2, bp.getIdBolsistaProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BolsistaProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    @Override
    public boolean alterarDataFimBolsistaProjeto(BolsistaProjeto bp) {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("UPDATE bolsistaprojeto ")
                .append("SET dataFim = ? ")
                .append("WHERE idBolsistaProjeto = ? ");
        con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SqlBuilder.toString());
            stmt.setString(1, bp.getDataFim());
            stmt.setInt(2, bp.getIdBolsistaProjeto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BolsistaProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
