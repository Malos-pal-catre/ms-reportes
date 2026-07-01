package com.pesquera.reportes.dto;

import com.pesquera.reportes.model.TipoReporte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ReporteRequestDTO {

    @NotNull(message = "El tipo de reporte es obligatorio")
    private TipoReporte tipo;

    @NotBlank(message = "El periodo es obligatorio")
    private String periodo;

    @NotBlank(message = "La especie es obligatoria")
    private String especie;

    @NotNull(message = "El total de kilos es obligatorio")
    @Positive(message = "El total de kilos debe ser mayor a 0")
    private Double totalKilos;

    @NotNull(message = "El total del monto es obligatorio")
    @Positive(message = "El total del monto debe ser mayor a 0")
    private Double totalMonto;

    @NotNull(message = "El total de transacciones es obligatorio")
    @Positive(message = "El total de transacciones debe ser mayor a 0")
    private Integer totalTransacciones;

    private String observaciones;
}