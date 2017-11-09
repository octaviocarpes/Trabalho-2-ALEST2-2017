package helpers;

import model.Grafo;
import model.Vertice;
import model.Vertices;
import sun.misc.Queue;

import java.util.ArrayList;
import java.util.HashMap;

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

    public void escolheMenorCaminho(String nomeDepositador, ArrayList<Vertice> contasCandidatasParaDepositar, String nomeBeneficiado, ArrayList<Vertice> contasCandidatasParaReceber) throws InterruptedException {
        ArrayList<Vertice> verticesDoCaminho = new ArrayList<>();

        for (String caminhos:caminhosArmazenados
             ) {
            String[] dadosVertices = caminhos.split(" ");
            for (int i = 0; i < dadosVertices.length ; i++) {
                for (Vertice verticesDoGrafo:grafoParaAnalisar.getVertices()
                     ) {
                    if (dadosVertices[i].split("-")[0].equals(verticesDoGrafo.getNumeroConta())){
                        Vertice aux = new Vertice(verticesDoGrafo.getNumeroConta(),verticesDoGrafo.getNomeCorrentista1(),verticesDoGrafo.getNomeCorrentista2());
                        aux.setNivel(Integer.parseInt(dadosVertices[i].split("-")[1]));
                        verticesDoCaminho.add(aux);
                    }
                }
            }
        }

        ArrayList<Vertice> recebedores = new ArrayList<>();



        for (Vertice vertice: verticesDoCaminho
             ) {
            for (Vertice candidatoApagamento:contasCandidatasParaReceber
                 ) {
                if (vertice.getNumeroConta().equals(candidatoApagamento.getNumeroConta())){
                    recebedores.add(vertice);
                }
            }
        }

        Integer menor = recebedores.get(0).getNivel();
        Integer maior = recebedores.get(0).getNivel();

        for (Vertice vertice:recebedores
             ) {
            if (vertice.getNivel() < menor){
                menor = vertice.getNivel();
            }
            if (vertice.getNivel() > maior){
                maior = vertice.getNivel();
            }
        }

        Vertice verticeMenorDistancia = null;

        for (Vertice vertice:recebedores
             ) {
            if (vertice.getNivel() == menor){
                verticeMenorDistancia = vertice;
            }
        }

        String menorVertice = verticeMenorDistancia.getNumeroConta() + "-" + verticeMenorDistancia.getNivel();

        for (String caminhoCandidato: caminhosArmazenados
             ) {
            if (caminhoCandidato.contains(menorVertice)){
                imprimeCaminhoComMenorDistancia(caminhoCandidato,nomeDepositador,nomeBeneficiado);
            }
        }


    }

    private void imprimeCaminhoComMenorDistancia(String caminho,String nomeDeposito, String nomeRecebe) throws InterruptedException {
        ArrayList<String> numeroContasVertices = new ArrayList<>();
        ArrayList<Vertice> verticesDoCaminho = new ArrayList<>();

        String[] verticesEdistancias = caminho.split(" ");

        for (String vertice:verticesEdistancias
             ) {
            numeroContasVertices.add(vertice.split("-")[0]);
        }


        HashMap<String,String> caminhoTransferencia = new HashMap<>();


        for (int i = numeroContasVertices.size() -1; i >= 0; i--) {
            for (Vertice vertice:grafoParaAnalisar.getVertices()
                 ) {
                if (numeroContasVertices.get(i).equals(vertice.getNumeroConta())){
                    vertice.setVisitado(false);
                    verticesDoCaminho.add(vertice);
                }
            }
        }


    }


}

