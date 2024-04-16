import React, { Component } from "react";
import "../Styles/HomeComponent.css";
import GroupComponent from "./GroupComponent";
import Accordion from "react-bootstrap/Accordion";

import MatchDisplayComponent from "./MatchDisplayComponent";

import { getTeamsByGroup } from "../Services/GroupService";

class HomeComponent extends Component {
  render() {

    console.log(getTeamsByGroup("A"))
    return (
      <div className="Home-Component-Container">
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
                  name={"B Group"}
                  country1={"Inglaterra"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/uk-flag.gif"
                  }
                  country2={"Iran"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/ir-flag.gif"
                  }
                  country3={"Estados Unidos"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/us-flag.gif"
                  }
                  country4={"Gales"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/ir-flag.gif"
                  }
                />

                <GroupComponent
                  name={"C Group"}
                  country1={"Argentina"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/ar-flag.gif"
                  }
                  country2={"Arabia Saudita"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/sa-flag.gif"
                  }
                  country3={"Mexico"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/mx-flag.gif"
                  }
                  country4={"Polonia"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/pl-flag.gif"
                  }
                />

                <GroupComponent
                  name={"D Group"}
                  country1={"Francia"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/fr-flag.gif"
                  }
                  country2={"Australia"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/au-flag.gif"
                  }
                  country3={"Dinamarca"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/da-flag.gif"
                  }
                  country4={"Tunez"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/ts-flag.gif"
                  }
                />

                <GroupComponent
                  name={"E Group"}
                  country1={"España"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/sp-flag.gif"
                  }
                  country2={"Costa Rica"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/cr-flag.gif"
                  }
                  country3={"Alemania"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/gm-flag.gif"
                  }
                  country4={"Japon"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/gt-flag.gif"
                  }
                />

                <GroupComponent
                  name={"F Group"}
                  country1={"Belgica"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/be-flag.gif"
                  }
                  country2={"Canada"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/ca-flag.gif"
                  }
                  country3={"Marruecos"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/mo-flag.gif"
                  }
                  country4={"Croacia"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/hr-flag.gif"
                  }
                />

                <GroupComponent
                  name={"G Group"}
                  country1={"Brasil"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/br-flag.gif"
                  }
                  country2={"Serbia"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/ri-flag.gif"
                  }
                  country3={"Suiza"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/sz-flag.gif"
                  }
                  country4={"Camerun"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/cm-flag.gif"
                  }
                />

                <GroupComponent
                  name={"H Group"}
                  country1={"Portugal"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/po-flag.gif"
                  }
                  country2={"Ghana"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/gh-flag.gif"
                  }
                  country3={"Uruguay"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/uy-flag.gif"
                  }
                  country4={"Korea"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/ks-flag.gif"
                  }
                />

                <GroupComponent
                  name={"I Group"}
                  country1={"PLACEHOLDER"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country2={"PLACEHOLDER"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country3={"PLACEHOLDER"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country4={"PLACEHOLDER"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                />

                <GroupComponent
                  name={"J Group"}
                  country1={"PLACEHOLDER"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country2={"PLACEHOLDER"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country3={"PLACEHOLDER"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country4={"PLACEHOLDER"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                />

                <GroupComponent
                  name={"K Group"}
                  country1={"PLACEHOLDER"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country2={"PLACEHOLDER"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/ukak-flag.gif"
                  }
                  country3={"PLACEHOLDER"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country4={"PLACEHOLDER"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                />

                <GroupComponent
                  name={"L Group"}
                  country1={"PLACEHOLDER"}
                  urlBandera1={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country2={"PLACEHOLDER"}
                  urlBandera2={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country3={"PLACEHOLDER"}
                  urlBandera3={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                  country4={"PLACEHOLDER"}
                  urlBandera4={
                    "https://www.worldometers.info/img/flags/ka-flag.gif"
                  }
                />
              </div>
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
      </div>
    );
  }
}

export default HomeComponent;
