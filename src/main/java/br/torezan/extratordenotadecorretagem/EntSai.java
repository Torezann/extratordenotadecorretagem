package br.torezan.extratordenotadecorretagem;

public class EntSai {

    private int entrada;

    public EntSai(int entrada) {
        this.entrada = entrada;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSaida() {
        return entrada * 2;
    }



}
