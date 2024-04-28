import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import HomeComponent from "./HomeComponent";

import { useEffect, useState } from "react";
import LoginComponent from "./LoginComponent";
import TournamentDashboard from "./TournametDashboard";

function App() {


  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route
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

          <Route
            path="/Tournament"
            element={
              <TournamentDashboard />
            }
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
