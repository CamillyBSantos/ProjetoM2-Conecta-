package app.view;

import app.dao.ProdutoDAO;
import app.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TelaProdutos extends JDialog {
    private JTextField txtNome, txtPreco, txtQtd;
    private JTable tabela;
    private DefaultTableModel model;

    public TelaProdutos(Frame parent) {
        super(parent, "Produtos", true);
        setSize(700,500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4,2,10,10));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        form.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        form.add(txtNome);

        form.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        form.add(txtPreco);

        form.add(new JLabel("Quantidade:"));
        txtQtd = new JTextField();
        form.add(txtQtd);

        JButton btnAdd = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        form.add(btnAdd);
        form.add(btnAtualizar);

        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"ID","Nome","Preço","Quantidade"},0);
        tabela = new JTable(model);
        JScrollPane sp = new JScrollPane(tabela);
        add(sp, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton btnRemover = new JButton("Remover selecionado");
        bottom.add(btnRemover);
        add(bottom, BorderLayout.SOUTH);

        carregarTabela();

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Produto p = new Produto(txtNome.getText(), Double.parseDouble(txtPreco.getText()), Integer.parseInt(txtQtd.getText()));
                    app.dao.ProdutoDAO dao = new app.dao.ProdutoDAO();
                    dao.adicionar(p);
                    JOptionPane.showMessageDialog(TelaProdutos.this, "Produto adicionado com sucesso!");
                    carregarTabela();
                    txtNome.setText(""); txtPreco.setText(""); txtQtd.setText("") ;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(TelaProdutos.this, "Erro: " + ex.getMessage());
                }
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabela.getSelectedRow();
                if (row >= 0) {
                    try {
                        int id = Integer.parseInt(model.getValueAt(row,0).toString());
                        Produto p = new Produto(id, txtNome.getText(), Double.parseDouble(txtPreco.getText()), Integer.parseInt(txtQtd.getText()));
                        app.dao.ProdutoDAO dao = new app.dao.ProdutoDAO();
                        dao.atualizar(p);
                        JOptionPane.showMessageDialog(TelaProdutos.this, "Produto atualizado com sucesso!");
                        carregarTabela();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(TelaProdutos.this, "Erro: " + ex.getMessage());
                    }
                } else JOptionPane.showMessageDialog(TelaProdutos.this, "Selecione um produto na tabela primeiro.");
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabela.getSelectedRow();
                if (row >= 0) {
                    int id = Integer.parseInt(model.getValueAt(row,0).toString());
                    try {
                        app.dao.ProdutoDAO dao = new app.dao.ProdutoDAO();
                        dao.remover(id);
                        JOptionPane.showMessageDialog(TelaProdutos.this, "Produto removido.");
                        carregarTabela();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(TelaProdutos.this, "Erro: " + ex.getMessage());
                    }
                } else JOptionPane.showMessageDialog(TelaProdutos.this, "Selecione um produto na tabela primeiro.");
            }
        });

        tabela.getSelectionModel().addListSelectionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row >= 0) {
                txtNome.setText(model.getValueAt(row,1).toString());
                txtPreco.setText(model.getValueAt(row,2).toString());
                txtQtd.setText(model.getValueAt(row,3).toString());
            }
        });
    }

    private void carregarTabela() {
        try {
            app.dao.ProdutoDAO dao = new app.dao.ProdutoDAO();
            java.util.List<Produto> lista = dao.listar();
            model.setRowCount(0);
            for (Produto p : lista) {
                model.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + ex.getMessage());
        }
    }
}
