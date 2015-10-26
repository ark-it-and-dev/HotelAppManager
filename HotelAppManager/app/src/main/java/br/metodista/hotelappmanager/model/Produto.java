package br.metodista.hotelappmanager.model;

import br.metodista.hotelappmanager.enumeration.CategoriaProduto;

/**
 * Created by Gustavo Assalin on 25/10/2015.
 */
public class Produto {
    private static Produto produto;

    private String nome;
    private String descricao;
    private double preco;
    private CategoriaProduto categoria;

    private Produto() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

    public static Produto getInstance() {
        if(produto == null) {
            return new Produto();
        } else {
            return produto;
        }
    }
}
