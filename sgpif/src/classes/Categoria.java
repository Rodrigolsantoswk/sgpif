package classes;

public class Categoria {
    private int idCategoria;
    private String nomeCategoria;
    
    public void setIdCategoria(int idCategoria){
        this.idCategoria = idCategoria;
    }
    
    public void setNomeCategoria(String nomeCategoria){
        this.nomeCategoria = nomeCategoria;
    }
    
    public int getIdCategoria(){
        return idCategoria;
    }
    
    public String getNomeCategoria(){
        return nomeCategoria;
    }
}
