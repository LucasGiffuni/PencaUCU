import React, { Component } from "react";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

import Avatar from "@mui/material/Avatar";
import Stack from "@mui/material/Stack";

import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";

import Dropdown from "react-bootstrap/Dropdown";

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
  }, []);

  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="/">Penca UCU</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/predicciones">Predicciones</Nav.Link>
            <Nav.Link href="/ranking">Ranking</Nav.Link>

          </Nav>
        </Navbar.Collapse>
      </Container>

      <Dropdown className="NavBar-Component-Dropdown">
        <Dropdown.Toggle
          as={CustomToggle}
          id={`dropdown-button-drop-start`}
          name={alumno.nombre + " " + alumno.apellido}
        />
        <Dropdown.Menu>
          <Dropdown.Item>Perfil</Dropdown.Item>
          <Dropdown.Item>Mis Apuestas</Dropdown.Item>
          <Dropdown.Item onClick={logout}>Logout</Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>
    </Navbar>
  );
}

export default NavBarComponent;
