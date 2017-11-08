package model;

public class Aresta {
    private Vertice vertice1;
    private Vertice vertice2;

    public Aresta(Vertice vertice1, Vertice vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
    }

    public Vertice getVertice1() {
        return vertice1;
    }

    public void setVertice1(Vertice vertice1) {
        this.vertice1 = vertice1;
    }

    public Vertice getVertice2() {
        return vertice2;
    }

    public void setVertice2(Vertice vertice2) {
        this.vertice2 = vertice2;
    }

    @Override
    public String toString() {
        return "*************" +"\nAresta\n" + vertice1 + "\n" + vertice2 + "*************\n";
    }
}
