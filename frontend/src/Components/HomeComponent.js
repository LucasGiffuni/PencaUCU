import React, { Component } from "react";
import "../Styles/HomeComponent.css";
import GroupComponent from "./GroupComponent";
import Accordion from "react-bootstrap/Accordion";
import MatchDisplayComponent from "./MatchDisplayComponent";
import { getTeamsByGroup } from "../Services/GroupService";

import NavBarComponent from "./NavBarComponent";

import { Route, Routes } from "react-router-dom";

import LoginComponent from "./LoginComponent";

class HomeComponent extends Component {
  render() {
    return (
      <>
     
        <div className="Home-Component-Container">
          <NavBarComponent />

          <div className="Home-Component-MatchDisplayComponent">
            <MatchDisplayComponent />
          </div>

          <Accordion className="Accordion-Groups">
            <Accordion.Item eventKey="1">
              <Accordion.Header>Group Phase</Accordion.Header>
              <Accordion.Body>
                <div className="Groups-Component-Container">
                  <GroupComponent group={getTeamsByGroup("A")} />
                  <GroupComponent group={getTeamsByGroup("A")} />
                  <GroupComponent group={getTeamsByGroup("A")} />
                </div>
              </Accordion.Body>
            </Accordion.Item>
          </Accordion>
        </div>
      </>
    );
  }
}

export default HomeComponent;
