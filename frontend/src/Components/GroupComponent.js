import React, { useEffect, useState } from "react";
import "../Styles/HomeComponent.css";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import IGroup from "../Model/Group";

import { getTeamsByGroup } from "../Services/GroupService";

import TeamDetailsComponent from "./TeamDetailsComponent";

function GroupComponent(props) {


  const [group, setGroup] = useState(null);
  const groupId = group ? group.groupId : null;
  const teams = group ? group.teams : null;


  const [selectedCountry, setSelectedCountry] = useState(0)
  const [modalShow, setModalShow] = useState(false)

  const [clicks, setClicks] = useState(0)

  useEffect(() => {
    fetchDatos();
  }, [selectedCountry]);

  const fetchDatos = () => {
    setGroup(getTeamsByGroup("A"))
  };

  const showModal = (teamId) => {
    setClicks(clicks + 1);
    setSelectedCountry(teamId);
    setModalShow(true);
  }



  return (
    <>
      <Card className="Card-Container" border="dark">
        <Card.Title className="Card-Container-Title">{groupId}</Card.Title>
        <ListGroup variant="flush">
          {teams?.map((team, i) => {
            return (
              <ListGroup.Item className="Card-Container-Item" onClick={() => showModal(team.teamID)} key={i}>
                <div className="Card-Container-Item-CountryFlag">
                  <img className="Card-Container-Item-Flag" src={team.flagUrl}></img>
                </div>
                <div className="Card-Container-Item-CountryName">
                  {team.name}
                </div>
                <div className="Card-Container-Item-CountryPoints">
                  <p> {team.groupPoints}</p>
                </div>
              </ListGroup.Item>
            );
          })}

        </ListGroup>
      </Card>

      <TeamDetailsComponent teamId={selectedCountry} setShow={modalShow} clicks={clicks} />
    </>
  );
};

export default GroupComponent;