package helpers;

import model.Grafo;

import java.io.*;

/**
 * Created by octaviocarpes on 10/22/17.
 */
public class LeitorDeArquivos {

    private BufferedReader meuLeitor;
    private String nomeDepositador;
    private String nomeBeneficiario;

    public LeitorDeArquivos(String nomeDoArquivo) {
        try {
            meuLeitor = new BufferedReader(new FileReader(new File(nomeDoArquivo)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nomeDepositador = "";
        nomeBeneficiario = "";
    }

    public void leArquivo(Grafo grafoParaPopular){
        String linha = "";
        Integer quantidadeClientes = 0;
        try {
            quantidadeClientes = Integer.parseInt(meuLeitor.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while((linha = meuLeitor.readLine()) != null && quantidadeClientes > 0){
                String[] dados = linha.split(" ");

                Integer numeroDaConta = Integer.parseInt(dados[0]);
                String nomeCorrentista1 = dados[1];
                String nomeCorrentista2 = dados[2];

                grafoParaPopular.adicionaVertice(numeroDaConta,nomeCorrentista1,nomeCorrentista2);
                quantidadeClientes--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            String transferencia = linha;
            String[] dadosTransferencia = transferencia.split(" ");
            nomeDepositador = dadosTransferencia[0];
            nomeBeneficiario = dadosTransferencia[1];

    }

    public String getNomeDepositador() {
        return nomeDepositador;
    }

    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }
}
