package Interfaces;

import classes.categoria;
import java.util.List;

public interface InterfaceCategoriaDAO {
    public List<classes.categoria> selecionarCategorias();
    public boolean deletarCategoria(int idCategoria);
    public boolean inserirCategoria(categoria Categoria);
    public boolean alterarNomeCategoria(categoria Categoria);
}
