package Interfaces;

import java.util.List;
import classes.area;

public interface InterfaceAreaDAO  {
    public List<classes.area> selecionarAreas();
    public boolean deletarArea(int idArea);
    public boolean inserirArea(area Area);
    public boolean alterarNomeArea(area Area);
}
