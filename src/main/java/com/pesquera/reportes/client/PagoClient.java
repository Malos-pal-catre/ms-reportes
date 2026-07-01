package com.pesquera.reportes.client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
@Component
public class PagoClient {
    private final WebClient webClient;
    public PagoClient(WebClient.Builder builder, @Value("${app.ms-pagos.url}") String url) {
        this.webClient = builder.baseUrl(url).build();
    }
    public List obtenerPagos() {
        return webClient.get()
                .uri("/api/pagos")
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }
}