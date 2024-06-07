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

import LoginComponent from "./usuarios/LoginComponent";
const MiddlewareRouteComponent = (props) => {
  const [logged, setLogged] = useState(false);

  useEffect(() => {
    const jwt = localStorage.getItem("jwt");

    jwt == null ? setLogged(false) : setLogged(true);
    jwt == "" ? setLogged(false) : setLogged(true);

  }, []);

  if (logged) {
    return <HomeComponent />;
  } else {
    return <LoginComponent />;
  }
};

export default MiddlewareRouteComponent;
