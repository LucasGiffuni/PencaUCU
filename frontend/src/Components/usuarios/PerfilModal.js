import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";

import { cargarResultadoPartido } from "../../Services/TeamService";

function PerfilModal(props) {
  const [show, setShow] = useState(props.setShow);

  const handleClose = () => setShow(false);

  useEffect(() => {
    setShow(props.setShow);
  }, [props.clicks]);

  if (show) {
    return (
      <>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Mis Datos</Modal.Title>
          </Modal.Header>
          <Modal.Body></Modal.Body>
          <Modal.Footer>
            <Button variant="primary">Save Changes</Button>
          </Modal.Footer>
        </Modal>
      </>
    );
  }
}

export default PerfilModal;
