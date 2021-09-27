package Interfaces;

import java.util.List;
import classes.Coordenador;

public interface InterfaceCoordenadorDAO {
    public List<classes.Coordenador> selecionarCoordenadores();
    public boolean deletarCoordenador(int idCoordenador);
    public boolean inserirCoordenador(Coordenador Cord);
    public boolean alterarEndereco(Coordenador coord);
}
