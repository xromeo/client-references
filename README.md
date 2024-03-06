
# (requerido) Una breve reseña sobre la estrategia/tecnología/frameworks utilizados en la implementación de las distintas partes de la solución, esta reseña debe estar claramente descrita en el archivo README del repositorio.

En la implementación de la solución, se ha utilizado el framework Spring Boot

*Spring Boot
*Spring Data JPA:
*H2 Database (Base de Datos en Memoria):
*JUnit y Mockito:

La estructura de directorios es la siguiente
```
src
├── main
│   ├── java
│   │   └── com
│   │       └── rivera
│   │           └── clientreferences
│   │               ├── controller          # Controladores REST
│   │               ├── converter           # Convertidores de DTO a Entidad y viceversa
│   │               ├── dto                 # Objetos de Transferencia de Datos (DTO)
│   │               ├── exception           # Clases de excepciones personalizadas
│   │               ├── model               # Entidades y clases de modelo
│   │               ├── repository          # Interfaces de repositorio para acceso a datos
│   │               ├── service             # Capa de servicios de negocio
│   │               └── ClientReferencesApplication.java  # Clase principal de la aplicación
│   ├── resources
│   │   └── application.properties          # Configuraciones de la aplicación
│   └── test
│       └── java
│           └── com
│               └── rivera
│                   └── clientreferences
│                       ├── controller      # Pruebas de controladores
│                       └── ClientReferencesApplicationTests.java  # Pruebas de integración
```

# (opcional) Archivo Dockerfile que permita desplegar la aplicación en un contenedor. En caso de crear este archivo, incluir al archivo README las instrucciones para crear y levantar la aplicación desde el Dockerfile.

Docker es requerido para crear la imagen

Para Crear la Imagen 

```
$ docker build --tag=client-references:label1 .

```

Para correr como container:

```
$ docker run --rm -p 8080:8080 client-references:label1
```

# (opcional) Documentación de la API.
El archivo
```
API.http
``` 
tiene los curls para ejcutar los endpoints y la secuencia de pasos
