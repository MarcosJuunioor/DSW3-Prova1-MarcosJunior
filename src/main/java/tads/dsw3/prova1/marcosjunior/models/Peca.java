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
public class Peca extends Mensagem{
    private Celula[] celulas;
    private String tipoPeca;
    private boolean emMovimento;
    
    public Peca(String tipoPeca){
        this.celulas = new Celula[4];
        
        switch(tipo){
            case "I":
                celulas[0] = new Celula(1, "D"); 
                celulas[1] = new Celula(1, "E"); 
                celulas[2] = new Celula(1, "F"); 
                celulas[3] = new Celula(1, "G"); 
                break;
            case "L":
                celulas[0] = new Celula(1, "E"); 
                celulas[1] = new Celula(2, "E"); 
                celulas[2] = new Celula(3, "E"); 
                celulas[3] = new Celula(3, "F"); 
                break;
            case "O":
                celulas[0] = new Celula(1, "E"); 
                celulas[1] = new Celula(2, "E"); 
                celulas[2] = new Celula(1, "F"); 
                celulas[3] = new Celula(2, "F"); 
                break;
            case "S":
                celulas[0] = new Celula(1, "E"); 
                celulas[1] = new Celula(1, "F"); 
                celulas[2] = new Celula(2, "D"); 
                celulas[3] = new Celula(2, "E");
                break;
            case "T":
                celulas[0] = new Celula(1, "D"); 
                celulas[1] = new Celula(1, "E"); 
                celulas[2] = new Celula(1, "F"); 
                celulas[3] = new Celula(2, "E");
                break;
            case "Z":
                celulas[0] = new Celula(1, "D"); 
                celulas[1] = new Celula(1, "E"); 
                celulas[2] = new Celula(2, "E"); 
                celulas[3] = new Celula(2, "F");
                break;
        }
        
        this.tipoPeca = tipoPeca;
        this.emMovimento = true;
    }

    /**
     * @return the celulas
     */
    public Celula[] getCelulas() {
        return celulas;
    }

    /**
     * @param celulas
     */
    public void setCelulas(Celula[] celulas) {
        this.celulas = celulas;
    }

    /**
     * @return the tipo
     */
    public String getTipoPeca() {
        return tipoPeca;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipoPeca(String tipoPeca) {
        this.tipoPeca = tipoPeca;
    }

    /**
     * @return the emMovimento
     */
    public boolean isEmMovimento() {
        return emMovimento;
    }

    /**
     * @param emMovimento the emMovimento to set
     */
    public void setEmMovimento(boolean emMovimento) {
        this.emMovimento = emMovimento;
    }
}
