package com.pesquera.reportes.dto;
import com.pesquera.reportes.model.Reporte;
public class ReporteMapper {
    public static ReporteResponseDTO toDTO(Reporte reporte) {
        ReporteResponseDTO dto = new ReporteResponseDTO();
        dto.setId(reporte.getId());
        dto.setTipo(reporte.getTipo().name());
        dto.setPeriodo(reporte.getPeriodo());
        dto.setEspecie(reporte.getEspecie());
        dto.setTotalKilos(reporte.getTotalKilos());
        dto.setTotalMonto(reporte.getTotalMonto());
        dto.setTotalTransacciones(reporte.getTotalTransacciones());
        dto.setFechaGeneracion(reporte.getFechaGeneracion());
        dto.setObservaciones(reporte.getObservaciones());
        return dto;
    }
    public static Reporte toEntity(ReporteRequestDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setTipo(dto.getTipo());
        reporte.setPeriodo(dto.getPeriodo());
        reporte.setEspecie(dto.getEspecie());
        reporte.setTotalKilos(dto.getTotalKilos());
        reporte.setTotalMonto(dto.getTotalMonto());
        reporte.setTotalTransacciones(dto.getTotalTransacciones());
        reporte.setObservaciones(dto.getObservaciones());
        return reporte;
    }
}