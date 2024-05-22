import React, { useEffect, useState } from "react";
import NavBarComponent from "./NavBarComponent";

import { getRankingAlumnos } from '../Services/UserService'


function RankingComponent(props) {



    

    const [ranking, setRanking] = useState([]);
    const [actualUser, setActualUser] = useState('');

    useEffect(() => {
        const getRankingResponse = getRankingAlumnos().then((data) => {
            setRanking(data[1])
            console.log(ranking)
        })

        setActualUser()
        console.log()
    }, []);

    return (
        <div className="Home-Component-Container">
            <NavBarComponent />
            <h1 className="Ranking-Header">Ranking</h1>

            {ranking && ranking.map((alumno, i) => {
                if (JSON.parse(localStorage.getItem("alumno")).userId === alumno.userId) {
                    return (
                        <div className="User-Container-CurrentUser" key={i}>
                            <h2>{alumno.nombre} </h2>
                            <h2>{alumno.puntaje} pts.</h2>
                        </div>
                    )
                } else {
                    return (
                        <div className="User-Container" key={i}>
                            <h2>{alumno.nombre} </h2>
                            <h2>{alumno.puntaje} pts.</h2>
                        </div>
                    )
                }

            })}
        </div>
    );
}


export default RankingComponent;