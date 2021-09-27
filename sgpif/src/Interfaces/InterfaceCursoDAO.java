package Interfaces;

import classes.Curso;
import java.util.List;

public interface InterfaceCursoDAO {
    public List<classes.Curso> selecionarCursos();
    public boolean deletarCurso(int idCurso);
    public boolean inserirCurso(Curso curso);
    public boolean alterarNomeCurso(Curso c);
}
