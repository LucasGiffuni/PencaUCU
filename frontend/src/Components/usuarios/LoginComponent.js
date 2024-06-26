import React from "react";
import { useEffect, useState } from "react";
import {
  getCarreras,
  createUser,
  createAlumno,
  login,
} from "../../Services/UserService";

import { useNavigate } from "react-router-dom";
import Alert from "react-bootstrap/Alert";

import { getTeams } from "../../Services/TeamService";

import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";

const LoginComponent = (props) => {
  const [loginMode, setMode] = useState(true);

  const [successfulLogin, setSuccessfulLogin] = useState(false);
  const [failedLogin, setFailedLogin] = useState(false);

  const [failedRegister, setFailedRegister] = useState(false);

  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 600,
    height: 600,
    bgcolor: "background.paper",
    border: "2px solid #000",
    boxShadow: 24,
    p: 4,
  };

  const [carreras, setCarreras] = useState(null);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [suername, setSurename] = useState("");
  const [mail, setMail] = useState("");
  const [bithDate, setBirthDate] = useState("");
  const [carrera, setCarrera] = useState("");
  const [cedulaIdentidad, setCedulaIdentidad2] = useState("");

  const setCedulaIdentidad = (value) => {
    const valor = value;

    // Eliminar cualquier cosa que no sea un dígito
    value = valor.replace(/[^0-9]/g, "");
    if (value.length > 8) {
      alert("Ingrese una Cedula Valida");
      value = value.slice(0, 8);
    }
    setCedulaIdentidad2(value);
  };

  const [idEquipoCampeon, setIdEquipoCampeon] = useState("");
  const [nombreEquipoCampeon, setNombreEquipoCampeon] = useState("");

  const [idEquipoSubCampeon, setIdEquipoSubCampeon] = useState("");
  const [nombreEquipoSubCampeon, setNombreEquipoSubCampeon] = useState("");

  const [equipos, setEquipos] = useState([]);

  const [openEquipoCampeonModal, setOpenEquipoCampeonModal] =
    React.useState(false);
  const [openEquipoSubCampeonModal, setOpenEquipoSubCampeonModal] =
    React.useState(false);

  const [openSelectorEquiposError, setOpenSelectorEquiposError] =
    React.useState(false);

  const abrirModelEquipoCampeon = () => {
    setOpenEquipoCampeonModal(true);
  };
  const abrirModelEquipoSubCampeon = () => {
    setOpenEquipoSubCampeonModal(true);
  };
  const handleClose = () => {
    setOpenEquipoCampeonModal(false);
    setOpenEquipoSubCampeonModal(false);
  };

  const setEquipoCampeon = (equipo) => {
    if (idEquipoSubCampeon !== equipo.id) {
      setIdEquipoCampeon(equipo.id);
      setNombreEquipoCampeon(equipo.nombre);
      handleClose();
    } else {
      setOpenSelectorEquiposError(true);

      setTimeout(() => {
        setOpenSelectorEquiposError(false);
      }, 2000);

      handleClose();
    }
  };

  const setEquipoSubCampeon = (equipo) => {
    if (idEquipoCampeon !== equipo.id) {
      setIdEquipoSubCampeon(equipo.id);
      setNombreEquipoSubCampeon(equipo.nombre);
      handleClose();
    } else {
      setOpenSelectorEquiposError(true);
      setTimeout(() => {
        setOpenSelectorEquiposError(false);
      }, 2000);
      handleClose();
    }
  };

  const navigate = useNavigate();

  const successfullLoginOrRegister = () => {
    setSuccessfulLogin(true);
    navigate("/home");
  };

  const changeMode = () => {
    setMode(!loginMode);
  };

  useEffect(() => {
    const response2 = getCarreras().then((data) => {
      setCarreras(data[1].carreras);
      setCarrera(data[1].carreras[1])
    });

    const getEquiposResponse = getTeams().then((data) => {
      setEquipos(data[1]);
    });
  }, []);

  const loginUser = () => {
    login(username, password).then((loginResponse) => {
      if (loginResponse[1].response.code === "200") {
        localStorage.setItem("jwt", loginResponse[1].jwt);
        localStorage.setItem("alumno", JSON.stringify(loginResponse[1].alumno));

        successfullLoginOrRegister();
      } else {
        setFailedLogin(true);
        setTimeout(() => {
          setFailedLogin(false);
        }, 2000);
      }
    });
  };

  const createUserInDatabase = () => {
    if (
      (cedulaIdentidad != "" &&
        name != "" &&
        suername != "" &&
        bithDate != "" &&
        mail != "" &&
        carrera != "",
      idEquipoCampeon != "" && idEquipoSubCampeon != "")
    ) {
      createUser(username, password).then((createUserResponse) => {
        if (createUserResponse[1].response.code === "200") {
          createAlumno(
            createUserResponse[1].jwt,
            cedulaIdentidad,
            name,
            suername,
            bithDate,
            mail,
            carrera,
            username,
            idEquipoCampeon,
            idEquipoSubCampeon
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
              idCampeon: idEquipoCampeon,
              idSubcampeon: idEquipoSubCampeon,
            };

            localStorage.setItem("alumno", JSON.stringify(alumno));

            successfullLoginOrRegister();
          });
        } else {
          setFailedRegister(true);
          setTimeout(() => {
            setFailedRegister(false);
          }, 2000);
        }
      });
    } else {
      setFailedRegister(true);
      setTimeout(() => {
        setFailedRegister(false);
      }, 2000);
    }
  };

  const _handleKeyDown = (e) => {
    if (e.key === "Enter") {
      loginUser();
    }
  };

  return (
    <>
      <div>
        {loginMode ? (
          <div>
            <div>
              <form className="Login-Component">
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
                  onKeyDown={_handleKeyDown}
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
                  onKeyDown={_handleKeyDown}
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
            <div>
              <form className="Register-Component-Form">
                <div className="Datos-Basicos-Rgister-Form">
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
                    type="number"
                    min="0:"
                    max="99999999"
                    step="0"
                    required
                    pattern="\d{8}"
                    placeholder="Cedula Identidad"
                    id="CI"
                    onChange={(event) => setCedulaIdentidad(event.target.value)}
                  />

                  <label htmlFor="email">Email</label>
                  <input
                    type="email"
                    placeholder="prueba@example.com"
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
                    className="Register-Component-Selector-Carrera"
                    id="carrera"
                    name="Carrera"
                    onChange={(event) => setCarrera(event.target.value)}
                  >
                    {carreras.map((carrera, i) => {
                      return (
                        <option
                          className="Carreras-Options"
                          value={carrera.idCarrera}
                          key={i}
                        >
                          {carrera.nombreCarrera}
                        </option>
                      );
                    })}
                  </select>

                  <div className="Buttons-Register-Form">
                    <button
                      className="Login-Component-Button"
                      type="button"
                      onClick={createUserInDatabase}
                    >
                      Register
                    </button>
                    <p className="Login-Component-Text">Back to</p>
                    <p
                      className="Login-Component-RegisterText"
                      onClick={changeMode}
                    >
                      Login
                    </p>
                  </div>
                </div>
                <div className="Prediccion-Rgister-Form">
                  <br />
                  <label htmlFor="campeon">
                    Equipo Campeon: {nombreEquipoCampeon}
                  </label>
                  <Button onClick={abrirModelEquipoCampeon}>
                    Seleccionar Campeon
                  </Button>

                  <br />
                  <label htmlFor="campeon">
                    Equipo SubCampeon: {nombreEquipoSubCampeon}
                  </label>
                  <Button onClick={abrirModelEquipoSubCampeon}>
                    Seleccionar Sub-Campeon
                  </Button>
                </div>
              </form>
            </div>
          </div>
        )}
      </div>

      <Modal
        open={openEquipoCampeonModal}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Seleccionar Equipo Campeon
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            <div className="grid-container">
              {equipos &&
                equipos.map((team) => {
                  return (
                    <div
                      className="grid-item"
                      onClick={() => setEquipoCampeon(team)}
                    >
                      <img
                        className="Login-Component-Flags"
                        src={team.urlBandera}
                        alt={team.nombre}
                      />
                    </div>
                  );
                })}
            </div>
          </Typography>
        </Box>
      </Modal>

      <Modal
        open={openEquipoSubCampeonModal}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Seleccionar Equipo Sub Campeon
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            <div className="grid-container">
              {equipos &&
                equipos.map((team) => {
                  return (
                    <div
                      className="grid-item"
                      onClick={() => {
                        setEquipoSubCampeon(team);
                      }}
                    >
                      <img
                        className="Login-Component-Flags"
                        src={team.urlBandera}
                        alt={team.nombre}
                      />
                    </div>
                  );
                })}
            </div>
          </Typography>
        </Box>
      </Modal>

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

      <Alert
        className="Login-Component-Alert"
        key={"selectorEquiposFailed"}
        variant={"danger"}
        show={openSelectorEquiposError}
        dismissible
        onClose={() => setOpenSelectorEquiposError(false)}
      >
        Campeon y Sub-Campeon deben ser distintos
      </Alert>
    </>
  );
};

export default LoginComponent;
