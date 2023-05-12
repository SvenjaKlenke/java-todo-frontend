import axios from "axios";
import './Card.css';

type TaskCard = {
    "description": string,
    "status": string,
    "id": string,
    "getTasks": ()=>void
}

function Card(props:TaskCard) {


    function showDetails() {
    }


    function showEditPage() {
    }

    function deleteTask() {
        axios.delete("api/todo/" + props.id)
            .then(props.getTasks)
    }


    function changeStatus() {
        if (props.status === "OPEN") {
            axios.put("api/todo/" + props.id, {
                id: props.id,
                description: props.description,
                status: "IN_PROGRESS"
            })
                .then(props.getTasks)
        } else {
            axios.put("api/todo/" + props.id, {
                id: props.id,
                description: props.description,
                status: "DONE"
            })
                .then(props.getTasks)
        }
    }
    return (
        <div className="Cards">
            <div className={props.status === "OPEN"|| props.status === "IN_PROGRESS" ? "open" : "done"}>
                <h1> {props.description}</h1>
            </div>
            <p> {props.status}</p>
            <h3>
                <button onClick={showDetails}>Details</button>
                <button onClick={showEditPage}>Edit</button>
                {props.status === "DONE"?<button onClick={deleteTask}>Delete</button>:<button onClick={changeStatus}>Next Status</button>}
            </h3>
        </div>
    );
}

export default Card;