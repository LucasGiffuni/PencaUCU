import axios from "axios";


export async function getTeamDetailByID(teamID) {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      "Authorization": `Bearer ${localStorage.getItem("jwt")}`
    },
  });
  let res = instance.get("http://127.0.0.1:8080/equipo/" + teamID + "/getEquipo")
    .then(response => {
      let serviceResponse = [];
      serviceResponse[0] = response.data.defaultResponse;
      serviceResponse[1] = response.data.equipo;

      return serviceResponse
    })
    .catch(error => {
      let serviceResponse = [];
      serviceResponse[0] = error;
      return serviceResponse
    });
  return res;

}

export async function getTeams() {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json'
    },
  });
  let res = instance.get("http://127.0.0.1:8080/public/getEquipos")
    .then(response => {
      let serviceResponse = [];
      serviceResponse[0] = response.data.defaultResponse;
      serviceResponse[1] = response.data.equipo;

      return serviceResponse
    })
    .catch(error => {
      let serviceResponse = [];
      serviceResponse[0] = error;
      return serviceResponse
    });
  return res;

}

export async function cargarResultadoPartido(teamID, resultadoEquipo1, resultadoEquipo2) {
  const instance = axios.create({
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      "Authorization": `Bearer ${localStorage.getItem("jwt")}`
    },
  });
  let res = instance.put("http://127.0.0.1:8080/partido/ " + teamID + "/cargarResultadoPartido?puntajeEquipo1=" + resultadoEquipo1 + "&puntajeEquipo2=" + resultadoEquipo2)
    .then(response => {
      let serviceResponse = [];
      serviceResponse[0] = response.data.defaultResponse;
      serviceResponse[1] = response.data.Partido;

      return serviceResponse
    })
    .catch(error => {
      let serviceResponse = [];
      serviceResponse[0] = error;
      return serviceResponse
    });
  return res;

}