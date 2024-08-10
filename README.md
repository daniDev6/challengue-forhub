# ForoHUB
Este programa en un __Foro__ en el cual distintos estudiantes de Alura deben de poder publicar contenido,
esto por medio del uso de distintas funciones CRUD (Create, Read, Update, Delete) y el uso de una API REST.


## Requisitos

- Java JDK: version 11 en adelante
- Maven version 4 en adelante
- Mysql
- IDE (IntelliJ IDEA o similares)
- Postman version 11 enn adelante

# Iniciar programa

Primero debes de crear la DataBase en mysql con el siguiente nombre "forohub".
Debe configurar el propertis con sus respectivos datos.


# Dependencias

+ Spring Boot Starter Web
+ Spring Boot Devtools
+ Spring Boot Starter Test
+ Spring Boot Starter Data JPA
+ Lombok
+ Flyway migration
+ Validation
+ Spring Security

## Configuracion inicial
Se debe configurar la base de datos, los datos de migration, el secret key en base64. 

````
spring.datasource.url=jdbc:mysql://localhost:3306/suNombreDataBase
spring.datasource.username= el username de su base de datos
spring.datasource.password= su contraseña

spring.flyway.url=jdbc:mysql://localhost:3306/suNombreDataBase
spring.flyway.user= username de su base de datos
spring.flyway.password= su contraseña

security.jwt.expiration-minutes= tiempo de duracion del token
security.jwt.secrete-key= su clave secreta en base 64



````








## Funcionalidades

Para empezar debes autenticarte, envia una solicitud POST a /login con tu usuario y contraseña. Luego de esto
el programa te devolvera un TOKEN que debes incluirlo en el header "Authorization" como un Bearer Token para
realizar proximas consultas y obtener información de las mismas.

Por defecto se crean 2 usuarios uno con la autoridad de user y otro admin

## Usuarios
- Admin : Permisos para Guardar Actualizar y ver los usuarios
  username: admin
  password: admin123
-  User
   username: user solo permiso para ver usuarios
   password: user123

### EndPoints CRUD
### Usuario Controller
- CREATE : /usuarios/crear
- READ : /usuarios/traer
- READ : /usuarios/traer/{id}
- UPDATE : /usuarios/editar/{id}
- DELETE : /usuarios/eliminar/{id}
### Curso Controller
- CREATE : /curso/crear
- READ : /curso/traer
- READ : /curso/traer/{id}
- UPDATE : /curso/editar/{id}
- DELETE : /curso/eliminar/{id}
### Perfil Controller
- CREATE : /perfil/crear
- READ : /perfil/traer
- READ : /perfil/traer/{id}
- UPDATE : /perfil/editar/{id}
- DELETE : /perfil/eliminar/{id}
### Respuesta Controller
- CREATE : /respuesta/crear
- READ : /respuesta/traer
- READ : /respuesta/traer/{id}
- UPDATE : /respuesta/editar/{id}
- DELETE : /respuesta/eliminar/{id}
### Topico Controller
- CREATE : /topico/crear
- READ : /topico/traer
- READ : /topico/traer/{id}
- UPDATE : /topico/editar/{id}
- DELETE : /topico/eliminar/{id}

## Autor
Este proyecto fue creado por Daniel Villalba.