package com.loja.agricola.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ferramenta")
public class Ferramenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false)
    private Integer quantidade = 0;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    public Ferramenta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}