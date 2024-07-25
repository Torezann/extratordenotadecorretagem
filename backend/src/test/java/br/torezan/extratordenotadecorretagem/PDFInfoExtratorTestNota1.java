package br.torezan.extratordenotadecorretagem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PDFInfoExtratorTestNota1 {

    private PDFInfoExtrator pdfInfoExtrator = new PDFInfoExtrator();

    @Test
    public void testarNota1() throws Exception {
        String textoArquivo = pdfInfoExtrator.extrairTextoTxt("/modelos/Nota1.txt");
        List<NotaCorretagem> notas = pdfInfoExtrator.extrairDadosNotasCorretagem(textoArquivo);
        assertFalse(notas.isEmpty());
        NotaCorretagem notaCorretagem = notas.get(0);
        assertEquals("321.654.987-01", notaCorretagem.getCpfDoCliente());
        assertEquals("QUADRA 900, 2 - 204-A 81110370 MACAE RIO DE Tel. (11) 985112957", notaCorretagem.getEnderecoCliente());
    }
}