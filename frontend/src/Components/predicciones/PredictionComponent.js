import React, { useEffect, useState } from "react";
import NavBarComponent from "../NavBarComponent";
import PredictionMatchComponent from "./PredictionMatchComponent";
import { getPartidosNoJugados } from "../../Services/MatchService";

const PredictionComponent = (props) => {
  const [partidos, setPartidos] = useState([]);

  useEffect(() => {
    const getPartidosResponse = getPartidosNoJugados().then((data) => {
      setPartidos(data[1]);
    });
  }, []);

  return (
    <div className="Home-Component-Container">
      <NavBarComponent />

      <div className="Prediction-Component-Matchs">
        {partidos &&
          partidos.map((partido, i) => {
            return (<PredictionMatchComponent match={partido} key2={i}/>)
          })}
      </div>
    </div>
  );
}

export default PredictionComponent;
