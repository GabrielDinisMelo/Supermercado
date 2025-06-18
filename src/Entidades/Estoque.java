package Entidades;

public class Estoque {
    private int id;
    private int quantidade;
    private Produto produto;

    public Estoque(int quantidade, Produto produto) {
        setProduto(produto);
        setQuantidade(quantidade);
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public int getId() {
        return id;
    }
}
