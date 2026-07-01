# MS-Reportes

Microservicio encargado de generar reportes de subastas, liquidaciones y volumenes transados para Sernapesca en la Caleta Lo Abarca.

## Tecnologias
- Java 21
- Spring Boot 4.0.6
- PostgreSQL (Neon)
- Maven

## Puerto
8088

## Endpoints

### Reportes
- POST /api/reportes - Crear nuevo reporte
- GET /api/reportes - Obtener todos los reportes
- GET /api/reportes/{id} - Obtener reporte por ID
- GET /api/reportes/tipo/{tipo} - Obtener reportes por tipo
- GET /api/reportes/periodo/{periodo} - Obtener reportes por periodo
- GET /api/reportes/especie/{especie} - Obtener reportes por especie
- POST /api/reportes/sernapesca - Generar reporte Sernapesca

## Como correr el proyecto
1. git clone https://github.com/Malos-pal-catre/ms-reportes.git
2. cd ms-reportes
3. ./mvnw spring-boot:run
