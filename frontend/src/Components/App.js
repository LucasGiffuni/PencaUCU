import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import HomeComponent from "./HomeComponent";

import { useEffect, useState } from "react";
import LoginComponent from "./LoginComponent";
import RegisterComponent from "./RegisterComponent";

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const [email, setEmail] = useState("");

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route
            path="/login"
            element={
              <LoginComponent setLoggedIn={setLoggedIn} setEmail={setEmail} />
            }
          />

          <Route
            path="/register"
            element={
              <RegisterComponent setLoggedIn={setLoggedIn} setEmail={setEmail} />
            }
          />
          <Route
            path="/"
            element={
              <HomeComponent
                email={email}
                loggedIn={loggedIn}
                setLoggedIn={setLoggedIn}
              />
            }
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
