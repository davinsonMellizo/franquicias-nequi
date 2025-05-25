# Descripción:
Microservicio para la administración de franquicias.

## Servicios que ofrece el proyecto
- Consulta de las franquicias
- Consulta de las sucursales para una franquicia
- Consulta de los productos con mas stock de cada sucursal para una franquicia
- Creación de franquicias, sucursales y productos
- Actualización de franquicias, sucursales y producto
- Eliminación de productos

# Arrancar el proyecto con Docker 

Asegurate de tener Docker y que los puertos 8080 y 3306 estén libres, así como los nombres usados para 
la red, las imágenes y los contenedores no estén siendo usados.

**NOTA:** Ejecuta los comandos en la carpeta raíz del proyecto donde se encuentra ubicado el archivo Dockerfile 

**Ejecuta los siguientes comandos:**
- `docker network create franquicias_red .`
- `docker pull mysql`
- `docker run -d --name mysql-franquicias --network franquicias_red -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=franquicias  mysql`
- `docker build -t franquicias-img .`
- `docker run -d --name franquicias-cont --network franquicias_red -p 8080:8080 franquicias-img`

# Arrancar el proyecto en un IDE
- Abre el proyecto en tu IDE de preferencia
- Crea una base de datos mysql usando docker con los siguientes comando
- `docker pull mysql`
- `docker run -d --name mysql-franquicias --network franquicias_red -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=franquicias  mysql`
- Abre la clase de configuración SecretsConfig(applications/app-service/config) y cambia el host por defecto de la
conexión a "localhost"

# Servicios externos utilizados en el proyecto
- **MySql** como base de datos para la persistencia de datos
- **SecretManager AWS** para la administración de los datos de conexión a la base de datos
# Arquitectura Basada en clean architecture

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**
