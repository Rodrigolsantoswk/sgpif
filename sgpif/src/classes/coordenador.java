package classes;

public class coordenador {
    public int SIAPE;
    public String nome;
    public String endereco;
    public area Area;
    public categoria Categoria;
    
    public void setSIAPE(int SIAPE){
        this.SIAPE = SIAPE;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public void setArea(area Area){
        this.Area = Area;
    }
    
    public void setCategoria(categoria Categoria){
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
    
    public area getArea(){
        return Area;
    }
    
    public categoria getCategoria(){
        return Categoria;
    }
    
}
