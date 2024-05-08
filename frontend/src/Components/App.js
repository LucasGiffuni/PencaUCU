import React from "react";
import {
  BrowserRouter, Route, Routes, Navigate,useNavigate 
} from "react-router-dom";

import HomeComponent from "./HomeComponent";

import { useEffect, useState } from "react";
import LoginComponent from "./LoginComponent";

const App = (props) => {
  const [jwt, setJwt] = useState("");


  return (
    <div className="App-Component">
      <BrowserRouter>
        <Routes>
          <Route
            path="/home"
            element={
              <HomeComponent />
            }
          />
          <Route
            className={"App-Component"}
            path="/login"
            element={
              <LoginComponent />
            }
          />

          <Route
            className={"App-Component"}
            path="/"
            render={() => (
              localStorage.getItem("jwt") === null ? (
                <Navigate replace to="/login" />
              ) : (
                <Navigate replace to="/home" />
              )
            )}
          />


        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
