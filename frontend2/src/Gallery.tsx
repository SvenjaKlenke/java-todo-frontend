import React, {ChangeEvent, useEffect, useState} from 'react';
import axios from "axios";
import Card from "./Card";

type Task = {
    "description": string,
    "status": string,
    "id": string
}

function Gallery() {

    const [allTasks, setAllTasks]= useState<Task[]>([]);
    const [inputDescription, setInputDescription]= useState<string>("")

    useEffect(getTasks,[])
    function getTasks (){
        axios.get("api/todo")
            .then((response)=> {
                setAllTasks(response.data)
            })
    }
    function addNewTask(){
        axios.post("api/todo", {description: inputDescription, status:"OPEN"})
            .then(getTasks)
    }

    function useTextInput(event : ChangeEvent<HTMLInputElement>) {
        setInputDescription(event.target.value)
    }

    return (
        <div>
            <input type="text" placeholder="Add new task" value={inputDescription}
                   onChange={useTextInput}/>
            <button onClick={addNewTask}>Add</button>
            {allTasks.map((task) => <Card key = {task.id} description={task.description} status={task.status} id={task.id} getTasks={getTasks}/>)}
        </div>
    );
}

export default Gallery;