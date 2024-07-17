package br.torezan.extratordenotadecorretagem;

import java.time.LocalDate;
import java.util.Date;

public class NotaCorretagem {
    private Long numeroNota;
    private Integer folha;
    private LocalDate dataPregao;
    private String nomeCorretora;
    private String nomeCliente;
    private Long codigoCliente;

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
}
