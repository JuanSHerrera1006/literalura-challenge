# Literalura

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-11-blue)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-brightgreen)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8.2-blue)](https://maven.apache.org/download.cgi)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13.4-blue)](https://www.postgresql.org/download/)

Reto propuesto por [Alura LATAM](https://www.aluracursos.com) para la ruta de formacion BACKEND.

![literalura](images/literalura_logo.png)
## Acerca del proyecto

Literalura es una aplicación por consola que permite interactuar con datos de autores y libros.
Además de consumir la API de [Gutendex](https://gutendex.com) para obtener información del catalogo de libros y de sus metadatos.

### Funcionalidades

- Buscar un libro por título.
- Listar todos los libros.
- Listar todos los autores.
- Buscar un libro por idioma.
- Buscar autores por año de fallecimiento.
- Mostrar los 10 libros más descargados.
- Generar estadísticas de los libros.
- Buscar un autor por nombre.
- Buscar autores fallecidos por siglo.

## Comenzando 
### Prerrequisitos

Antes de comenzar, asegúrate de tener instalado lo siguiente en tu máquina local:

- Debes de tener instalado la version 11 o más reciente de [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- Debes de tener instalado [Maven](https://maven.apache.org/download.cgi).
- Debes de tener instalado [PostgreSQL](https://www.postgresql.org/download/).

### Instalación
1. Clona el repositorio en tu máquina local.
```sh
git clone https://github.com/JuanSHerrera1006/literalura-challenge.git
```
2. Configura las variables de entorno en el archivo `application.properties`.
```properties
server.port=${PORT:8080}
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```
3. Ejecuta el siguiente comando para compilar el proyecto.
```sh
mvn clean install
```
4. Ejecuta el siguiente comando para correr la aplicación.
```sh
mvn spring-boot:run
```

## Contribuir

Si deseas contribuir a este proyecto, por favor, haz un fork del repositorio y crea una pull request. Asegúrate de que tu código sigue las convenciones de estilo de Java.

## Licencia
Este proyecto está licenciado bajo los términos de la licencia MIT. Consulta el archivo `LICENSE.txt`

## Authors
| [<img src="https://avatars.githubusercontent.com/u/86378159?&v=4" width=115><br><sub>Juan Sebastian Herrera Gomez</sub>]() |
|:-----------------------------------------------------------------------------------------------:|
