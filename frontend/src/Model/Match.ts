interface IMatch{
    matchId: number,
    teamId1: number,
    teamScore1: number,
    teamId2: number,
    teamScore2: number,
    stage: string,
    date: Date,
    winnerTeamId?: number,
    played: boolean,
}
export default IMatch;