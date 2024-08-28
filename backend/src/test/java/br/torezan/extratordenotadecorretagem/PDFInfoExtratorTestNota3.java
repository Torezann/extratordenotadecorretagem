package br.torezan.extratordenotadecorretagem;

import br.torezan.extratordenotadecorretagem.dto.NotaCorretagem;
import br.torezan.extratordenotadecorretagem.dto.NotaCorretagemNegocio;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class PDFInfoExtratorTestNota3 {

    private PDFInfoExtrator pdfInfoExtrator = new PDFInfoExtrator();

    @Test
    public void testarNota1() throws Exception {
        String textoArquivo = pdfInfoExtrator.extrairTextoTxt("/modelos/Nota3.txt");
        List<NotaCorretagem> notas = pdfInfoExtrator.extrairDadosNotasCorretagem(textoArquivo);
        assertFalse(notas.isEmpty());
        NotaCorretagem notaCorretagem = notas.get(0);
        assertEquals(123456789, notaCorretagem.getCodigoCliente());
        assertEquals("Neymar da Silva Santos Junior", notaCorretagem.getNomeCliente());
        assertEquals("321.654.987-01", notaCorretagem.getCpfDoCliente());
        assertEquals("QUADRA 900, 2 - 204-A 81110370 MACAE RIO DE Tel. (11) 985112957", notaCorretagem.getEnderecoCliente());

        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorDebentures());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorVendaAVista());
        assertEquals(new BigDecimal("355.0"), notaCorretagem.getValorLiquidoDasOperacoes());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorCompraAVista());
        assertEquals(new BigDecimal("0.09"  ), notaCorretagem.getTaxaDeLiquidacao());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorCompras());
        assertEquals(new BigDecimal("0.24"  ), notaCorretagem.getTaxaDeRegistro());
        assertEquals(new BigDecimal("355.0"), notaCorretagem.getValorVendas());
        assertEquals(new BigDecimal("354.67"), notaCorretagem.getTotalCBLC());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorOperacoesATermo());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorOperacoesComTitulosPublicos());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getTaxaDeTermo());
        assertEquals(new BigDecimal("355.0"), notaCorretagem.getValorDasOperacoes());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getTaxaANA());
        assertEquals(new BigDecimal("0.13"  ), notaCorretagem.getValorEmolumentos());
        assertEquals(new BigDecimal("0.13"  ), notaCorretagem.getValorTotalBovespa());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorClearing());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorExecucao());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorExecucaoCasa());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorISS());
        assertEquals(new BigDecimal("0.01"  ), notaCorretagem.getValorIRRFSemOperacoesBase());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorOutrasBovespa());
        assertEquals(new BigDecimal("0.0"  ), notaCorretagem.getValorTotalCorretagemDespesas());
        assertEquals(new BigDecimal("354.54"), notaCorretagem.getValorLiquido());

        for (int i = 0; i < notaCorretagem.getNegocios().size(); i++) {
            NotaCorretagemNegocio ncn = notaCorretagem.getNegocios().get(i);
            if (i == 0) {
                assertEquals("1-BOVESPA", ncn.getNegociacao());
                assertEquals("V", ncn.getCv());
                assertEquals("OPCAO DE COMPRA", ncn.getTipoDeMercado());
                assertEquals("02/24", ncn.getPrazo());
                assertEquals("BBASB610 ON", ncn.getEspecificacaoDoTitulo());
                assertEquals(500, ncn.getQuantidade());
                assertEquals(new BigDecimal("0.5"), ncn.getPreco());
                assertEquals(new BigDecimal("250.0"), ncn.getValorOperacao());
                assertEquals("C", ncn.getDc());
            }
            if (i == 1) {
                assertEquals("1-BOVESPA", ncn.getNegociacao());
                assertEquals("V", ncn.getCv());
                assertEquals("OPCAO DE COMPRA", ncn.getTipoDeMercado());
                assertEquals("02/24", ncn.getPrazo());
                assertEquals("ITSAB113 PN", ncn.getEspecificacaoDoTitulo());
                assertEquals(2100, ncn.getQuantidade());
                assertEquals(new BigDecimal("0.05"), ncn.getPreco());
                assertEquals(new BigDecimal("105.0"), ncn.getValorOperacao());
                assertEquals("C", ncn.getDc());
            }
        }
    }
}