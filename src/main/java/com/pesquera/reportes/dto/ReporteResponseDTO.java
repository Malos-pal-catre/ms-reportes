package com.pesquera.reportes.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class ReporteResponseDTO {
    private Long id;
    private String tipo;
    private String periodo;
    private String especie;
    private Double totalKilos;
    private Double totalMonto;
    private Integer totalTransacciones;
    private LocalDateTime fechaGeneracion;
    private String observaciones;
}