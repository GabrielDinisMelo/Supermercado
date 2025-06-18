package Entidades;

public class Categoria {
    private int id;
    private String nome;

    public Categoria(String nome) {
        setCategoria(nome);
    }

    public void setCategoria(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return nome;
    }
    public int getId() {
        return id;
    }
}
