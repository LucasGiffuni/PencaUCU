import React, { useEffect, useState } from "react";

import NavBarComponent from "../NavBarComponent";

import { getRankingAlumnos } from "../../Services/UserService";

function RankingComponent(props) {
  const [ranking, setRanking] = useState([]);
  const [actualUser, setActualUser] = useState([]);

  const UserContainerBronzePodium = (isActualUser) => ({
    height: "12%",
    width: "50%",
    border: "5px solid #CD7F32",
    borderRadius: "10px",
    margin: " 0 auto",
    backgroundColor: isActualUser ? "#4BAA7E" : "rgb(99, 99, 99)",
    color: "white",
    display: "flex",
    justifyContent:
      "space-between" /* Distribuye los elementos al borde izquierdo y derecho */,
    alignItems: "center" /* Centra verticalmente los elementos */,
    padding: "10px",
    marginBottom: "15px",
  });

  const UserContainerSilverPodium = (isActualUser) => ({
    height: "12%",
    width: "50%",
    border: "5px solid #c0c0c0",
    borderRadius: "10px",
    margin: " 0 auto",
    backgroundColor: isActualUser ? "#4BAA7E" : "rgb(99, 99, 99)",
    color: "white",
    display: "flex",
    justifyContent:
      "space-between" /* Distribuye los elementos al borde izquierdo y derecho */,
    alignItems: "center" /* Centra verticalmente los elementos */,
    padding: "10px",
    marginBottom: "15px",
  });


  const UserContainerGoldPodium = (isActualUser) => ({
    height: "12%",
    width: "50%",
    border: "5px solid #ffd700",
    borderRadius: "10px",
    margin: " 0 auto",
    backgroundColor: isActualUser ? "#4BAA7E" : "rgb(99, 99, 99)",
    color: "white",
    display: "flex",
    justifyContent: "space-between" /* Distribuye los elementos al borde izquierdo y derecho */,
    alignItems: "center" /* Centra verticalmente los elementos */,
    padding: "10px",
    marginBottom: "15px",
  });

  useEffect(() => {
    const getRankingResponse = getRankingAlumnos().then((data) => {
      setRanking(data[1]);
    });



  }, []);

  return (
    <div className="Home-Component-Container">
      <NavBarComponent />
      <h1 className="Ranking-Header">Ranking</h1>

      {ranking &&
        ranking.map((alumno, i) => {
          if (i === 2) {
            return (
              <div style={UserContainerBronzePodium(JSON.parse(localStorage.getItem("alumno")).cedulaIdentidad === alumno.cedulaIdentidad)} key={i}>
                <h2>{alumno.nombre} {alumno.apellido}</h2>
                <h2>{alumno.puntaje} pts.</h2>
              </div>
            );
          } else if (i === 1) {
            return (
              <div style={UserContainerSilverPodium(JSON.parse(localStorage.getItem("alumno")).cedulaIdentidad === alumno.cedulaIdentidad)} key={i}>
                <h2>{alumno.nombre} {alumno.apellido}</h2>
                <h2>{alumno.puntaje} pts.</h2>
              </div>
            );
          } else if (i === 0) {
            return (
              <div style={UserContainerGoldPodium(JSON.parse(localStorage.getItem("alumno")).cedulaIdentidad === alumno.cedulaIdentidad)} key={i}>
                <h2>{alumno.nombre} {alumno.apellido}</h2>
                <h2>{alumno.puntaje} pts.</h2>
              </div>
            );
          } else {
            return (
              <div className="User-Container" key={i}>
                <h2>{alumno.nombre} {alumno.apellido}</h2>
                <h2>{alumno.puntaje} pts.</h2>
              </div>
            );
          }
        })}
    </div>
  );
}

export default RankingComponent;
