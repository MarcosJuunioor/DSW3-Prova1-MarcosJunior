/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.dsw3.prova1.marcosjunior.encoders;

import com.google.gson.Gson;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import tads.dsw3.prova1.marcosjunior.models.Tabuleiro;


/**
 *
 * @author marco
 */
public class TabuleiroEncoder implements Encoder.Text<Tabuleiro>{
    
    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(final Tabuleiro tabuleiro) throws EncodeException {
        Gson gson = new Gson();
        return gson.toJson(tabuleiro);
    }
}
