package com.pesquera.reportes.service;
import com.pesquera.reportes.exception.RecursoNoEncontradoException;
import com.pesquera.reportes.model.Reporte;
import com.pesquera.reportes.model.TipoReporte;
import com.pesquera.reportes.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ReporteService {
    private final ReporteRepository reporteRepository;
    public Reporte crearReporte(Reporte reporte) {
        reporte.setFechaGeneracion(LocalDateTime.now());
        return reporteRepository.save(reporte);
    }
    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }
    public Reporte obtenerPorId(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reporte no encontrado con id: " + id));
    }
    public List<Reporte> obtenerPorTipo(TipoReporte tipo) {
        return reporteRepository.findByTipo(tipo);
    }
    public List<Reporte> obtenerPorPeriodo(String periodo) {
        return reporteRepository.findByPeriodo(periodo);
    }
    public List<Reporte> obtenerPorEspecie(String especie) {
        return reporteRepository.findByEspecie(especie);
    }
    public Reporte generarReporteSernapesca(String periodo, String especie, Double totalKilos, Double totalMonto, Integer totalTransacciones) {
        Reporte reporte = new Reporte();
        reporte.setTipo(TipoReporte.SERNAPESCA);
        reporte.setPeriodo(periodo);
        reporte.setEspecie(especie);
        reporte.setTotalKilos(totalKilos);
        reporte.setTotalMonto(totalMonto);
        reporte.setTotalTransacciones(totalTransacciones);
        reporte.setFechaGeneracion(LocalDateTime.now());
        return reporteRepository.save(reporte);
    }
}