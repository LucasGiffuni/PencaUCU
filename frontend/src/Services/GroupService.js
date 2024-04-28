import axios from "axios";


export async function getTeamsByGroup(groupID) {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      "Authorization": `Bearer ${localStorage.getItem("jwt")}`
    },
  });
  let res = instance.get("http://127.0.0.1:8080/grupo/" + groupID + "/getGrupo")
    .then(response => {
      let serviceResponse = [];
      serviceResponse[0] = response.data.defaultResponse;
      serviceResponse[1] = response.data.grupo;

      return serviceResponse
    })
    .catch(error => {
      let serviceResponse = [];
      serviceResponse[0] = error;
      return serviceResponse
    });
  return res;

}