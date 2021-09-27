package Interfaces;

import classes.GrupoPesquisa;
import java.util.List;

public interface InterfaceGrupoPesquisaDAO {
    public List<classes.GrupoPesquisa> selecionarGrupoPesquisa();
    public boolean deletarGrupoPesquisa(int idGrupoPesquisa);
    public boolean inserirGrupoPesquisa(GrupoPesquisa gp);
    public boolean alterarNomeGrupoPesquisa(GrupoPesquisa gp);
}
