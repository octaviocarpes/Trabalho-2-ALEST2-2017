package controller;

import helpers.LeitorDeArquivos;
import model.Grafo;

/**
 * Created by octaviocarpes on 10/22/17.
 */
public class Main {
    public static void main(String[] args) {

        Grafo meuGrafo = null;

        LeitorDeArquivos meuLeitor = new LeitorDeArquivos("casoTeste.txt");

        meuGrafo = new Grafo(meuLeitor.retornaTamGrafo());

        meuLeitor.leArquivo(meuGrafo);

        System.out.println(meuGrafo.toString());

        meuGrafo.adicionaArestasAosVertices();

        for(int i = 0; i < meuGrafo.getTamanhoGrafo(); i++){
            System.out.println(meuGrafo.getVertices().get(i));
        }

        System.out.println("\nRealizar Transferencia!");
        System.out.println(meuLeitor.getNomeDepositador() + " - " + meuLeitor.getNomeBeneficiario());

        System.out.println();

        meuGrafo.realizaTransefrencia(meuLeitor.getNomeDepositador(),meuLeitor.getNomeBeneficiario());

    }
}
