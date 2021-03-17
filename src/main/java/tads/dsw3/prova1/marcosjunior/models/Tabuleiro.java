/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.dsw3.prova1.marcosjunior.models;

import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Tabuleiro extends Mensagem{
    private final ArrayList<Celula> celulas = new ArrayList<Celula>();
    private Peca pecaAtual;
    private Peca proximaPeca;
    private final String ID;
    private static final int NUMLINHAS = 20;
    private static final int NUMCOLUNAS = 10;
    private static final String[] colunas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
    public Tabuleiro(String id){
        this.ID = id;     
        
        //Cria as celulas do tabuleiro
        for(int l=0; l<NUMLINHAS; l++){
            for(int c=0; c<NUMCOLUNAS; c++){
                celulas.add(new Celula((l+1), colunas[c], "grey"));
            }
        }
    }

    /**
     * @return the celulas
     */
    public ArrayList<Celula> getCelulas() {
        return celulas;
    }

    /**
     * @return the pecaAtual
     */
    public Peca getPecaAtual() {
        return pecaAtual;
    }

    /**
     * @param pecaAtual the pecaAtual to set
     */
    public void setPecaAtual(Peca pecaAtual) {
        this.pecaAtual = pecaAtual;
    }

    /**
     * @return the proximaPeca
     */
    public Peca getProximaPeca() {
        return proximaPeca;
    }

    /**
     * @param proximaPeca the proximaPeca to set
     */
    public void setProximaPeca(Peca proximaPeca) {
        this.proximaPeca = proximaPeca;
    }

    /**
     * @return the id
     */
    public String getId() {
        return ID;
    }

    /**
     * @return the numLinhas
     */
    public static int getNumLinhas() {
        return NUMLINHAS;
    }

    /**
     * @return the numColunas
     */
    public static int getNumColunas() {
        return NUMCOLUNAS;
    }

    /**
     * @return the colunas
     */
    public static String[] getColunas() {
        return colunas;
    }
    
    public void setTipoConexao(TipoConexao tipo){
        this.tipo = tipo;
    }
    
    public boolean celulaEstaOcupada(int linha, String coluna){
        for(Celula cel: this.celulas){
            if(cel.getLinha() == linha && cel.getColuna().equals(coluna)){
                return cel.isOcupada();
            }
        }
        return true;
    }
    
    public void ocuparCelula(int linha, String coluna, String cor){
        for(Celula cel: this.celulas){
            if(cel.getLinha() == linha && cel.getColuna().equals(coluna)){
                cel.setOcupada(true);
                cel.setCor(cor);
                break;
            }
        }
    }
}
