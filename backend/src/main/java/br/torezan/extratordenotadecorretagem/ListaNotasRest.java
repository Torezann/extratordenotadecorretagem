package br.torezan.extratordenotadecorretagem;

import br.torezan.extratordenotadecorretagem.conversores.LocalDateAdapter;
import br.torezan.extratordenotadecorretagem.dto.DadosBasicosNotaDto;
import br.torezan.extratordenotadecorretagem.dto.NotaCorretagem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.commons.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ListaNotasRest {

    @Value("${diretorio-de-notas}")
    private String diretorioDeNotas;

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    @GetMapping("/notas")
    public ResponseEntity<List<DadosBasicosNotaDto>> listar() {
        File dir = new File(diretorioDeNotas);
        List notas = new ArrayList<DadosBasicosNotaDto>();
        if (dir.exists() && dir.isDirectory() && dir.listFiles().length > 0) {
            for (File file : dir.listFiles()) {
                DadosBasicosNotaDto nota = new DadosBasicosNotaDto();
                String fileName = file.getName();
                nota.setNome(fileName);
                nota.setNumero(Long.valueOf(fileName.substring(2, fileName.indexOf("-"))));
                nota.setFolha(Integer.valueOf(fileName.substring(fileName.indexOf("-")+1, fileName.indexOf("."))));
                notas.add(nota);
            }
        }
        return ResponseEntity.ok().body(notas);
    }

    @GetMapping("/nota/{numero}/{folha}")
    public ResponseEntity<NotaCorretagem> recuperarNota(@PathVariable Long numero, @PathVariable Integer folha) throws IOException {
        String json = recuperarNotaJson(numero, folha);
        NotaCorretagem nota = gson.fromJson(json, NotaCorretagem.class);
        return ResponseEntity.ok().body(nota);
    }

    private String recuperarNotaJson(Long numero, Integer folha) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(diretorioDeNotas + "nc" + numero + "-" + folha + ".json"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        return stringBuilder.toString();
    }
}
