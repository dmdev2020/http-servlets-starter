package com.dmdev.http.server;

public class HttpServerRunner {

    public static void main(String[] args) {
        var httpServer = new HttpServer(9000, 100);
        httpServer.run();
    }
}
