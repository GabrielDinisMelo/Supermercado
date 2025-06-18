package DAOs;

import Entidades.Operador;

import java.sql.*;
import java.math.BigDecimal;

public class OperadorDAO {

    public Operador buscarPorId(int id) throws SQLException {
        String sql = "SELECT f.*, u.login, u.senha " +
                "FROM funcionario f JOIN usuario u ON f.id = u.id_funcionario " +
                "WHERE f.id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String documento = rs.getString("documento");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                BigDecimal salario = rs.getBigDecimal("salario");
                String login = rs.getString("login");
                String senha = rs.getString("senha");

                return new Operador(id, nome, documento, telefone, email, salario, login, senha);
            } else {
                return null;
            }
        }
    }
}

