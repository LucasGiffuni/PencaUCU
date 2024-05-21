import React, { useEffect, useState } from "react";
import NavBarComponent from "./NavBarComponent";
import UserComponent from "./UserComponent";

import { getRankingAlumnos } from '../Services/UserService'


function RankingComponent(props) {

    const [ranking, setRanking] = useState([]);

    useEffect(() => {
        const getRankingResponse = getRankingAlumnos().then((data) => {
            setRanking(data[0])
            console.log(setRanking)
        })

    }, []);


    return (
        <div className="Home-Component-Container">
            <NavBarComponent />
            <h1 className="Ranking-Header">Ranking</h1>
            {ranking && ranking.map((alumno, i) => {
                return (
                    <div className="User-Container">
                        <h2>Javier Moreno</h2>
                        <h2>0 pts.</h2>
                    </div>
                );
            })}
        </div>
    );
}


export default RankingComponent;