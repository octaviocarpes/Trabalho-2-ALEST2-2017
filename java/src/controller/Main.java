package controller;

import helpers.BuscaPorLargura;
import helpers.LeitorDeArquivos;
import model.Aresta;
import model.Grafo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by octaviocarpes on 10/22/17.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Escolha um dos casos teste: ");
        System.out.println("1 - caso01.txt");
        System.out.println("2 - caso02.txt");
        System.out.println("3 - caso03.txt");
        System.out.println("4 - caso04.txt");
        System.out.println("5 - caso05.txt");
        System.out.println("6 - caso06.txt");
        System.out.println("7 - caso07.txt");
        System.out.println("8 - caso08.txt");
        System.out.println("9 - caso09.txt");
        System.out.println("10 - caso10.txt");
        System.out.println("11 - casoTeste.txt");
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        LeitorDeArquivos meuLeitor = null;

        option = scanner.nextInt();
        switch(option){

            case 1:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso01.txt");
                break;

            case 2:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso02.txt");
                break;

            case 3:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso03.txt");
                break;

            case 4:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso04.txt");
                break;

            case 5:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso05.txt");
                break;

            case 6:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso06.txt");
                break;

            case 7:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso07.txt");
                break;

            case 8:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso08.txt");
                break;

            case 9:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso09.txt");
                break;

            case 10:
                meuLeitor = new LeitorDeArquivos("CasosTeste/caso10.txt");
                break;

            case 11:
                meuLeitor = new LeitorDeArquivos("CasosTeste/casoTeste.txt");
                break;
        }

        Grafo meuGrafo = new Grafo();

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

        bfs.encontraContasCandidatas(meuLeitor.getNomeDepositador(), meuLeitor.getNomeBeneficiario());
        try {
            bfs.coletaRespostas();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //bfs.pesquisaMenorCaminho(meuLeitor.getNomeDepositador(),meuLeitor.getNomeBeneficiario());

        bfs.imprimeCaminhos(meuLeitor.getNomeDepositador(),meuLeitor.getNomeBeneficiario());

    }
}
