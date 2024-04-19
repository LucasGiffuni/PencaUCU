import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import reportWebVitals from "./reportWebVitals";
import HomeComponent from "./Components/HomeComponent";
import { BrowserRouter as Router } from "react-router-dom";
import App from "./Components/App";
import "bootstrap/dist/css/bootstrap.min.css";


const root = ReactDOM.createRoot(document.getElementById("root"));


root.render(
  <React.StrictMode>
    <App/>
  </React.StrictMode>
);

reportWebVitals();
