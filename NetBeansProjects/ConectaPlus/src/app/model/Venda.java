package app.model;

import java.time.LocalDateTime;

public class Venda {
    private int id;
    private int produtoId;
    private int quantidade;
    private LocalDateTime dataVenda;

    public Venda() {}

    public Venda(int produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.dataVenda = LocalDateTime.now();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDateTime dataVenda) { this.dataVenda = dataVenda; }
}
