package classes;

public class Projeto {
    private int idProjeto;
    private String nomeProjeto;
    public AgenciaFomento agFomento;
    private String dataInicial;
    private String dataFinal;
    public Situacao situacao;
    private String motivoCancelamento;
    public Edital edital;
    public GrupoPesquisa grupoPesquisa;
    public Coordenador coordenador;
    private String dataRelatorio1;
    private String dataRelatorio2;
    
    public void setIdProjeto(int idProjeto){
        this.idProjeto = idProjeto;
    }
    
    public void setNomeProjeto(String nomeProjeto){
        this.nomeProjeto = nomeProjeto;
    }
    
    public void setAgFomento(AgenciaFomento agFomento){
        this.agFomento = agFomento;
    }
    
    public void setDataInicial(String dataInicial){
        this.dataInicial = dataInicial;
    }
    
    public void setDataFinal(String dataFinal){
        this.dataFinal = dataFinal;
    }
    
    public void setSituacao(Situacao situacao){
        this.situacao = situacao;
    }
    
    public void setMotivoCancelamento(String motivoCancelamento){
        this.motivoCancelamento = motivoCancelamento;
    }
    
    public void setEdital(Edital edital){
        this.edital = edital;
    }
            
    public void setGrupoPesquisa (GrupoPesquisa grupoPesquisa){
        this.grupoPesquisa = grupoPesquisa;
    }
    
    public void setCoordenador(Coordenador coordenador){
        this.coordenador = coordenador;
    }
    
    public void setDataRelatorio1(String dataRelatorio1){
        this.dataRelatorio1 = dataRelatorio1;
    }
    
    public void setDataRelatorio2(String dataRelatorio2){
        this.dataRelatorio2 = dataRelatorio2;
    }
    
    public int getIdProjeto(){
        return idProjeto;
    }
    
    public String getNomeProjeto(){
        return nomeProjeto;
    }
    
    public AgenciaFomento getAgFomento(){
        return agFomento;
    }
    
    public String getDataInicial(){
        return dataInicial;
    }
    
    public String getDataFinal(){
        return dataFinal;
    }
    
    public Situacao getSituacao(){
        return situacao;
    }
    
    public String getMotivoCancelamento(){
        return motivoCancelamento;
    }
    
    public Edital getEdital(){
        return edital;
    }
    
    public GrupoPesquisa getGrupoPesquisa(){
        return grupoPesquisa;
    }
    
    public Coordenador getCoordenador(){
        return coordenador;
    }
    
    public String getDataRelatorio1(){
        return dataRelatorio1;
    }
    
    public String getDataRelatorio2(){
        return dataRelatorio2;
    }

}
