package helpers;

import model.Grafo;
import model.Vertice;

import java.util.ArrayList;

/**
 * Created by octaviocarpes on 11/8/17.
 */
public class CaminhosEncontrados {
    ArrayList<String> caminhosArmazenados;
    ArrayList<String> caminhosParaComparar;
    private Grafo grafoParaAnalisar;

    public CaminhosEncontrados(Grafo grafo) {
        this.caminhosArmazenados = new ArrayList<>();
        this.caminhosParaComparar = new ArrayList<>();
        this.grafoParaAnalisar = grafo;
    }

    public void adicionaCaminho(String caminho){
        caminhosArmazenados.add(caminho);
    }

    public void escolheMenorCaminho(String nomeDepositador, ArrayList<Vertice> contasCandidatasParaDepositar, String nomeBeneficiado, ArrayList<Vertice> contasCandidatasParaReceber){
        Vertice[] verticesQueDepositam = new Vertice[contasCandidatasParaDepositar.size()];
        Vertice[] verticesQueRecebem = new Vertice[contasCandidatasParaReceber.size()];
        ArrayList<String> verticesEncontrados = new ArrayList<>();
        for (int i = 0; i < caminhosArmazenados.size() ; i++) {
             String[] montaVertices = caminhosArmazenados.get(i).split(" ");
            for (int j = 0; j < montaVertices.length; j++) {
                verticesEncontrados.add(montaVertices[j]);
            }
        }

        System.out.println(verticesEncontrados);
    }
}
