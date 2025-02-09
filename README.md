# Tarifa Service API

Tarifa Service API es una aplicación Spring Boot que provee un endpoint REST para consultar tarifas basadas en la fecha de aplicación, el identificador de producto y la marca (brand). La aplicación sigue los principios de Domain Driven Design (DDD) y Arquitectura Hexagonal, lo que facilita la mantenibilidad, testabilidad y escalabilidad del código.

## Características

- **API REST Documentada con OpenAPI/Swagger:**  
  La documentación de la API se genera automáticamente y está disponible en `http://localhost:8080/precios/swagger-ui/index.html`.

- **Arquitectura Hexagonal y DDD:**  
  La aplicación separa claramente el dominio, la capa de aplicación y los adaptadores de entrada y salida.  
  ![Arquitectura Hexagonal](docs/hexagonal-architecture.png)



- **Base de Datos H2 en Memoria:**  
  Se inicializa automáticamente con datos de ejemplo mediante el script `data.sql`.

- **Uso de Lombok y MapStruct:**  
  Se utiliza Lombok para reducir el código boilerplate. Es necesario tener el plugin de Lombok configurado en el IDE. Además, se usa MapStruct para el mapeo automático entre capas.

- **Perfiles de Entorno:**  
    - **dev:** Perfil para pruebas en desarrollo con configuraciones adicionales.
    - **default:** Perfil por defecto para entornos productivos.

## Requisitos

- **Java 21** o superior.
- **Maven**
- **Lombok:** Asegúrate de tener el plugin de Lombok habilitado en tu IDE.

## Cómo Probar la Aplicación

** 1. Clonar el repositorio:**

  ```bash
  git clone https://github.com/agarciab/precios.git
  cd precios
  ```
** 2. Compilar el proyecto:**
    Es necesario compilar el proyecto para generar el código de Lombok y MapStruct.
 
  ```bash
  mvn clean install
  ```
** 3. Ejecutar la aplicación:**

- **Para producción (recomendado actualmente para demo)** (perfil por defecto):

```bash
mvn spring-boot:run
```

- **Para desarrollo** (perfil `dev`):

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
   
** 4. Probar el endpoint REST:**

  - La aplicación se levanta en `http://localhost:8080/precios`.
  - Ejemplo de consulta (token OAuth2 requerido, se puede usar éste en todas la consultas):
    
```bash
      curl -X GET "http://localhost:8080/precios/tarifas?fecha=2020-06-14T10:00:00&producto=35455&brandId=1" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzM4NTI0MjA2fQ.1d_yAXQezLvvjoJobiQBGrR3FptqZ7LX4X-pV2C1SPk"
```

   - Documentación de la API:  
      Visita [http://localhost:8080/precios/swagger-ui/index.html](http://localhost:8080/precios/swagger-ui/index.html) para ver la documentación OpenAPI.

## Arquitectura Hexagonal

La aplicación está organizada de la siguiente manera:

               +-------------------------+
               | Adaptadores de Entrada  |
               |       (REST API)        |
               +-----------+-------------+
                           |
                           v
                 +---------------------+
                 | Capa de Aplicación  |
                 | (Casos de Uso, DTOs,|
                 |    Mappers)         |
                 +-----------+---------+
                             |
                             v
                 +---------------------+
                 |   Dominio (DDD)     |
                 | (Entidades, Repos,  |
                 |  Servicios, etc.)   |
                 +-----------+---------+
                             |
                             v
               +-------------------------+
               | Adaptadores de Salida   |
               |  (Persistencia, etc.)   |
               +-------------------------+

               
El diagrama anterior ilustra la separación de responsabilidades y la independencia de la lógica de negocio respecto a detalles de infraestructura.

## Notas Adicionales

- **Lombok:**  
  Recuerda instalar y habilitar el plugin de Lombok en tu IDE para evitar problemas de compilación.

- **Primera Compilación:**  
  Realiza una primera compilación (`mvn clean install`) para que se genere el código necesario de Lombok y MapStruct.

- **Perfiles de Entorno:**  
  El perfil `dev` está pensado para pruebas en desarrollo y puede incluir configuraciones específicas (por ejemplo, logging o datos de prueba adicionales). El perfil por defecto se utiliza para entornos productivos.

## Pruebas

La suite de pruebas de integración se encuentra en el paquete `com.snglr.precios.adapters.in.rest` y utiliza MockMvc para validar distintos escenarios, incluidos casos de éxito y casos extremos (fecha fuera de rango, formato inválido, parámetros faltantes).

## Ejecución del Reporte de Cobertura con JaCoCo

Para ejecutar los tests y generar el reporte de cobertura de código, utiliza el siguiente comando:

```bash
mvn clean package -Pcoverage
```

El informe es accesible en `target/site/jacoco/index.html`

---
