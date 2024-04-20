
CREATE DATABASE PencaUCU;
USE PencaUCU;

CREATE TABLE LOGIN
(
    userId  varchar(20) PRIMARY KEY NOT NULL,
    password VARCHAR(50) NOT NULL,
    rol ENUM('ADMIN', 'USER')
);

create Table EQUIPO 
(
	idEquipo INT(8) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(30) NOT NULL,
    urlBandera VARCHAR(512) not null,
    status boolean
);

create table EQUIPOGRUPO(
    grupo ENUM('A','B', 'C', 'D', 'E', 'F', 'G','H', 'I','J','K','L') NOT NULL,
    idEquipo INT(8) not null,
    puntos INT(2) not null,
    PRIMARY KEY (idEquipo),
    FOREIGN KEY (idEquipo) references EQUIPO (idEquipo)
    
);

create Table PARTIDO
(
	idPartido INT(8) NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	idEquipo1  INT(8),
    resultadoEquipo1 INT(2),
    idEquipo2 INT(8),
    resultadoEquipo2 INT(2),
    fecha datetime,
    etapa ENUM('FASE DE GRUPOS','OCTAVOS DE FINAL', 'CUARTOS DE FINAL', 'SEMIFINAL', 'FINAL'),
    idEquipoGanador INT(8),
    jugado boolean,
	FOREIGN KEY (idEquipo1) REFERENCES EQUIPO (idEquipo),
	FOREIGN KEY (idEquipo2) REFERENCES EQUIPO (idEquipo),
	FOREIGN KEY (idEquipoGanador) REFERENCES EQUIPO (idEquipo),
    CONSTRAINT unique_equipos UNIQUE(idEquipo1, idEquipo2, etapa)
);

create Table CARRERA(
	idCarrera INT(8) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nombreCarrera varchar(30) not null
);

create table ALUMNO(
	cedulaIdentidad INT(9) NOT NULL PRIMARY KEY,
	nombre varchar(30) not null,
	apellido varchar(30) not null,
    fechaNacimiento date not null,
    email varchar(60) not null,
    idCarrera INT(8) NOT NULL,
    userId  varchar(20),
	FOREIGN KEY (userId) REFERENCES LOGIN (userId),
	FOREIGN KEY (idCarrera) REFERENCES CARRERA (idCarrera)
);

create Table PREDICCION(
	cedulaIdentidad  INT(9) NOT NULL,
    idPartido INT(8) NOT NULL,
    idEquipoGanador INT(8) NOT NULL,
    idResultadoEquipo1 INT(2),
	idResultadoEquipo2 INT(2),
    PRIMARY KEY (cedulaIdentidad, idPartido)
);

#/ DDL SECTION
insert into EQUIPO(nombre, urlBandera, status) values ("Uruguay", "https://www.worldometers.info/img/flags/uy-flag.gif", true);
insert into EQUIPO(nombre, urlBandera, status) values ("Argentina", "https://www.worldometers.info/img/flags/ar-flag.gif", true);
insert into EQUIPO(nombre, urlBandera, status) values ("Italia", "https://www.worldometers.info/img/flags/it-flag.gif", true);
insert into EQUIPO(nombre, urlBandera, status) values ("Francia", "https://www.worldometers.info/img/flags/fr-flag.gif", true);
insert into EQUIPO(nombre, urlBandera, status) values ("Portugal", "https://www.worldometers.info/img/flags/po-flag.gif", true);
insert into EQUIPO(nombre, urlBandera, status) values ("Ghana", "https://www.worldometers.info/img/flags/gh-flag.gif", true);
insert into EQUIPO(nombre, urlBandera, status) values ("South Korea", "https://www.worldometers.info/img/flags/ks-flag.gif", true);

insert into PARTIDO(idEquipo1, resultadoEquipo1, idEquipo2, resultadoEquipo2, fecha, etapa, idEquipoGanador) values (01, 0, 07,0, "2024-11-22 20:00:00", "FASE DE GRUPOS", null);
insert into PARTIDO(idEquipo1, resultadoEquipo1, idEquipo2, resultadoEquipo2, fecha, etapa, idEquipoGanador) values (01, 2, 06,0, "2024-11-22 20:00:00", "FASE DE GRUPOS", 01);
insert into PARTIDO(idEquipo1, resultadoEquipo1, idEquipo2, resultadoEquipo2, fecha, etapa, idEquipoGanador) values (01, 0, 05,2, "2024-11-22 20:00:00", "FASE DE GRUPOS", 05);