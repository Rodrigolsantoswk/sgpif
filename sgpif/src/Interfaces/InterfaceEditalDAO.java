package Interfaces;

import classes.Edital;
import java.util.List;

public interface InterfaceEditalDAO {
    public List<classes.Edital> selecionarEdital();
    public boolean deletarEdital(int idEdital);
    public boolean inserirEdital(Edital edital);
    public boolean alterarNomeEdital(Edital edital);
    public boolean alterarModalidade(Edital edital);
}
