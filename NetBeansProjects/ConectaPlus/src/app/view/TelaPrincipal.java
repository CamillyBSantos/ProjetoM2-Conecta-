package app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Conecta+ - Gestão Simples");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(Color.WHITE);
        // logo (resources/logo.png)
        ImageIcon logo = null;
        try {
            logo = new ImageIcon(getClass().getResource("/resources/logo.png"));
        } catch (Exception e) {
            // ignore if not found
        }
        if (logo != null) {
            JLabel l = new JLabel(logo);
            top.add(l);
        } else {
            JLabel l = new JLabel("CONECTA +");
            l.setFont(new Font("Arial", Font.BOLD, 24));
            top.add(l);
        }
        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.WHITE);
        center.setLayout(new GridLayout(2, 2, 20, 20));
        center.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnProdutos = new JButton("Produtos");
        btnProdutos.setFont(new Font("Arial", Font.BOLD, 20));
        btnProdutos.setBackground(new Color(59,130,246));
        btnProdutos.setForeground(Color.WHITE);

        JButton btnVendas = new JButton("Vendas");
        btnVendas.setFont(new Font("Arial", Font.BOLD, 20));
        btnVendas.setBackground(new Color(245,158,11));
        btnVendas.setForeground(Color.WHITE);

        JButton btnRelatorios = new JButton("Relatórios");
        btnRelatorios.setFont(new Font("Arial", Font.BOLD, 20));
        btnRelatorios.setBackground(new Color(34,197,94));
        btnRelatorios.setForeground(Color.WHITE);

        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Arial", Font.BOLD, 20));
        btnSair.setBackground(new Color(107,114,128));
        btnSair.setForeground(Color.WHITE);

        center.add(btnProdutos);
        center.add(btnVendas);
        center.add(btnRelatorios);
        center.add(btnSair);

        add(center, BorderLayout.CENTER);

        btnProdutos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaProdutos tp = new TelaProdutos(TelaPrincipal.this);
                tp.setVisible(true);
            }
        });

        btnVendas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaVendas tv = new TelaVendas(TelaPrincipal.this);
                tv.setVisible(true);
            }
        });

        btnRelatorios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaRelatorio tr = new TelaRelatorio(TelaPrincipal.this);
                tr.setVisible(true);
            }
        });

        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaPrincipal tp = new TelaPrincipal();
                tp.setVisible(true);
            }
        });
    }
}
