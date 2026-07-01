package com.pesquera.reportes.client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;
@Component
public class CapturaClient {
    private final WebClient webClient;
    public CapturaClient(WebClient.Builder builder, @Value("${app.ms-capturas.url}") String url) {
        this.webClient = builder.baseUrl(url).build();
    }
    public List obtenerCapturas() {
        return webClient.get()
                .uri("/api/capturas")
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }
}