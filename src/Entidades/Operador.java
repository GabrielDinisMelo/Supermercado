package Entidades;

import java.math.BigDecimal;

public class Operador extends Funcionario {
    public Operador(int id, String nome, String documento, String telefone,
                    String email, BigDecimal salario,
                    String login, String senha) {
        super(id, nome, documento, telefone, email,salario, login, senha);
    }
}
