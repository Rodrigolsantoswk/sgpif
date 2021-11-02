package sgpif;

import Conexao.Conexao;
import Interfaces.InterfaceAreaDAO;
import Interfaces.InterfaceProjetoDAO;
import classes.Area;
import classes.Projeto;
import classesDAO.AreaDAO;
import classesDAO.ProjetoDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Sgpif {
    
    public static void main(String[] args) {
        Conexao c = new Conexao();
        
        Connection conexao= c.getConnection();
        InterfaceProjetoDAO pjd = new ProjetoDAO();
        
        List<Projeto> lista = new ArrayList<>();
        
        lista = pjd.selecionarProjeto();
        
        System.out.println(lista);
        
        for(Projeto p: lista){
            System.out.println("IdProjeto: "+p.getIdProjeto()+" DataInicial: "+p.getDataInicial()+
                    " DataFinal: "+p.getDataFinal()+" Coordenador: "+p.getCoordenador().getNome());
            
        }
        
        InterfaceAreaDAO ad = new AreaDAO();
        
//        Area a = new Area();
//        a.setIdArea(0);
//        a.setNomeArea("area 4");
//
//        ad.inserirArea(a);
//        
        List<Area> list2 = new ArrayList<>();
        
        list2 = ad.selecionarAreas();
        
        for(Area area : list2){
            System.out.println("IdArea: "+area.getIdArea()+" nomeArea: "+area.getNomeArea());
        }
        
        
    }

}
