
DROP DATABASE IF exists pencaucu;

CREATE DATABASE PencaUCU;
USE PencaUCU;

CREATE TABLE LOGIN
(
    userId varchar(20) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    rol ENUM('ADMIN', 'ALUMNO')
);

create table GRUPO (
	ID char primary key,
	descripcion varchar(20)
);

create table ESTADIO (
	Id INT(8) primary key auto_increment,
	nombre VARCHAR(60) not null,
	estado VARCHAR(30) not null,
	ciudad VARCHAR(30) not null,
	calle VARCHAR(30) not null,
	numero VARCHAR(10) not null,
	capacidad INT(4) not null
);

create table ETAPA (
	nombreEtapa varchar(20) primary key
);

create Table EQUIPO 
(
	idEquipo INT(8) PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(30) NOT NULL,
    urlBandera VARCHAR(512) not null,
    status ENUM('HABILITADO', 'DESCALIFICADO') default 'HABILITADO',
    idGrupo char not null,
    puntaje INT(8) default 0,
    etapaActual VARCHAR(20) not null default 'FASE DE GRUPOS',
    foreign key (idGrupo) references GRUPO (ID),
    foreign key (etapaActual) references ETAPA (nombreEtapa)
);

create Table PARTIDO
(
	idPartido INT(8) PRIMARY KEY AUTO_INCREMENT, 
	idEquipo1  INT(8) not null,
    resultadoEquipo1 INT(2) default null,
    idEquipo2 INT(8) not null,
    resultadoEquipo2 INT(2) default null,
    fecha datetime,
    idEstadio INT(8) not null, 
    etapa varchar(20) not null,
    jugado boolean default false,
	FOREIGN KEY (idEquipo1) REFERENCES EQUIPO (idEquipo),
	FOREIGN KEY (idEquipo2) REFERENCES EQUIPO (idEquipo),
	foreign key (etapa) references ETAPA (nombreEtapa),
	foreign key (idEstadio) references ESTADIO (Id),
    CONSTRAINT unique_equipos UNIQUE(idEquipo1, idEquipo2, etapa)
);

create Table CARRERA(
	idCarrera INT(8) PRIMARY KEY AUTO_INCREMENT,
	nombreCarrera varchar(60) not null
);

create table ALUMNO(
	cedulaIdentidad INT(9) PRIMARY KEY,
	nombre varchar(30) not null,
	apellido varchar(30) not null,
    fechaNacimiento date not null,
	email varchar(60) not null,
    idCarrera INT(8) NOT NULL,
    userId  varchar(20) not null,
    puntaje INT(8) default 0,
    idCampeon INT(8) not null,
    puntosPorCampeon INT(2) default null,
    idSubcampeon INT(8) not null,
    puntosPorSubcampeon INT(2) default null,
	FOREIGN KEY (userId) REFERENCES LOGIN (userId),
	FOREIGN KEY (idCarrera) REFERENCES CARRERA (idCarrera),
	FOREIGN KEY (idCampeon) REFERENCES EQUIPO (idEquipo),
	FOREIGN KEY (idSubcampeon) REFERENCES EQUIPO (idEquipo),
	CONSTRAINT unique_userid UNIQUE(userId),
	CONSTRAINT unique_email UNIQUE(email)
);

create Table PREDICCION(
	cedulaIdentidad  INT(9) NOT NULL,
    idPartido INT(8) NOT NULL,
    resultadoEquipo1 INT(2) not null,
	resultadoEquipo2 INT(2) not null,
	puntosObtenidos INT(2) default null,
    PRIMARY KEY (cedulaIdentidad, idPartido),
    foreign key (cedulaIdentidad) references ALUMNO (cedulaIdentidad),
    foreign key (idPartido) references PARTIDO (idPartido)
);



#/ DDL SECTION

insert into GRUPO values ('A', "GRUPO A");
insert into GRUPO values ('B', "GRUPO B");
insert into GRUPO values ('C', "GRUPO C");
insert into GRUPO values ('D', "GRUPO D");

insert into ETAPA values ('FASE DE GRUPOS');
insert into ETAPA values ('CUARTOS DE FINAL');
insert into ETAPA values ('SEMIFINAL');
insert into ETAPA values ('TERCER PUESTO');
insert into ETAPA values ('FINAL');

insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Estadio bernabeu", "Florida", "Miami", "LaCalle","123", 100);

insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Uruguay", "https://www.worldometers.info/img/flags/uy-flag.gif", 'A');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Venezuela", "https://www.worldometers.info/img/flags/ar-flag.gif", 'A');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Argentina", "https://www.worldometers.info/img/flags/it-flag.gif", 'A');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Colombia", "https://www.worldometers.info/img/flags/fr-flag.gif", 'A');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("México", "https://www.worldometers.info/img/flags/po-flag.gif", 'B');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Chile", "https://www.worldometers.info/img/flags/gh-flag.gif", 'B');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Estados Unidos", "https://www.worldometers.info/img/flags/ks-flag.gif", 'B');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Canadá", "https://www.worldometers.info/img/flags/ks-flag.gif", 'B');

insert into PARTIDO(idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (1, 7, "2024-11-22 20:00:00", "FASE DE GRUPOS", 1);
insert into PARTIDO(idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (1, 6, "2024-11-22 20:00:00", "FASE DE GRUPOS", 1);
insert into PARTIDO(idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (1, 5, "2024-11-22 20:00:00", "FASE DE GRUPOS", 1);

insert into CARRERA (nombreCarrera) values ("Ingeniería en Informática");
insert into CARRERA (nombreCarrera) values ("Ingeniería en Telecomunicaciones");




