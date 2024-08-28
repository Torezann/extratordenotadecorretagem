package br.torezan.extratordenotadecorretagem.dto;

public class DadosBasicosNotaDto {
    private Integer folha;
    private Long numero;
    private String nome;

    public Integer getFolha() {
        return folha;
    }

    public void setFolha(Integer folha) {
        this.folha = folha;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}