package model;

import java.util.ArrayList;

public interface Vertices {

    String getNumeroConta();
    String getNomeCorrentista1();
    String getNomeCorrentista2();
    ArrayList<Vertice> getVerticesAdjacentes();
    void adicionaVerticeAdjacente(Vertice adjacente);
}
