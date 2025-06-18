package Entidades;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Venda {
    private int id;
    private Timestamp data;
    private BigDecimal total;
    private FormaPagamento formaPagamento;
    private Operador operador;
    private Caixa caixa;
    private List<VendaItem> itens;

    public Venda(Timestamp data, BigDecimal total, FormaPagamento formaPagamento,
                 Operador operador, Caixa caixa, List<VendaItem> itens) {
        setData(data);
        setTotal(total);
        setFormaPagamento(formaPagamento);
        setOperador(operador);
        setCaixa(caixa);
        setItens(itens);
    }

    public void setData(Timestamp data) {
        this.data = data;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public void setOperador(Operador operador) {
        this.operador = operador;
    }
    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
    public void setItens(List<VendaItem> itens) {
        this.itens = itens;
    }

    public Timestamp getData() {
        return data;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public Operador getOperador() {
        return operador;
    }
    public Caixa getCaixa() {
        return caixa;
    }
    public List<VendaItem> getItens() {
        return itens;
    }
    public int getId() {
        return id;
    }
}
