# SISTRANS

**SISTRANS**, toma datos de un archivo plano en la cual cada fila representa un registro en
la DB, cada una de las filas debe ser parseado de acuerdo a una estructura definida en una plantilla
editable desde la aplicación.

* En la siguiente tabla se muestra la estructura inicial de la plantilla ..

| Campo   		|      Tipo     |  Longitud |  Orden	|
|:--------------|:-------------:|:---------:|----------:|
| Fecha 		|  fecha		|  	  8  	|  	  1  	|
| Hora	 		|  hora			|  	  6  	|  	  2  	|
| Tarjeta 		|  digito		|  	  16  	|  	  3  	|
| Canal			|  digito		|  	  8  	|  	  4  	|
| Monto 		|  digito		|  	  10  	|  	  5  	|
| BIN	 		|  digito		|  	  11  	|  	  6  	|
| Autorizador	|  digito		|  	  11 	|  	  7 	|

### Tecnologías de la solución
* Java 8
* Spring MVC
* Spring-JDBC
* MySQL
* AngularJS
* JQuery
* Materializecss

* Maven (Gestión en el Backend)
* Bower (Gestion en el Frontend)

### Ejecución
* Crear la BD sistransdb y ejecutar el script sistransdb.sql
* Importar el proyecto como proyecto maven en (Eclipse, Netbeans o IntelliJ IDEA)
* Renombrar resource/config.properties.example a resource/config.properties
    * Poner los datos para la conexión a la base de datos
    * Cree una base de datos(como lo llamó en *config.properties*) y corra el script de la rama *database*
* Ejecutar