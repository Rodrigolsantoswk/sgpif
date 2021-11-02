package Interfaces;

import java.util.List;
import classes.Area;

public interface InterfaceAreaDAO  {
    public List<classes.Area> selecionarAreas();
    public boolean deletarArea(int idArea);
    public boolean inserirArea(Area Area);
    public boolean alterarNomeArea(Area Area);
}
