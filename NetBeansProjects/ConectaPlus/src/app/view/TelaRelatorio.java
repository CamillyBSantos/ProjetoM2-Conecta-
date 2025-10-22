package app.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TelaRelatorio extends JDialog {
    private JTable tabela;
    private DefaultTableModel model;

    public TelaRelatorio(Frame parent) {
        super(parent, "Relatórios", true);
        setSize(700,450);
        setLocationRelativeTo(parent);
        model = new DefaultTableModel(new Object[]{"ID","Produto","Quantidade","Data"},0);
        tabela = new JTable(model);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        carregarVendas();
    }

    private void carregarVendas() {
        String sql = "SELECT v.id, p.nome, v.quantidade, v.data_venda FROM vendas v JOIN produtos p ON v.produto_id = p.id ORDER BY v.data_venda DESC;";
        try (Connection c = app.Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{ rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4) });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar relatório: " + ex.getMessage());
        }
    }
}
