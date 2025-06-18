package DAOs;

import Entidades.FormaPagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoDAO {

    public List<FormaPagamento> listarTodos() throws SQLException {
        List<FormaPagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM forma_de_pagamento";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FormaPagamento fp = new FormaPagamento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getBoolean("parcelamento"),
                        rs.getBoolean("desconto")
                );
                lista.add(fp);
            }
        }

        return lista;
    }
}
