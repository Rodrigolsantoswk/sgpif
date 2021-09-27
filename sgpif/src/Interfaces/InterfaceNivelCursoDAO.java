package Interfaces;

import classes.NivelCurso;
import java.util.List;

public interface InterfaceNivelCursoDAO {
    public List<classes.NivelCurso> selecionarNivelCurso();
    public boolean deletarNivelCurso(int idNivelCurso);
    public boolean inserirNivelCurso(NivelCurso nivelCurso);
    public boolean alterarNomeNivelCurso(NivelCurso nivelCurso);
    
}
