package model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by octaviocarpes on 11/6/17.
 */
public class Caminho {

    private LinkedList<Integer> caminho;

    public Caminho() {
        this.caminho = new LinkedList<>();
    }

    public void addConta(int numeroConta){
        caminho.add(numeroConta);
    }

    public int size(){
        return caminho.size();
    }

    public LinkedList getCaminho(){
        return  caminho;
    }

    @Override
    public String toString() {
        return "Caminho{" +
                "caminho=" + caminho +
                '}';
    }
}
