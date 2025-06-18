package Entidades;

import java.sql.Timestamp;

public class MovimentacaoEstoque {
    private int id;
    private Produto produto;
    private Funcionario funcionario;
    private String tipo;
    private int quantidade;
    private String motivo;
    private Timestamp dataHora;

}
