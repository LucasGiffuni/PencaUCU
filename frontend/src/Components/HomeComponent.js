import React, { Component } from "react";
import "../Styles/HomeComponent.css";
import GroupComponent from "./GroupComponent";
import Accordion from "react-bootstrap/Accordion";
import { getTeamsByGroup } from "../Services/GroupService";

import MatchDisplayComponent from "./MatchDisplayComponent";

import NavBarComponent from "./NavBarComponent";
class HomeComponent extends Component {
  render() {
    return (
      <>
        <div className="Home-Component-Container">
          <NavBarComponent />

          <div className="Home-Component-MatchDisplayComponent">
            <MatchDisplayComponent />
          </div>

          <div>
          </div>

          <Accordion className="Accordion-Groups">
            <Accordion.Item eventKey="1">
              <Accordion.Header>Fase de Grupos</Accordion.Header>
              <Accordion.Body>
                <div className="Groups-Component-Container">
                  <GroupComponent groupID={"A"} />
                  <GroupComponent groupID={"B"} />
                  <GroupComponent groupID={"C"} />
                  <GroupComponent groupID={"D"} />
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
