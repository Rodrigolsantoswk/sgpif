package classes;

public class Edital {
    private int idEdital;
    private String nomeEdital;
    private int anoLetivo; //4 digitos
    private Modalidade modalidade;
    
    public void setIdEdital(int idEdital){
        this.idEdital = idEdital;
    }
    
    public void setNomeEdital(String nomeEdital){
        this.nomeEdital = nomeEdital;
    }
    
    public void setAnoLetivo(int anoLetivo){
        this.anoLetivo = anoLetivo;
    }
    
    public void setModalidade (Modalidade modalidade){
        this.modalidade = modalidade;
    }
    
    public int getIdEdital(){
        return idEdital;
    }
    
    public String getNomeEdital(){
        return nomeEdital;
    }
    
    public int getAnoLetivo(){
        return anoLetivo;
    }
    
    public Modalidade getModalidade(){
        return modalidade;
    }
    
    public boolean checkAno(int ano){
        return ano>2000 && ano <2999;
    }
    
}
