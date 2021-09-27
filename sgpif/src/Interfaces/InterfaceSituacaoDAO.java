package Interfaces;

import classes.Situacao;
import java.util.List;

public interface InterfaceSituacaoDAO {
    public List<classes.Situacao> selecionarSituacao();
    public boolean deletarSituacao(int idSituacao);
    public boolean inserirSituacao(Situacao sit);
    public boolean alterarNomeSituacao(Situacao s);
}
