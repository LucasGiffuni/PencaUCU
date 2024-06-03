import axios from "axios";


export async function crearPrediccion(idPartido, userId, resultadoEquipo1, resultadoEquipo2) {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      "Authorization": `Bearer ${localStorage.getItem("jwt")}`
    },
  });
  let res = instance.post("http://127.0.0.1:8080/prediccion/" + idPartido + "/cargarPrediccion?userId=" + userId + "&resultadoEquipo1=" + resultadoEquipo1 + "&resultadoEquipo2=" + resultadoEquipo2)
    .then(response => {
      let serviceResponse = [];
      serviceResponse[0] = response.data.defaultResponse;
      serviceResponse[1] = response.data.partidos;

      return serviceResponse
    })
    .catch(error => {
      let serviceResponse = [];
      serviceResponse[0] = error;
      return serviceResponse
    });
  return res;

}

export async function obtenerPrediccion(idPartido, userId) {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      "Authorization": `Bearer ${localStorage.getItem("jwt")}`
    },
  });
  let res = instance.get("http://127.0.0.1:8080/prediccion/" + userId + "/consultarPrediccion?idPartido=" + idPartido)
    .then(response => {
      let serviceResponse = [];
      serviceResponse[0] = response.data.defaultResponse;
      serviceResponse[1] = response.data.prediccion;

      return serviceResponse
    })
    .catch(error => {
      let serviceResponse = [];
      serviceResponse[0] = error;
      return serviceResponse
    });
  return res;

}


export async function obtenerPrediccionDadoUsuario(userId) {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      "Authorization": `Bearer ${localStorage.getItem("jwt")}`
    },
  });
  let res = instance.get("http://127.0.0.1:8080/prediccion/" + userId + "/consultarPredicciones")
    .then(response => {
      let serviceResponse = [];
      serviceResponse[0] = response.data.defaultResponse;
      serviceResponse[1] = response.data.detallePrediccionUsuario;

      return serviceResponse
    })
    .catch(error => {
      let serviceResponse = [];
      serviceResponse[0] = error;
      return serviceResponse
    });
  return res;

}