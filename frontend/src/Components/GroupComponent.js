import React, { useState } from "react";
import "../Styles/HomeComponent.css";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';



function GroupComponent(props) {
  const [showCountry1, setshowCountry1] = useState(false);
  const [showCountry2, setshowCountry2] = useState(false);
  const [showCountry3, setshowCountry3] = useState(false);
  const [showCountry4, setshowCountry4] = useState(false);

  const handleClose = () => {
    setshowCountry1(false);
    setshowCountry2(false);
    setshowCountry3(false);
    setshowCountry4(false);


  };
  const handleCloseCountr1 = () => setshowCountry1(true);
  const handleCloseCountr2 = () => setshowCountry2(true);
  const handleCloseCountr3 = () => setshowCountry3(true);
  const handleCloseCountr4 = () => setshowCountry4(true);

  return (
    <>
      <Card className="Card-Container" border="dark">
        <Card.Title className="Card-Container-Title">{props.name}</Card.Title>
        <ListGroup variant="flush">

          <ListGroup.Item className="Card-Container-Item" onClick={handleCloseCountr1}>
            <div className="Card-Container-Item-CountryFlag">
              <img className="Card-Container-Item-Flag" src={props.urlBandera1}></img>
            </div>
            <div className="Card-Container-Item-CountryName">
              {props.country1}
            </div>
            <div className="Card-Container-Item-CountryPoints">
              <p>80</p>
            </div>
          </ListGroup.Item>

          <ListGroup.Item className="Card-Container-Item" onClick={handleCloseCountr2}>
            <div className="Card-Container-Item-CountryFlag">
              <img className="Card-Container-Item-Flag" src={props.urlBandera2}></img>
            </div>
            <div className="Card-Container-Item-CountryName">
              {props.country2}
            </div>
            <div className="Card-Container-Item-CountryPoints">
              <p>80</p>
            </div>
          </ListGroup.Item>

          <ListGroup.Item className="Card-Container-Item" onClick={handleCloseCountr3}>
            <div className="Card-Container-Item-CountryFlag">
              <img className="Card-Container-Item-Flag" src={props.urlBandera3}></img>
            </div>
            <div className="Card-Container-Item-CountryName">
              {props.country3}
            </div>
            <div className="Card-Container-Item-CountryPoints">
              <p>80</p>
            </div>
          </ListGroup.Item>

          <ListGroup.Item className="Card-Container-Item" onClick={handleCloseCountr4}>
            <div className="Card-Container-Item-CountryFlag">
              <img className="Card-Container-Item-Flag" src={props.urlBandera4}></img>
            </div>
            <div className="Card-Container-Item-CountryName">
              {props.country4}
            </div>
            <div className="Card-Container-Item-CountryPoints">
              <p>80</p>
            </div>
          </ListGroup.Item>
        </ListGroup>
      </Card>

      <Modal show={showCountry1} onHide={handleCloseCountr1}>
        <Modal.Header >
          <Modal.Title>{props.country1}</Modal.Title>
        </Modal.Header>
        <Modal.Body >

          <h4>Historial:</h4>

          <div class="Country-Matches-Container">
            <div class="left">
              <h5>Uruguay</h5>
            </div>
            <div class="middle">
              <h3>2 : 0</h3>
            </div>
            <div class="right">
              <h5>{props.country1}</h5>
            </div>
          </div>

          <hr />

          <div class="Country-Matches-Container">
            <div class="left">
              <h5>Colombia</h5>
            </div>
            <div class="middle">
              <h3>2 : 2</h3>
            </div>
            <div class="right">
              <h5>{props.country1}</h5>
            </div>
          </div>

        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showCountry2} onHide={handleCloseCountr2}>
        <Modal.Header >
          <Modal.Title>{props.country2}</Modal.Title>
        </Modal.Header>
        <Modal.Body>Woohoo, you are reading this text in a modal!</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showCountry3} onHide={handleCloseCountr3}>
        <Modal.Header >
          <Modal.Title>{props.country3}</Modal.Title>
        </Modal.Header>
        <Modal.Body>Woohoo, you are reading this text in a modal!</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showCountry4} onHide={handleCloseCountr4}>
        <Modal.Header >
          <Modal.Title>{props.country4}</Modal.Title>
        </Modal.Header>
        <Modal.Body>Woohoo, you are reading this text in a modal!</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default GroupComponent;
