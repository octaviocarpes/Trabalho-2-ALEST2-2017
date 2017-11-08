package controller;

import helpers.BuscaPorLargura;
import helpers.LeitorDeArquivos;
import model.Aresta;
import model.Grafo;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by octaviocarpes on 10/22/17.
 */
public class Main {
    public static void main(String[] args) {

        Grafo meuGrafo = new Grafo();
        LeitorDeArquivos meuLeitor = new LeitorDeArquivos("CasosTeste/casoTeste.txt");

        meuLeitor.leArquivo(meuGrafo);

        System.out.println(meuGrafo.toString());

        meuGrafo.adicionaArestasAosVertices();

        for(int i = 0; i < meuGrafo.getTamanhoGrafo(); i++){
            System.out.println(meuGrafo.getVertices().get(i));
        }


        BuscaPorLargura bfs = new BuscaPorLargura(meuGrafo);


        System.out.println("\nRealizar Transferencia!");
        System.out.println(meuLeitor.getNomeDepositador() + " - " + meuLeitor.getNomeBeneficiario());
        System.out.println();

        bfs.encontraContasCandidatas(meuLeitor.getNomeDepositador());
        try {
            bfs.coletaRespostas();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bfs.imprimeRespostas();


    }
}
