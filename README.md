# PencaUCU


## INSTRUCTIVO


Para una correcta ejecucion del programa es necesario ejecutar secuencialmente los siguientes pasos:

1- Dentro de la carpeta /backend ejecutaremos el comando " mvn clean package " para generar el ejecutable de la aplicación ( ES NECESARIO TENER INSTALDO MAVEN MVN )

2- Dentro de la carpeta /frontend ejecutar el comando " npm run build " para generar el build del frontend.

3- Ubicados en la carpeta Padre "PencaUCU" ejecutar el Dockerfile con el comando " docker compose up -d ".

4- Verificar que los 3 contenedores se hayan descargado e instalado, estos son:
    1- backend
    2- front
    3- database

5- Una vez verificado el estado de los contenedores nos conectaremos a la base de datos con las siguientes credenciales:
    hostname: 127.0.0.1
    port: 3306
    username: root
    password: 1234
    ( EN CASO DE CAMBIAR LOS DATOS EN EL DOCKERFILE DE LA BASE DE DATOS MODIFICAR ACA TAMBIEN)

6- Ejecutar el archivo penca.sql dentro de la carpeta BDD (database\penca.sql).

7- En este punto la aplicación ya está preparada para utilizar, ahora detallaremos algunos detalles a tener en cuenta:

1- En caso de fallar los pasos 1 al 3 podemos independientemente lanzar los servicios, pero si es necesario lanzar independiente el docker de la base de datos.

ejecucion: 

1- Para lanzar el backend simplemente entramos al main " backend\src\main\java\com\pencaucu\backend\BackendApplication.java " y ejecutamos la aplicación.

2- Para lanzar el frontend, se debe ejecutar el comando "npm start" desde la carpeta /frontend.