package Interfaces;

import classes.Bolsista;
import java.util.List;

public interface InterfaceBolsistaDAO {
    public List<classes.Bolsista> selecionarBolsista();
    public boolean deletarBolsista(int idBolsista);
    public boolean inserirBolsista(Bolsista bolsista);
    public boolean alterarCursoBolsista(Bolsista bolsista);
    public boolean alterarCodBancoBolsista(Bolsista bolsista);
    public boolean alterarCodAgenciaBolsista(Bolsista bolsista);
    public boolean alterarNumContaBolsista(Bolsista bolsista);
}
