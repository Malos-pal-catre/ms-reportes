package com.pesquera.reportes.repository;
import com.pesquera.reportes.model.Reporte;
import com.pesquera.reportes.model.TipoReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByTipo(TipoReporte tipo);
    List<Reporte> findByPeriodo(String periodo);
    List<Reporte> findByEspecie(String especie);
    @Query("SELECT r FROM Reporte r WHERE r.tipo = com.pesquera.reportes.model.TipoReporte.SERNAPESCA AND r.periodo = :periodo")
    List<Reporte> findReportesSernapescaByPeriodo(@Param("periodo") String periodo);
    @Query("SELECT r FROM Reporte r WHERE r.especie = :especie ORDER BY r.fechaGeneracion DESC")
    List<Reporte> findReportesByEspecieOrdenados(@Param("especie") String especie);
    @Query(value = "SELECT * FROM reportes ORDER BY fecha_generacion DESC", nativeQuery = true)
    List<Reporte> findTodosOrdenados();
}