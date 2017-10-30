package model;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by octaviocarpes on 10/22/17.
 */
public class Grafo {

    private class Node{
        String nomeCorrentista1;
        String nomeCorrentista2;
        Integer numeroConta;
        boolean visited;


        ArrayList<Node> adjacentes;

        public Node(String nomeCorrentista1, String nomeCorrentista2, Integer numeroConta) {
            this.nomeCorrentista1 = nomeCorrentista1;
            this.nomeCorrentista2 = nomeCorrentista2;
            this.numeroConta = numeroConta;
            this.visited = false;
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

        @Override
        public String toString(){
            return "\n" + "Conta : " + numeroConta + "\n" + "Correntista 1: " + nomeCorrentista1 + "\n" + "Correntista 2: " + nomeCorrentista2 + "\n";
        }
    }

    //Atributo
    private ArrayList<Node> vertices;
    private Integer[] distanciaVertices;
    private String[] edgeTo;
    private boolean[] marked;
    private ArrayList<Caminho> caminhosEncontrados;
    HashMap<Integer,Integer> mapaDeCaminhos;


    //Construtor
    public Grafo(int tamGrafo) {
        vertices = new ArrayList<>();
        edgeTo = new String[tamGrafo*10];
        marked = new boolean[tamGrafo*10];
        distanciaVertices = new Integer[tamGrafo*10];
        caminhosEncontrados = new ArrayList<>();
        mapaDeCaminhos = new HashMap<>();
    }


    //Métodos
    public void adicionaVertice(Integer numeroConta,String nomeCorrentista1,String nomeCorrentista2){
        Node vertice = new Node(nomeCorrentista1,nomeCorrentista2,numeroConta);
        vertices.add(vertice);
    }

    public void adicionaArestasAosVertices(){
        int countTamGrafo = 0;
        for (Node verticeAnalisado:vertices
             ) {
            for (Node verticeComparado:vertices
                 ) {
                    if (verticeAnalisado.equals(verticeComparado)){
                        continue;
                    }else{
                        if (    verticeAnalisado.nomeCorrentista1.equals(verticeComparado.nomeCorrentista1)||
                                verticeAnalisado.nomeCorrentista1.equals(verticeComparado.nomeCorrentista2)||
                                verticeAnalisado.nomeCorrentista2.equals(verticeComparado.nomeCorrentista1)||
                                verticeAnalisado.nomeCorrentista2.equals(verticeComparado.nomeCorrentista2)  )
                        {
                            verticeAnalisado.adicionaAdjacentes(verticeComparado);
                    }

                }
            }
        }
    }


    public int getTamanhoGrafo() {
        return vertices.size();
    }

    public ArrayList<Node> getVertices() {
        return vertices;
    }

    public void montaListaCaminhos(Node contaAnalisada){

    }

    private void verificaDistancias(int iterador, String nomeDepositador ,String nomeBeneficiario){


        for (int i = 0; i < iterador; i++) {
            String[]data = edgeTo[i].split(" - ");
            mapaDeCaminhos.put(Integer.parseInt(data[0]),Integer.parseInt(data[1]));
        }
        System.out.println(mapaDeCaminhos);



        // A idéia é pegar todas as contas que tenham o nomeDepositador
        // e entao tracar os caminhos
        // para entao poder escolher o menor caminho possivel.
        ArrayList<Node> contasCandidatasParaDepositar = new ArrayList<>();
        ArrayList<Node> contasCandidatasParaReceber = new ArrayList<>();

        for (Node conta: vertices
             ) {
            if (conta.nomeCorrentista1.equals(nomeDepositador) || conta.nomeCorrentista2.equals(nomeDepositador)){
                contasCandidatasParaDepositar.add(conta);
            }
        }

        for (Node conta: vertices
                ) {
            if (conta.nomeCorrentista1.equals(nomeBeneficiario) || conta.nomeCorrentista2.equals(nomeBeneficiario)){
                contasCandidatasParaReceber.add(conta);
            }
        }

//        System.out.println(contasCandidatasParaDepositar);
//        System.out.println(contasCandidatasParaReceber);

        ArrayList<Integer> distanciaCaminhos = new ArrayList<>();
        for (Node contaCandidata: contasCandidatasParaDepositar
             ) {
            distanciaCaminhos.add(executaCaminho(contaCandidata.numeroConta,mapaDeCaminhos));
        }
        System.out.println(distanciaCaminhos);

        System.out.println("------------------");
        System.out.println(caminhosEncontrados);

        verificaCaminhos(nomeBeneficiario);
    }

    private Integer executaCaminho(Integer contaCandidata, HashMap<Integer, Integer> mapaDeCaminhos) {
        Caminho caminho = new Caminho();

        while(mapaDeCaminhos.get(contaCandidata) != null){
            caminho.addConta(contaCandidata);
            System.out.println(caminho);
            System.out.println(contaCandidata);
            contaCandidata = mapaDeCaminhos.get(contaCandidata);
            System.out.println(contaCandidata);
            System.out.println("*******");
        }
        caminhosEncontrados.add(caminho);
        return caminho.size();
    }

    private void verificaCaminhos(String nomeBeneficiado){
        validaCaminhos(caminhosEncontrados.get(0),nomeBeneficiado);
    }

    private void validaCaminhos(Caminho cam,String nomeBeneficiado) {
        Node
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
