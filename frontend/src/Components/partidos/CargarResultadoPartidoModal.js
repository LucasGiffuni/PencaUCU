import React, { useEffect, useState, } from "react";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';


import { cargarResultadoPartido } from '../../Services/TeamService';

function CargarResultadoPartidoModal(props) {


    const [show, setShow] = useState(props.setShow);

    const handleClose = () => setShow(false);
    const matchID = props.setMatch.id;

    const [resultadoEquipo1, setResultadoEquipo1] = useState("");
    const [resultadoEquipo2, setResultadoEquipo2] = useState("");





    useEffect(() => {
        setShow(props.setShow)
    }, [props.clicks]);

    const cargarResultado = () => {
        cargarResultadoPartido(matchID, resultadoEquipo1, resultadoEquipo2).then((resultado) => {
            if (resultado[0].code === "200") {
                handleClose();
                setShow(false);
                props.function()
            }
        });
    };

    if (show) {
        return (
            <>
                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>{props.setMatch.nombreEquipo1} vs {props.setMatch.nombreEquipo2}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <label className="login-label" htmlFor="username">
                            {props.setMatch.nombreEquipo1}:
                        </label>
                        <input
                            className="login-input"
                            type="number"
                            min="0"
                            placeholder="Resultado"
                            id="resultadoEquipo1"
                            onChange={(event) => setResultadoEquipo1(event.target.value)}
                            value={resultadoEquipo1}
                        />
                        <hr></hr>
                        <label className="login-label" htmlFor="username">
                            {props.setMatch.nombreEquipo2}:
                        </label>
                        <input
                            className="login-input"
                            type="number"
                            min="0"
                            placeholder="Resultado"
                            id="resultadoEquipo2"
                            onChange={(event) => setResultadoEquipo2(event.target.value)}
                            value={resultadoEquipo2}
                        />
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="primary" onClick={cargarResultado}>
                            Cargar Partido
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
};

export default CargarResultadoPartidoModal;