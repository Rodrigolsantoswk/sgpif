package Interfaces;

import classes.Projeto;
import java.util.List;

public interface InterfaceProjetoDAO {
    public List<classes.Projeto> selecionarProjeto();
    public boolean deletarProjeto(int idProjeto);
    public boolean inserirProjeto(Projeto projeto);
    public boolean alterarNomeProjeto(Projeto projeto);
    public boolean alterarSituacaoProjeto(Projeto projeto);
    public boolean alterarDataFinalProjeto(Projeto projeto);
    public boolean alterarDataRelatorio1Projeto(Projeto projeto);
    public boolean alterarDataRelatorio2Projeto(Projeto projeto);
    public boolean alterarCoordenadorProjeto(Projeto projeto);
    public boolean alterarMotivoCancelamentoProjeto(Projeto projeto);
    public boolean alterarAgenciaFomentoProjeto(Projeto projeto); 
}
