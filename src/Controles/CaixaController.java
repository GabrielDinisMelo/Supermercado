package Controles;

import DAOs.*;
import Entidades.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class CaixaController {

    private final OperadorDAO operadorDAO = new OperadorDAO();
    private final CaixaDAO caixaDAO = new CaixaDAO();
    private final VendaDAO vendaDAO = new VendaDAO();

    public boolean registrarVenda(List<VendaItem> itens, FormaPagamento formaPagamento) throws Exception {
        if (itens == null || itens.isEmpty()) {
            throw new Exception("Carrinho está vazio!");
        }

        BigDecimal total = itens.stream()
                .map(i -> i.getPrecoUnitario().multiply(new BigDecimal(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Operador operador = operadorDAO.buscarPorId(1);
        if (operador == null) throw new Exception("Funcionário não encontrado.");

        Caixa caixa = caixaDAO.buscarPorId(1);
        if (caixa == null) throw new Exception("Caixa não encontrado.");

        Venda venda = new Venda(new Timestamp(System.currentTimeMillis()), total, formaPagamento, operador, caixa, itens);
        vendaDAO.registrarVenda(venda);
        return true;
    }
}
