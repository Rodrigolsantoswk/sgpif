package sgpif;

import Conexao.Conexao;
import java.sql.Connection;

public class Sgpif {
    
    public static void main(String[] args) {
        Conexao c = new Conexao();
        
        Connection conexao=c.getConnection();
    }
}
