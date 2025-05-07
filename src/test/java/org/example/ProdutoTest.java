package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

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
        assertTrue(p.getPreco() < 0);
    }

    @Test
    public void testCriacaoProdutoEstoqueNegativo() {
        Produto p = new Produto("Borracha", 1.0, -5);
        assertTrue(p.getEstoque() < 0);
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
        assertTrue(p.getPreco() < 0);
    }

    @Test
    public void testAumentoEstoque() {
        Produto p = new Produto("Caneta", 2.0, 5);
        p.aumentarEstoque(10);
        assertEquals(15, p.getEstoque());
    }

    @Test
    public void testDiminuicaoEstoqueValida() {
        Produto p = new Produto("Caneta", 2.0, 10);
        assertTrue(p.diminuirEstoque(4));
        assertEquals(6, p.getEstoque());
    }

    @Test
    public void testDiminuicaoEstoqueInvalida() {
        Produto p = new Produto("Caneta", 2.0, 3);
        assertFalse(p.diminuirEstoque(5));
        assertEquals(3, p.getEstoque());
    }
}
