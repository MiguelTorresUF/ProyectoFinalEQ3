# EQUIPO3   
INTEGRANTES DEL EQUIPO: *Vanessa Lizbeth Rocha Puente.
                        *Miguel Angel Torres Coronado.
                        *Gerardo Flores Ramirez.
                        *Raúl Fernando Lugo Castillo.
                        
# OBJETIVO DEL PROYECTO
Se requiere implementar una API que facilite al usuario un CRUD agregar, actualizar y eliminar. Tenga una base de datos para concentrar toda la informacion.

# DEPENDENCIAS
utilizamos las siguientes dependencias para la conexion a la base de datos

<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>2.3.5</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-jdbc</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.18</version>
		</dependency>
	</dependencies>
  
  # REQUERIMIENTO 1
  Una agencia de viajes y turismo desea implementar un crud con los siguientes requerimientos: 
  
* Se debe permitir el alta, baja, modificación y consulta de vuelos.
* Se debe permitir el alta, baja, modificación y consulta de hoteles
* Se debe permitir el alta, baja, modificación y consulta de reservas.

ROLES Y PERMISOS
* Para esta US aún no se aplicarán roles o permisos de ningún tipo. Se deberán generar únicamente los endpoints correspondientes.

POST -> /api/v1/hotels/new/ : Alta de un nuevo hotel.
POST -> /api/v1/flights/new : Alta de un nuevo vuelo.
POST -> /api/v1/hotel-booking/new : Alta de una reserva de hotel.
POST -> /api/v1/flight-reservation/new : Alta de una reserva de vuelo.

MODIFICACIONES

PUT -> /api/v1/flights/edit?flightNumber=number : Modificación de un vuelo.
PUT -> /api/v1/hotels/edit?hotelCode=code : Modificación de un hotel.
PUT -> /api/v1/hotel-booking/edit?id=num_id : Modificación de una reserva de hotel.
PUT -> /api/v1/flight-reservation/edit?id=num_id : Modificación de una reserva de vuelo.

CONSULTAS/LECTURAS
GET -> /api/v1/hotels : Listado de todos los hoteles
GET -> /api/v1/hotels?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&destination=destination_name : Listado de hoteles según filtros.
GET -> /api/v1/flights : Listado de todos los vuelos
GET -> /api/v1/flights?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&origin=origin_name&destination=destination_name :Listado de vuelos según filtros
GET -> /api/v1/hotel-bookings/ : Listado de todas las reservas de hotel
GET -> /api/v1/flight-reservations/ : Listado de todas las reservas de vuelos

BAJAS
DELETE -> /api/v1/hotels/delete?hotelCode=code : Baja de un hotel
DELETE -> /api/v1/flights/delete?flightNumber=number : Baja de un vuelo
DELETE -> /api/v1/hotel-booking/delete?id=num_id : Baja de una reserva de hotel
DELETE -> /api/v1/flight-reservation/delete?id=num_id : Baja de una reserva de vuelo


# REQUERIMIENTO 2

La agencia de viajes y turismo desea empezar a implementar el armado de paquetes turísticos; para esto, especificó que cada paquete turístico podrá estar conformado de la siguiente manera:

*Dos reservas de vuelos.
*Dos reservas de hotel.
*Una reserva de vuelo y una reserva de hotel.

Los paquetes turísticos ofrecen como beneficio al cliente, un descuento del 10% sobre el valor total de la sumatoria de los dos ítems que tenga incorporado. Por ejemplo: Si se tiene una reserva de vuelo por $30.000 y una reserva de hotel por $15.000, la sumatoria total es de $45.000. Si aplicamos el descuento del 10% tendremos $45.000 - $4500, por lo que el precio total del paquete sería de $40.500.

POST -> /api/v1/touristicpackage/new/ : Alta de un nuevo paquete
PUT -> /api/v1/touristicpackage/edit?packageNumber=number : Modificación de un paquete
GET -> /api/v1/touristicpackages : Listado de todos los paquetes dados de alta
DELETE -> /api/v1/touristicpackage/delete?packageNumber=number : Baja de un paquete


# REQUERIMIENTO 3

Implementación de sistema de caja

poder obtener la sumatoria de los montos totales por mes a partir de las reservas PARA informar los ingresos de un determinado mes al dueño de la agencia.

GET -> /api/v1/income?date=dd/mm/yyyy :Total de ingresos brutos para un día en particular  a partir de reservas.
GET -> /api/v1/income?month=1&year=2021 : Total de ingresos brutos para un mes y año en particular  a partir de reservas.


# REQUERIMIENTO 4

Top 3 de Clientes

 poder consultar el top 3 de clientes que realizaron mayor cantidad de reservas en un año PARA poder informar al dueño de la agencia.

GET -> /api/v1/clients/top-3 : Listado del top 3 de clientes en base a cantidad de reservas realizadas.


