# BARBERMANAGER

Sistema de gestión para barberías desarrollado con Spring Boot, Spring Security, Thymeleaf, JPA/Hibernate y MySQL.

## Características

### Administración de Clientes

* Registrar clientes.
* Editar información de clientes.
* Eliminar clientes.
* Consultar listado de clientes.

### Administración de Barberos

* Registrar barberos.
* Actualizar información de barberos.
* Eliminar barberos.
* Consultar listado de barberos.

### Administración de Servicios

* Registrar servicios.
* Editar servicios.
* Definir precios.
* Consultar catálogo de servicios.

### Gestión de Citas

* Crear citas.
* Consultar citas registradas.
* Asociar clientes, barberos y servicios.
* Eliminar citas (solo administradores).

### Seguridad

* Autenticación mediante Spring Security.
* Roles:

  * ADMIN
  * CLIENTE
* Restricción de acceso según permisos.

## Tecnologías Utilizadas

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Thymeleaf
* MySQL
* Maven
* HTML5
* CSS3

## Estructura General

* Cliente
* Barbero
* Servicio
* Cita

## Instalación

1. Clonar el repositorio.
2. Configurar la base de datos MySQL.
3. Modificar el archivo application.properties con las credenciales correspondientes.
4. Ejecutar:

mvn clean install

5. Iniciar la aplicación:

mvn spring-boot:run

## Acceso

Los usuarios y contraseñas se configuran en Spring Security.

## Objetivo

BarberManager busca facilitar la administración de una barbería mediante la gestión centralizada de clientes, servicios, barberos y citas desde una interfaz web moderna y sencilla.
