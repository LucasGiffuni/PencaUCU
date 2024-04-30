import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import HomeComponent from "./HomeComponent";

import { useEffect, useState } from "react";
import LoginComponent from "./LoginComponent";
function App() {


  return (
    <div className="App-Component">
      <BrowserRouter>
        <Routes>
          <Route
          className={"App-Component"}
            path="/login"
            element={
              <LoginComponent />
            }
          />


          <Route
            path="/"
            element={
              <HomeComponent />
            }
          />

       
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
