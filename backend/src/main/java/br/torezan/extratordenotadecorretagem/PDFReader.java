package br.torezan.extratordenotadecorretagem;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PDFReader {

    public static String extrairTexto(byte[] bytes) throws IOException {

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(new ByteArrayInputStream(bytes)));
        StringBuilder text = new StringBuilder();

        for (int numeroDaPagina = 1; numeroDaPagina <= pdfDoc.getNumberOfPages(); numeroDaPagina++) {
            String conteudoDaPagina = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(numeroDaPagina));
            text.append(conteudoDaPagina);
        }

        return text.toString();
    }
}