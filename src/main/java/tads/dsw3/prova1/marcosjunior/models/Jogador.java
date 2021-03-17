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
public class Jogador extends Mensagem{
    private final Tabuleiro tabuleiro;
    private final String nome;
    private boolean vencedor;
    //pronto para iniciar o jogo
    private boolean pronto;
    
    public Jogador(Tabuleiro tabuleiro, String nome, TipoConexao tipo){
        this.tipo = tipo;
        this.tabuleiro = tabuleiro;
        this.nome = nome;
        this.pronto = false;
        this.vencedor = false;
    }
    
    public Tabuleiro getTabuleiro(){
        return this.tabuleiro;
    }
    
    public String getNome(){
        return this.nome;
    }

    /**
     * @return the pronto
     */
    public boolean isPronto() {
        return pronto;
    }

    /**
     * @param pronto the pronto to set
     */
    public void setPronto(boolean pronto) {
        this.pronto = pronto;
    }
    
    public void setTipoConexao(TipoConexao tipo){
        this.tipo = tipo;
    }

    /**
     * @return the vencedor
     */
    public boolean isVencedor() {
        return vencedor;
    }

    /**
     * @param vencedor the vencedor to set
     */
    public void setVencedor(boolean vencedor) {
        this.vencedor = vencedor;
    }
}
