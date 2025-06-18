package Entidades;

import java.math.BigDecimal;

public abstract class Funcionario extends Usuario{
    protected int id;
    protected String nome;
    protected String documento;
    protected String telefone;
    protected String email;
    protected BigDecimal salario;

    public Funcionario(int id, String nome, String documento,
                       String telefone, String email, BigDecimal salario,
                       String login, String senha) {
        super(login, senha);
        this.id = id;
        setNome(nome);
        setDocumento(documento);
        setTelefone(telefone);
        setEmail(email);
        setSalario(salario);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }
    public String getDocumento() {
        return documento;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public BigDecimal getSalario() {
        return salario;
    }
    public int getId() {
        return id;
    }
}
