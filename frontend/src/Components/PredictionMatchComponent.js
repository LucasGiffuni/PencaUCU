import React, { useEffect, useState } from "react";

const PredictionMatchComponent = (props) => {

    const [dayDate, setDayDate] = useState()
    const [date, setDate] = useState()

    const days = ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'];


    useEffect(() => {


        const dateaux = getDateFromString('2024-11-22 17:00:00')

        setDayDate(days[dateaux.getDay()]);
        setDate(dateaux.getDate())


    }, []);


    const getDateFromString = str => {
        const [date, time] = str.split(" ");
        // reformat string into YYYY-MM-DDTHH:mm:ss.sssZ
        str = `${date}T${time}.000Z`
        return new Date(str);
    };



    return (
        <div className="Prediction-Match-Component">

            <div className="Prediction-Match-Component-Team1">
                <img className="Prediction-Match-Component-TeamImage" src="https://www.worldometers.info/img/flags/uy-flag.gif"></img>
            </div>

            <div className="Prediction-Match-Component-Button">
                <div class="Versus">VS</div>
            </div>

            <div className="Prediction-Match-Component-Team2">
                <img className="Prediction-Match-Component-TeamImage" src="https://www.worldometers.info/img/flags/br-flag.gif"></img>

            </div>

            <div className="Prediction-Match-Component-Information">



                <div className="Prediction-Match-Component-Information-Date">
                    <p>{dayDate} {date}</p>
                </div>



                <div className="Prediction-Match-Component-Information-Stage">
                    <p>FASE DE GRUPOS</p>
                </div>
            </div>
        </div>
    );
}


export default PredictionMatchComponent;