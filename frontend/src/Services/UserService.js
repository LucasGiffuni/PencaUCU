import axios from "axios";


export async function getCarreras() {
    const instance = axios.create({
        baseURL: 'http://127.0.0.1:8080',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json'
        }
    });
    let res = instance.get(`/public/obtenerCarreras`)
        .then(response => {
            let serviceResponse = [];
            serviceResponse[0] = response.status;
            serviceResponse[1] = response.data;
            return serviceResponse
        })
        .catch(error => {
            let serviceResponse = [];
            serviceResponse[0] = error.code;
            serviceResponse[1] = error.response.status;
            return serviceResponse
        });
    return res;

}

export async function login(user, clave) {
    const instance = axios.create({
        baseURL: 'http://127.0.0.1:8080',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json'
        }
    });
    let res = instance.post("http://127.0.0.1:8080/public/login?user=" +
        user.trim() +
        "&clave=" +
        clave.trim())
        .then(response => {
            let serviceResponse = [];
            serviceResponse[0] = response.status;
            serviceResponse[1] = response.data;
            return serviceResponse
        })
        .catch(error => {
            let serviceResponse = [];
            serviceResponse[0] = error.code;
            serviceResponse[1] = error.response.status;
            return serviceResponse
        });
    return res;

}


export async function createUser(user, clave) {
    const instance = axios.create({
        baseURL: 'http://127.0.0.1:8080',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
        }
    });
    let res = instance.post("http://127.0.0.1:8080/public/register?user=" +
        user.trim() +
        "&clave=" +
        clave.trim())
        .then(response => {
            let serviceResponse = [];
            serviceResponse[0] = response.status;
            serviceResponse[1] = response.data;
            return serviceResponse
        })
        .catch(error => {
            let serviceResponse = [];
            serviceResponse[0] = error.code;
            serviceResponse[1] = error.response.status;
            return serviceResponse
        });
    return res;
}



export async function createAlumno(token, cedulaIdentidad, nombre, apellido, fechaNacimiento, email, idCarrera, userId,idCampeon,idSubcampeon) {

    const options = {
        method: 'POST',
        body: JSON.stringify({
            cedulaIdentidad: cedulaIdentidad.trim(),
            nombre: nombre.trim(),
            apellido: apellido.trim(),
            fechaNacimiento: fechaNacimiento,
            email: email,
            idCarrera: idCarrera,
            userId: userId,
            idCampeon: idCampeon,
            idSubcampeon:idSubcampeon
        }),
        headers: {
            'Access-Control-Allow-Origin': '*',
            "Authorization": `Bearer ${token}`,
            'Content-Type': 'application/json'

        },
    };
    fetch("http://127.0.0.1:8080/alumno/crearAlumno", options)

}


export async function getRankingAlumnos() {
    const instance = axios.create({
        baseURL: 'http://127.0.0.1:8080',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        }
    });
    let res = instance.get(`/alumno/getRankingAlumnos`)
        .then(response => {
            let serviceResponse = [];
            serviceResponse[0] = response.status;
            serviceResponse[1] = response.data;
            return serviceResponse
        })
        .catch(error => {
            let serviceResponse = [];
            serviceResponse[0] = error.code;
            serviceResponse[1] = error.response.status;
            return serviceResponse
        });
    return res;

}
