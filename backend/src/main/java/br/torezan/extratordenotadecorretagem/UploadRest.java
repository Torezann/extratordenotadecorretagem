package br.torezan.extratordenotadecorretagem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static br.torezan.extratordenotadecorretagem.PDFReader.extrairTexto;

@RestController
public class UploadRest {

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("pdf") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo está vazio!");
        }

        try {
            byte[] bytes = file.getBytes();
            String conteudo = PDFReader.extrairTexto(bytes);
//            Files.write(Paths.get("uploads/"+ new Date().getTime() + file.getOriginalFilename()), bytes);
            return ResponseEntity.ok(String.format("""
                        <html>
                            <body>
                                Sucesso ao extrair conteúdo do arquivo:
                                <code>
                                     %s
                                </code>
                            </body>
                        </html>
                    """, conteudo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao fazer upload do arquivo: " + e.getMessage());
        }
    }

}
