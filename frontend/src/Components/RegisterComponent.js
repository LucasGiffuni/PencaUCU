import React from "react";
import { useNavigate } from "react-router-dom";

const RegisterComponent = (props) => {
    const { loggedIn, email } = props;
    const navigate = useNavigate();



    const moveToLogin = () => {
        console.log("navigate to dashboard");
        navigate("/login");
    };

    return (

        <div>

            <form class="Register-Component-Form" >
                <h2 className="Login-Component-Title">Register</h2>

                <label for="username">Username</label>
                <input type="text" placeholder="Username" id="username" />

                <label for="password">Password</label>
                <input type="password" placeholder="Password" id="password" />
                <hr/>
                <p>Datos Alumno:</p>
                <label for="nombre">Nombre</label>
                <input type="Text" placeholder="Nombre" id="nombre" />

                <label for="apellido">Apellido</label>
                <input type="Text" placeholder="Apellido" id="apellido" />

                <label for="email">Email</label>
                <input type="Text" placeholder="Email" id="email" />

                <label for="fnac">Fecha Nacimiento</label>
                <input type="Date" placeholder="Date" id="date" />

                <label for="carrera">Carrera</label>
                <select id="carrera" name="Carrera">
                    <option value="volvo">Volvo</option>
                    <option value="saab">Saab</option>
                    <option value="fiat">Fiat</option>
                    <option value="audi">Audi</option>
                </select>

                <button
                    className="Login-Component-Button"
                    onClick={moveToLogin}>Register</button>


                <p className="Login-Component-Text">Back to</p>
                <p className="Login-Component-RegisterText" onClick={moveToLogin}>Login</p>
            </form>
        </div>
    );
};

export default RegisterComponent;
