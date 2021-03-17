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
public class Peca extends Mensagem {

    private Celula[] celulas;
    private String tipoPeca;
    private boolean emMovimento;
    private String cor;

    public Peca(String tipoPeca) {
        this.celulas = new Celula[4];

        switch (tipoPeca) {
            case "I":
                this.celulas[0] = new Celula(1, "D", "white");
                this.celulas[1] = new Celula(1, "E", "white");
                this.celulas[2] = new Celula(1, "F", "white");
                this.celulas[3] = new Celula(1, "G", "white");
                this.cor = "white";
                break;
            case "L":
                this.celulas[0] = new Celula(1, "E", "red");
                this.celulas[1] = new Celula(2, "E", "red");
                this.celulas[2] = new Celula(3, "E", "red");
                this.celulas[3] = new Celula(3, "F", "red");
                this.cor = "red";
                break;
            case "O":
                this.celulas[0] = new Celula(1, "E", "green");
                this.celulas[1] = new Celula(2, "E", "green");
                this.celulas[2] = new Celula(1, "F", "green");
                this.celulas[3] = new Celula(2, "F", "green");
                this.cor = "green";
                break;
            case "S":
                this.celulas[0] = new Celula(1, "E", "yellow");
                this.celulas[1] = new Celula(1, "F", "yellow");
                this.celulas[2] = new Celula(2, "D", "yellow");
                this.celulas[3] = new Celula(2, "E", "yellow");
                this.cor = "yellow";
                break;
            case "T":
                this.celulas[0] = new Celula(1, "D", "blue");
                this.celulas[1] = new Celula(1, "E", "blue");
                this.celulas[2] = new Celula(1, "F", "blue");
                this.celulas[3] = new Celula(2, "E", "blue");
                this.cor = "blue";
                break;
            case "Z":
                this.celulas[0] = new Celula(1, "D", "pink");
                this.celulas[1] = new Celula(1, "E", "pink");
                this.celulas[2] = new Celula(2, "E", "pink");
                this.celulas[3] = new Celula(2, "F", "pink");
                this.cor = "pink";
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
        return this.emMovimento;
    }

    /**
     * @param emMovimento the emMovimento to set
     */
    public void setEmMovimento(boolean emMovimento) {
        this.emMovimento = emMovimento;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

 
    
    public Peca getCopia(){
        Peca copia = new Peca(this.tipoPeca);
        return copia;
    }
    
    public void girarPeca(){
        Celula[] celulas = this.celulas;
        Celula celulaReferencia;
        if(this.tipoPeca=="I"){
            celulaReferencia = celulas[3];
            
        }
    }
}
