package br.torezan.extratordenotadecorretagem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PDFInfoExtrator {

    private static String[] TIPOS_MERCADO = new String[]{
            "VISTA", "OPCAO DE VENDA", "OPCAO DE COMPRA"
    };

    private static DateTimeFormatter dateTimeDDMMAAAA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static NumberFormat numberFormat = new DecimalFormat("##,###,###,##0.00");

    public static void main(String[] args) {
        try {
            PDFInfoExtrator pdfInfoExtrator = new PDFInfoExtrator();
//            String textoArquivo = pdfInfoExtrator.extrairTextoPdf("C:/Users/Admin/Downloads/EQI.pdf");
//            String textoArquivo = pdfInfoExtrator.extrairTextoPdf("C:/Users/Admin/Downloads/EMP1.pdf");
//            String textoArquivo = pdfInfoExtrator.extrairTextoPdf("C:/Users/Admin/Downloads/BTG-02-2024-004789172_20240201_20240229_OPTIONS_ALL.pdf");
//            String textoArquivo = pdfInfoExtrator.extrairTextoPdf("C:/Users/Admin/Downloads/Empiricus_20231001_20231031_SPOT_ALL.pdf");
//            String textoArquivo = pdfInfoExtrator.extrairTextoTxt("/modelos/Nota1.txt");
//            String textoArquivo = pdfInfoExtrator.extrairTextoTxt("/modelos/Nota2.txt");
            String textoArquivo = pdfInfoExtrator.extrairTextoTxt("/modelos/Nota3.txt");
//            String textoArquivo = pdfInfoExtrator.extrairTextoTxt("/modelos/Nota4.txt");
            pdfInfoExtrator.extrairDadosNotasCorretagem(textoArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NotaCorretagem> extrairDadosNotasCorretagem(String textoArquivo) {
        List<NotaCorretagem> notasCorretagem = new ArrayList<>();
        String[] notasDoArquivo = textoArquivo.split("NOTA DE CORRETAGEM");

        for (String nota : notasDoArquivo) {
            NotaCorretagem notaCorretagem = extrairDados(nota);
            if (notaCorretagem != null) {
                notasCorretagem.add(notaCorretagem);
            }
        }
        return notasCorretagem;
    }

    public String extrairTextoTxt(String nomeArquivo) throws IOException {
        InputStream is = PDFInfoExtrator.class.getResourceAsStream(nomeArquivo);
        byte[] bytes = is.readAllBytes();
        String texto = new String(bytes);
        texto = texto.replaceAll("\r\n", "\n");
        return texto;
    }

    private String extrairTextoPdf(String nomeArquivo) throws IOException {
        InputStream resource = new FileInputStream(nomeArquivo);
        byte[] bytes = resource.readAllBytes();
        return PDFReader.extrairTexto(bytes);
    }

    private void extrairNegociosRealizados(String textoArquivo, NotaCorretagem notaCorretagem) {
        int posicaoInicial = textoArquivo.indexOf("cios realizados");
        int posicaoFinal = textoArquivo.indexOf("Resumo dos Neg");
        String negociosRealizados = textoArquivo.substring(posicaoInicial, posicaoFinal);
        String[] linhas = negociosRealizados.split("\n");
        int linhaAtual = 0;
        for (String linha : linhas) {
            if (linhaAtual >= 2) {
                extrairDadosDosNegociosRealizados(linha, notaCorretagem);
            }
            linhaAtual++;
        }
    }

    private void extrairDadosDosNegociosRealizados(String linha, NotaCorretagem notaCorretagem) {
        StringBuilder sb = new StringBuilder(linha.trim());
        String dc = extrairUltimoToken(sb);
        String valorOperacao = extrairUltimoToken(sb);
        String preco = extrairUltimoToken(sb);
        String quantidade = extrairUltimoToken(sb);
        String negociacao = extrairPrimeiroToken(sb);
        String cv = extrairPrimeiroToken(sb);
        String tipoDeMercado = extrairSeEncontrar(sb, TIPOS_MERCADO);
        String prazo = extrairPrimeiroToken(sb);
        String especificacaoDoTitulo = sb.toString();
        NotaCorretagemNegocio negocio = new NotaCorretagemNegocio();
        negocio.setNegociacao(negociacao);
        negocio.setCv(cv);
        negocio.setTipoDeMercado(tipoDeMercado);
        negocio.setPrazo(prazo);
        negocio.setEspecificacaoDoTitulo(especificacaoDoTitulo);
        negocio.setQuantidade(Integer.parseInt(quantidade));
        negocio.setPreco(toBigDecimal(preco));
        negocio.setValorOperacao(toBigDecimal(valorOperacao));
        negocio.setDc(dc);
        notaCorretagem.getNegocios().add(negocio);
    }

    private String extrairSeEncontrar(StringBuilder sb, String[] tiposMercado) {

        String linha = sb.toString().toUpperCase();
        for (String tipo : tiposMercado) {
            if (linha.contains(tipo.toUpperCase())) {
                int pos = linha.indexOf(tipo.toUpperCase());
                sb.delete(pos, pos + tipo.length() + 1);
                return tipo;
            }
        }
        return null;
    }

    private String extrairPrimeiroToken(StringBuilder sb) {
        int pos = sb.indexOf(" ");
        String valor = null;
        if (pos != -1) {
            valor = sb.substring(0, pos);
            sb.delete(0, pos + 1);
        }
        return valor;
    }

    private String extrairUltimoToken(StringBuilder sb) {
        int pos = sb.lastIndexOf(" ");
        String valor = null;
        if (pos > -1) {
            valor = sb.substring(pos + 1);
            sb.delete(pos, sb.length());
        }
        return valor;
    }

    private NotaCorretagem extrairDados(String textoArquivo) {
        if (textoArquivo.trim().length() > 0) {
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
                    extrairCpfDoCliente(linhas[linhaAtual + 2], notaCorretagem);
                    extrairEndercoCliente(linhas[linhaAtual + 3], notaCorretagem);
                }
                if (linha.contains("Resumo dos Neg")) {
                    extrairDebentures(linhas[linhaAtual + 1], notaCorretagem);
                    extrairVendasAVista(linhas[linhaAtual + 2], notaCorretagem);
                    extrairValorLiquidoDasOperacoes(linhas[linhaAtual + 2], notaCorretagem);
                    extrairComprasAVista(linhas[linhaAtual + 3], notaCorretagem);
                    extrairTaxaDeLiquidacao(linhas[linhaAtual + 3], notaCorretagem);
                    extrairValorCompras(linhas[linhaAtual + 4], notaCorretagem);
                    extrairTaxaDeResgistro(linhas[linhaAtual + 4], notaCorretagem);
                    extrairValorVendas(linhas[linhaAtual + 5], notaCorretagem);
                    extrairTotalCBLC(linhas[linhaAtual + 5], notaCorretagem);
                    extrairValorOperacoesATermo(linhas[linhaAtual + 6], notaCorretagem);
                    extrairValorOperacoesComTitulosPublicos(linhas[linhaAtual + 7], notaCorretagem);
                    extrairTaxaDeTermo(linhas[linhaAtual + 7], notaCorretagem);
                    extrairValorDasOperacoes(linhas[linhaAtual + 8], notaCorretagem);
                    extrairTaxaANA(linhas[linhaAtual + 8], notaCorretagem);
                    extrairValorEmolumentos(linhas[linhaAtual + 9], notaCorretagem);
                    extrairValorTotalBovespa(linhas[linhaAtual + 10], notaCorretagem);
                    extrairValorClearing(linhas[linhaAtual + 12], notaCorretagem);
                    extrairValorExecucao(linhas[linhaAtual + 13], notaCorretagem);
                    extrairValorExecucaoCasa(linhas[linhaAtual + 14], notaCorretagem);
                    extrairValorISS(linhas[linhaAtual + 15], notaCorretagem);
                    extrairValorIRRFSemOperacoesBase(linhas[linhaAtual + 16], notaCorretagem);
                    extrairValorOutrasBovespa(linhas[linhaAtual + 17], notaCorretagem);
                    extrairValorTotalCorretagemDespesas(linhas[linhaAtual + 18], notaCorretagem);
                    extrairValorLiquido(linhas[linhaAtual + 20], notaCorretagem);
                }
                linhaAtual++;
            }
            extrairNegociosRealizados(textoArquivo, notaCorretagem);
            return notaCorretagem;
        }
        return null;
    }

    private void extrairValorLiquido(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorLiquido = dados[0];
        notaCorretagem.setValorLiquido(toBigDecimal(valorLiquido));
    }

    private void extrairValorTotalCorretagemDespesas(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorTotalCorretagemDespesas = dados[4];
        notaCorretagem.setValorTotalCorretagemDespesas(toBigDecimal(valorTotalCorretagemDespesas));
    }

    private void extrairValorOutrasBovespa(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorOutrasBovespa = dados[2];
        notaCorretagem.setValorOutrasBovespa(toBigDecimal(valorOutrasBovespa));
    }

    private void extrairValorIRRFSemOperacoesBase(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorIRRFSemOperacoesBase = dados[6];
        notaCorretagem.setValorIRRFSemOperacoesBase(toBigDecimal(valorIRRFSemOperacoesBase));
    }

    private void extrairValorISS(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorISS = dados[5];
        notaCorretagem.setValorISS(toBigDecimal(valorISS));
    }

    private void extrairValorExecucaoCasa(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorExecucaoCasa = dados[2];
        notaCorretagem.setValorExecucaoCasa(toBigDecimal(valorExecucaoCasa));
    }

    private void extrairValorExecucao(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorExecucao = dados[1];
        notaCorretagem.setValorExecucao(toBigDecimal(valorExecucao));
    }

    private void extrairValorClearing(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorClearing = dados[10];
        notaCorretagem.setValorClearing(toBigDecimal(valorClearing));
    }

    private void extrairValorTotalBovespa(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorTotalBovespa = dados[4];
        notaCorretagem.setValorTotalBovespa(toBigDecimal(valorTotalBovespa));
    }

    private void extrairValorEmolumentos(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorEmolumentos = dados[1];
        notaCorretagem.setValorEmolumentos(toBigDecimal(valorEmolumentos));
    }

    private void extrairTaxaANA(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String taxaANA = dados[6];
        notaCorretagem.setTaxaANA(toBigDecimal(taxaANA));
    }

    private void extrairValorDasOperacoes(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorDasOperacoes = dados[3];
        notaCorretagem.setValorDasOperacoes(toBigDecimal(valorDasOperacoes));
    }

    private void extrairTaxaDeTermo(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String taxaDeTermo = dados[12];
        notaCorretagem.setTaxaDeTermo(toBigDecimal(taxaDeTermo));
    }

    private void extrairValorOperacoesComTitulosPublicos(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorOperacoesComTitulosPublicos = dados[8];
        notaCorretagem.setValorOperacoesComTitulosPublicos(toBigDecimal(valorOperacoesComTitulosPublicos));
    }

    private void extrairValorOperacoesATermo(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorOperacoesATermo = dados[3];
        notaCorretagem.setValorOperacoesATermo(toBigDecimal(valorOperacoesATermo));
    }

    private void extrairTotalCBLC(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String totalCBLC = dados[6];
        notaCorretagem.setTotalCBLC(toBigDecimal(totalCBLC));
    }

    private void extrairValorVendas(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorVendas = dados[3];
        notaCorretagem.setValorVendas(toBigDecimal(valorVendas));
    }

    private void extrairTaxaDeResgistro(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String taxaDeRegistro = dados[7];
        notaCorretagem.setTaxaDeRegistro(toBigDecimal(taxaDeRegistro));
    }

    private void extrairValorCompras(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorCompras = dados[3];
        notaCorretagem.setValorCompras(toBigDecimal(valorCompras));
    }

    private void extrairTaxaDeLiquidacao(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String taxaDeLiquidacao = dados[7];
        notaCorretagem.setTaxaDeLiquidacao(toBigDecimal(taxaDeLiquidacao));
    }

    private void extrairComprasAVista(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorCompraAVista = dados[3];
        notaCorretagem.setValorCompraAVista(toBigDecimal(valorCompraAVista));
    }

    private void extrairValorLiquidoDasOperacoes(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorLiquidoDasOperacoes = dados[8];
        notaCorretagem.setValorLiquidoDasOperacoes(toBigDecimal(valorLiquidoDasOperacoes));
    }

    private void extrairVendasAVista(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorVendaAVista = dados[3];
        notaCorretagem.setValorVendaAVista(toBigDecimal(valorVendaAVista));
    }

    private void extrairDebentures(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String valorDebentures = dados[1];
        notaCorretagem.setValorDebentures(toBigDecimal(valorDebentures));
    }

    private void extrairEndercoCliente(String linha, NotaCorretagem notaCorretagem) {
        notaCorretagem.setEnderecoCliente(linha);
    }

    private void extrairCpfDoCliente(String linha, NotaCorretagem notaCorretagem) {
        notaCorretagem.setCpfDoCliente(linha);
    }

    private void extrairDadosCliente(String linha, NotaCorretagem notaCorretagem) {
        String codigo = cortarAntes(linha, " ");
        String nome = cortarDepois(linha, " ");
        notaCorretagem.setCodigoCliente(Long.parseLong(codigo));
        notaCorretagem.setNomeCliente(nome);
    }

    private String cortarAntes(String texto, String separador) {
        int posicao = texto.indexOf(separador);
        return texto.substring(0, posicao);
    }

    private String cortarDepois(String texto, String separador) {
        int posicao = texto.indexOf(separador) + separador.length();
        return texto.substring(posicao);
    }

    private void extrairNomeCorretora(String linha, NotaCorretagem notaCorretagem) {
        notaCorretagem.setNomeCorretora(linha);
    }

    private void extrairNumeroNotaFolhaDataPregao(String linha, NotaCorretagem notaCorretagem) {
        String[] dados = linha.split(" ");
        String numeroNota = dados[0];
        String folha = dados[1];
        String dataPregao = dados[2];
        notaCorretagem.setNumeroNota(Long.parseLong(numeroNota));
        notaCorretagem.setFolha(Integer.parseInt(folha));
        notaCorretagem.setDataPregao(toLocalDate(dataPregao));
    }

    private BigDecimal toBigDecimal(String texto) {
        String txt = texto.replaceAll("\\.", "").replaceAll(",", ".");
        return BigDecimal.valueOf(Double.parseDouble(txt));
    }

    private LocalDate toLocalDate(String dataPregao) {
        return LocalDate.parse(dataPregao, dateTimeDDMMAAAA);
    }
}
