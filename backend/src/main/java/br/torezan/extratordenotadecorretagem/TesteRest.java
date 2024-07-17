package br.torezan.extratordenotadecorretagem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteRest {

    @GetMapping("/teste")
    public String retornaTexto(){
        return "Hello World";
    }

    @GetMapping("/teste2")
    public String retornaTexto2(){
        return "Oi mundo";
    }

    @GetMapping("/testeplainobj")
    public String retornaJson(){
        return """
                {
                    "id": 1,
                    "nome": "Joao Get"
                }
                """;
    }

    @PostMapping("/testeplainobj")
    public String retornaJsonPost(){
        return """
                {
                    "id": 1,
                    "nome": "Joao Post"
                }
                """;
    }

    @PostMapping("/api/teste/{numero}")
    public String retornaJsonPost(
            @PathVariable("numero") int valor
    ){
        return String.format("""
                {
                    "entrada": %s,
                    "saida": %s
                }
                """, valor, valor*2);
    }

    @PostMapping("/api/teste2/{numero}")
    public EntSai retornaJsonPostObj(
            @PathVariable("numero") int valor
    ){
        return new EntSai(valor);
    }


}
