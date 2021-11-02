package classes;

public class Curso {
    private int idCurso;
    private String nomeCurso;
    private Area area;
    private NivelCurso nivelCurso;
    
    public void setIdCurso(int idCurso){
        this.idCurso = idCurso;
    }
    
    public void setNomeCurso(String nomeCurso){ 
       this.nomeCurso = nomeCurso;
    }
    
    public void setArea(Area area){
        this.area = area;
    }
    
    public void setNivelCurso(NivelCurso nivelCurso){
        this.nivelCurso = nivelCurso;
    }
    
    public int getIdCurso(){
        return idCurso;
    }
    
    public String getNomeCurso(){
        return nomeCurso;
    }
    
    public Area getArea(){
        return area;
    }
    
    public NivelCurso getNivelCurso(){
        return nivelCurso;
    }
    
}
