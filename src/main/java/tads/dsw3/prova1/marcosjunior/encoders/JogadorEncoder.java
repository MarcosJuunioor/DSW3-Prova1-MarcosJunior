package tads.dsw3.prova1.marcosjunior.encoders;

import com.google.gson.Gson;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import tads.dsw3.prova1.marcosjunior.models.Jogador;

public class JogadorEncoder implements Encoder.Text<Jogador> {

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(final Jogador jogador) throws EncodeException {
        Gson gson = new Gson();
        return gson.toJson(jogador);
    }
}
