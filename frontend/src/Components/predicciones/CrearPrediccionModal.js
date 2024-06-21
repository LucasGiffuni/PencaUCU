import React, { useEffect, useState, } from "react";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

import { propTypes } from "react-bootstrap/esm/Image";

import { crearPrediccion, obtenerPrediccion, modificarPrediccion } from '../../Services/PredicctionService'


function CrearPrediccionModal(props) {

    const [match, setMatch] = useState(null);

    const [show, setShow] = useState(props.setShow);

    const handleClose = () => setShow(false);

    const [resultadoEquipo1, setResultadoEquipo1] = useState("");
    const [resultadoEquipo2, setResultadoEquipo2] = useState("");
    const [resultadoExistente, setResultadoExiste] = useState(false);





    useEffect(() => {
        setShow(props.setShow)
        obtenerPrediccion(props.setMatch.id, JSON.parse(localStorage.getItem("alumno")).userId).then((resultado) => {
          

            if (resultado[1] != null) {
                setResultadoEquipo1(resultado[1].resultadoEquipo1)
                setResultadoEquipo2(resultado[1].resultadoEquipo2)
                setResultadoExiste(true);
            }
        })

    }, [props.clicks]);

    const MakePrediction = () => {
        if (!resultadoExistente) {
            crearPrediccion(props.setMatch.id, JSON.parse(localStorage.getItem("alumno")).userId, resultadoEquipo1, resultadoEquipo2).then((resultado) => {
                if (resultado[0].code === "200") {
                    handleClose();
                    setShow(false);
                    props.function()
                    setResultadoExiste(true)
                }

            });
        }else{
            modificarPrediccion(props.setMatch.id, JSON.parse(localStorage.getItem("alumno")).userId, resultadoEquipo1, resultadoEquipo2).then((resultado) => {
                if (resultado[0].code === "200") {
                    handleClose();
                    setShow(false);
                    props.function()
                }

            });
            
        }
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

                        <Button variant="primary" onClick={MakePrediction}>
                            Cargar Prediccion
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }

};

export default CrearPrediccionModal;