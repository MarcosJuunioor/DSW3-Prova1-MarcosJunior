/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.dsw3.prova1.marcosjunior.socket;

import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import tads.dsw3.prova1.marcosjunior.encoders.JogadorEncoder;
import tads.dsw3.prova1.marcosjunior.encoders.TabuleiroEncoder;
import tads.dsw3.prova1.marcosjunior.models.Jogador;
import tads.dsw3.prova1.marcosjunior.models.Jogo;
import tads.dsw3.prova1.marcosjunior.models.Peca;
import tads.dsw3.prova1.marcosjunior.models.Tabuleiro;
import tads.dsw3.prova1.marcosjunior.models.TipoConexao;

/**
 *
 * @author marco
 */
@ServerEndpoint(value = "/tetris", encoders = {JogadorEncoder.class, TabuleiroEncoder.class})
public class JogoEndpoint {

    private static Session s1;
    private static Session s2;
    private static Jogo jogo;

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        if (s1 == null) {
            s1 = session;
            jogo = new Jogo();
            Tabuleiro tabuleiro = new Tabuleiro("tb1");
            Jogador jogador1 = new Jogador(tabuleiro, "jogador1", TipoConexao.OPEN);
            jogo.adicionarJogador(jogador1);
            s1.getBasicRemote().sendObject(jogador1);
        } else if (s2 == null) {
            s2 = session;
            Tabuleiro tabuleiro = new Tabuleiro("tb2");
            Jogador jogador2 = new Jogador(tabuleiro, "jogador2", TipoConexao.OPEN);
            jogo.adicionarJogador(jogador2);
            //envia objeto jogador ao jogador2
            s2.getBasicRemote().sendObject(jogador2);
            //envia o oponente do jogador 2 (jogador 1)
            s2.getBasicRemote().sendObject(jogo.getJogadores()[0]);
            //envia oponente do jogador 1 (jogador 2)
            s1.getBasicRemote().sendObject(jogo.getJogadores()[1]);

        } else {
            session.close();
        }
    }

    @OnMessage
    public void onMessage(Session session, String mensagem) throws EncodeException, IOException {

        Jogador jogador1 = jogo.getJogadores()[0];
        Tabuleiro tabuleiroJogador1 = jogador1.getTabuleiro();
        Jogador jogador2 = jogo.getJogadores()[1];
        Tabuleiro tabuleiroJogador2 = jogador2.getTabuleiro();

        //Start jogo
        if (mensagem.equals(jogador1.getNome()) || mensagem.equals(jogador2.getNome())) {
            if (mensagem.equals(jogador1.getNome())) {
                jogador1.setPronto(true);
            } else if (mensagem.equals(jogador2.getNome())) {
                jogador2.setPronto(true);
            }

            //Se ambos os jogadores estão prontos, o jogo pode começar
            if (jogador1.isPronto() && jogador2.isPronto()) {
                Peca pecaAtual = jogo.gerarPecaAleatoria();
                Peca proximaPeca = jogo.gerarPecaAleatoria();
                //Os dois jogadores devem começar com a mesma peça atual e próxima, por isso é feita uma cópia abaixo para o jogador2.
                Peca copiaPecaAtual = pecaAtual.getCopia();
                Peca copiaProximaPeca = proximaPeca.getCopia();

                //Seta peça atual e próxima peça do jogador 1
                tabuleiroJogador1.setPecaAtual(pecaAtual);
                tabuleiroJogador1.setProximaPeca(proximaPeca);
                jogador1.setTipoConexao(TipoConexao.MESSAGE);

                //Seta peça atual e próxima peça do jogador 2
                tabuleiroJogador2.setPecaAtual(copiaPecaAtual);
                tabuleiroJogador2.setProximaPeca(copiaProximaPeca);
                jogador2.setTipoConexao(TipoConexao.MESSAGE);

                //Envia as informações atualizadas para os jogadores
                enviarParaJogadores(jogador1, jogador2);
            }
        } else if (mensagem.equals("movimentarPecasParabaixo") && session.equals(s1)) {

            //Enquando estiver em movimento, a peça é deslocada para baixo no tabuleiro
            if (tabuleiroJogador1.getPecaAtual().isEmMovimento()) {
                jogo.movimentarPecaUmaCasaParaBaixo(tabuleiroJogador1);
                jogador1.setTipoConexao(TipoConexao.MESSAGE);
            }

            //Enquando estiver em movimento, a peça é deslocada para baixo no tabuleiro
            if (tabuleiroJogador2.getPecaAtual().isEmMovimento()) {
                jogo.movimentarPecaUmaCasaParaBaixo(tabuleiroJogador2);
                jogador2.setTipoConexao(TipoConexao.MESSAGE);
            }

            if (jogador1.isVencedor() || jogador2.isVencedor()) {
                jogador1.setTipoConexao(TipoConexao.MESSAGE);
                jogador2.setTipoConexao(TipoConexao.MESSAGE);
            } else if (!tabuleiroJogador1.getPecaAtual().isEmMovimento() && !tabuleiroJogador2.getPecaAtual().isEmMovimento()) {
                //Se as peças atuais de ambos os jogadores não estão mais em movimento, a próxima peça é setada como atual
                //A atual passa a ser a próxima
                tabuleiroJogador1.setPecaAtual(tabuleiroJogador1.getProximaPeca());
                tabuleiroJogador2.setPecaAtual(tabuleiroJogador2.getProximaPeca());
                //E uma nova próxima peça é gerada
                Peca proximaPeca = jogo.gerarPecaAleatoria();
                //Cópia da próxima peça para o jogador 2
                Peca copiaProximaPeca = proximaPeca.getCopia();

                tabuleiroJogador1.setProximaPeca(proximaPeca);
                tabuleiroJogador2.setProximaPeca(copiaProximaPeca);
            }
            //Envia as informações atualizadas para os jogadores
            enviarParaJogadores(jogador1, jogador2);
        } else if (mensagem.equals("moverPecaAtualParaDireita")) {
            if (session.equals(s1)) {
                jogo.movimentarPecaParaDireita(tabuleiroJogador1);
                jogador1.setTipoConexao(TipoConexao.MESSAGE);
            } else if (session.equals(s2)) {
                jogo.movimentarPecaParaDireita(tabuleiroJogador2);
                jogador2.setTipoConexao(TipoConexao.MESSAGE);
            } else {
                session.close();
            }
            //Envia as informações atualizadas para os jogadores
            enviarParaJogadores(jogador1, jogador2);
        } else if (mensagem.equals("moverPecaAtualParaEsquerda")) {
            if (session.equals(s1)) {
                jogo.movimentarPecaParaEsquerda(tabuleiroJogador1);
                jogador1.setTipoConexao(TipoConexao.MESSAGE);
            } else if (session.equals(s2)) {
                jogo.movimentarPecaParaEsquerda(tabuleiroJogador2);
                jogador2.setTipoConexao(TipoConexao.MESSAGE);
            }
            //Envia as informações atualizadas para os jogadores
            enviarParaJogadores(jogador1, jogador2);
        } else if (mensagem.equals("girarPecaAtualNoventaGrausParaDireita")) {
            //A fazer
        } else if (mensagem.equals("moverPecaMaisRapido")) {
            if (session.equals(s1)) {
                if (tabuleiroJogador1.getPecaAtual().isEmMovimento()) {
                    jogo.movimentarPecaUmaCasaParaBaixo(tabuleiroJogador1);
                    jogador1.setTipoConexao(TipoConexao.MESSAGE);
                }
            } else if (session.equals(s2)) {
                if (tabuleiroJogador2.getPecaAtual().isEmMovimento()) {
                    jogo.movimentarPecaUmaCasaParaBaixo(tabuleiroJogador2);
                    jogador2.setTipoConexao(TipoConexao.MESSAGE);
                }
            }
            //Envia as informações atualizadas para os jogadores
            enviarParaJogadores(jogador1, jogador2);
        } else if (mensagem.equals("endgame")) {
            jogador2.setTipoConexao(TipoConexao.ENDGAME);
            jogador1.setTipoConexao(TipoConexao.ENDGAME);
           
            //Envia as informações atualizadas para os jogadores
            s1.getBasicRemote().sendObject(jogador1);
            s2.getBasicRemote().sendObject(jogador2);
            //enviarParaJogadores(jogador1, jogador2);

        }

    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {        
        if(session.equals(s1)){
            s1 = null;    
        }else{
            s2 = null;
        }
            
            
    }

    @OnError
    public void onError(Throwable t) {
    }

    //Envia as informações atualizadas para os jogadores
    public void enviarParaJogadores(Jogador jogador1, Jogador jogador2) throws IOException, EncodeException {
        s1.getBasicRemote().sendObject(jogador1);
        s1.getBasicRemote().sendObject(jogador2);
        s2.getBasicRemote().sendObject(jogador1);
        s2.getBasicRemote().sendObject(jogador2);
    }
}
