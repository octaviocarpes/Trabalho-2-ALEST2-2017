package helpers;

public class BuscaPorLargura {
    private boolean[] marcados;
    private String[] arestaDe;
    private Integer[] distancia;

    public BuscaPorLargura(int tamanhoGrafo) {
        this.marcados = new boolean[tamanhoGrafo];
        this.arestaDe = new String[tamanhoGrafo];
        this.distancia = new Integer[tamanhoGrafo];
    }
}
