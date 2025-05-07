package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendaTest {

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
    public void testVendaProdutoNulo() {
        Venda v = new Venda(null, 5);
        boolean resultado = v.realizarVenda();
        assertFalse(resultado);
    }

    @Test
    public void testVendaComQuantidadeNegativa() {
        Produto p = new Produto("Caderno", 5.0, 10);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Venda(p, -2);
        });

        assertEquals("Quantidade vendida não pode ser negativa", ex.getMessage());
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
