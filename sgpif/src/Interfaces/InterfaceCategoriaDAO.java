package Interfaces;

import classes.Categoria;
import java.util.List;

public interface InterfaceCategoriaDAO {
    public List<classes.Categoria> selecionarCategorias();
    public boolean deletarCategoria(int idCategoria);
    public boolean inserirCategoria(Categoria Categoria);
    public boolean alterarNomeCategoria(Categoria Categoria);
}
