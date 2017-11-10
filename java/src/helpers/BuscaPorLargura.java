package helpers;

import model.Grafo;
import model.Vertice;

import java.util.ArrayList;
import java.util.Queue;

public class BuscaPorLargura {
    private Grafo grafoParaAnalisar;
    private CaminhosEncontrados caminhosEncontrados;

    private ArrayList<Vertice> contasCandidatasParaDepositar;
    private ArrayList<Vertice> contasCandidatasParaReceber;
    private ArrayList<String> resultadoCaminhamento;
    private ArrayList<Integer> resultadoDistanciaCaminhamentos;
    private Vertice[] veioDe;
    private Vertice[] foiPara;
    private Integer qtdArestas;

    public BuscaPorLargura(Grafo grafoParaAnalisar) {
        this.grafoParaAnalisar = grafoParaAnalisar;
        this.caminhosEncontrados = new CaminhosEncontrados(grafoParaAnalisar);
        this.contasCandidatasParaDepositar = new ArrayList<>();
        this.contasCandidatasParaReceber = new ArrayList<>();
        this.resultadoCaminhamento = new ArrayList<>();
        this.resultadoDistanciaCaminhamentos = new ArrayList<>();
        this.veioDe = new Vertice[grafoParaAnalisar.getTamanhoGrafo()*2];
        this.foiPara = new Vertice[grafoParaAnalisar.getTamanhoGrafo()*2];
        this.qtdArestas = 0;
    }

    public void encontraContasCandidatas(String nomeCorrentistaQueDeposita, String nomeCorrentistaQueRecebe){
        for (Vertice conta:grafoParaAnalisar.getVertices()
                ) {
            if (conta.getNomeCorrentista1().equals(nomeCorrentistaQueDeposita)||
                conta.getNomeCorrentista2().equals(nomeCorrentistaQueDeposita)){
                contasCandidatasParaDepositar.add(conta);
            }
        }

        for (Vertice conta:grafoParaAnalisar.getVertices()
                ) {
            if (conta.getNomeCorrentista1().equals(nomeCorrentistaQueRecebe)||
                    conta.getNomeCorrentista2().equals(nomeCorrentistaQueRecebe)){
                contasCandidatasParaReceber.add(conta);
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
        for (int i = 0; i < contasCandidatasParaDepositar.size() ; i++) {
            resetCaminhos();
            resultadoDistanciaCaminhamentos.add(realizaBFS(contasCandidatasParaDepositar.get(i)));
        }
    }

    public void imprimeRespostas(){
        for (int i = 0; i < contasCandidatasParaDepositar.size() ; i++) {
            System.out.println("Caminhamento de: " + contasCandidatasParaDepositar.get(i).getNumeroConta());
            System.out.println("Distancia: " + resultadoDistanciaCaminhamentos.get(i));
            System.out.println("Passos: " + resultadoCaminhamento.get(i));
            System.out.println();
        }
    }

    private int realizaBFS(Vertice contaCandidata) throws InterruptedException {
        sun.misc.Queue<Vertice> fila = new sun.misc.Queue<>();
        Integer distanciaTotalBFS = 0;
        qtdArestas = 0;
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
                    veioDe[qtdArestas] = contaNaFila;
                    foiPara[qtdArestas] = adjacente;
                    qtdArestas++;
                }
            }

            // Grava caminho do vértice
            caminhamento.append(contaNaFila.getNumeroConta() + "-" + contaNaFila.getNivel() + " ");
        }
        // Adiciona caminhamento
        resultadoCaminhamento.add(caminhamento.toString());
        caminhosEncontrados.adicionaCaminho(caminhamento.toString());
        return distanciaTotalBFS -1;
    }

    public void pesquisaMenorCaminho(String nomeDepositador, String nomeBeneficiado){
        try {
            caminhosEncontrados.escolheMenorCaminho(nomeDepositador,contasCandidatasParaDepositar,nomeBeneficiado,contasCandidatasParaReceber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void imprimeCaminhos(String nomeDepositador, String nomeBeneficiado){
        for (int i = 0; i < qtdArestas; i++) {
            if (foiPara[i].getNomeCorrentista1().equals(nomeBeneficiado) ||
                foiPara[i].getNomeCorrentista2().equals(nomeBeneficiado)){
                System.out.println(veioDe[i].getNomeCorrentista1() +" - "+ veioDe[i].getNomeCorrentista2() + " -> " + foiPara[i].getNomeCorrentista1() + " - " + foiPara[i].getNomeCorrentista2() + " (" + foiPara[i].getNivel() + ")");
                break;
            }
            System.out.println(veioDe[i].getNomeCorrentista1() +" - "+ veioDe[i].getNomeCorrentista2() + " -> " + foiPara[i].getNomeCorrentista1() + " - " + foiPara[i].getNomeCorrentista2() + " (" + foiPara[i].getNivel() + ")");
        }

        // Procura o vertice do beneficiado
        Vertice beneficiado = null;
        Integer posicaoBeneficiado = 0;
        for (int i = 0; i < qtdArestas; i++) {
            if(foiPara[i].getNomeCorrentista1().equals(nomeBeneficiado)||
                    foiPara[i].getNomeCorrentista2().equals(nomeBeneficiado)){
                beneficiado = foiPara[i];
                posicaoBeneficiado = i;
            }
        }

        // Procura o vertice do depositador
        Vertice depositador = null;
        Integer posicaoDepositador = 0;
        for (int i = 0; i < qtdArestas; i++) {
            if(veioDe[i].getNomeCorrentista1().equals(nomeDepositador)||
                    veioDe[i].getNomeCorrentista2().equals(nomeDepositador)){
                depositador = veioDe[i];
                posicaoDepositador = i;
            }
        }

        StringBuilder res = new StringBuilder();



    }


    private int procuraFoiPara(Vertice foiPara){;
        for (int i = 0; i < qtdArestas; i++) {
            if(foiPara.equals(this.foiPara[i])){
                 return i;
            }
        }
        return -1;
    }

    private int procuraVeioDe(Vertice veioDe){;
        for (int i = 0; i < qtdArestas; i++) {
            if(veioDe.equals(this.veioDe[i])){
                return i;
            }
        }
        return -1;
    }

}
