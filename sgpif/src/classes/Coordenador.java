package classes;

public class Coordenador {
    private int SIAPE;
    private String nome;
    private String endereco;
    private Area area;
    private Categoria Categoria;
    
    public void setSIAPE(int SIAPE){
        this.SIAPE = SIAPE;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public void setArea(Area Area){
        this.area = area;
    }
    
    public void setCategoria(Categoria Categoria){
        this.Categoria = Categoria;
    }
    
    public int getSiape(){
        return SIAPE;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public Area getArea(){
        return area;
    }
    
    public Categoria getCategoria(){
        return Categoria;
    }
    
}
