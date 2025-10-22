package app.dao;

import app.model.Venda;
import app.Conexao;

import java.sql.*;

public class VendaDAO {

    public void registrarVenda(Venda v) throws SQLException {
        String sqlVenda = "INSERT INTO vendas (produto_id, quantidade) VALUES (?, ?);";
        String sqlUpdateProduto = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ?;";
        Connection c = null;
        try {
            c = app.Conexao.getConnection();
            c.setAutoCommit(false);

            try (PreparedStatement ps = c.prepareStatement(sqlVenda)) {
                ps.setInt(1, v.getProdutoId());
                ps.setInt(2, v.getQuantidade());
                ps.executeUpdate();
            }

            try (PreparedStatement ps2 = c.prepareStatement(sqlUpdateProduto)) {
                ps2.setInt(1, v.getQuantidade());
                ps2.setInt(2, v.getProdutoId());
                ps2.executeUpdate();
            }

            c.commit();
        } catch (SQLException ex) {
            if (c != null) try { c.rollback(); } catch (SQLException e) {}
            throw ex;
        } finally {
            if (c != null) try { c.setAutoCommit(true); c.close(); } catch (SQLException e) {}
        }
    }
}
