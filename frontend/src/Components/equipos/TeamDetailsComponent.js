/* eslint-disable jsx-a11y/alt-text */
import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";

import {
  getTeamDetailByID,
  obtenerPartidosEquipo,
} from "../../Services/TeamService";

function TeamDetailsComponent(props) {
  const [team, setTeam] = useState(null);

  const [partidos, setPartidos] = useState([]);

  const [show, setShow] = useState(props.setShow);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  useEffect(() => {
    setShow(props.setShow);
    if (props.setShow) {
      fetchDatos();
    }
  }, [props.clicks]);

  const fetchDatos = () => {
    getTeamDetailByID(props.teamId).then((response) => {
      setTeam(response[1]);
    });

    obtenerPartidosEquipo(props.teamId).then((response) => {
      setPartidos(response[1]);
    });
  };

  if (show) {
    return (
      <>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>{team && team.nombre}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {partidos &&
              partidos.map((partido, i) => {
                return (
                  <div className="Prediccions-Component-Body" key={i}>
                    <div className="Prediccions-Component-Body-Phase">
                      {partido.etapa}
                    </div>

                    <div className="Prediccions-Component-Body-Nombre-Equipo1">
                      {partido.nombreEquipo1}
                    </div>

                    <div className="Prediccions-Component-Body-Bandera-Equipo1">
                      <img
                        className="Prediction-Match-Component-TeamImage"
                        src={partido.urlBanderaEquipo1}
                      ></img>
                    </div>
                    <div className="Prediction-Component-Body-Versus">VS</div>

                    <div className="Prediccions-Component-Body-Nombre-Equipo2">
                      {partido.nombreEquipo2}
                    </div>

                    <div className="Prediccions-Component-Body-Bandera-Equipo2">
                      <img
                        className="Prediction-Match-Component-TeamImage"
                        src={partido.urlBanderaEquipo2}
                      ></img>
                    </div>
                    {partido.jugado  === "true" ? (
                      <>
                        <div className="Prediction-Component-Body-ResultadoEquipo1">
                          {partido.puntajeEquipo1}
                        </div>
                        <div className="Prediction-Component-Body-ResultadoEquipo2">
                          {partido.puntajeEquipo2}
                        </div>
                      </>
                    ) : (
                      <>
                        <div className="Prediction-Component-Body-ResultadoEquipo1">
                          {}
                        </div>
                        <div className="Prediction-Component-Body-ResultadoEquipo2">
                          {}
                        </div>
                      </>
                    )}
                  </div>
                );
              })}
          </Modal.Body>
        </Modal>
      </>
    );
  }
}

export default TeamDetailsComponent;
