import React from "react";
import { useNavigate } from "react-router-dom";

const LoginComponent = (props) => {
  const { loggedIn, email } = props;
  const navigate = useNavigate();

  const onButtonClick = () => {
    console.log("navigate to dashboard");
    navigate("/");
  };

  return (
    
    <div>
   
      <form class="background" > 
        <h2 className="Login-Component-Title">Login</h2>

        <label for="username">Username</label>
        <input type="text" placeholder="Email or Phone" id="username" />

        <label for="password">Password</label>
        <input type="password" placeholder="Password" id="password" />

        <button onClick={onButtonClick}>Log In</button>
        
      </form>
    </div>
  );
};

export default LoginComponent;
