import React, { useState, useEffect } from "react";
import PlaceholderExamplePlaceholder from "../PlaceholderImageComponent";
import Carousel from 'react-bootstrap/Carousel';

import { getProximosPartidos } from '../../Services/MatchService'


const MatchDisplayComponent = (props) => {

  const [partidos, setPartidos] = useState([]);





  useEffect(() => {
    const getPartidosResponse = getProximosPartidos().then((data) => {
      setPartidos(data[1])
    })

  }, []);

  return (
    <div className="Carousel-Container">
      <Carousel >
        {partidos && partidos.map((match, i) => {
          return (
            <Carousel.Item key={i}>
              <PlaceholderExamplePlaceholder />
              <Carousel.Caption>
                <h1>{match.etapa}</h1>
                <div className="container">
                  <div className="column">
                    <div className="Match-Display-FlagContainer">
                      <img src={match.urlBanderaEquipo1} alt="Imagen 1" />
                    </div>
                    <h3 className="name">{match.nombreEquipo1}</h3>
                  </div>

                  <div className="VS-Column">
                    <h1>VS</h1>
                  </div>


                  <div className="column">
                    <div className="Match-Display-FlagContainer">
                      <img src={match.urlBanderaEquipo2} alt="Imagen 2" />
                    </div>
                    <h3 className="name">{match.nombreEquipo2}</h3>
                  </div>


                </div>
                <p>{match.fecha.toString()}</p>
              </Carousel.Caption>
            </Carousel.Item>
          );
        })}
      </Carousel>
    </div>
  );
}

export default MatchDisplayComponent