/* eslint-disable jsx-a11y/alt-text */
import React, { Component } from "react";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

import Avatar from "@mui/material/Avatar";
import Stack from "@mui/material/Stack";

import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";

import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";

import Dropdown from "react-bootstrap/Dropdown";

import { obtenerPrediccionDadoUsuario } from "../Services/PredicctionService";

function stringToColor(string) {
  let hash = 0;
  let i;

  /* eslint-disable no-bitwise */
  for (i = 0; i < string.length; i += 1) {
    hash = string.charCodeAt(i) + ((hash << 5) - hash);
  }

  let color = "#";

  for (i = 0; i < 3; i += 1) {
    const value = (hash >> (i * 8)) & 0xff;
    color += `00${value.toString(16)}`.slice(-2);
  }
  /* eslint-enable no-bitwise */

  return color;
}

function stringAvatar(name) {
  return {
    sx: {
      bgcolor: stringToColor(name),
    },
    children: `${name.split(" ")[0][0]}${name.split(" ")[1][0]}`,
  };
}

const CustomToggle = React.forwardRef(({ children, onClick, name }, ref) => (
  <a
    ref={ref}
    onClick={(e) => {
      e.preventDefault();
      onClick(e);
    }}
  >
    {children}
    <Avatar className="NavBar-Avatar-Component" {...stringAvatar(name)} />
  </a>
));

function NavBarComponent(props) {
  const navigate = useNavigate();

  let alumno = JSON.parse(localStorage.getItem("alumno"));

  const [predicciones, setPredicciones] = useState([]);

  const [show, setShow] = useState(false);
  const [showPerfil, setShowPerfil] = useState(false);

  const handleClose = () => {
    setShow(false);
    setShowPerfil(false);
  };

  const [isUserAdmin, setIsUserAdmin] = useState(false);

  if (alumno === null) {
    alumno = {
      nombre: "A",
      apellido: "B",
    };
  }

  const logout = () => {
    navigate("/login");
  };

  useEffect(() => {
    let newObject = localStorage.getItem("alumno");
    alumno = newObject;

    obtenerPrediccionesUsuario();
  }, []);

  const obtenerPrediccionesUsuario = () => {
    let alumno = JSON.parse(localStorage.getItem("alumno"));

    obtenerPrediccionDadoUsuario(alumno.userId).then((res) => {
      setPredicciones(res[1]);
    });
  };
  const showModalPerfil = () => {
    setShowPerfil(true);
  };

  const showModalApuestas = () => {
    setShow(true);
  };

  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary">
        <>
          {alumno.rol === "ADMIN" ? (
            <Container>
              <Navbar.Brand href="/home">Penca UCU</Navbar.Brand>
              <Navbar.Toggle aria-controls="basic-navbar-nav" />
              <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                  <Nav.Link href="/predicciones">Predicciones</Nav.Link>
                  <Nav.Link href="/ranking">Ranking</Nav.Link>
                  <Nav.Link href="/partidos">Partidos</Nav.Link>
                </Nav>
              </Navbar.Collapse>
            </Container>
          ) : (
            <Container>
              <Navbar.Brand href="/home">Penca UCU</Navbar.Brand>
              <Navbar.Toggle aria-controls="basic-navbar-nav" />
              <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                  <Nav.Link href="/predicciones">Predicciones</Nav.Link>
                  <Nav.Link href="/ranking">Ranking</Nav.Link>
                </Nav>
              </Navbar.Collapse>
            </Container>
          )}
        </>
        <Dropdown className="NavBar-Component-Dropdown">
          <Dropdown.Toggle
            as={CustomToggle}
            id={`dropdown-button-drop-start`}
            name={alumno.nombre + " " + alumno.apellido}
          />
          <Dropdown.Menu>
            <Dropdown.Item
              onClick={() => {
                showModalPerfil();
              }}
            >
              Perfil
            </Dropdown.Item>
            <Dropdown.Item
              onClick={() => {
                showModalApuestas();
              }}
            >
              Mis Apuestas
            </Dropdown.Item>
            <Dropdown.Item onClick={logout}>Logout</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </Navbar>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Mis Predicciones</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {predicciones &&
            predicciones.map((prediccion, i) => {
              return (
                <div className="Prediccions-Component-Body" key={i}>
                  <div className="Prediccions-Component-Body-Phase">
                    {prediccion.idPartido}
                  </div>

                  <div className="Prediccions-Component-Body-Nombre-Equipo1">
                    {prediccion.nombreEquipo1}
                  </div>

                  <div className="Prediccions-Component-Body-Bandera-Equipo1">
                    <img
                      className="Prediction-Match-Component-TeamImage"
                      src={prediccion.urlBanderaEquipo1}
                    ></img>
                  </div>
                  <div className="Prediction-Component-Body-Versus">VS</div>

                  <div className="Prediccions-Component-Body-Nombre-Equipo2">
                    {prediccion.nombreEquipo2}
                  </div>

                  <div className="Prediccions-Component-Body-Bandera-Equipo2">
                    <img
                      className="Prediction-Match-Component-TeamImage"
                      src={prediccion.urlBanderaEquipo2}
                    ></img>
                  </div>
                  <div className="Prediction-Component-Body-ResultadoEquipo1">
                    {prediccion.resultadoEquipo1}
                  </div>

                  <div className="Prediction-Component-Body-ResultadoEquipo2">
                    {prediccion.resultadoEquipo2}
                  </div>
                </div>
              );
            })}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleClose}>
            Salir
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal
        show={showPerfil}
        onHide={handleClose}
        className="UserProfile-Modal"
      >
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">
            Mis Datos
          </Modal.Title>
        </Modal.Header>
        <Modal.Body className="UserProfile-Component-Body">
          <div className="UserProfile-Component-Container">
            <CustomToggle
              name={alumno.nombre + " " + alumno.apellido}
              className="UserProfile-Component-UserImage"
            />
            <h2>{alumno.nombre + " " + alumno.apellido}</h2>
            <p>{alumno.email}</p>
            <hr></hr>
            <br></br>
            <p>Puntos: {alumno.puntaje} </p>
          </div>
        </Modal.Body>
        <Modal.Footer></Modal.Footer>
      </Modal>
    </>
  );
}

export default NavBarComponent;
