# Challenge Oracle One - Hotel Alura
![](imagenesRepositorio/logo.png)
## Descripción

Repositorio de la resolución del desafío del Challenge de creación de un sistema de reserva para el Hotel Alura,
impartido por el Programa Oracle One en coordinación con Alura Latinoamérica.


✔️ **Antecedentes**

El Hotel Alura conocido por su espectaculares instalaciones y paquetes promocionales para Desarrolladores de Software está teniendo problemas para llevar el control de las reservaciones hechas por sus clientes.

✔️ **Objetivo**

El hotel solicita nuestra ayuda para desarrollar un sistema de reserva.

✔️ **Requisitos**

- Sistema de autenticación de usuario para que solo usuarios pertenecientes al hotel consigan acceder al sistema.
- Permitir crear, editar y eliminar una reserva para los clientes.
- Buscar en la base de datos todas las informaciones tanto de los clientes como de las reservas.
- Registrar, editar y eliminar datos de los huéspedes.
- Calcular el valor de la reserva en base a la cantidades de días de la reserva y a una tasa diaria que puede ser asignada por ti y en la moneda local de tu país, por ejemplo si tenemos una reserva de 3 días y el valor de nuestra tasa diaria son $20.00 debemos multiplicar esos 3 días por el valor de la tasa diaria, lo que serian $60.00, todo esto deberá ser hecho automáticamente y mostrado al usuario antes de guardar la reserva.
- Base de datos para almacenar todos los datos pedidos anteriormente.

✔️ **Información Adicional**

El líder del proyecto puso a disposición un Diagrama de Entidad Relación que usaremos para crear las tablas en nuestra base de Datos

✔️ 🖥️ **Tecnologías Utilizadas**

- Java
- Eclipse
- Biblioteca JCalendar
- MySql
- Plugin WindowBuilder

# Pantallas

## 1. Login

Por el momento el sistema de login hace un llamado a la base de datos preguntando si la información de usuario y contraseña
ingresados en el formulario se encuentran ingresadas en la respectiva tabla llamada user.

El sistema utiliza la base de datos de mysql, se valida el usuario y clave con los datos registrados en la base de datos, si
la información es correcta, se da acceso a sistema.


![](imagenesRepositorio/login.png)

## 2. Menú principal

Menú de todas las funcionalidades que posee el sistema, registro de reserva y busqueda.

![](imagenesRepositorio/pantalla-principal.png)

## 3. Registro de reservas

Ventana donde se ingresar los datos para realizar un registro de una reserva, se solicita fecha de entrada, fecha salida, valor,
Forma de Pago.

![](imagenesRepositorio/pantalla-reserva.png)

## 3. Registro Huespedes

Luego de realizar una reserva, se procede al registro del Huesped en esta ventana se solicita los datos del huesped: nombre, apellido,
fecha de nacimiento, nacionalidad y teléfono.

![](imagenesRepositorio/pantalla-huesped.png)

## 4. Sistema de Busqueda

Pantalla donde se puede ver todas las reservas registradas, asi como todos los huespedes.
Detalle de todas las reservas y los huespedes.

![](imagenesRepositorio/pantalla-listar-reservas.png) ![](imagenesRepositorio/Ventana-listar-huespedes.png)

El sistema permite realizar busquedas por dos elementos:
 * Id de reserva
 * Apellido del huésped

### Busqueda por id de reserva

![](imagenesRepositorio/busqueda-por-id.png)  

### Busqueda por apellido de huesped

![](imagenesRepositorio/busqueda-por-apellido.png)  

Se puede realizar la edicion de los campos de la tablas desplegadas de reservas y huespedes.

![](imagenesRepositorio/confirmacion-edicion.png)

Se permite la eliminación de registros

![](imagenesRepositorio/eliminacion-huespedes.png)

## Video Funcionamiento

Link Video en Youtube: https://youtu.be/cAMs7px4Ln4

## Autor

Manuel Estévez

<a href="https://www.linkedin.com/in/manuel-estevez-perfil/"><img src="imagenesRepositorio/linkedinlogo.svg"></a>

[Manuel Estevez (manueletz)](https://github.com/manueletz)








