package model;

import java.util.ArrayList;

public class Vertice implements Vertices{

    private String numeroConta;
    private String nomeCorrentista1;
    private String nomeCorrentista2;
    private Integer nivel;
    private boolean visitado;
    private ArrayList<Vertice> adjacentes;

    public Vertice(String numeroConta, String nomeCorrentista1, String nomeCorrentista2) {
        this.numeroConta = numeroConta;
        this.nomeCorrentista1 = nomeCorrentista1;
        this.nomeCorrentista2 = nomeCorrentista2;
        this.nivel = 0;
        this.visitado = false;
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    @Override
    public ArrayList<Vertice> getVerticesAdjacentes(){
        return adjacentes;
    }

    @Override
    public void adicionaVerticeAdjacente(Vertice adjacente){
        adjacentes.add(adjacente);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertice vertice = (Vertice) o;

        if (numeroConta != null ? !numeroConta.equals(vertice.numeroConta) : vertice.numeroConta != null) return false;
        if (nomeCorrentista1 != null ? !nomeCorrentista1.equals(vertice.nomeCorrentista1) : vertice.nomeCorrentista1 != null)
            return false;
        if (nomeCorrentista2 != null ? !nomeCorrentista2.equals(vertice.nomeCorrentista2) : vertice.nomeCorrentista2 != null)
            return false;
        return adjacentes != null ? adjacentes.equals(vertice.adjacentes) : vertice.adjacentes == null;
    }

    @Override
    public int hashCode() {
        int result = numeroConta != null ? numeroConta.hashCode() : 0;
        result = 31 * result + (nomeCorrentista1 != null ? nomeCorrentista1.hashCode() : 0);
        result = 31 * result + (nomeCorrentista2 != null ? nomeCorrentista2.hashCode() : 0);
        result = 31 * result + (adjacentes != null ? adjacentes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String vertice = numeroConta + "\n" + nomeCorrentista1 + "\n" + nomeCorrentista2 + "\n";
        return vertice;
    }
}
