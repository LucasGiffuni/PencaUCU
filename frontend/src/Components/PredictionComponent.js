import React, { useEffect, useState } from "react";
import NavBarComponent from "./NavBarComponent";
import PredictionMatchComponent from "./PredictionMatchComponent";

function PredictionComponent(props) {




    return (
        <div className="Home-Component-Container">
            <NavBarComponent />



            <div className="Prediction-Component-Matchs">
                <PredictionMatchComponent />
            </div>


        </div>
    );
}


export default PredictionComponent;