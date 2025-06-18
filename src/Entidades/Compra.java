package Entidades;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Compra {
    private int id;
    private Timestamp data;
    private BigDecimal total;
    private Fornecedor fornecedor;
    private Operador operador;
    private List<CompraItem> itens;

    public Compra(Timestamp data, BigDecimal total, Fornecedor fornecedor,
                  Operador operador, List<CompraItem> itens) {
        setData(data);
        setFornecedor(fornecedor);
        setTotal(total);
        setOperador(operador);
        setItens(itens);
    }

    public void setData(Timestamp data) {
        this.data = data;
    }
    public void setItens(List<CompraItem> itens) {
        this.itens = itens;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public List<CompraItem> getItens() {
        return itens;
    }
    public Timestamp getData() {
        return data;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public Operador getOperador() {
        return operador;
    }
    public int getId() {
        return id;
    }
}
