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
public class categoria {
    public int idCategoria;
    public String nomeCategoria;
    
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
