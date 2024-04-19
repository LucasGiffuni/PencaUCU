import IGroup from "../Model/Group";
import ITeam from "../Model/Team";


let Team1: ITeam = {
    teamID: 1,
    name: "Uruguay",
    flagUrl: "https://www.worldometers.info/img/flags/uy-flag.gif",
    groupPoints: 0
}
let Team2: ITeam = {
    teamID: 2,
    name: "Ghana",
    flagUrl: "https://www.worldometers.info/img/flags/uy-flag.gif",
    groupPoints: 0
}
let Team3: ITeam = {
    teamID: 3,
    name: "Portugal",
    flagUrl: "https://www.worldometers.info/img/flags/uy-flag.gif",
    groupPoints: 0
}
let Team4: ITeam = {
    teamID: 4,
    name: "South Korea",
    flagUrl: "https://www.worldometers.info/img/flags/uy-flag.gif",
    groupPoints: 0
}

export function getTeamDataByID(teamID: number) {
    switch (teamID) {
        case 1:
            return Team1;
            break;
        case 2:
            return Team2;
            break;
        case 3:
            return Team3;
            break;
        case 4:
            return Team4;
            break;
        default:
            return Team1;
            break;
    }

}
