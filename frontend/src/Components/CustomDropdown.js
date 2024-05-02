import React, { useState } from "react";
import styled from "styled-components";

const DropDownContainer = styled("div")`
  width: 10.5em;
  margin: 0 auto;
`;

const DropDownHeader = styled("div")`
  margin-bottom: 0.8em;
  padding: 0.4em 2em 0.4em 1em;
  box-shadow: 0 2px 3px rgba(0, 0, 0, 0.15);
  font-weight: 500;
  font-size: 1.3rem;
  color: #3faffa;
  background: #ffffff;
  
`;

const DropDownListContainer = styled("div")`

overflow:scroll;
width: 200px;
`;

const DropDownList = styled("ul")`
  padding: 0;
  margin: 0;
  padding-left: 1em;
  background: #ffffff;
  border: 2px solid #e5e5e5;
  box-sizing: border-box;
  color: #3faffa;
  font-size: 1.3rem;
  font-weight: 500;
  &:first-child {
    padding-top: 0.8em;
  }
  height: 120px
`;

const ListItem = styled("li")`
  list-style: none;
  margin-bottom: 0.8em;
  color: red;
`;

const options = ["Mangoes", "Apples", "Oranges"];

let Teams = [
  {
    idEquipo: 0,
    nombre: "Uruguay",
    urlBandera: "https://www.worldometers.info/img/flags/uy-flag.gif",
  },
  {
    idEquipo: 1,
    nombre: "Argentina",
    urlBandera: "https://www.worldometers.info/img/flags/ar-flag.gif",
  },
  {
    idEquipo: 1,
    nombre: "Argentina",
    urlBandera: "https://www.worldometers.info/img/flags/ar-flag.gif",
  },
  {
    idEquipo: 1,
    nombre: "Argentina",
    urlBandera: "https://www.worldometers.info/img/flags/ar-flag.gif",
  },
  {
    idEquipo: 1,
    nombre: "Argentina",
    urlBandera: "https://www.worldometers.info/img/flags/ar-flag.gif",
  },
  {
    idEquipo: 1,
    nombre: "Argentina",
    urlBandera: "https://www.worldometers.info/img/flags/ar-flag.gif",
  },
  {
    idEquipo: 1,
    nombre: "Argentina",
    urlBandera: "https://www.worldometers.info/img/flags/ar-flag.gif",
  },
  {
    idEquipo: 1,
    nombre: "Argentina",
    urlBandera: "https://www.worldometers.info/img/flags/ar-flag.gif",
  },
];

export default function CustomDropdown() {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState(null);

  const toggling = () => setIsOpen(!isOpen);

  const onOptionClicked = (value) => () => {
    setSelectedOption(value);
    setIsOpen(false);
    console.log(selectedOption);
  };

  return (
    <div>
      <DropDownContainer>
        <DropDownHeader onClick={toggling}>
          {selectedOption || ""}
        </DropDownHeader>
        {isOpen && (
          <DropDownListContainer>
            <DropDownList>
              {Teams.map((option) => (
                <ListItem
                  onClick={onOptionClicked(option.nombre)}
                  key={Math.random()}
                >
                  <h3>{option.nombre}</h3>
                  <img
                    className="Card-Container-Item-Flag"
                    src={option.urlBandera}
                  ></img>
                </ListItem>
              ))}
            </DropDownList>
          </DropDownListContainer>
        )}
      </DropDownContainer>
    </div>
  );
}
