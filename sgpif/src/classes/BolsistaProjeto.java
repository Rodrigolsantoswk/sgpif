package classes;

public class BolsistaProjeto {
    private int idBolsistaProjeto;
    private Projeto projeto;
    private Bolsista bolsista;
    private  Statusbp statusbp;
    private String dataInicio;
    private String dataFim;
    
    public void setIdBolsistaProjeto(int idBolsistaProjeto){
        this.idBolsistaProjeto = idBolsistaProjeto;
    }
    
    public void setProjeto(Projeto projeto){
        this.projeto = projeto;
    }
    
    public void setBolsista(Bolsista bolsista){
        this.bolsista = bolsista;
    }
    
    public void setStatusbp(Statusbp statusbp){
        this.statusbp = statusbp;
    }
    
    public void setDataInicio(String dataInicio){
        this.dataInicio = dataInicio;
    }
    
    public void setDataFim(String dataFim){
        this.dataFim = dataFim;
    }
    
    public int getIdBolsistaProjeto(){
        return idBolsistaProjeto;
    }
    
    public Projeto getProjeto(){
        return projeto;
    }
    
    public Bolsista getBolsista(){
        return bolsista;
    }
    
    public Statusbp getStatusbp(){
        return statusbp;
    }
    
    public String getDataInicio(){
        return dataInicio;
    }
    
    public String getDataFim(){
        return dataFim;
    }
    
}
