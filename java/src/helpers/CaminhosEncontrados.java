package helpers;

import java.util.ArrayList;

/**
 * Created by octaviocarpes on 11/8/17.
 */
public class CaminhosEncontrados {
    ArrayList<String> caminhosArmazenados;

    public CaminhosEncontrados() {
        this.caminhosArmazenados = new ArrayList<>();
    }

    public void adicionaCaminho(String caminho){
        caminhosArmazenados.add(caminho);
    }
}
