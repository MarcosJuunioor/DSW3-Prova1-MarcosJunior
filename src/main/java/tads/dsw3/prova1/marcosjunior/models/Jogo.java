/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.dsw3.prova1.marcosjunior.models;

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

}
