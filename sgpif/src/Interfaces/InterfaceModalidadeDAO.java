package Interfaces;

import classes.Modalidade;
import java.util.List;

public interface InterfaceModalidadeDAO {
    public List<classes.Modalidade> selecionarModalidade();
    public boolean deletarModalidade(int idModalidade);
    public boolean inserirModalidade(Modalidade modalidade);
    public boolean alterarNomeModalidade(Modalidade modalidade);
    public boolean alterarNivelModalidade(Modalidade modalidade);
}
