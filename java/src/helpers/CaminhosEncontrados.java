package helpers;

import model.Grafo;
import model.Vertice;
import model.Vertices;

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

    public void escolheMenorCaminho(String nomeDepositador, ArrayList<Vertice> contasCandidatasParaDepositar, String nomeBeneficiado, ArrayList<Vertice> contasCandidatasParaReceber) {
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
                System.out.println("Caminho com menor distancia: " + caminhoCandidato);
            }
        }


    }

}

