package Entidades;

import java.math.BigDecimal;

public class Admin extends Funcionario {
    
    public Admin(int id, String nome, String documento, String telefone,
                 String email, BigDecimal salario, String login, String senha) {
        super(id,nome, documento, telefone, email, salario, login, senha);
    }

}
