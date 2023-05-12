import React, {useEffect, useState} from 'react';
import axios from "axios";

type TaskCard = {
    "description": string,
    "status": string,
    "id": string
}

function Card(props:TaskCard) {

    const [tasks, setTasks]= useState<TaskCard[]>([]);
    function showDetails() {
    }


    function showEditPage() {
    }

    function deleteTask() {
    }

    function changeStatus() {
    }

    return (
        <div>
            <h2>
                {props.description}
            </h2>
            <button onClick={showDetails}>Details</button>
            <button onClick={showEditPage}>Edit</button>
            {props.status === "DONE"?<button onClick={deleteTask}>Delete</button>:<button onClick={changeStatus}>Next</button>}

        </div>
    );
}

export default Card;