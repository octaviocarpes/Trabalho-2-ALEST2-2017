package model;

import java.util.ArrayList;

public class Vertice implements Vertices{

    private String numeroConta;
    private String nomeCorrentista1;
    private String nomeCorrentista2;
    private ArrayList<Vertice> adjacentes;

    public Vertice(String numeroConta, String nomeCorrentista1, String nomeCorrentista2) {
        this.numeroConta = numeroConta;
        this.nomeCorrentista1 = nomeCorrentista1;
        this.nomeCorrentista2 = nomeCorrentista2;
        adjacentes = new ArrayList<>();
    }

    @Override
    public String getNumeroConta() {
        return numeroConta;
    }

    @Override
    public String getNomeCorrentista1() {
        return nomeCorrentista1;
    }

    @Override
    public String getNomeCorrentista2() {
        return nomeCorrentista2;
    }

    @Override
    public void adicionaVerticeAdjacente(Vertice adjacente){

    }
}
