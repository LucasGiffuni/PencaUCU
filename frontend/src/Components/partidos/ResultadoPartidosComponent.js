/* eslint-disable jsx-a11y/alt-text */
import React, { useEffect, useState } from "react";

import CargarResultadoPartidoModal from "./CargarResultadoPartidoModal";

const ResultadoPartidosComponent = (props) => {

  function obtenerNombreDiaYNumeroMes(fecha) {
    const diasSemana = [
      "Domingo",
      "Lunes",
      "Martes",
      "Miércoles",
      "Jueves",
      "Viernes",
      "Sábado",
    ];

    const meses = [
      "Enero",
      "Febrero",
      "Marzo",
      "Abril",
      "Mayo",
      "Junio",
      "Julio",
      "Agosto",
      "Septiembre",
      "Octubre",
      "Noviembre",
      "Diciembre"
    ];
    const fechaObjeto = new Date(fecha);
    const nombreDiaSemana = diasSemana[fechaObjeto.getDay()];
    const numeroDia = fechaObjeto.getDate(); 
    const numeroMes = meses[fechaObjeto.getMonth()]; // Sumar 1 porque los meses en JavaScript van de 0 a 11

    return `${nombreDiaSemana} ${numeroDia} de ${numeroMes}`;
  }

  const [modalShow, setModalShow] = useState(false);

  const [clicks, setClicks] = useState(0);
  const [resultPrediction, setResultPrediction] = useState(true);

  function setResultPredictionParent() {
    
    setResultPrediction(true);
  }


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
          <p>{obtenerNombreDiaYNumeroMes(props.match.fecha)}</p>

          </div>

          <div className="Prediction-Match-Component-Information-Stage">
            <p>{props.match.etapa}</p>
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
