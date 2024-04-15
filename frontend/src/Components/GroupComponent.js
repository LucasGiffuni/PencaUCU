import React, { useState } from 'react'
import '../Styles/HomeComponent.css'
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';


function GroupComponent(props) {
    const [value, setValue] = useState(props.value);


 


    

        return (

            <Card className="Card-Container" border="dark" >
                <Card.Title className='Card-Container-Title'>{props.name}</Card.Title>
                <ListGroup variant="flush">
                    <ListGroup.Item>{props.country1}</ListGroup.Item>
                    <ListGroup.Item>{props.country2}</ListGroup.Item>
                    <ListGroup.Item>{props.country3}</ListGroup.Item>
                    <ListGroup.Item>{props.country4}</ListGroup.Item>

                </ListGroup>
            </Card>

        );
    


}

export default GroupComponent;