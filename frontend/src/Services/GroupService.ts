import IGroup from "../Model/Group";

let Groups: IGroup[] = [
  {
    groupId: "A",
    teams: [
      {
        teamID: 1,
        name: "Uruguay",
        flagUrl: "string",
        groupPoints: 0,
      },
      {
        teamID: 2,
        name: "Ghana",
        flagUrl: "string",
        groupPoints: 0,
      },
      {
        teamID: 3,
        name: "Portugal",
        flagUrl: "string",
        groupPoints: 0,
      },
      {
        teamID: 4,
        name: "South Korea",
        flagUrl: "string",
        groupPoints: 0,
      },
    ],
  },
];

export function getTeamsByGroup(groupID: string) {
  return Groups[0];
}
