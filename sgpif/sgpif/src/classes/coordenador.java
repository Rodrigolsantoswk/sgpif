/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author uie30005
 */
public class coordenador {
    int SIAPE;
    String nome;
    String endereco;
    area Area;
    categoria Categoria;
    
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
    
    
    
}
