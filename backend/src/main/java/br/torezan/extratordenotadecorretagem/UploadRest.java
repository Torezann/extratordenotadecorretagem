package br.torezan.extratordenotadecorretagem;

import br.torezan.extratordenotadecorretagem.conversores.LocalDateAdapter;
import br.torezan.extratordenotadecorretagem.dto.NotaCorretagem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.commons.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
public class UploadRest {

    @Value("${diretorio-de-notas}")
    private String diretorioDeNotas;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("pdf") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo está vazio!");
        }

        try {
            byte[] bytes = file.getBytes();
            String conteudo = PDFReader.extrairTexto(bytes);
            List<NotaCorretagem> notas = new PDFInfoExtrator().extrairDadosNotasCorretagem(conteudo);
            salvarNotasCorretagem(notas);
//            Files.write(Paths.get("uploads/"+ new Date().getTime() + file.getOriginalFilename()), bytes);
//            return ResponseEntity.ok(String.format("""
//                        <html>
//                            <body>
//                                Sucesso ao extrair conteúdo do arquivo:
//                                <code>
//                                     %s
//                                </code>
//                            </body>
//                        </html>
//                    """, conteudo));
            var respBuilder = ResponseEntity.status(HttpStatus.FOUND);
            respBuilder.location(
                    new URI(
                            "http://localhost:5000/listarnotas"
                    )
            );
            return respBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao fazer upload do arquivo: " + e.getMessage());
        }
    }

    private void salvarNotasCorretagem(List<NotaCorretagem> notas) {
        notas.forEach(nota -> {
            try {
                salvarNota(nota);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void salvarNota(NotaCorretagem nota) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String txt = gson.toJson(nota);
        FileWriter fw = new FileWriter(diretorioDeNotas + "nc" + nota.getNumeroNota() + "-" + nota.getFolha() + ".json");
        fw.write(txt);
        fw.close();
    }


}
