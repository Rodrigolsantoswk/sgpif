package classes;

public class Bolsista {
    private int idBolsista;
    private long matricula;
    private String nomeBolsista;
    private String endereco;
    private Curso curso;
    private int codBanco;
    private int codAgencia;
    private String numConta;
    
    public void setIdBolsista(int idBolsista){
        this.idBolsista = idBolsista;
    }
    
    public void setMatricula(long Matricula){
        this.matricula = matricula;
    }
    
    public void setNomeBolsista(String nomeBolsista){
        this.nomeBolsista = nomeBolsista;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public void setCurso(Curso curso){
        this.curso = curso;
    }
    
    public void setCodBanco(int codBanco){
        this.codBanco = codBanco;
    }
    
    public void setCodAgencia(int codAgencia){
        this.codAgencia = codAgencia;
    }
    
    public void setNumConta(String numConta){
        this.numConta = numConta;
    }
    
    public int getIdBolsista(){
        return idBolsista;
    }
    
    public long getMatricula(){
        return matricula;
    }
    
    public String getNomeBolsista(){
        return nomeBolsista;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public Curso getCurso(){
        return curso;
    }
    
    public int getCodBanco(){
        return codBanco;
    }
    
    public int getCodAgencia(){
        return codAgencia;
    }
    
    public String getNumConta(){
        return numConta;
    }
}
