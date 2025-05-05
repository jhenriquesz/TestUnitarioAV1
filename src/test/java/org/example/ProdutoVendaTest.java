package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoVendaTest {

    @Test
    public void testCriacaoProdutoValido() {
        Produto p = new Produto("Caneta", 2.5, 10);
        assertEquals("Caneta", p.getNome());
        assertEquals(2.5, p.getPreco());
        assertEquals(10, p.getEstoque());
    }

    @Test
    public void testCriacaoProdutoPrecoNegativo() {
        Produto p = new Produto("Lápis", -1.0, 10);
        assertTrue(p.getPreco() < 0); // O código original permite isso, mas deveria bloquear
    }

    @Test
    public void testCriacaoProdutoEstoqueNegativo() {
        Produto p = new Produto("Borracha", 1.0, -5);
        assertTrue(p.getEstoque() < 0); // O código permite estoque negativo
    }

    @Test
    public void testAlterarNomeProdutoValido() {
        Produto p = new Produto("Lápis", 1.0, 10);
        p.setNome("Lápis HB");
        assertEquals("Lápis HB", p.getNome());
    }

    @Test
    public void testAlterarPrecoValido() {
        Produto p = new Produto("Lápis", 1.0, 10);
        p.setPreco(1.5);
        assertEquals(1.5, p.getPreco());
    }

    @Test
    public void testAlterarEstoquePositivo() {
        Produto p = new Produto("Lápis", 1.0, 10);
        p.setEstoque(20);
        assertEquals(20, p.getEstoque());
    }

    @Test
    public void testAlterarPrecoNegativo() {
        Produto p = new Produto("Lápis", 1.0, 10);
        p.setPreco(-2.0);
        assertTrue(p.getPreco() < 0); // Deveria impedir isso
    }

    @Test
    public void testVendaQuantidadeMenorQueEstoque() {
        Produto p = new Produto("Livro", 20.0, 10);
        Venda v = new Venda(p, 5);
        assertTrue(v.realizarVenda());
        assertEquals(5, v.getQuantidadeVendida());
    }

    @Test
    public void testVendaQuantidadeIgualEstoque() {
        Produto p = new Produto("Livro", 20.0, 10);
        Venda v = new Venda(p, 10);
        assertTrue(v.realizarVenda());
        assertEquals(0, p.getEstoque());
    }

    @Test
    public void testVendaQuantidadeMaiorQueEstoque() {
        Produto p = new Produto("Livro", 20.0, 5);
        Venda v = new Venda(p, 6);
        assertFalse(v.realizarVenda());
    }

    @Test
    public void testCalculoTotalVenda() {
        Produto p = new Produto("Caderno", 10.0, 10);
        Venda v = new Venda(p, 3);
        assertTrue(v.realizarVenda());
        assertEquals(30.0, v.getTotalVenda());
    }

    @Test
    public void testAumentoEstoqueAposVenda() {
        Produto p = new Produto("Caneta", 2.0, 5);
        p.aumentarEstoque(10);
        assertEquals(15, p.getEstoque());
    }

    @Test
    public void testDiminuiEstoqueAposVenda() {
        Produto p = new Produto("Caneta", 2.0, 10);
        Venda v = new Venda(p, 4);
        v.realizarVenda();
        assertEquals(6, p.getEstoque());
    }

    @Test
    public void testVendaProdutoNulo() {
        Venda v = new Venda(null, 5);
        assertThrows(NullPointerException.class, v::realizarVenda);
    }

    @Test
    public void testVendaComQuantidadeNegativa() {
        Produto p = new Produto("Caderno", 5.0, 10);
        Venda v = new Venda(p, -2);
        boolean resultado = v.realizarVenda();

        assertFalse(resultado, "Vendas com quantidade negativa não devem ser permitidas");
        assertEquals(10, p.getEstoque(), "O estoque não deve ser alterado após tentativa inválida de venda");
    }


    @Test
    public void testEstoqueAposVendaInsuficiente() {
        Produto p = new Produto("Apontador", 2.0, 3);
        Venda v = new Venda(p, 5);
        v.realizarVenda();
        assertEquals(3, p.getEstoque()); // Não deve mudar
    }

    @Test
    public void testMultiplosProdutosComEstoqueCompartilhado() {
        Produto p1 = new Produto("Papel A4", 1.0, 100);
        Produto p2 = new Produto("Cartolina", 2.0, 100);

        Venda v1 = new Venda(p1, 10);
        Venda v2 = new Venda(p2, 20);

        v1.realizarVenda();
        v2.realizarVenda();

        assertEquals(90, p1.getEstoque());
        assertEquals(80, p2.getEstoque());
    }

    @Test
    public void testPrecoAlteradoAntesDaVenda() {
        Produto p = new Produto("Caderno", 10.0, 10);
        p.setPreco(15.0);
        Venda v = new Venda(p, 2);
        v.realizarVenda();
        assertEquals(30.0, v.getTotalVenda());
    }

    @Test
    public void testVendaComEstoqueZero() {
        Produto p = new Produto("Grampeador", 15.0, 0);
        Venda v = new Venda(p, 1);
        assertFalse(v.realizarVenda());
    }

    @Test
    public void testVendaAposReposicaoDeEstoque() {
        Produto p = new Produto("Régua", 3.0, 0);
        p.aumentarEstoque(5);
        Venda v = new Venda(p, 3);
        assertTrue(v.realizarVenda());
        assertEquals(2, p.getEstoque());
    }
}
