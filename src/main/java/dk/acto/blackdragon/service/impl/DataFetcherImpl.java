package dk.acto.blackdragon.service.impl;

import dk.acto.blackdragon.service.DataFetcher;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Note source: https://www.baeldung.com/java-9-http-client

public class DataFetcherImpl implements DataFetcher {
    @Override
    public String fetchData(URL url) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(url.toURI()).GET().build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return  response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
