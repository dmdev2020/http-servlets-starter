package com.dmdev.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;

public class HttpClientExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        var httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.google.com"))
                .GET()
                .build();

        HttpRequest request2 = HttpRequest.newBuilder(URI.create("https://www.google.com"))
                .POST(BodyPublishers.ofFile(Path.of("path", "to", "file")))
                .header("content-type", "text/plain")
                .build();

        var response = httpClient.send(request, BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response.headers());
    }
}
