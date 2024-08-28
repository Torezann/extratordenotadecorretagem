package br.torezan.extratordenotadecorretagem.dto;

import java.math.BigDecimal;

public class NotaCorretagemNegocio {
    private String negociacao;
    private String cv;
    private String tipoDeMercado;
    private String prazo;
    private String especificacaoDoTitulo;
    private Integer quantidade;
    private BigDecimal preco;
    private BigDecimal valorOperacao;
    private String dc;

    public String getNegociacao() {
        return negociacao;
    }

    public void setNegociacao(String negociacao) {
        this.negociacao = negociacao;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getTipoDeMercado() {
        return tipoDeMercado;
    }

    public void setTipoDeMercado(String tipoDeMercado) {
        this.tipoDeMercado = tipoDeMercado;
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

    public BigDecimal getValorOperacao() {
        return valorOperacao;
    }

    public void setValorOperacao(BigDecimal valorOperacao) {
        this.valorOperacao = valorOperacao;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getEspecificacaoDoTitulo() {
        return especificacaoDoTitulo;
    }

    public void setEspecificacaoDoTitulo(String especificacaoDoTitulo) {
        this.especificacaoDoTitulo = especificacaoDoTitulo;
    }

}
