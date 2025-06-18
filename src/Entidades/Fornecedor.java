package Entidades;

public class Fornecedor {
    private int id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String cep;
    private String telefone;

    public Fornecedor(String nome, String cnpj, String endereco,
                      String cep, String telefone) {
        setNome(nome);
        setCnpj(cnpj);
        setEndereco(endereco);
        setCep(cep);
        setTelefone(telefone);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }
    public String getCnpj() {
        return cnpj;
    }
    public String getEndereco() {
        return endereco;
    }
    public String getCep() {
        return cep;
    }
    public String getTelefone() {
        return telefone;
    }
    public int getId() {
        return id;
    }
}
