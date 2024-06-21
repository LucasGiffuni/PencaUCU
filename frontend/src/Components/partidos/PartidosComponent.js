import React, { useEffect, useState } from "react";
import NavBarComponent from "../NavBarComponent";
import { getPartidosNoJugadosParaPrediccion } from "../../Services/MatchService";
import ResultadoPartidosComponent from "./ResultadoPartidosComponent";

const PartidosComponent = (props) => {
  const [partidos, setPartidos] = useState([]);

  useEffect(() => {
    const getPartidosResponse = getPartidosNoJugadosParaPrediccion().then((data) => {
      setPartidos(data[1]);
    });
  }, []);

  return (
    <div className="Home-Component-Container">
      <NavBarComponent />

      <div className="Prediction-Component-Matchs">
        {partidos &&
          partidos.map((partido, i) => {
            return (<ResultadoPartidosComponent match={partido} key2={i} />)
          })}
      </div>
    </div>
  );
}

export default PartidosComponent;
