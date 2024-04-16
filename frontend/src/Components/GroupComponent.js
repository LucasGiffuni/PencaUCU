import React, { useState } from "react";
import "../Styles/HomeComponent.css";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";



function GroupComponent(props) {
  const [value, setValue] = useState(props.value);

  return (
    <Card className="Card-Container" border="dark">
      <Card.Title className="Card-Container-Title">{props.name}</Card.Title>
      <ListGroup variant="flush">
        <ListGroup.Item className="Card-Container-Item">
          <div className="Card-Container-Item-CountryName">
            {props.country1}
          </div>
          <div className="Card-Container-Item-CountryPoints">
            <p>80</p>
          </div>
        </ListGroup.Item>
        <ListGroup.Item className="Card-Container-Item">
          {props.country2}
        </ListGroup.Item>
        <ListGroup.Item className="Card-Container-Item">
          {props.country3}
        </ListGroup.Item>
        <ListGroup.Item className="Card-Container-Item">
          {props.country4}
        </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default GroupComponent;
