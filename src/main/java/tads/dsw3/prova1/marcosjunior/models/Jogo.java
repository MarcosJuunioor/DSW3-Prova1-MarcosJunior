/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.dsw3.prova1.marcosjunior.models;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author marco
 */
public class Jogo {

    private final Jogador jogadores[] = new Jogador[2];

    public Jogo() {
    }

    /**
     * @return the jogadores
     */
    public Jogador[] getJogadores() {
        return jogadores;
    }

    /**
     * @param jogador
     */
    public void adicionarJogador(Jogador jogador) {
        if (this.jogadores[0] == null) {
            this.jogadores[0] = jogador;
        } else {
            this.jogadores[1] = jogador;
        }
    }

    public Peca gerarPecaAleatoria() {
        Random random = new Random();
        int numero = random.nextInt(6);
        
        switch (numero) {
            case 0:
                return new Peca("I");
            case 1:
                return new Peca("L");
            case 2:
                return new Peca("O");
            case 3:
                return new Peca("S");
            case 4:
                return new Peca("T");
            case 5:
                return new Peca("Z");
        }
        return null;
    }
    
    public void movimentarPecaUmaCasaParaBaixo(Tabuleiro tab){
        Peca peca = tab.getPecaAtual();
        Celula[] celulas = peca.getCelulas();
        //Retorna true se puder incrementar (se puder "se movimentar" para baixo).
        boolean resultado1 = celulas[0].podeIncrementarLinha(tab);
        boolean resultado2 = celulas[1].podeIncrementarLinha(tab);
        boolean resultado3 = celulas[2].podeIncrementarLinha(tab);
        boolean resultado4 = celulas[3].podeIncrementarLinha(tab);
      
        if(resultado1 && resultado2  && resultado3 && resultado4){
            //Incrementa as linhas das células
            celulas[0].incrementarLinha();
            celulas[1].incrementarLinha();
            celulas[2].incrementarLinha();
            celulas[3].incrementarLinha();
            
            peca.setEmMovimento(true);
        }else{ //se a celula não puder mais se mover, as células que fazem parte da peça irão ocupar a tabela (a peça fica parada).
            tab.ocuparCelula(peca.getCelulas()[0].getLinha(), peca.getCelulas()[0].getColuna(), peca.getCor());
            tab.ocuparCelula(peca.getCelulas()[1].getLinha(), peca.getCelulas()[1].getColuna(), peca.getCor());
            tab.ocuparCelula(peca.getCelulas()[2].getLinha(), peca.getCelulas()[2].getColuna(), peca.getCor());
            tab.ocuparCelula(peca.getCelulas()[3].getLinha(), peca.getCelulas()[3].getColuna(), peca.getCor());
            //Se qualquer das células da peça atual chegar a linha 1, o jogador perdeu.
            if(peca.getCelulas()[0].getLinha() == 1 || peca.getCelulas()[1].getLinha() == 1
                || peca.getCelulas()[2].getLinha() == 1 || peca.getCelulas()[3].getLinha() == 1){
                if(tab.getId().equals("tb1")){
                    this.jogadores[1].setVencedor(true);
                }else{
                    this.jogadores[0].setVencedor(true);
                }
            }
            peca.setEmMovimento(false);
        }
        
        
    }
    
    public void movimentarPecaParaDireita(Tabuleiro tab){
        Peca peca = tab.getPecaAtual();
        Celula[] celulas = peca.getCelulas();
        //Retorna true se puder incrementar (se puder "se movimentar" para baixo).
        boolean resultado1 = celulas[0].podeIncrementarColuna(tab);
        boolean resultado2 = celulas[1].podeIncrementarColuna(tab);
        boolean resultado3 = celulas[2].podeIncrementarColuna(tab);
        boolean resultado4 = celulas[3].podeIncrementarColuna(tab);
      
        if(resultado1 && resultado2  && resultado3 && resultado4){
            //Incrementa as colunas das células
            celulas[0].incrementarColuna();
            celulas[1].incrementarColuna();
            celulas[2].incrementarColuna();
            celulas[3].incrementarColuna();
        }
    }
    
    public void movimentarPecaParaEsquerda(Tabuleiro tab){
        Peca peca = tab.getPecaAtual();
        Celula[] celulas = peca.getCelulas();
        //Retorna true se puder incrementar (se puder "se movimentar" para baixo).
        boolean resultado1 = celulas[0].podeDecrementarColuna(tab);
        boolean resultado2 = celulas[1].podeDecrementarColuna(tab);
        boolean resultado3 = celulas[2].podeDecrementarColuna(tab);
        boolean resultado4 = celulas[3].podeDecrementarColuna(tab);
      
        if(resultado1 && resultado2  && resultado3 && resultado4){
            //Decrementa as colunas das células
            celulas[0].decrementarColuna();
            celulas[1].decrementarColuna();
            celulas[2].decrementarColuna();
            celulas[3].decrementarColuna();
        }
    }
    
  /*  public void girarPecaNoventaGrausParaDireita(Tabuleiro tab){
        Peca peca = tab.getPecaAtual();
        peca.girarPeca();
    } */
    
    public void desintegrar(Tabuleiro tab){
        ArrayList<Celula> celulas = tab.getCelulas();
        int quantCelulas = tab.getCelulas().size();
        int linha = 0;
        int aux = 1;
        for(int i=0; i<quantCelulas;i++){
            if(celulas.get(i).isOcupada()){
                if(aux == 10){
                    linha+=10;
                    i=linha;
                    aux = 1;
                    continue;
                }
                aux++;
            }else{
                linha+=10;
                i = linha;
                aux = 1;
            }
        }
    }
            
}
