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
    public Jogador(Tabuleiro tabuleiro, String nome, TipoConexao tipo){
        this.tipo = tipo;
        this.tabuleiro = tabuleiro;
        this.nome = nome;
    }
    
    public Tabuleiro getTabuleiro(){
        return this.tabuleiro;
    }
    
    public String getNome(){
        return this.nome;
    }
}
