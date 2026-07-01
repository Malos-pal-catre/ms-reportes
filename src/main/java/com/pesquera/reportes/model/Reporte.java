package com.pesquera.reportes.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoReporte tipo;

    @Column(nullable = false)
    private String periodo;

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    private Double totalKilos;

    @Column(nullable = false)
    private Double totalMonto;

    @Column(nullable = false)
    private Integer totalTransacciones;

    @Column(nullable = false)
    private LocalDateTime fechaGeneracion;

    private String observaciones;
}