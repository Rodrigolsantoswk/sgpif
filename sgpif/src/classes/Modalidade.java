package classes;

public class Modalidade {
    public int idModalidade;
    public String nomeModalidade;
    public NivelCurso nivelCurso;
    
    public void setIdModalidade(int idModalidade){
        this.idModalidade =  idModalidade;
    }
    
    public void setNomeModalidade(String nomeModalidade){
        this.nomeModalidade = nomeModalidade;
    }
    
    public void setNivelCurso(NivelCurso nivelCurso){
        this.nivelCurso = nivelCurso;
    }
    
    public int getIdModalidade(){
        return idModalidade;
    }
    
    public String getNomeModalidade(){
        return nomeModalidade;
    }
    
    public NivelCurso getNivelCurso(){
        return nivelCurso;
    }
}
