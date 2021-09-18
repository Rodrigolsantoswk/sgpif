package Interfaces;

import java.util.List;

public interface InterfaceCoordenadorDAO {
    public List<classes.coordenador> selecionarCoordenadores();
    public boolean deletarCoordenador(int idCoordenador);
    public boolean inserirCoordenador(int SIAPE, String nome, String endereco, int idArea, int idCoordenador);
    public boolean alterarEndereco(int idCoordenador, String endereco);
}
