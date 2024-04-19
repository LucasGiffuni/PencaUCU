import React from "react";
import { useNavigate } from "react-router-dom";

const LoginComponent = (props) => {
  const { loggedIn, email } = props;
  const navigate = useNavigate();

  const onButtonClick = () => {
    console.log("navigate to dashboard");
    navigate("/");
  };
  const moveToRegister = () => {
    console.log("navigate to dashboard");
    navigate("/register");
  };


  return (

    <div>

      <form class="background" >
        <h2 className="Login-Component-Title">Login</h2>

        <label className="login-label" for="username">Username</label>
        <input className="login-input" type="text" placeholder="Username" id="username" />

        <label className="login-label" for="password">Password</label>
        <input className="login-input" type="password" placeholder="Password" id="password" />

        <button
          className="Login-Component-Button"
          onClick={onButtonClick}>Log In</button>


        <p className="Login-Component-Text">Not a user?</p>
        <p className="Login-Component-RegisterText" onClick={moveToRegister}>Register</p>
      </form>
    </div>
  );
};

export default LoginComponent;
