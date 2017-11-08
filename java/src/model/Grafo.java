package model;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by octaviocarpes on 10/22/17.
 */
public class Grafo {
    private ArrayList<Vertice> verticeList;
    private ArrayList<Aresta> arestaList;
    private int tamanhoGrafo;

    public Grafo() {
        verticeList = new ArrayList<Vertice>();
        arestaList = new ArrayList<Aresta>();
        tamanhoGrafo = 0;
    }

    public int getTamanhoGrafo() {
        return tamanhoGrafo;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanhoGrafo = tamanho;
    }

    public ArrayList<Vertice> getVertices() {
        return verticeList;
    }

    public void adicionaVertice(Integer numeroDaConta, String nomeCorrentista1, String nomeCorrentista2) {
        Vertice vertice = new Vertice(numeroDaConta.toString(),nomeCorrentista1,nomeCorrentista2);
        verticeList.add(vertice);
    }

    public void adicionaArestasAosVertices() {
        for (Vertice contaAnalisada:verticeList
             ) {
            for (Vertice contaComparada:verticeList
                 ) {
                if (contaAnalisada.getNomeCorrentista1().equals(contaComparada.getNomeCorrentista1()) ||
                    contaAnalisada.getNomeCorrentista2().equals(contaComparada.getNomeCorrentista2()) ||
                    contaAnalisada.getNomeCorrentista1().equals(contaComparada.getNomeCorrentista2()) ||
                    contaAnalisada.getNomeCorrentista2().equals(contaComparada.getNomeCorrentista1())){

                    if (contaAnalisada.equals(contaComparada))continue;

                    contaAnalisada.adicionaVerticeAdjacente(contaComparada);

                    Aresta aresta = new Aresta(contaAnalisada,contaComparada);

                    if (arestaList.contains(aresta)){
                        continue;
                    }else {
                        arestaList.add(aresta);
                    }

                }
            }
        }
    }

    @Override
    public String toString() {
        return "Grafo{" +
                "tamanhoGrafo=" + tamanhoGrafo +
                '}';
    }

    public ArrayList<Aresta> getArestas() {
        return arestaList;
    }

}
