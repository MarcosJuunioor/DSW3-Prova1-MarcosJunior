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
import tads.dsw3.prova1.marcosjunior.models.Jogador;
import tads.dsw3.prova1.marcosjunior.models.Jogo;
import tads.dsw3.prova1.marcosjunior.models.Tabuleiro;
import tads.dsw3.prova1.marcosjunior.models.TipoConexao;

/**
 *
 * @author marco
 */
@ServerEndpoint(value="/tetris", encoders = JogadorEncoder.class)
public class JogoEndpoint extends JogadorEncoder{
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
          //MoveResult ret = game.move(session == s1 ? Player.PLAYER1 : Player.PLAYER2, message);
          /*if (ret.getMove() == Move.VALID) {
              if (ret.getWinner() == null) {
                  sendMessage(new Message(ConnectionType.MESSAGE, game.getTurn(), game.getBoard()));
              } else {
                  sendMessage(new Message(ConnectionType.ENDGAME, game.getWinner(), game.getBoard()));
              }
          }*/
      }

      @OnClose
      public void onClose(Session session) throws IOException, EncodeException {
          /*if (s1 == session) {
              if (reason.getCloseCode() != CloseReason.CloseCodes.NORMAL_CLOSURE) {
                  s2.getBasicRemote().sendObject(new Message(ConnectionType.ENDGAME, Winner.PLAYER2, game.getBoard()));
              }
              s1 = null;
          }
          if (s2 == session) {
              if (reason.getCloseCode() != CloseReason.CloseCodes.NORMAL_CLOSURE) {
                  s1.getBasicRemote().sendObject(new Message(ConnectionType.ENDGAME, Winner.PLAYER1, game.getBoard()));
              }
              s2 = null;
          } */
      }

    @OnError
    public void onError(Throwable t) {
    }

}
