package Interfaces;

import java.util.List;
import classes.AgenciaFomento;

public interface InterfaceAgenciaFomentoDAO {
    public List<classes.AgenciaFomento> selecionarAgenciaFomento();
    public boolean deletarAgenciaFomento(int idAgFomento);
    public boolean inserirAgenciaFomento(AgenciaFomento ag);
    public boolean alterarAgenciaFomento(AgenciaFomento ag);
}
