import React, { useState } from "react";
import Carousel from "react-bootstrap/Carousel";
import PlaceholderExamplePlaceholder from "./PlaceholderImageComponent";


function MatchDisplayComponent(props) {
  const [value, setValue] = useState(props.value);

  let lightsMatrix = [];

  let Matches = [
    {
      id: 1,
      country1: "Uruguay",
      country2: "Paraguay",
      date: "2024-05-20",
      etapa: "FASE DE GRUPOS"
    },
    {
      id: 2,
      country1: "Rusia",
      country2: "Colombia",
      date: "2024-05-21",
      etapa: "OCTAVOS DE FINAL"

    },
    {
      id: 3,
      country1: "Venezuela",
      country2: "Peru",
      date: "2024-05-22",
      etapa: "CUARTOS DE FINAl"
    },
    {
      id: 4,
      country1: "Uruguay",
      country2: "Alemania",
      date: "2024-05-23",
      etapa: "SEMIFINAL"
    },
    {
      id: 4,
      country1: "Uruguay",
      country2: "Argentina",
      date: "2024-05-23",
      etapa: "FINAL"
    },
  ];

  return (
    <div>
      <Carousel>
        {Matches.map((match, i) => {
          return (
            <Carousel.Item>
              <PlaceholderExamplePlaceholder />
              <Carousel.Caption>
                <h1>{match.etapa}</h1>
                <h3>
                  {match.country1} VS {match.country2}
                </h3>
                <p>{match.date}</p>
              </Carousel.Caption>
            </Carousel.Item>
          );
        })}
      </Carousel>
    </div>
  );
}

export default MatchDisplayComponent;
