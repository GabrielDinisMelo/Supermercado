package DAOs;

import Entidades.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, preco_compra, preco_venda, quantidade_limite, codigo_de_barras, id_fornecedor, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoMySQL.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getPrecoCompra());
            stmt.setBigDecimal(3, produto.getPrecoVenda());
            stmt.setInt(4, produto.getQuantidadeLimite());
            stmt.setString(5, produto.getCodigoBarras());
            stmt.executeUpdate();
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = ConexaoMySQL.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

            }
        }
        return lista;
    }
}
