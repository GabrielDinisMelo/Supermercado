package DAOs;

import Entidades.Venda;
import Entidades.VendaItem;

import java.sql.*;

public class VendaDAO {
    public void registrarVenda(Venda venda) throws SQLException {
        Connection conn = ConexaoMySQL.conectar();
        try {
            conn.setAutoCommit(false);
            String sqlVenda = "INSERT INTO venda (id_pagamento, id_funcionario, id_caixa, total) VALUES (?, ?, ?, ?)";
            PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            stmtVenda.setInt(1, venda.getFormaPagamento().getId());
            stmtVenda.setInt(2, venda.getOperador().getId());
            stmtVenda.setInt(3, venda.getCaixa().getId());
            stmtVenda.setBigDecimal(4, venda.getTotal());
            stmtVenda.executeUpdate();
            ResultSet rs = stmtVenda.getGeneratedKeys();
            rs.next();
            int idVenda = rs.getInt(1);

            String sqlItem = "INSERT INTO venda_produto (id_venda, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
            for (VendaItem item : venda.getItens()) {
                PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                stmtItem.setInt(1, idVenda);
                stmtItem.setInt(2, item.getProduto().getId());
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.setBigDecimal(4, item.getPrecoUnitario());
                stmtItem.executeUpdate();

                String sqlUpdateEstoque = "UPDATE estoque SET quantidade = quantidade - ? WHERE id_produto = ?";
                PreparedStatement stmtEstoque = conn.prepareStatement(sqlUpdateEstoque);
                stmtEstoque.setInt(1, item.getQuantidade());
                stmtEstoque.setInt(2, item.getProduto().getId());
                stmtEstoque.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }
}