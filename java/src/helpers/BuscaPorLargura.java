package helpers;

import model.Grafo;
import model.Vertice;

import java.util.ArrayList;
import java.util.Queue;

public class BuscaPorLargura {
    private Grafo grafoParaAnalisar;
    private CaminhosEncontrados caminhosEncontrados;

    private ArrayList<Vertice> contasCandidatas;
    private ArrayList<String> resultadoCaminhamento;
    private ArrayList<Integer> resultadoDistanciaCaminhamentos;

    public BuscaPorLargura(Grafo grafoParaAnalisar) {
        this.grafoParaAnalisar = grafoParaAnalisar;
        this.caminhosEncontrados = new CaminhosEncontrados();
        this.contasCandidatas = new ArrayList<>();
        this.resultadoCaminhamento = new ArrayList<>();
        this.resultadoDistanciaCaminhamentos = new ArrayList<>();
    }

    public void encontraContasCandidatas(String nomeCorrentistaQueDeposita){
        for (Vertice conta:grafoParaAnalisar.getVertices()
                ) {
            if (conta.getNomeCorrentista1().equals(nomeCorrentistaQueDeposita)||
                conta.getNomeCorrentista2().equals(nomeCorrentistaQueDeposita)){
                contasCandidatas.add(conta);
            }
        }
    }

    private void resetCaminhos(){
        for (Vertice vertice:grafoParaAnalisar.getVertices()
             ) {
            vertice.setVisitado(false);
            vertice.setNivel(0);
        }
    }

    public void coletaRespostas() throws InterruptedException {
        for (int i = 0; i < contasCandidatas.size() ; i++) {
            resetCaminhos();
            resultadoDistanciaCaminhamentos.add(realizaBFS(contasCandidatas.get(i)));
        }
    }

    public void imprimeRespostas(){
        for (int i = 0; i < contasCandidatas.size() ; i++) {
            System.out.println("Caminhamento de: " + contasCandidatas.get(i).getNumeroConta());
            System.out.println("Distancia: " + resultadoDistanciaCaminhamentos.get(i));
            System.out.println("Passos: " + resultadoCaminhamento.get(i));
            System.out.println();
        }
    }

    private int realizaBFS(Vertice contaCandidata) throws InterruptedException {
        sun.misc.Queue<Vertice> fila = new sun.misc.Queue<>();
        Integer distanciaTotalBFS = 0;
        StringBuilder caminhamento = new StringBuilder();

        // Marca a primeira conta como visitada
        contaCandidata.setVisitado(true);
        // Poe a primeira conta na fila
        fila.enqueue(contaCandidata);

        // Enquanto a fila não está vazia
        while (!fila.isEmpty()){

            //Pega o primeiro vertice na fila
            Vertice contaNaFila = fila.dequeue();

            // Incrementa a distancia em 1
            distanciaTotalBFS++;

            // Para cada vertice Adjacente
            for (Vertice adjacente:contaNaFila.getVerticesAdjacentes()
                 ) {
                //Se o vertice ja foi visitado, pula para o próximo
                if (adjacente.isVisitado())continue;
                //Caso o contrário enfileirar adjacente e marcá-lo como visitado
                else{
                    adjacente.setVisitado(true);
                    fila.enqueue(adjacente);
                    adjacente.setNivel(contaNaFila.getNivel() + 1);
                }
            }

            // Grava caminho do vértice
            caminhamento.append(contaNaFila.getNumeroConta() + "(" +contaNaFila.getNivel()+ ") ");
        }
        // Adiciona caminhamento
        resultadoCaminhamento.add(caminhamento.toString());
        caminhosEncontrados.adicionaCaminho(caminhamento.toString());
        return distanciaTotalBFS -1;
    }

    public void pesquisaMenorCaminho(String nomeDepositador, String nomeBeneficiado){

    }


}
