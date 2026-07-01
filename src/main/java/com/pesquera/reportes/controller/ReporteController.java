package com.pesquera.reportes.controller;

import com.pesquera.reportes.dto.ReporteMapper;
import com.pesquera.reportes.dto.ReporteRequestDTO;
import com.pesquera.reportes.dto.ReporteResponseDTO;
import com.pesquera.reportes.model.Reporte;
import com.pesquera.reportes.model.TipoReporte;
import com.pesquera.reportes.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "Generación de reportes - Caleta Pesquera")
public class ReporteController {

    private final ReporteService reporteService;

    @PostMapping
    @Operation(
            summary = "Crear un nuevo reporte",
            description = "Registra un reporte genérico (Sernapesca, liquidaciones, subastas o general) con sus totales agregados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reporte creado correctamente",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ReporteCreado",
                                    value = "{\"id\":1,\"tipo\":\"GENERAL\",\"periodo\":\"2026-06\",\"especie\":\"Loco\",\"totalKilos\":1850.0,\"totalMonto\":12500000.0,\"totalTransacciones\":15,\"fechaGeneracion\":\"2026-06-30T12:00:00\",\"observaciones\":\"Reporte mensual de cierre\"}"
                            ))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ErrorValidacion",
                                    value = "{\"totalKilos\":\"El total de kilos debe ser mayor a 0\"}"
                            )))
    })
    public ResponseEntity<ReporteResponseDTO> crearReporte(
            @org.springframework.web.bind.annotation.RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del reporte a crear",
                    required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReporteRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "NuevoReporte",
                                    value = "{\"tipo\":\"GENERAL\",\"periodo\":\"2026-06\",\"especie\":\"Loco\",\"totalKilos\":1850.0,\"totalMonto\":12500000.0,\"totalTransacciones\":15,\"observaciones\":\"Reporte mensual de cierre\"}"
                            )))
            @Valid ReporteRequestDTO dto) {
        Reporte reporte = ReporteMapper.toEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReporteMapper.toDTO(reporteService.crearReporte(reporte)));
    }

    @GetMapping
    @Operation(
            summary = "Listar todos los reportes",
            description = "Retorna el listado completo de reportes generados, de cualquier tipo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ListadoReportes",
                                    value = "[{\"id\":1,\"tipo\":\"GENERAL\",\"periodo\":\"2026-06\",\"especie\":\"Loco\",\"totalKilos\":1850.0,\"totalMonto\":12500000.0,\"totalTransacciones\":15,\"fechaGeneracion\":\"2026-06-30T12:00:00\",\"observaciones\":\"Reporte mensual de cierre\"}]"
                            )))
    })
    public ResponseEntity<List<ReporteResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(reporteService.obtenerTodos().stream().map(ReporteMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar reporte por ID",
            description = "Retorna los datos de un reporte específico según su identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ReporteEncontrado",
                                    value = "{\"id\":1,\"tipo\":\"GENERAL\",\"periodo\":\"2026-06\",\"especie\":\"Loco\",\"totalKilos\":1850.0,\"totalMonto\":12500000.0,\"totalTransacciones\":15,\"fechaGeneracion\":\"2026-06-30T12:00:00\",\"observaciones\":\"Reporte mensual de cierre\"}"
                            ))),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<ReporteResponseDTO> obtenerPorId(
            @Parameter(description = "ID del reporte", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(ReporteMapper.toDTO(reporteService.obtenerPorId(id)));
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(
            summary = "Listar reportes por tipo",
            description = "Retorna los reportes filtrados por tipo: SERNAPESCA, LIQUIDACIONES, SUBASTAS o GENERAL."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de reportes por tipo",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ReportesPorTipo",
                                    value = "[{\"id\":1,\"tipo\":\"SERNAPESCA\",\"periodo\":\"2026-06\",\"especie\":\"Loco\",\"totalKilos\":1850.0,\"totalMonto\":12500000.0,\"totalTransacciones\":15,\"fechaGeneracion\":\"2026-06-30T12:00:00\",\"observaciones\":null}]"
                            )))
    })
    public ResponseEntity<List<ReporteResponseDTO>> obtenerPorTipo(
            @Parameter(description = "Tipo de reporte", example = "SERNAPESCA")
            @PathVariable TipoReporte tipo) {
        return ResponseEntity.ok(reporteService.obtenerPorTipo(tipo).stream().map(ReporteMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/periodo/{periodo}")
    @Operation(
            summary = "Listar reportes por periodo",
            description = "Retorna los reportes generados en un periodo determinado (ej. '2026-06')."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de reportes por periodo",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ReportesPorPeriodo",
                                    value = "[{\"id\":1,\"tipo\":\"GENERAL\",\"periodo\":\"2026-06\",\"especie\":\"Loco\",\"totalKilos\":1850.0,\"totalMonto\":12500000.0,\"totalTransacciones\":15,\"fechaGeneracion\":\"2026-06-30T12:00:00\",\"observaciones\":null}]"
                            )))
    })
    public ResponseEntity<List<ReporteResponseDTO>> obtenerPorPeriodo(
            @Parameter(description = "Periodo a consultar", example = "2026-06")
            @PathVariable String periodo) {
        return ResponseEntity.ok(reporteService.obtenerPorPeriodo(periodo).stream().map(ReporteMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/especie/{especie}")
    @Operation(
            summary = "Listar reportes por especie",
            description = "Retorna los reportes generados para una especie determinada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de reportes por especie",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ReportesPorEspecie",
                                    value = "[{\"id\":1,\"tipo\":\"GENERAL\",\"periodo\":\"2026-06\",\"especie\":\"Loco\",\"totalKilos\":1850.0,\"totalMonto\":12500000.0,\"totalTransacciones\":15,\"fechaGeneracion\":\"2026-06-30T12:00:00\",\"observaciones\":null}]"
                            )))
    })
    public ResponseEntity<List<ReporteResponseDTO>> obtenerPorEspecie(
            @Parameter(description = "Nombre de la especie", example = "Loco")
            @PathVariable String especie) {
        return ResponseEntity.ok(reporteService.obtenerPorEspecie(especie).stream().map(ReporteMapper::toDTO).collect(Collectors.toList()));
    }

    @PostMapping("/sernapesca")
    @Operation(
            summary = "Generar reporte para Sernapesca",
            description = "Genera automáticamente un reporte de tipo SERNAPESCA a partir de los totales agregados de un periodo y especie, consolidando datos provenientes de ms-capturas, ms-subastas y ms-pagos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reporte Sernapesca generado correctamente",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ReporteSernapescaGenerado",
                                    value = "{\"id\":2,\"tipo\":\"SERNAPESCA\",\"periodo\":\"2026-06\",\"especie\":\"Loco\",\"totalKilos\":1850.0,\"totalMonto\":12500000.0,\"totalTransacciones\":15,\"fechaGeneracion\":\"2026-06-30T12:00:00\",\"observaciones\":null}"
                            )))
    })
    public ResponseEntity<ReporteResponseDTO> generarReporteSernapesca(
            @Parameter(description = "Periodo del reporte", example = "2026-06")
            @RequestParam String periodo,
            @Parameter(description = "Especie reportada", example = "Loco")
            @RequestParam String especie,
            @Parameter(description = "Total de kilos transados en el periodo", example = "1850.0")
            @RequestParam Double totalKilos,
            @Parameter(description = "Total de monto transado en el periodo", example = "12500000.0")
            @RequestParam Double totalMonto,
            @Parameter(description = "Total de transacciones realizadas en el periodo", example = "15")
            @RequestParam Integer totalTransacciones) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ReporteMapper.toDTO(reporteService.generarReporteSernapesca(periodo, especie, totalKilos, totalMonto, totalTransacciones)));
    }
}