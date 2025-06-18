package Entidades;

import java.math.BigDecimal;

public class VendaItem {
    private int id;
    private int quantidade;
    private BigDecimal precoUnitario;
    private Venda venda;
    private Produto produto;

    public VendaItem(int quantidade, BigDecimal precoUnitario,
                     Venda venda, Produto produto) {
        setProduto(produto);
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
        setVenda(venda);
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }
    public Produto getProduto() {
        return produto;
    }
    public Venda getVenda() {
        return venda;
    }
    public int getId() {
        return id;
    }
}
