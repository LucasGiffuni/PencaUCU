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
    },
    {
      id: 2,
      country1: "Rusia",
      country2: "Colombia",
      date: "2024-05-21",
    },
    {
      id: 3,
      country1: "Venezuela",
      country2: "Peru",
      date: "2024-05-22",
    },
  ];

  return (
    <div>
      <Carousel>
        {Matches.map((match, i) => {
          return (
            <Carousel.Item>
              <PlaceholderExamplePlaceholder text="First slide" />
              <Carousel.Caption>
                <h1>MATCH</h1>
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
