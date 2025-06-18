package Entidades;

import java.math.BigDecimal;

public class CompraItem {
    private int id;
    private int quantidade;
    private BigDecimal precoUnitario;
    private Compra compra;
    private Produto produto;

    public CompraItem(int quantidade, BigDecimal precoUnitario,
                      Compra compra, Produto produto) {
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
        setProduto(produto);
        setCompra(compra);
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }
    public Compra getCompra() {
        return compra;
    }
    public Produto getProduto() {
        return produto;
    }
    public int getId() {
        return id;
    }
}
