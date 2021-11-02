package Interfaces;

import java.util.List;
import classes.BolsistaProjeto;

public interface InterfaceBolsistaProjetoDAO {
    public List<classes.BolsistaProjeto> selecionarBolsistaProjeto();
    public boolean deletarBolsistaProjeto(int idBolsistaProjeto);
    public boolean inserirBolsistaProjeto(BolsistaProjeto bp);
    public boolean alterarStatusBolsistaProjeto(BolsistaProjeto bp);
    public boolean alterarDataFimBolsistaProjeto(BolsistaProjeto bp);
}
