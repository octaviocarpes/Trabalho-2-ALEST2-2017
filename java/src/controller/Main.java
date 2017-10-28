package controller;

import helpers.LeitorDeArquivos;
import model.Grafo;

/**
 * Created by octaviocarpes on 10/22/17.
 */
public class Main {
    public static void main(String[] args) {

        Grafo meuGrafo = new Grafo();
        LeitorDeArquivos meuLeitor = new LeitorDeArquivos("caso01.txt");

        meuLeitor.leArquivo(meuGrafo);

        System.out.println(meuGrafo.toString());

        meuGrafo.adicionaArestasAosVertices();

    }
}
