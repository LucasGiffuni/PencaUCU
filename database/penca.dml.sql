
CREATE DATABASE PencaUCU;

USE PencaUCU;
CREATE TABLE LOGIN
(
    userId    varchar(20) PRIMARY KEY NOT NULL,
    password VARCHAR(50)     NOT NULL,
    rol ENUM('ADMIN', 'USER')
);

create Table EQUIPO 
(
	idEquipo INT(8) NOT NULL PRIMARY KEY,
    nombre varchar(30) NOT NULL,
    grupo ENUM('A','B', 'C', 'D', 'E', 'F', 'G','H', 'I','J','K','L') NOT NULL,
    puntajeGrupo int(2) NOT NULL,
    urlBandera VARCHAR(512) not null,
    status boolean
);

create Table PARTIDO
(
	idPartido INT(8) NOT NULL PRIMARY KEY,
	idEquipo1  INT(8),
    resultadoEquipo1 INT(2),
    idEquipo2 INT(8),
    resultadoEquipo2 INT(2),
    fecha datetime,
    etapa ENUM('FASE DE GRUPOS','OCTAVOS DE FINAL', 'CUARTOS DE FINAL', 'SEMIFINAL', 'FINAL'),
    idEquipoGanador INT(8),
	FOREIGN KEY (idEquipo1) REFERENCES EQUIPO (idEquipo),
	FOREIGN KEY (idEquipo2) REFERENCES EQUIPO (idEquipo),
	FOREIGN KEY (idEquipoGanador) REFERENCES EQUIPO (idEquipo)

);

create Table Carrera(
	idCarrera INT(8) NOT NULL PRIMARY KEY,
	nombreCarrera varchar(30) not null
);

create table ALUMNO(
	cedulaIdentidad INT(9) NOT NULL PRIMARY KEY,
	nombre varchar(30) not null,
	apellido varchar(30) not null,
    fechaNacimiento date not null,
    email varchar(30) not null,
    idCarrera INT(8) NOT NULL,
    userId  varchar(20),
	FOREIGN KEY (userId) REFERENCES LOGIN (userId),
	FOREIGN KEY (idCarrera) REFERENCES Carrera (idCarrera)
);

create Table PREDICCION(
	cedulaIdentidad  INT(9) NOT NULL,
    idPartido INT(8) NOT NULL,
    idEquipoGanador INT(8) NOT NULL,
    idResultadoEquipo1 INT(2),
	idResultadoEquipo2 INT(2)
);



