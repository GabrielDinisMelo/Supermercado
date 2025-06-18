package Entidades;

public class Caixa {
    private int id;
    private int numero;

    public Caixa(int id, int numero) {
        this.id = id;
        setCaixa(numero);
    }

    public void setCaixa(int numero) {
        this.numero = numero;
    }

    public int getCaixa() {
        return numero;
    }
    public int getId() {
        return id;
    }
}
