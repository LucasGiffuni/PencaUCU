import React, { Component } from 'react'
import '../Styles/HomeComponent.css'
import GroupComponent from './GroupComponent';
import Accordion from 'react-bootstrap/Accordion';


class HomeComponent extends Component {
    render() {

        return (
            <div className='Home-Component-Container'>

                <Accordion defaultActiveKey="0" flush className='Accordion-Groups'>
                    <Accordion.Item eventKey="0">
                        <Accordion.Header>Group Phase</Accordion.Header>
                        <Accordion.Body>
                            <div className='Groups-Component-Container'>
                                <GroupComponent name={"A Group"} country1={'Catar'} country2={'Ecuador'} country3={'Senegal'} country4={'Paises Bajos'} />
                                <GroupComponent name={"B Group"} country1={'Inglaterra'} country2={'Iran'} country3={'Estados Unidos'} country4={'Gales'} />
                                <GroupComponent name={"C Group"} country1={'Argentina'} country2={'Arabia Saudita'} country3={'Mexico'} country4={'Polobia'} />
                                <GroupComponent name={"D Group"} country1={'Francia'} country2={'Australia'} country3={'Dinamarca'} country4={'Tunez'} />
                                <GroupComponent name={"E Group"} country1={'España'} country2={'Costa Rica'} country3={'Alemania'} country4={'Japon'} />
                                <GroupComponent name={"F Group"} country1={'Belgica'} country2={'Canada'} country3={'Marruecos'} country4={'Croacia'} />
                                <GroupComponent name={"G Group"} country1={'Brasil'} country2={'Serbia'} country3={'Suiza'} country4={'Camerun'} />
                                <GroupComponent name={"H Group"} country1={'Portugal'} country2={'Ghana'} country3={'Uruguay'} country4={'Korea'} />
                                <GroupComponent name={"I Group"} />
                                <GroupComponent name={"J Group"} />
                                <GroupComponent name={"K Group"} />
                                <GroupComponent name={"L Group"} />
                            </div>
                        </Accordion.Body>
                    </Accordion.Item>
                </Accordion>

            </div>
        )
    }



}

export default HomeComponent;