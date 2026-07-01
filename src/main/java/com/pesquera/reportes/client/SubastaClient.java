package com.pesquera.reportes.client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
@Component
public class SubastaClient {
    private final WebClient webClient;
    public SubastaClient(WebClient.Builder builder, @Value("${app.ms-subastas.url}") String url) {
        this.webClient = builder.baseUrl(url).build();
    }
    public List obtenerSubastas() {
        return webClient.get()
                .uri("/api/subastas")
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }
}