import React, { useEffect, useState, } from "react";
import ITeam from "../Model/Team";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

import { getTeamDataByID } from "../Services/TeamService";
import { propTypes } from "react-bootstrap/esm/Image";



function TeamDetailsComponent(props) {

    const [team, setTeam] = useState(null);
    const [teamId, setTeamId] = useState(props.teamId);

    const [show, setShow] = useState(props.setShow);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        fetchDatos();
        setShow(props.setShow)
    }, [props.clicks]);

    const fetchDatos = () => {
        setTeam(getTeamDataByID(props.teamId))
    };


    if (show) {
        return (
            <>

                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>{team.name}</Modal.Title>
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

};

export default TeamDetailsComponent;