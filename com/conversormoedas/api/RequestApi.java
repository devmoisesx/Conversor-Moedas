package com.conversormoedas.api;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestApi {
    private static final String API_KEY = "2ccafac633532aef3d7a5203";

    public static String converterMoeda(String moedaASerConvertida, String moedaConverter, Double valorParaConverter) {
        String dados = moedaASerConvertida + "/" + moedaConverter + "/" + valorParaConverter;
        return response("pair", dados);
    }

    public static String taxaCambio(String moedaBase) {
        return response("latest", moedaBase);
    }

    private static String response(String metodo, String dados) {
        var resposta = "";
        try {
            String urlString = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/" + metodo + "/" + dados;
            URL url = new URL(urlString);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Resultado da Conversão: ");
            resposta = response.body();
        } catch (Exception e) {
            System.out.println("Error em:");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        return resposta;
    }

    public static void main(String[] args) {

    }
}
