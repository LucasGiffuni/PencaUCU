import React, { useState } from "react";
import PlaceholderExamplePlaceholder from "./PlaceholderImageComponent";
import Carousel from 'react-bootstrap/Carousel';


export function MatchDisplayComponent() {

  let Matches = [
    {
      matchId: 1,
      teamId1: 1,
      teamScore1: 2,
      teamId2: 2,
      teamScore2: 0,
      stage: "FASE DE GRUPOS",
      date: new Date(2024, 11, 22),
      played: true,
    },
    {
      matchId: 2,
      teamId1: 1,
      teamScore1: 2,
      teamId2: 3,
      teamScore2: 1,
      stage: "FASE DE GRUPOS",
      date: new Date(2024, 11, 23),
      played: true,
    },
    {
      matchId: 2,
      teamId1: 1,
      teamScore1: 0,
      teamId2: 4,
      teamScore2: 0,
      stage: "FASE DE GRUPOS",
      date: new Date(2024, 11, 23),
      played: false,
    },
  ];

  return (
    <div>
      <Carousel>
        {Matches.map((match, i) => {
          return (
            <Carousel.Item key={i}>
              <PlaceholderExamplePlaceholder />
              <Carousel.Caption>
                <h1>{match.stage}</h1>
                <h3>
                  {match.teamId1} VS {match.teamId2}
                </h3>
                <p>{match.date.toString()}</p>
              </Carousel.Caption>
            </Carousel.Item>
          );
        })}
      </Carousel>
    </div>
  );
}
