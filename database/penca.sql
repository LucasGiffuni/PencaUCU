
DROP DATABASE IF exists PencaUCU;

CREATE DATABASE PencaUCU;
USE PencaUCU;


CREATE table GRUPO (
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
	idEquipo1  INT(8) default null,
    resultadoEquipo1 INT(2) default null,
    idEquipo2 INT(8) default null,
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

create table USUARIO (
	userId varchar(20) primary key,
    password VARCHAR(50) NOT NULL,
    rol ENUM('ADMIN', 'ALUMNO'),
	cedulaIdentidad INT(9),
	nombre varchar(30),
	apellido varchar(30),
    fechaNacimiento date,
	email varchar(60),
    idCarrera INT(8),
    puntaje INT(8) default 0,
    idCampeon INT(8),
    puntosPorCampeon INT(2) default null,
    idSubcampeon INT(8),
    puntosPorSubcampeon INT(2) default null,
	FOREIGN KEY (idCarrera) REFERENCES CARRERA (idCarrera),
	FOREIGN KEY (idCampeon) REFERENCES EQUIPO (idEquipo),
	FOREIGN KEY (idSubcampeon) REFERENCES EQUIPO (idEquipo),
	CONSTRAINT unique_userid UNIQUE(userId),
	CONSTRAINT unique_email UNIQUE(email)
);

create Table PREDICCION(
	userId varchar(20),
    idPartido INT(8),
    resultadoEquipo1 INT(2) not null,
	resultadoEquipo2 INT(2) not null,
	puntosObtenidos INT(2) default null,
    PRIMARY KEY (userId, idPartido),
    foreign key (userId) references USUARIO (userId),
    foreign key (idPartido) references PARTIDO (idPartido)
);

#/ DDL SECTION


-- Usuario ADMIN
insert into USUARIO (userId, password, rol) values ("admin", "21232f297a57a5a743894a0e4a801fc3", "ADMIN");

-- Grupos
insert into GRUPO values ('A', "GRUPO A");
insert into GRUPO values ('B', "GRUPO B");
insert into GRUPO values ('C', "GRUPO C");
insert into GRUPO values ('D', "GRUPO D");

-- Etapas
insert into ETAPA values ('FASE DE GRUPOS');
insert into ETAPA values ('CUARTOS DE FINAL');
insert into ETAPA values ('SEMIFINAL');
insert into ETAPA values ('TERCER PUESTO');
insert into ETAPA values ('FINAL');

-- Equipos
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Argentina", "https://www.worldometers.info/img/flags/ar-flag.gif", 'A');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Perú", "https://www.worldometers.info/img/flags/pe-flag.gif", 'A');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Chile", "https://www.worldometers.info/img/flags/ci-flag.gif", 'A');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Canadá", "https://www.worldometers.info/img/flags/ca-flag.gif", 'A');

insert into EQUIPO(nombre, urlBandera, idGrupo) values ("México", "https://www.worldometers.info/img/flags/mx-flag.gif", 'B');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Ecuador", "https://www.worldometers.info/img/flags/ec-flag.gif", 'B');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Venezuela", "https://www.worldometers.info/img/flags/ve-flag.gif", 'B');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Jamaica", "https://www.worldometers.info/img/flags/jm-flag.gif", 'B');

insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Estados Unidos", "https://www.worldometers.info/img/flags/us-flag.gif", 'C');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Uruguay", "https://www.worldometers.info/img/flags/uy-flag.gif", 'C');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Panamá", "https://www.worldometers.info/img/flags/pm-flag.gif", 'C');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Bolivia", "https://www.worldometers.info/img/flags/bl-flag.gif", 'C');

insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Brasil", "https://www.worldometers.info/img/flags/br-flag.gif", 'D');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Colombia", "https://www.worldometers.info/img/flags/co-flag.gif", 'D');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Paraguay", "https://www.worldometers.info/img/flags/pa-flag.gif", 'D');
insert into EQUIPO(nombre, urlBandera, idGrupo) values ("Costa Rica", "https://www.worldometers.info/img/flags/cs-flag.gif", 'D');

-- Estadios
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Mercedes-Benz Stadium", "Georgia", "Atlanta", "1 AMB Dr NW","GA 30313", 71000);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Levi's Stadium", "California", "Santa Clara", "4900 Marie P DeBartolo Way","CA 95054", 72840);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Bank of America Stadium", "Carolina Del Norte", "Charlotte", "800 S Mint St","NC 28202",74479);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("State Farm Stadium", "Arizona", "Glendale", "1 Cardinals Dr","AZ 85305",63400);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("SoFi Stadium", "California", "Inglewood", "1001 Stadium Dr","CA 90301",70000);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Inter&Co Stadium", "Florida", "Orlando", "655 W Church St,","FL 32805",25400);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Estadio NRG", "Texas", "Huston", "NRG Pkwy","TX 77054",72220);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Q2 Stadium", "Texas", "Austin", "10414 McKalla Place Austin","TX 78758",20738);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("AT&T Stadium", "Texas", "Arlington", "1 AT&T Way","TX 76011",80000);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("MetLife Stadium", "Nueva Jersey", "East Rutherford", "1 MetLife Stadium Dr","NJ 07073",82500);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Allegiant Stadium", "Nevada", "Las Vegas", "3333 Al Davis Way","NV 89118",65000);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Arrowhead Stadium", "Misuri", "Kansas City", "1 Arrowhead Dr","MO 64129",79451);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Children's Mercy Park", "Kansas", "Kansas City", "1 Sporting Way","KS 66111",18467);
insert into ESTADIO (nombre, estado, ciudad, calle, numero, capacidad) values ("Hard Rock Stadium", "Florida", "Miami", "347 Don Shula Dr Suite 102","FL 33056",75540);

-- Partidos Grupo A
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (1, 1, 4, "2024-06-20 20:00:00", "FASE DE GRUPOS", 1);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (2, 2, 3, "2024-06-21 19:00:00", "FASE DE GRUPOS", 9);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (10, 2, 4, "2024-06-25 17:00:00", "FASE DE GRUPOS", 13);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (9, 3, 1, "2024-06-25 21:00:00", "FASE DE GRUPOS", 10);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (17, 1, 2, "2024-06-29 20:00:00", "FASE DE GRUPOS", 14);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (18, 4, 3, "2024-06-29 20:00:00", "FASE DE GRUPOS", 6);

-- Partidos Grupo B
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (4, 6, 7, "2024-06-22 15:00:00", "FASE DE GRUPOS", 2);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (3, 5, 8, "2024-06-22 20:00:00", "FASE DE GRUPOS", 7);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (12, 6, 8, "2024-06-26 15:00:00", "FASE DE GRUPOS", 11);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (11, 7, 5, "2024-06-26 18:00:00", "FASE DE GRUPOS", 5);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (19, 5, 6, "2024-06-30 17:00:00", "FASE DE GRUPOS", 4);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (20, 8, 7, "2024-06-30 19:00:00", "FASE DE GRUPOS", 8);

-- Partidos Grupo C
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (5, 9, 12, "2024-06-23 17:00:00", "FASE DE GRUPOS", 9);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (6, 10, 11, "2024-06-23 21:00:00", "FASE DE GRUPOS", 14);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (13, 11, 9, "2024-06-27 18:00:00", "FASE DE GRUPOS", 1);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (14, 10, 12, "2024-06-27 21:00:00", "FASE DE GRUPOS", 10);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (21, 9, 10, "2024-07-01 20:00:00", "FASE DE GRUPOS", 12);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (22, 12, 11, "2024-07-01 21:00:00", "FASE DE GRUPOS", 6);

-- Partidos Grupo D
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (8, 14, 15, "2024-06-24 17:00:00", "FASE DE GRUPOS", 7);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (7, 13, 16, "2024-06-24 18:00:00", "FASE DE GRUPOS", 5);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (16, 14, 16, "2024-06-28 15:00:00", "FASE DE GRUPOS", 4);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (15, 15, 13, "2024-06-28 18:00:00", "FASE DE GRUPOS", 11);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (23, 13, 14, "2024-07-02 18:00:00", "FASE DE GRUPOS", 2);
insert into PARTIDO(idPartido, idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (24, 16, 15, "2024-07-02 20:00:00", "FASE DE GRUPOS", 8);

-- Partidos Cuartos de Final
insert into PARTIDO(idPartido, fecha, etapa, idEstadio) values 
(25, "2024-07-04 20:00:00", "CUARTOS DE FINAL", 7),
(26, "2024-07-05 20:00:00", "CUARTOS DE FINAL", 9),
(27, "2024-07-06 15:00:00", "CUARTOS DE FINAL", 4),
(28, "2024-07-06 18:00:00", "CUARTOS DE FINAL", 11);

-- Partidos Semifinal
insert into PARTIDO(idPartido, fecha, etapa, idEstadio) values 
(29, "2024-07-09 20:00:00", "SEMIFINAL", 10),
(30, "2024-07-10 20:00:00", "SEMIFINAL", 3);

-- Partido tercer puesto
insert into PARTIDO(idPartido, fecha, etapa, idEstadio) values
(31, "2024-07-13 20:00:00", "TERCER PUESTO", 3);

-- Partido Final
insert into PARTIDO(idPartido, fecha, etapa, idEstadio) values
(32, "2024-07-14 20:00:00", "FINAL", 14);

-- Carreras
insert into CARRERA (nombreCarrera) values 
("Abogacía"),
("Acompañamiento Terapéutico"),
("Agronomía"),
("Analista en Informática"),
("Arquitectura"),
("Artes Escénicas"),
("Artes Visuales"),
("Business Analytics"),
("Ciencia Política"),
("Cine"),
("Comunicación"),
("Comunicación y Marketing"),
("Contador Público"),
("Datos y Negocios"),
("Desarrollador de Software"),
("Dirección de Empresas"),
("Economía"),
("Educación Inicial"),
("Finanzas"),
("Fisioterapia"),
("Fonoaudiología"),
("Gestión Humana"),
("Ingeniería Ambiental"),
("Ingeniería en Alimentos"),
("Ingeniería Electrónica"),
("Ingeniería en Informática"),
("Ingeniería Industrial"),
("Inteligencia Artificial y Ciencia de Datos"),
("Licenciatura en Enfermería"),
("Licenciatura en Enfermería (Profesionalización)"),
("Licenciatura en Informática"),
("Medicina"),
("Negocios Internacionales"),
("Negocios y Economía"),
("Notariado"),
("Nutrición"),
("Odontología"),
("Psicología"),
("Psicomotricidad"),
("Psicopedagogía"),
("Recreación Educativa"),
("Sociología"),
("Trabajo Social");