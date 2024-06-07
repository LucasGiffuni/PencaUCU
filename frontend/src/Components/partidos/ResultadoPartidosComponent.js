/* eslint-disable jsx-a11y/alt-text */
import React, { useEffect, useState } from "react";

import CargarResultadoPartidoModal from "./CargarResultadoPartidoModal";

const ResultadoPartidosComponent = (props) => {
  const [dayDate, setDayDate] = useState();
  const [date, setDate] = useState();

  const days = [
    "Domingo",
    "Lunes",
    "Martes",
    "Miercoles",
    "Jueves",
    "Viernes",
    "Sabado",
  ];
  const [modalShow, setModalShow] = useState(false);

  const [clicks, setClicks] = useState(0);
  const [resultPrediction, setResultPrediction] = useState(true);

  function setResultPredictionParent() {
    setResultPrediction(true);
  }

  useEffect(() => {
    let newDate = Date(props.match.fecha);

    const dateaux = getDateFromString(newDate);

    setDayDate(days[dateaux.getDay()]);
    setDate(dateaux.getDate());
  }, []);

  const getDateFromString = (str) => {
    const [date, time] = str.split(" ");
    str = `${date}T${time}.000Z`;
    return new Date(str);
  };

  const showModal = (id) => {
    setClicks(clicks + 1);
    setModalShow(true);
  };

  return (
    <>
      <div
        className="Prediction-Match-Component"
        onClick={() => {
          showModal(props.match.id);
        }}
        key={props.key2}
      >
        <div className="Prediction-Match-Component-Team1">
          <img
            className="Prediction-Match-Component-TeamImage"
            src={props.match.urlBanderaEquipo1}
          ></img>
        </div>

        <div className="Prediction-Match-Component-Button">
          <div className="Versus">VS</div>
        </div>

        <div className="Prediction-Match-Component-Team2">
          <img
            className="Prediction-Match-Component-TeamImage"
            src={props.match.urlBanderaEquipo2}
          ></img>
        </div>

        <div className="Prediction-Match-Component-Information">
          <div className="Prediction-Match-Component-Information-Date">
            <p>{props.match.fecha}</p>
          </div>

          <div className="Prediction-Match-Component-Information-Stage">
            <p>FASE DE GRUPOS</p>
          </div>
          
        </div>
      </div>
      <CargarResultadoPartidoModal
        setMatch={props.match}
        setShow={modalShow}
        clicks={clicks}
        function={setResultPredictionParent}
      />
    </>
  );
};

export default ResultadoPartidosComponent;
