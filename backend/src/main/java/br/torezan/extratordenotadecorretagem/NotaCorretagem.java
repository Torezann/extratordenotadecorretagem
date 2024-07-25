package br.torezan.extratordenotadecorretagem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotaCorretagem {
    private Long numeroNota;
    private Integer folha;
    private LocalDate dataPregao;
    private String nomeCorretora;
    private String nomeCliente;
    private Long codigoCliente;
    private String cpfDoCliente;
    private String enderecoCliente;

    private BigDecimal valorVendaAVista;
    private BigDecimal valorLiquidoDasOperacoes;
    private BigDecimal taxaDeLiquidacao;
    private BigDecimal valorCompraAVista;
    private BigDecimal valorDebentures;
    private BigDecimal valorCompras;
    private BigDecimal taxaDeRegistro;
    private BigDecimal valorVendas;
    private BigDecimal totalCBLC;
    private BigDecimal valorOperacoesATermo;
    private BigDecimal valorOperacoesComTitulosPublicos;
    private BigDecimal taxaDeTermo;
    private BigDecimal valorDasOperacoes;
    private BigDecimal taxaANA;
    private BigDecimal valorEmolumentos;
    private BigDecimal valorTotalBovespa;
    private BigDecimal valorClearing;
    private BigDecimal valorExecucao;
    private BigDecimal valorExecucaoCasa;
    private BigDecimal valorISS;
    private BigDecimal valorIRRFSemOperacoesBase;
    private BigDecimal valorOutrasBovespa;
    private BigDecimal valorTotalCorretagemDespesas;
    private BigDecimal valorLiquido;

    private List<NotaCorretagemNegocio> negocios = new ArrayList<NotaCorretagemNegocio>();

    public Long getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(Long numeroNota) {
        this.numeroNota = numeroNota;
    }

    public Integer getFolha() {
        return folha;
    }

    public void setFolha(Integer folha) {
        this.folha = folha;
    }

    public LocalDate getDataPregao() {
        return dataPregao;
    }

    public void setDataPregao(LocalDate dataPregao) {
        this.dataPregao = dataPregao;
    }

    public void setNomeCorretora(String nomeCorretora) {
        this.nomeCorretora = nomeCorretora;
    }

    public String getNomeCorretora() {
        return nomeCorretora;
    }

    public void setCodigoCliente(long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public long getCodigoCliente() {
        return codigoCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setCpfDoCliente(String cpfDoCliente) {
        this.cpfDoCliente = cpfDoCliente;
    }

    public String getCpfDoCliente() {
        return cpfDoCliente;
    }

    public BigDecimal getValorVendaAVista() {
        return valorVendaAVista;
    }

    public void setValorVendaAVista(BigDecimal valorVendaAVista) {
        this.valorVendaAVista = valorVendaAVista;
    }

    public BigDecimal getValorLiquidoDasOperacoes() {
        return valorLiquidoDasOperacoes;
    }

    public void setValorLiquidoDasOperacoes(BigDecimal valorLiquidoDasOperacoes) {
        this.valorLiquidoDasOperacoes = valorLiquidoDasOperacoes;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public BigDecimal getTaxaDeLiquidacao() {
        return taxaDeLiquidacao;
    }

    public void setTaxaDeLiquidacao(BigDecimal taxaDeLiquidacao) {
        this.taxaDeLiquidacao = taxaDeLiquidacao;
    }

    public BigDecimal getValorCompraAVista() {
        return valorCompraAVista;
    }

    public void setValorCompraAVista(BigDecimal valorCompraAVista) {
        this.valorCompraAVista = valorCompraAVista;
    }

    public BigDecimal getValorDebentures() {
        return valorDebentures;
    }

    public void setValorDebentures(BigDecimal valorDebentures) {
        this.valorDebentures = valorDebentures;
    }

    public BigDecimal getTaxaDeRegistro() {
        return taxaDeRegistro;
    }

    public void setTaxaDeRegistro(BigDecimal taxaDeRegistro) {
        this.taxaDeRegistro = taxaDeRegistro;
    }

    public BigDecimal getValorCompras() {
        return valorCompras;
    }

    public void setValorCompras(BigDecimal valorCompras) {
        this.valorCompras = valorCompras;
    }

    public BigDecimal getValorVendas() {
        return valorVendas;
    }

    public void setValorVendas(BigDecimal valorVendas) {
        this.valorVendas = valorVendas;
    }

    public BigDecimal getTotalCBLC() {
        return totalCBLC;
    }

    public void setTotalCBLC(BigDecimal totalCBLC) {
        this.totalCBLC = totalCBLC;
    }

    public BigDecimal getValorOperacoesATermo() {
        return valorOperacoesATermo;
    }

    public void setValorOperacoesATermo(BigDecimal valorOperacoesATermo) {
        this.valorOperacoesATermo = valorOperacoesATermo;
    }

    public BigDecimal getValorOperacoesComTitulosPublicos() {
        return valorOperacoesComTitulosPublicos;
    }

    public void setValorOperacoesComTitulosPublicos(BigDecimal valorOperacoesComTitulosPublicos) {
        this.valorOperacoesComTitulosPublicos = valorOperacoesComTitulosPublicos;
    }

    public BigDecimal getTaxaDeTermo() {
        return taxaDeTermo;
    }

    public void setTaxaDeTermo(BigDecimal taxaDeTermo) {
        this.taxaDeTermo = taxaDeTermo;
    }

    public BigDecimal getValorDasOperacoes() {
        return valorDasOperacoes;
    }

    public void setValorDasOperacoes(BigDecimal valorDasOperacoes) {
        this.valorDasOperacoes = valorDasOperacoes;
    }

    public BigDecimal getTaxaANA() {
        return taxaANA;
    }

    public void setTaxaANA(BigDecimal taxaANA) {
        this.taxaANA = taxaANA;
    }

    public BigDecimal getValorEmolumentos() {
        return valorEmolumentos;
    }

    public void setValorEmolumentos(BigDecimal valorEmolumentos) {
        this.valorEmolumentos = valorEmolumentos;
    }

    public BigDecimal getValorTotalBovespa() {
        return valorTotalBovespa;
    }

    public void setValorTotalBovespa(BigDecimal valorTotalBovespa) {
        this.valorTotalBovespa = valorTotalBovespa;
    }

    public BigDecimal getValorClearing() {
        return valorClearing;
    }

    public void setValorClearing(BigDecimal valorClearing) {
        this.valorClearing = valorClearing;
    }

    public BigDecimal getValorExecucao() {
        return valorExecucao;
    }

    public void setValorExecucao(BigDecimal valorExecucao) {
        this.valorExecucao = valorExecucao;
    }

    public BigDecimal getValorExecucaoCasa() {
        return valorExecucaoCasa;
    }

    public void setValorExecucaoCasa(BigDecimal valorExecucaoCasa) {
        this.valorExecucaoCasa = valorExecucaoCasa;
    }

    public BigDecimal getValorISS() {
        return valorISS;
    }

    public void setValorISS(BigDecimal valorISS) {
        this.valorISS = valorISS;
    }

    public BigDecimal getValorIRRFSemOperacoesBase() {
        return valorIRRFSemOperacoesBase;
    }

    public void setValorIRRFSemOperacoesBase(BigDecimal valorIRRFSemOperacoesBase) {
        this.valorIRRFSemOperacoesBase = valorIRRFSemOperacoesBase;
    }

    public BigDecimal getValorOutrasBovespa() {
        return valorOutrasBovespa;
    }

    public void setValorOutrasBovespa(BigDecimal valorOutrasBovespa) {
        this.valorOutrasBovespa = valorOutrasBovespa;
    }

    public BigDecimal getValorTotalCorretagemDespesas() {
        return valorTotalCorretagemDespesas;
    }

    public void setValorTotalCorretagemDespesas(BigDecimal valorTotalCorretagemDespesas) {
        this.valorTotalCorretagemDespesas = valorTotalCorretagemDespesas;
    }

    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }


    public List<NotaCorretagemNegocio> getNegocios() {
        return negocios;
    }

    public void setNegocios(List<NotaCorretagemNegocio> negocios) {
        this.negocios = negocios;
    }
}