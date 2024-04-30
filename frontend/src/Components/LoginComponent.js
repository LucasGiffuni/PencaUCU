import React from "react";
import { useEffect, useState } from "react";
import {
  getCarreras,
  createUser,
  createAlumno,
  login,
} from "../Services/UserService";

import { useNavigate } from "react-router-dom";
import Alert from "react-bootstrap/Alert";

import CustomDropdown from "./CustomDropdown";

const LoginComponent = (props) => {
  const { loggedIn, email } = props;

  const [loginMode, setMode] = useState(true);

  const [successfulLogin, setSuccessfulLogin] = useState(false);
  const [failedLogin, setFailedLogin] = useState(false);
  const [loginResponseData, loginRegisterResponseData] = useState(false);

  const [successfulRegister, setSuccessfulRegister] = useState(false);
  const [failedRegister, setFailedRegister] = useState(false);
  const [registerResponseData, setRegisterResponseData] = useState(false);

  const data = [
    {
      id: "1",
      name: "Uruguay",
      imageUrl: "https://www.worldometers.info/img/flags/uy-flag.gif",
    },
  ];

  const [carreras, setCarreras] = useState(null);
  const [carrerasFetched, setCarrerasFetched] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [suername, setSurename] = useState("");
  const [mail, setMail] = useState("");
  const [bithDate, setBirthDate] = useState("");
  const [carrera, setCarrera] = useState("");
  const [cedulaIdentidad, setCedulaIdentidad] = useState("");

  const navigate = useNavigate();

  const successfullLoginOrRegister = () => {
    setSuccessfulLogin(true);
    navigate("/");
  };

  const changeMode = () => {
    setMode(!loginMode);
  };

  const handleSelect = (id) => {
    console.log(`Selected item with id ${id}`);
  };

  useEffect(() => {
    const response2 = getCarreras().then((data) => {
      setCarreras(data[1].carreras);
      setCarrerasFetched(true);
    });
  }, []);

  const loginUser = () => {
    login(username, password).then((loginResponse) => {
      if (loginResponse[1].response.code == "200") {
        localStorage.setItem("jwt", loginResponse[1].jwt);
        localStorage.setItem("alumno", JSON.stringify(loginResponse[1].alumno));
        successfullLoginOrRegister();
      } else {
        setFailedLogin(true);
      }
    });
  };

  const createUserInDatabase = () => {
    createUser(username, password).then((createUserResponse) => {
      if (createUserResponse[1].response.code == "200") {
        createAlumno(
          createUserResponse[1].jwt,
          cedulaIdentidad,
          name,
          suername,
          bithDate,
          mail,
          carrera,
          username
        ).then((data) => {
          localStorage.setItem("jwt", createUserResponse[1].jwt);

          let alumno = {
            cedulaIdentidad: cedulaIdentidad,
            nombre: name,
            apellido: suername,
            fechaNacimiento: bithDate,
            email: mail,
            idCarrera: carrera,
            userId: username,
          };

          localStorage.setItem("alumno", JSON.stringify(alumno));

          successfullLoginOrRegister();
        });
      } else {
        setRegisterResponseData(createUserResponse[1].response.description);
        setFailedRegister(true);
      }
    });
  };

  return (
    <>
      <div>
        {loginMode ? (
          <div>
            <div>
              <form class="Login-Component">
                <h2 className="Login-Component-Title">Login</h2>

                <label className="login-label" htmlFor="username">
                  Username
                </label>
                <input
                  className="login-input"
                  type="text"
                  placeholder="Username"
                  id="username"
                  onChange={(event) => setUsername(event.target.value)}
                />

                <label className="login-label" htmlFor="password">
                  Password
                </label>
                <input
                  className="login-input"
                  type="password"
                  placeholder="Password"
                  id="password"
                  onChange={(event) => setPassword(event.target.value)}
                />

                <button
                  className="Login-Component-Button"
                  type="button"
                  onClick={loginUser}
                >
                  Log In
                </button>

                <p className="Login-Component-Text">Not a user?</p>
                <p
                  className="Login-Component-RegisterText"
                  onClick={changeMode}
                >
                  Register
                </p>
              </form>
            </div>
          </div>
        ) : (
          <div>
            <form class="Register-Component-Form">
              <h2 className="Login-Component-Title">Register</h2>

              <label htmlFor="username">Username</label>
              <input
                type="text"
                placeholder="Username"
                id="username"
                onChange={(event) => setUsername(event.target.value)}
              />

              <label htmlFor="password">Password</label>
              <input
                type="password"
                placeholder="Password"
                id="password"
                onChange={(event) => setPassword(event.target.value)}
              />
              <hr />
              <p>Datos Alumno:</p>
              <label htmlFor="nombre">Nombre</label>
              <input
                type="Text"
                placeholder="Nombre"
                id="nombre"
                onChange={(event) => setName(event.target.value)}
              />

              <label htmlFor="apellido">Apellido</label>
              <input
                type="Text"
                placeholder="Apellido"
                id="apellido"
                onChange={(event) => setSurename(event.target.value)}
              />

              <label htmlFor="cedulaIdentidad">Cedula Identidad</label>
              <input
                type="Text"
                placeholder="Cedula Identidad"
                id="CI"
                onChange={(event) => setCedulaIdentidad(event.target.value)}
              />

              <label htmlFor="email">Email</label>
              <input
                type="Text"
                placeholder="Email"
                id="email"
                onChange={(event) => setMail(event.target.value)}
              />

              <label htmlFor="fnac">Fecha Nacimiento</label>
              <input
                type="Date"
                placeholder="Date"
                id="date"
                data-date-format="YYYY DD MM"
                onChange={(event) => setBirthDate(event.target.value)}
              />

              <label htmlFor="carrera">Carrera</label>
              <select
                id="carrera"
                name="Carrera"
                onChange={(event) => setCarrera(event.target.value)}
              >
                {carreras.map((carrera, i) => {
                  return (
                    <option value={carrera.idCarrera} key={i}>
                      {carrera.nombreCarrera}
                    </option>
                  );
                })}
              </select>

              <label htmlFor="campeon">Equipo Campeon</label>
              <CustomDropdown />

              <label htmlFor="campeon">Equipo SubCampeon</label>
              <CustomDropdown />

              <button
                className="Login-Component-Button"
                type="button"
                onClick={createUserInDatabase}
              >
                Register
              </button>
              <p className="Login-Component-Text">Back to</p>
              <p className="Login-Component-RegisterText" onClick={changeMode}>
                Login
              </p>
            </form>
          </div>
        )}
      </div>

      <Alert key={"success"} variant={"success"} show={successfulLogin}>
        This is a you like.
      </Alert>

      <Alert
        className="Login-Component-Alert"
        key={"loginFailed"}
        variant={"danger"}
        show={failedLogin}
        dismissible
        onClose={() => setFailedLogin(false)}
      >
        Error during login process
      </Alert>

      <Alert
        className="Login-Component-Alert"
        key={"registerFailed"}
        variant={"danger"}
        show={failedRegister}
        dismissible
        onClose={() => setFailedRegister(false)}
      >
        Error during register process
      </Alert>
    </>
  );
};

export default LoginComponent;
