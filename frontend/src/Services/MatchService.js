import axios from "axios";



export async function getProximosPartidos() {
    const instance = axios.create({
      baseURL: 'http://127.0.0.1:8080',
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
        "Authorization": `Bearer ${localStorage.getItem("jwt")}`
      },
    });
    let res = instance.get("http://127.0.0.1:8080/partido/getProximosPartidos")
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

export async function getPartidosJugados() {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      "Authorization": `Bearer ${localStorage.getItem("jwt")}`
    },
  });
  let res = instance.get("http://127.0.0.1:8080/partido/getPartidosJugados")
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

export async function getPartidosNoJugados() {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      "Authorization": `Bearer ${localStorage.getItem("jwt")}`
    },
  });
  let res = instance.get("http://127.0.0.1:8080/partido/getPartidosNoJugados")
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

export async function getPartidosNoJugadosParaPrediccion() {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      "Authorization": `Bearer ${localStorage.getItem("jwt")}`
    },
  });
  let res = instance.get("http://127.0.0.1:8080/partido/getPartidosNoJugadosParaPrediccion")
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