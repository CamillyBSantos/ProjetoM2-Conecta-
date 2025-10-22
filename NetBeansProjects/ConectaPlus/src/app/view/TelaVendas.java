package app.view;

import app.dao.ProdutoDAO;
import app.dao.VendaDAO;
import app.model.Produto;
import app.model.Venda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TelaVendas extends JDialog {
    private JComboBox<Produto> comboProdutos;
    private JTextField txtQtd;

    public TelaVendas(Frame parent) {
        super(parent, "Registrar Venda", true);
        setSize(400,250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3,2,10,10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(new JLabel("Produto:"));
        comboProdutos = new JComboBox<>();
        add(comboProdutos);

        add(new JLabel("Quantidade:"));
        txtQtd = new JTextField("1");
        add(txtQtd);

        JButton btnRegistrar = new JButton("Registrar Venda");
        add(new JLabel());
        add(btnRegistrar);

        carregarProdutos();

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Produto p = (Produto)comboProdutos.getSelectedItem();
                if (p == null) { JOptionPane.showMessageDialog(TelaVendas.this, "Nenhum produto selecionado."); return; }
                try {
                    int qtd = Integer.parseInt(txtQtd.getText());
                    Venda v = new Venda(p.getId(), qtd);
                    VendaDAO dao = new VendaDAO();
                    dao.registrarVenda(v);
                    JOptionPane.showMessageDialog(TelaVendas.this, "Venda registrada com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(TelaVendas.this, "Erro ao registrar venda: " + ex.getMessage());
                }
            }
        });
    }

    private void carregarProdutos() {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            List<Produto> lista = dao.listar();
            comboProdutos.removeAllItems();
            for (Produto p : lista) comboProdutos.addItem(p);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + ex.getMessage());
        }
    }
}
