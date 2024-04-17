import React, { Component } from "react";
import "../Styles/HomeComponent.css";
import GroupComponent from "./GroupComponent";
import Accordion from "react-bootstrap/Accordion";
import MatchDisplayComponent from "./MatchDisplayComponent";
import { getTeamsByGroup } from "../Services/GroupService";

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

          <Accordion defaultActiveKey="0" flush className="Accordion-Groups">
            <Accordion.Item eventKey="0">
              <Accordion.Header>Group Phase</Accordion.Header>
              <Accordion.Body>
                <div className="Groups-Component-Container">
                  <GroupComponent
                    group={getTeamsByGroup("A")}
                  />
                  <GroupComponent
                    group={getTeamsByGroup("A")}
                  />
                  <GroupComponent
                    group={getTeamsByGroup("A")}
                  />
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
