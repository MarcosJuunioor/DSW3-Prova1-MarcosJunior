/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.dsw3.prova1.marcosjunior.models;

/**
 *
 * @author marco
 */
public class Celula {
    private int linha;
    private String coluna;
    private boolean ocupada;
    
    public Celula(int linha, String coluna){
        this.linha = linha;
        this.coluna = coluna;
        this.ocupada = false;
    }

    /**
     * @return the linha
     */
    public int getLinha() {
        return linha;
    }

    /**
     * @param linha the linha to set
     */
    public void setLinha(int linha) {
        this.linha = linha;
    }

    /**
     * @return the coluna
     */
    public String getColuna() {
        return coluna;
    }

    /**
     * @param coluna the coluna to set
     */
    public void setColuna(String coluna) {
        this.coluna = coluna;
    }

    /**
     * @return the ocupada
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * @param ocupada the ocupada to set
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    
    
}
