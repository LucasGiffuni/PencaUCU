import React, { useEffect, useState } from "react";

import MakePrediction from "./MakePredictionComponent";
import Alert from "react-bootstrap/Alert";



const PredictionMatchComponent = (props) => {

    const [dayDate, setDayDate] = useState()
    const [date, setDate] = useState()

    const days = ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'];
    const [modalShow, setModalShow] = useState(false)

    const [clicks, setClicks] = useState(0)
    const [resultPrediction, setResultPrediction] = useState(true);



    function setResultPredictionParent() {
        setResultPrediction(true)
    }

    useEffect(() => {


        let newDate = Date(props.match.fecha)
        console.log(newDate)

        const dateaux = getDateFromString(newDate)

        setDayDate(days[dateaux.getDay()]);
        setDate(dateaux.getDate())


    }, []);


    const getDateFromString = str => {
        const [date, time] = str.split(" ");
        str = `${date}T${time}.000Z`
        return new Date(str);
    };

    const showModal = (id) => {
        setClicks(clicks + 1);
        setModalShow(true);
    }



    return (
        <>

            <div className="Prediction-Match-Component" onClick={() => {
                showModal(props.match.id);
            }}>


                <div className="Prediction-Match-Component-Team1">
                    <img className="Prediction-Match-Component-TeamImage" src={props.match.urlBanderaEquipo1}></img>
                </div>

                <div className="Prediction-Match-Component-Button">
                    <div class="Versus">VS</div>
                </div>

                <div className="Prediction-Match-Component-Team2">
                    <img className="Prediction-Match-Component-TeamImage" src={props.match.urlBanderaEquipo2}></img>

                </div>

                <div className="Prediction-Match-Component-Information">



                    <div className="Prediction-Match-Component-Information-Date">
                        <p>{props.match.fecha}</p>
                    </div>



                    <div className="Prediction-Match-Component-Information-Stage">
                        <p>FASE DE GRUPOS</p>
                    </div>
                </div>
            </div>
            <MakePrediction setMatch={props.match} setShow={modalShow} clicks={clicks} function={setResultPredictionParent} />

           
        </>
    );
}


export default PredictionMatchComponent;