package Interfaces;

import java.util.List;
import classes.coordenador;

public interface InterfaceCoordenadorDAO {
    public List<classes.coordenador> selecionarCoordenadores();
    public boolean deletarCoordenador(int idCoordenador);
    public boolean inserirCoordenador(coordenador Cord);
    public boolean alterarEndereco(int idCoordenador, String endereco);
}
