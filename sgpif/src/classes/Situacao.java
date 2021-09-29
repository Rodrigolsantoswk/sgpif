package classes;

public class Situacao {
    private int idSituacao;
    private String nomeSituacao;
    
    public void setIdSituacao(int idSituacao){
        this.idSituacao = idSituacao;
    }
    
    public void setNomeSituacao(String nomeSituacao){
        this.nomeSituacao = nomeSituacao;
    }

    public int getIdSituacao(){
        return idSituacao;
    }
    
    public String getNomeSituacao(){
        return nomeSituacao;
    }
}
