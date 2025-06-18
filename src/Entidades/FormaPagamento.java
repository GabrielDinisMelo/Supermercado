package Entidades;

public class FormaPagamento {
    private int id;
    private String nome;
    private boolean parcelamento;
    private boolean desconto;

    public FormaPagamento(int id,String nome, boolean parcelamento, boolean desconto) {
        this.id = id;
        setNome(nome);
        setParcelamento(parcelamento);
        setDesconto(desconto);
    }

    public FormaPagamento(int idPagamento) {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setParcelamento(boolean parcelamento) {
        this.parcelamento = parcelamento;
    }
    public void setDesconto(boolean desconto) {
        this.desconto = desconto;
    }

    public String getNome() {
        return nome;
    }
    public boolean getParcelamento() {
        return parcelamento;
    }
    public boolean getDesconto() {
        return desconto;
    }
    public int getId() {
        return id;
    }


    public String toString() {
        return nome;
    }
}
