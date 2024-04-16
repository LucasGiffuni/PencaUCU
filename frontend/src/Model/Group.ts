import ITeam from "./Team";

interface IGroup{

    groupId: string,
    teams: ITeam[],
}

export default IGroup;