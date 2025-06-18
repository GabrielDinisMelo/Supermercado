package Entidades;

import java.math.BigDecimal;

public class Produto {
    private int id;
    private int quantidadeLimite;
    private String nome;
    private String codigoBarras;
    private BigDecimal precoCompra;
    private BigDecimal precoVenda;
    private Fornecedor fornecedor;
    private Categoria categoria;

    public Produto(int quantidadeLimite, String nome, String codigoBarras,
                   BigDecimal precoCompra, BigDecimal precoVenda,
                   Fornecedor fornecedor, Categoria categoria) {
       setCategoria(categoria);
       setFornecedor(fornecedor);
       setCodigoBarras(codigoBarras);
       setPrecoVenda(precoVenda);
       setNome(nome);
       setPrecoCompra(precoCompra);
       setQuantidadeLimite(quantidadeLimite);
    }

    public Produto() {

    }

    public void setQuantidadeLimite(int quantidadeLimite) {
        this.quantidadeLimite = quantidadeLimite;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }
    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeLimite() {
        return quantidadeLimite;
    }
    public String getNome() {
        return nome;
    }
    public String getCodigoBarras() {
        return codigoBarras;
    }
    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }
    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public int getId() {
        return id;
    }
}
