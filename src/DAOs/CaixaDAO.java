package DAOs;

import Entidades.Caixa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CaixaDAO {
    public Caixa buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM caixa WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Caixa(rs.getInt("id"), rs.getInt("numero"));
            }
        }
        return null;
    }
}