package Fronteiras;

import Controles.CaixaController;
import DAOs.*;
import Entidades.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class ViewCaixa extends JFrame {
    private final JTextField txtBusca;
    private final JButton btnBuscar;
    private final JButton btnAdicionar;
    private final JButton btnFinalizar;
    private final JButton btnHistorico;
    private final JTable tabelaProdutos, tabelaCarrinho;
    private final DefaultTableModel modeloProdutos, modeloCarrinho;
    private final JComboBox<FormaPagamento> cbPagamento;
    private final List<VendaItem> carrinho = new ArrayList<>();

    public ViewCaixa() {
        setTitle("Caixa - Venda de Produtos");
        setSize(800, 600);
        setLayout(null);

        JLabel lblBusca = new JLabel("Buscar Produto:");
        lblBusca.setBounds(10, 10, 120, 25);
        add(lblBusca);

        txtBusca = new JTextField();
        txtBusca.setBounds(130, 10, 200, 25);
        add(txtBusca);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(340, 10, 100, 25);
        add(btnBuscar);

        modeloProdutos = new DefaultTableModel(new String[]{"ID", "Nome", "Preço", "Estoque"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaProdutos = new JTable(modeloProdutos);
        JScrollPane scrollProdutos = new JScrollPane(tabelaProdutos);
        scrollProdutos.setBounds(10, 50, 760, 150);
        add(scrollProdutos);

        btnAdicionar = new JButton("Adicionar ao Carrinho");
        btnAdicionar.setBounds(10, 210, 200, 25);
        add(btnAdicionar);

        modeloCarrinho = new DefaultTableModel(new String[]{"Produto", "Qtd", "Preço Unit"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaCarrinho = new JTable(modeloCarrinho);
        JScrollPane scrollCarrinho = new JScrollPane(tabelaCarrinho);
        scrollCarrinho.setBounds(10, 240, 760, 150);
        add(scrollCarrinho);

        JLabel lblPagamento = new JLabel("Forma de Pagamento:");
        lblPagamento.setBounds(10, 400, 150, 25);
        add(lblPagamento);

        cbPagamento = new JComboBox<>();
        cbPagamento.setBounds(160, 400, 200, 25);
        add(cbPagamento);
        carregarFormasDePagamento();

        btnFinalizar = new JButton("Finalizar Venda");
        btnFinalizar.setBounds(600, 400, 150, 30);
        add(btnFinalizar);

        btnHistorico = new JButton("Histórico de Vendas");
        btnHistorico.setBounds(600, 440, 150, 30);
        add(btnHistorico);

        btnBuscar.addActionListener(_ -> buscarProdutos());
        btnAdicionar.addActionListener(_ -> adicionarAoCarrinho());
        btnFinalizar.addActionListener(_ -> finalizarVenda());
        btnHistorico.addActionListener(_ -> mostrarHistorico());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        tabelaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2 && tabelaProdutos.getSelectedRow() != -1) {
                    adicionarAoCarrinho();
                }
            }
        });

    }

    private void carregarFormasDePagamento() {
        try {
            FormaPagamentoDAO dao = new FormaPagamentoDAO();
            for (FormaPagamento f : dao.listarTodos()) {
                cbPagamento.addItem(f);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar formas de pagamento.");
        }
    }
    private void buscarProdutos() {
        modeloProdutos.setRowCount(0);
        String termo = txtBusca.getText().trim();
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT p.id, p.nome, p.preco_venda, e.quantidade FROM produto p JOIN estoque e ON p.id = e.id_produto WHERE p.nome LIKE ? OR p.codigo_de_barras LIKE ?")) {
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modeloProdutos.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getBigDecimal(3), rs.getInt(4)});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void adicionarAoCarrinho() {
        int linha = tabelaProdutos.getSelectedRow();
        if (linha >= 0) {
            int id = (int) modeloProdutos.getValueAt(linha, 0);
            String nome = (String) modeloProdutos.getValueAt(linha, 1);
            BigDecimal preco = (BigDecimal) modeloProdutos.getValueAt(linha, 2);
            String qtdStr = JOptionPane.showInputDialog("Quantidade:");
            int qtd = Integer.parseInt(qtdStr);
            Produto produto = new Produto();
            produto.setId(id);
            produto.setNome(nome);
            VendaItem item = new VendaItem(qtd, preco, null, produto);
            carrinho.add(item);
            modeloCarrinho.addRow(new Object[]{nome, qtd, preco});
        }
    }

    private void finalizarVenda() {
        try {
            FormaPagamento forma = (FormaPagamento) cbPagamento.getSelectedItem();
            if (forma == null) {
                JOptionPane.showMessageDialog(null, "Forma de pagamento não selecionada!");
                return;
            }

            CaixaController controller = new CaixaController();
            controller.registrarVenda(carrinho, forma);

            JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso!");
            modeloCarrinho.setRowCount(0);
            carrinho.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao finalizar venda: " + ex.getMessage());
        }
    }

    private void mostrarItensDaVenda(int idVenda) {
        JFrame frame = new JFrame("Itens da Venda ID " + idVenda);
        frame.setSize(500, 300);
        frame.setLayout(null);

        DefaultTableModel modeloItens = new DefaultTableModel(new String[]{"Produto", "Qtd", "Preço Unitário"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabela = new JTable(modeloItens);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 10, 460, 230);
        frame.add(scroll);

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT p.nome, vp.quantidade, vp.preco_unitario FROM venda_produto vp JOIN produto p ON vp.id_produto = p.id WHERE vp.id_venda = ?")) {
            stmt.setInt(1, idVenda);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modeloItens.addRow(new Object[]{rs.getString("nome"), rs.getInt("quantidade"), rs.getBigDecimal("preco_unitario")});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar itens da venda.");
        }

        frame.setVisible(true);
    }

    private void mostrarHistorico() {
        JFrame frame = new JFrame("Histórico de Vendas");
        frame.setSize(1000, 600);
        frame.setLayout(null);

        JLabel lblInicio = new JLabel("Data Inicial (YYYY-MM-DD):");
        lblInicio.setBounds(10, 10, 200, 25);
        JTextField txtInicio = new JTextField();
        txtInicio.setBounds(200, 10, 150, 25);

        JLabel lblFim = new JLabel("Data Final (YYYY-MM-DD):");
        lblFim.setBounds(370, 10, 200, 25);
        JTextField txtFim = new JTextField();
        txtFim.setBounds(550, 10, 150, 25);

        JLabel lblProduto = new JLabel("Produto:");
        lblProduto.setBounds(10, 45, 100, 25);
        JTextField txtProduto = new JTextField();
        txtProduto.setBounds(200, 45, 150, 25);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(370, 45, 100, 25);
        JTextField txtCliente = new JTextField();
        txtCliente.setBounds(550, 45, 150, 25);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(720, 30, 100, 30);

        DefaultTableModel modeloHistorico = new DefaultTableModel(new String[]{"ID", "Data", "Total", "Funcionário", "Forma de Pagamento"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabela = new JTable(modeloHistorico);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 90, 960, 450);

        frame.add(lblInicio);
        frame.add(txtInicio);
        frame.add(txtFim);
        frame.add(lblFim);
        frame.add(lblProduto);
        frame.add(txtProduto);
        frame.add(lblCliente);
        frame.add(txtCliente);
        frame.add(btnFiltrar);
        frame.add(scroll);

        btnFiltrar.addActionListener(e -> {
            modeloHistorico.setRowCount(0);
            String inicio = txtInicio.getText().trim();
            String fim = txtFim.getText().trim();
            String produto = txtProduto.getText().trim();
            String cliente = txtCliente.getText().trim();

            StringBuilder sql = new StringBuilder(
                    "SELECT DISTINCT v.id, v.data_venda, v.total, f.nome AS funcionario, fp.nome AS pagamento " +
                            "FROM venda v " +
                            "JOIN funcionario f ON v.id_funcionario = f.id " +
                            "JOIN forma_de_pagamento fp ON v.id_pagamento = fp.id " +
                            "LEFT JOIN venda_produto vp ON vp.id_venda = v.id " +
                            "LEFT JOIN produto p ON vp.id_produto = p.id " +
                            "LEFT JOIN cliente c ON v.id_cliente = c.id " +
                            "WHERE 1=1 "
            );

            if (!inicio.isEmpty() && !fim.isEmpty()) {
                sql.append(" AND DATE(v.data_venda) BETWEEN ? AND ? ");
            }
            if (!produto.isEmpty()) {
                sql.append(" AND p.nome LIKE ? ");
            }
            if (!cliente.isEmpty()) {
                sql.append(" AND c.nome LIKE ? ");
            }

            sql.append(" ORDER BY v.data_venda DESC");

            try (Connection conn = ConexaoMySQL.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

                int paramIndex = 1;
                if (!inicio.isEmpty() && !fim.isEmpty()) {
                    stmt.setString(paramIndex++, inicio);
                    stmt.setString(paramIndex++, fim);
                }
                if (!produto.isEmpty()) {
                    stmt.setString(paramIndex++, "%" + produto + "%");
                }
                if (!cliente.isEmpty()) {
                    stmt.setString(paramIndex, "%" + cliente + "%");
                }

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    modeloHistorico.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getTimestamp("data_venda"),
                            rs.getBigDecimal("total"),
                            rs.getString("funcionario"),
                            rs.getString("pagamento")
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao carregar histórico de vendas.");
            }
        });

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2 && tabela.getSelectedRow() != -1) {
                    int idVenda = (int) tabela.getValueAt(tabela.getSelectedRow(), 0);
                    mostrarItensDaVenda(idVenda);
                }
            }
        });
        btnFiltrar.doClick();

        frame.setVisible(true);
    }
}
