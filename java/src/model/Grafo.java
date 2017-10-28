package model;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;

/**
 * Created by octaviocarpes on 10/22/17.
 */
public class Grafo {

    private class Node{
        String nomeCorrentista1;
        String nomeCorrentista2;
        Integer numeroConta;

        ArrayList<Node> adjacentes;

        public Node(String nomeCorrentista1, String nomeCorrentista2, Integer numeroConta) {
            this.nomeCorrentista1 = nomeCorrentista1;
            this.nomeCorrentista2 = nomeCorrentista2;
            this.numeroConta = numeroConta;
            adjacentes = new ArrayList<>();
        }

        public void adicionaAdjacentes(Node adjacente){
            adjacentes.add(adjacente);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (nomeCorrentista1 != null ? !nomeCorrentista1.equals(node.nomeCorrentista1) : node.nomeCorrentista1 != null)
                return false;
            if (nomeCorrentista2 != null ? !nomeCorrentista2.equals(node.nomeCorrentista2) : node.nomeCorrentista2 != null)
                return false;
            if (numeroConta != null ? !numeroConta.equals(node.numeroConta) : node.numeroConta != null) return false;
            return adjacentes != null ? adjacentes.equals(node.adjacentes) : node.adjacentes == null;
        }

        @Override
        public int hashCode() {
            int result = nomeCorrentista1 != null ? nomeCorrentista1.hashCode() : 0;
            result = 31 * result + (nomeCorrentista2 != null ? nomeCorrentista2.hashCode() : 0);
            result = 31 * result + (numeroConta != null ? numeroConta.hashCode() : 0);
            result = 31 * result + (adjacentes != null ? adjacentes.hashCode() : 0);
            return result;
        }
    }

    private ArrayList<Node> vertices;
    private int tamanhoGrafo;

    public Grafo() {
        vertices = new ArrayList<>();
        tamanhoGrafo = 0;
    }


    public void adicionaVertice(Integer numeroConta,String nomeCorrentista1,String nomeCorrentista2){
        Node vertice = new Node(nomeCorrentista1,nomeCorrentista2,numeroConta);
        vertices.add(vertice);
    }

    public void adicionaArestasAosVertices(){
        for (Node verticeAnalisado:vertices
             ) {
            for (Node verticeComparado:vertices
                 ) {
                if (verticeAnalisado.nomeCorrentista1.equals(verticeComparado.nomeCorrentista1)||
                    verticeAnalisado.nomeCorrentista1.equals(verticeComparado.nomeCorrentista2)||
                    verticeAnalisado.nomeCorrentista2.equals(verticeComparado.nomeCorrentista1)||
                    verticeAnalisado.nomeCorrentista2.equals(verticeComparado.nomeCorrentista2)  )
                {

                    if (verticeAnalisado.equals(verticeComparado)){
                        continue;
                    }else{
                        verticeAnalisado.adicionaAdjacentes(verticeComparado);
                    }

                }
            }
        }
    }


    public int getTamanhoGrafo() {
        return vertices.size();
    }



    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Grafo De Contas Conjuntas");
        res.append("\n");
        res.append("Tamanho: " + getTamanhoGrafo());

        return res.toString();
    }
}
