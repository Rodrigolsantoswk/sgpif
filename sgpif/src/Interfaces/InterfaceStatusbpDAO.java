package Interfaces;

import classes.Statusbp;
import java.util.List;


public interface InterfaceStatusbpDAO {
    public List<classes.Statusbp> selecionarStatusbp();
    public boolean deletarStatusbp(int idStatusbp);
    public boolean inserirStatusbp(Statusbp sbp);
    public boolean alterarStatusbp(Statusbp sbp);
}
