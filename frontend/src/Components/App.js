import React from "react";
import {
  BrowserRouter,
  Route,
  Routes,
  Navigate,
  useNavigate,
} from "react-router-dom";

import HomeComponent from "./HomeComponent";

import { useEffect, useState } from "react";
import LoginComponent from "./LoginComponent";

import MiddlewareRouteComponent from "./MiddlewareRouteComponent";
import PredictionComponent from "./PredictionComponent";
import RankingComponent from "./RankingComponent";

const App = (props) => {

  return (
    <div className="App-Component">
      <BrowserRouter>
        <Routes>
          <Route
            className={"App-Component"}
            path="/home"
            element={<HomeComponent />}
          />

          <Route
            className={"App-Component"}
            path="/login"
            element={<LoginComponent />}
          />

          <Route
            className={"App-Component"}
            path="/predicciones"
            element={<PredictionComponent />}
          />

          <Route
            className={"App-Component"}
            path="/ranking"
            element={<RankingComponent />}
          />

     

          <Route
            className={"App-Component"}
            path="/"
            element={<LoginComponent />}
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;
