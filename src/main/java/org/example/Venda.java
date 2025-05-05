package org.example;

public class Venda {
    private Produto produto;
    private int quantidadeVendida;
    private double totalVenda;

    public Venda(Produto produto, int quantidadeVendida) {
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public boolean realizarVenda() {
        if (produto == null) {
            throw new NullPointerException("Produto não pode ser nulo");
        }

        if (quantidadeVendida <= 0) {
            return false;
        }

        if (produto.diminuirEstoque(quantidadeVendida)) {
            totalVenda = produto.getPreco() * quantidadeVendida;
            return true;
        }

        return false;
    }



    public double getTotalVenda() {
        return totalVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
}
