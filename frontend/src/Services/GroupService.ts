import IGroup from "../Model/Group";

let Groups: IGroup[] = [
  {
    groupId: "A",
    teams: [
      {
        teamID: 1,
        name: "Uruguay",
        flagUrl: "https://www.worldometers.info/img/flags/uy-flag.gif",
        groupPoints: 0,
      },
      {
        teamID: 2,
        name: "Ghana",
        flagUrl: "https://www.worldometers.info/img/flags/gh-flag.gif",
        groupPoints: 0,
      },
      {
        teamID: 3,
        name: "Portugal",
        flagUrl: "https://www.worldometers.info/img/flags/po-flag.gif",
        groupPoints: 0,
      },
      {
        teamID: 4,
        name: "South Korea",
        flagUrl: "https://www.worldometers.info/img/flags/ks-flag.gif",
        groupPoints: 0,
      },
    ],
  },
];

export function getTeamsByGroup(groupID: string) {
  return Groups[0];
}
