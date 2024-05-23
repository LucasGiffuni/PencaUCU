# PencaUCU


## INSTRUCTIVO


Para una correcta ejecucion del programa es necesario ejecutar secuencialmente los siguientes pasos:

1- Dentro de la carpeta /Backend/clinica_ucu ejecutaremos el comando " mvn clean package " para generar el ejecutable de la aplicación ( ES NECESARIO TENER INSTALDO MAVEN MVN )

2- Ubicados en la carpeta Padre "ClinicaSaludUCU" ejecutar el Dockerfile con el comando " docker compose up -d ".

3- Verificar que los 3 contenedores se hayan descargado e instalado, estos son:
    1- backend
    2- front
    3- database

4- Una vez verificado el estado de los contenedores nos conectaremos a la base de datos con las siguientes credenciales:
    hostname: 127.0.0.1
    port: 3306
    username: root
    password: 1234
    ( EN CASO DE CAMBIAR LOS DATOS EN EL DOCKERFILE DE LA BASE DE DATOS MODIFICAR ACA TAMBIEN)

5- Ejecutar el archivo CLINICA DML.sql dentro de la carpeta BDD (ClinicaSaludUCU/BDD/CLINICA DML.sql).

6- En este punto la aplicación ya está preparada para utilizar, ahora detallaremos algunos detalles a tener en cuenta:

1- La aplicación cuenta con un usuario ADMINISTRADOR, pero es necesario crearlo. Para ello haremos un registro como un usuario normal pero el USERNAME debera ser "admin".
Esto habilitará las funcionalidades de cargar periodo y generar reporte.

2- En caso de fallar los pasos 1 al 3 podemos independientemente lanzar los servicios, pero si es necesario lanzar independiente el docker de la base de datos.

ejercucion: 

1- Para lanzar el backend simplemente entramos al main " Backend\clinica_ucu\src\main\java\com\example\clinica_ucu\ClinicaUcuApplication.java " y ejecutamos la aplicación.

2- Para lanzar el frontend necesitaremos de un live server, recomendamos el Live Server que provee Visual Studio Code.