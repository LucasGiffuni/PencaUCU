import { useEffect, useState } from "react";
import {
  BrowserRouter,
  Route,
  Routes,
  Navigate,
  useNavigate,
  redirect,
  Redirect,
} from "react-router-dom";

import HomeComponent from "./HomeComponent";

import LoginComponent from "./LoginComponent";

const MiddlewareRouteComponent = (props) => {
  const [logged, setLogged] = useState(false);

  useEffect(() => {
    const jwt = localStorage.getItem("jwt");

    jwt == null ? setLogged(false) : setLogged(true);
    jwt == "" ? setLogged(false) : setLogged(true);

    console.log(jwt);
  }, []);

  if (logged) {
    console.log("redirecting to home");
    return <HomeComponent />;
  } else {
    console.log("redirecting to login");
    return <LoginComponent />;
  }
};

export default MiddlewareRouteComponent;
