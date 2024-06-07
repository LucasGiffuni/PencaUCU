import React, { useEffect, useState, } from "react";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

import { getTeamDetailByID } from "../../Services/TeamService";



function TeamDetailsComponent(props) {

    const [team, setTeam] = useState(null);

    const [show, setShow] = useState(props.setShow);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        setShow(props.setShow)
        if (props.setShow) {
            fetchDatos();
        }
    }, [props.clicks]);

    const fetchDatos = () => {
        getTeamDetailByID(props.teamId).then((response) => {
            setTeam(response[1])
        })
    };


    if (show) {
        return (
            <>

                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>{team && team.nombre}</Modal.Title>
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