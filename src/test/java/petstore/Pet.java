// 1 - Pacote
package petstore;

// 2 - Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

// 3- Classe
public class Pet {
    // 3.1 -  Atributos (caracteristicas/adjetivos)
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    //3.2 - Métodos (São as ações que não retornam nenhum valor) e Funções (Devolvem um resultado)
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));   // Files é um objeto capaz de manipular arquivos e String é texto que será o resultado dessa leitura.
    }

    // Incluir - Create - Post
    @Test // identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe
        // Dado - Quando - Então
        // Given - When -  Then


        given() // Dado que...
                .contentType("application/json") // comum em API REST - antigas eram "text/xml"
                .log().all()
                .body(jsonBody)
        .when() // Quando
                .post(uri)
        .then() // Então
                .log().all()
                .statusCode(200)
        ;
    }
}
// Se conecta ao endereço "https://petstore.swagger.io/v2/pet"
// Faz um post que está no json
// Recebe retorno se foi cadastrado com sucesso