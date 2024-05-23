import React from "react";
import { useEffect, useState } from "react";
import NavBarComponent from "./NavBarComponent";
import { Bracket, IRoundProps } from "react-brackets";

const TournamentDashboard = () => {
  useEffect(() => {}, []);

  const rounds = [
    {
      title: "Cuartos de final",
      seeds: [
        {
          id: 1,
          date: new Date().toDateString(),
          teams: [{ name: "Team A1" }, { name: "Team B2" }],
        },
        {
          id: 2,
          date: new Date().toDateString(),
          teams: [{ name: "Team B1" }, { name: "Team A2" }],
        },
        {
          id: 3,
          date: new Date().toDateString(),
          teams: [{ name: "Team C1" }, { name: "Team D2" }],
        },
        {
          id: 4,
          date: new Date().toDateString(),
          teams: [{ name: "Team D1" }, { name: "Team C2" }],
        },
      ],
    },
    {
      title: "Semifinal",
      seeds: [
        {
          id: 5,
          date: new Date().toDateString(),
          teams: [{ name: "Por definir" }, { name: "Por definir" }],
        },
        {
          id: 6,
          date: new Date().toDateString(),
          teams: [{ name: "Por definir" }, { name: "Por definir" }],
        },
      ],
    },
    {
      title: "Final",
      seeds: [
        {
          id: 7,
          date: new Date().toDateString(),
          teams: [{ name: "Por definir" }, { name: "Por definir" }],
        },
      ],
    },
  ];

  return (
    <>
      <div className="Home-Component-Container">

        <Bracket rounds={rounds} />
      </div>
    </>
  );
};

export default TournamentDashboard;
