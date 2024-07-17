package br.torezan.extratordenotadecorretagem;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PDFInfoExtrator {

    private static DateTimeFormatter dateTimeDDMMAAAA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        try {
            InputStream resource = PDFInfoExtrator.class.getResourceAsStream("/modelos/EQI-01-2024.pdf");
//            InputStream resource = PDFInfoExtrator.class.getResourceAsStream("/modelos/EMP 29 MAI 2024.pdf");
            byte[] bytes = resource.readAllBytes();
            String textoArquivo = PDFReader.extrairTexto(bytes);
            List<NotaCorretagem> notasCorretagem = new ArrayList<>();
            String[] notasDoArquivo = textoArquivo.split("NOTA DE CORRETAGEM");
            for (String nota : notasDoArquivo) {
                NotaCorretagem notaCorretagem = extrairDados(nota);
                notasCorretagem.add(notaCorretagem);
            }
            System.out.println(textoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static NotaCorretagem extrairDados(String textoArquivo) {
        NotaCorretagem notaCorretagem = new NotaCorretagem();
        String[] linhas = textoArquivo.split("\n");
        int linhaAtual = 0;
        for (String linha : linhas) {
            if (linhaAtual == 2) {
                extrairNumeroNotaFolhaDataPregao(linha, notaCorretagem);
            }
            if (linhaAtual == 3) {
                extrairNomeCorretora(linha, notaCorretagem);
            }
            if (linha.contains("Cliente C.P.F.")) {
                extrairDadosCliente(linhas[linhaAtual + 1].trim(), notaCorretagem);
            }

            linhaAtual++;
        }
        return notaCorretagem;
    }

    private static void extrairDadosCliente(String linha, NotaCorretagem notaCorretagem) {
        String codigo = cortarAntes(linha, " ");
        String nome = cortarDepois(linha, " ");
        notaCorretagem.setCodigoCliente(Long.parseLong(codigo));
        notaCorretagem.setNomeCliente(nome);
    }

    private static String cortarAntes(String texto, String separador) {
        int posicao = texto.indexOf(separador);
        return texto.substring(0, posicao);
    }

    private static String cortarDepois(String texto, String separador) {
        int posicao = texto.indexOf(separador) + separador.length();
        return texto.substring(posicao);
    }

    private static void extrairNomeCorretora(String linha, NotaCorretagem notaCorretagem) {
        notaCorretagem.setNomeCorretora(linha);
    }

    private static void extrairNumeroNotaFolhaDataPregao(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String numeroNota = dados[0];
        String folha = dados[1];
        String dataPregao = dados[2];
        notaCorretagem.setNumeroNota(Long.parseLong(numeroNota));
        notaCorretagem.setFolha(Integer.parseInt(folha));
        notaCorretagem.setDataPregao(toLocalDate(dataPregao));
    }

    private static LocalDate toLocalDate(String dataPregao) {
        return LocalDate.parse(dataPregao, dateTimeDDMMAAAA);
    }
}
